package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			// TODO fill with your code
			// new UpdateCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new LoadCommand(),
			new ResetCommand(),
			new SetRoleCommand());

			public static Command parse(String[] commandWords) throws CommandParseException {
				if (commandWords == null || commandWords.length == 0) {
					throw new CommandParseException("[ERROR] No command provided.");
				}
				if (availableCommands == null || availableCommands.isEmpty()) {
					throw new CommandParseException("[ERROR] No available commands to parse.");
				}
			
				for (Command c : availableCommands) {
					try {
						Command cmd = c.parse(commandWords);
						if (cmd != null) {
							return cmd;
						}
					} catch (CommandParseException e) {
						throw e;
					}
				}

				throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
			}
			
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();

		for (Command c : availableCommands) {
			// TODO fill with your code
			if (c.helpText() != null) {
				commands.append(c.helpText());
			}
		}

		return commands.toString();
	}

}
