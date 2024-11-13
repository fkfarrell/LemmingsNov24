package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
//import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.*;
import tp1.logic.Position;

public class SetRoleCommand extends Command {

    // Forman parte de atributos de estado
    private static final String NAME = Messages.COMMAND_SET_ROLE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_SET_ROLE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_SET_ROLE_DETAILS;
    private static final String HELP = Messages.COMMAND_SET_ROLE_HELP + "\n     " + Messages.PARACHUTER_ROL_HELP
            + "\n     "
            + Messages.WALKER_ROL_HELP;
    private Position pos;
    private LemmingRole role;

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    public SetRoleCommand(LemmingRole role, Position pos) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.role = role;
        this.pos = pos;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        // sets the Lemming at the (pos) params constructor to the specified role
    }

    @Override
    public Command parse(String[] commandWords) {
        // parse info taken in and return cmd.

        // LEMMING ROLE
        String role = commandWords[1];
        String colPos = commandWords[2];
        String rowPos = commandWords[3];

        System.out.println(">>>>>" + role + colPos + rowPos);

        // if the given data is kosher, return "this"
        return this;
    }

    @Override
    public boolean showBoard() {
        return true;
    }

}
