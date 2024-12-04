package tp1.control.commands;

import java.util.Arrays;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords == null || commandWords.length == 0 ||
				commandWords[0].isEmpty()) {
			return new UpdateCommand();
		}

		if (!matchCommandName(commandWords[0].trim())) {
			return null;
		}

		if (commandWords.length == 1) {
			return this;
		} else {
			return null;
		}
	}

}
