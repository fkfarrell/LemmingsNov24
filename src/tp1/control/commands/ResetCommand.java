package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameModelException;
//import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends NoParamsCommand {

    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;

    public ResetCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            game.reset();
        } catch (Exception e) {
            throw new CommandExecuteException("An unexpected error occurred while resetting the game.", e);
        }
    }
    

    @Override
    public boolean showBoard() {
        return true;
    }

}