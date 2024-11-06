package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.control.commands.UpdateCommand;
import tp1.logic.Game;
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

	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 */
	// public void run() {
	// String[] words = null;

	// view.showWelcome();

	// view.showGame();

	// while (!game.isFinished()) {
	// words = view.getPrompt();
	// Command command = CommandGenerator.parse(words);

	// if (words[0].isEmpty()) {
	// System.out.println("RETURN KEY DOG");
	// // create a nuw Update command here??
	// Command runUpdate = new UpdateCommand();
	// runUpdate.execute(game, view);

	// view.showGame();
	// }

	// if (command != null) {

	// command.execute(game, view);

	// if (command.showBoard()) {
	// view.showGame();
	// }

	// } else
	// view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));

	// }
	// view.showEndMessage();
	// }

	public void run() {
		String[] words = null;

		view.showWelcome();
		view.showGame();

		while (!game.isFinished()) {
			words = view.getPrompt();

			if (words.length == 1 && words[0].isEmpty()) {
				Command runUpdate = new UpdateCommand();
				runUpdate.execute(game, view);
				if (runUpdate.showBoard()) {
					view.showGame();
				}
			} else {
				Command command = CommandGenerator.parse(words);

				if (command != null) {
					command.execute(game, view);

					if (command.showBoard()) {
						view.showGame();
					}
				} else {
					view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));
				}
			}
		}

		view.showEndMessage();
	}

}
