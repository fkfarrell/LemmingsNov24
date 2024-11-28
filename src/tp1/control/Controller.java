package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandException;
import tp1.control.commands.CommandExecuteException;
import tp1.control.commands.CommandGenerator;
import tp1.control.commands.UpdateCommand;
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
	
	public void run(){
    String[] words = null;

    view.showWelcome();
    view.showGame();

    while (!game.isFinished()) {
        words = view.getPrompt();

        if (words.length == 1 && words[0].isEmpty()) {
            Command runUpdate = new UpdateCommand();
            try {
				runUpdate.execute(game, view);
			} catch (CommandExecuteException e) {
				e.printStackTrace();
			}
            if (runUpdate.showBoard()) {
                view.showGame();
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
            } catch (CommandException e) {
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
