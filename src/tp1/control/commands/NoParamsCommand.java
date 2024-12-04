package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords == null || commandWords.length == 0 ||
				commandWords[0].isEmpty()) {
			return new UpdateCommand();

		}
	
		if (!matchCommandName(commandWords[0])) {
			throw new CommandParseException("Unknown command: " + commandWords[0]);
		}
	
		if (commandWords.length == 1) {
			return this;
		} else {
			throw new CommandParseException("Incorrect number of parameters for command: " + commandWords[0]);
		}
	}


}
