package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			new HelpCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new LoadCommand(),
			new SetRoleCommand());

	public static Command parse(String[] commandWords) throws CommandParseException {

		Command cmd = null;

		for (Command c : availableCommands) {

			cmd = c.parse(commandWords);

			if (cmd != null) {
				return cmd;
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
