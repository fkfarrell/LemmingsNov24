package tp1.control.commands;

import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO fill with your code
		String word = commandWords[0];

		if (matchCommandName(word)) {
			return this;
		}

		return null;
	}

}
