package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.lemmingRoles.*;
import tp1.logic.Position;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
import tp1.logic.Game;

public class SetRoleCommand extends Command {

    // Forman parte de atributos de estado
    private static final String NAME = Messages.COMMAND_SET_ROLE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_SET_ROLE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_SET_ROLE_DETAILS;
    private static final String HELP = Messages.COMMAND_SET_ROLE_HELP;
    private Position pos;
    private LemmingRole role;
    private static LemmingRole gameRole;
    private static Position rolePosition;

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    public SetRoleCommand(LemmingRole role, Position pos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.role = role;
        this.pos = pos;
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException, GameModelException {
        try {
            game.setLemmingRole(SetRoleCommand.rolePosition, SetRoleCommand.gameRole);
        } catch (GameModelException e) {
            throw new CommandExecuteException(
                    "[ERROR] SetRoleCommand error: " + e.getMessage(), e);
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
            throw new CommandParseException(Messages.INVALID_COMMAND.formatted(commandWords[0]),
                    e);
        } catch (CommandExecuteException e) {
            e.printStackTrace();
        }

        try {
            String rowPos = commandWords[2].toLowerCase();
            int rowNum = rowPos.charAt(0) - 'a';
            int colNum = Integer.parseInt(commandWords[3]);

            rolePosition = new Position(colNum - 1, rowNum);
            if (Game.isValidPosition(rolePosition)) {
                return new SetRoleCommand(gameRole, rolePosition);
            } else {
                throw new CommandParseException("[ERROR] Invalid column position format. Column must be an integer.");
            }

        } catch (NumberFormatException e) {
            throw new CommandParseException("[ERROR] Invalid column position format. Column must be an integer.", e);
        }
    }

    @Override
    public boolean showBoard() {
        return true;
    }

}
