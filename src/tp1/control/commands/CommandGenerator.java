package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			// TODO fill with your code
			// new UpdateCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new SetRoleCommand());

	public static Command parse(String[] commandWords) {

		Command cmd = null;

		for (Command c : availableCommands) {

			cmd = c.parse(commandWords);
			if (cmd != null) {
				return cmd;
			}

		}

		return null;
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
