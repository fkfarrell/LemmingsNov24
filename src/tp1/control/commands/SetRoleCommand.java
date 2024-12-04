package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
//import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.*;
import tp1.logic.Position;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Game;

public class SetRoleCommand extends Command {

    // Forman parte de atributos de estado
    private static final String NAME = Messages.COMMAND_SET_ROLE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_SET_ROLE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_SET_ROLE_DETAILS;
    private static final String HELP = Messages.COMMAND_SET_ROLE_HELP;
    private Position pos;
    private LemmingRole role;
    private LemmingRole gameRole;
    private Game game;
    private Position rolePosition;

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    public SetRoleCommand(LemmingRole role, Position pos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.role = role;
        this.pos = pos;
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        if (game.checkLemmingPosition(rolePosition)) {
            try {
                game.setLemmingRole(rolePosition, gameRole);
            } catch (OffBoardException e) {
                throw new CommandExecuteException("Error: Position is off the board or invalid.", e);
                }
        } else {
            throw new CommandExecuteException("[ERROR] SetRoleCommand error (Incorrect position or no object in that position admits that role)");
        }
    }


    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        if (commandWords.length < 4) {
            throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
        }

        try {
            LemmingRole role = LemmingRoleFactory.parse(commandWords);
            if (role == null) {
                throw new CommandParseException(Messages.INVALID_COMMAND.formatted(commandWords[1]));
            }
            gameRole = role;
        } catch (IllegalArgumentException e) {
            throw new CommandParseException(Messages.INVALID_COMMAND.formatted(commandWords[0]), e);
        }

        try {
            String rowPos = commandWords[2].toLowerCase();
            if (rowPos.length() != 1 || rowPos.charAt(0) < 'a' || rowPos.charAt(0) > 'j') {
                throw new CommandParseException("[ERROR] Row position out of board bounds. Must be between 'a' and 'j'.");
            }
            int rowNum = rowPos.charAt(0) - 'a';

            int colNum = Integer.parseInt(commandWords[3]);
            if (colNum < 0 || colNum >= 10) {
                throw new CommandParseException("[ERROR] Column position out of board bounds. Must be between 0 and 9.");
            }

            rolePosition = new Position(colNum - 1, rowNum);
            return new SetRoleCommand(gameRole, rolePosition); 

        } catch (NumberFormatException e) {
            throw new CommandParseException("[ERROR] Invalid column position format. Column must be an integer.", e);
        }
    }

    
    @Override
    public boolean showBoard() {
        return true;
    }

}
