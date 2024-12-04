package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.control.commands.UpdateCommand;
import tp1.exceptions.CommandException;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
//import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic
 */
public class Controller {

    private GameModel game;
    private GameView view;

    public Controller(GameModel game, GameView view) {
        this.game = game;
        this.view = view;
    }
    
    public void run() throws GameModelException {
    String[] words = null;

    view.showWelcome();
    view.showGame();

    while (!game.isFinished()) {
        words = view.getPrompt();
        
        if (words.length == 1 && words[0].isEmpty()) {
            Command runUpdate = new UpdateCommand();
            try {
                runUpdate.execute(game, view);
                if (runUpdate.showBoard()) {
                    view.showGame();
                }
            } catch (CommandExecuteException e) {
                view.showError(e.getMessage());
                Throwable wrapped = e;
                while ((wrapped = wrapped.getCause()) != null) {
                    view.showError(wrapped.getMessage());
                }
            }
        } else {
            try {
                Command command = CommandGenerator.parse(words);
                if (command != null) {
                    command.execute(game, view);
                    if (command.showBoard()) {
                        view.showGame();
                    }
                } else {
                    view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));
                }
            } catch (CommandParseException e) {
                view.showError(e.getMessage());
            } catch (CommandExecuteException e) {
                view.showError(e.getMessage());
                Throwable wrapped = e;
                while ((wrapped = wrapped.getCause()) != null) {
                    view.showError(wrapped.getMessage());
                }
            } 
        }
    }
    view.showEndMessage();
}

}
