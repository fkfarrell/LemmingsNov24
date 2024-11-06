package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
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
	public void run() {
		String[] words = null;

		view.showWelcome();

		view.showGame();

		while (!game.isFinished()) {
			words = view.getPrompt();
			Command command = CommandGenerator.parse(words);
			if (command != null) {

				System.out.println("EXECUTING : " + command);
				command.execute(game, view);

				// only showGame for UPDATE + RESET
				if (command.showBoard()) {
					view.showGame();
				}

			} else
				view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));

		}
		view.showEndMessage();
	}
}
