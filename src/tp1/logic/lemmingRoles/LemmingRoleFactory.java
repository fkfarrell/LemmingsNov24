package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.RoleParseException;
import tp1.view.Messages;

public class LemmingRoleFactory {

    private static final List<LemmingRole> availableRoles = Arrays.asList(
            new WalkerRole(),
            new ParachuterRole(),
            new DownCaverRole());

    public static LemmingRole parse(String input[]) throws CommandExecuteException {
        try{
        if (input.length < 2 || input[1].isEmpty()) {
            throw new RoleParseException(Messages.COMMAND_PARAMETERS_MISSING);
        }

        String desiredRole = input[1];
        char drLetter = desiredRole.charAt(0);
        String drShortLetter = String.valueOf(drLetter);

        for (LemmingRole r : availableRoles) {
            // Run parse here for the roles and have them decetect theier own names
            if (desiredRole.equalsIgnoreCase(r.toString()) ||
                    drShortLetter.equalsIgnoreCase(r.getShortcut())) {
                return r;
            }
        }

        throw new RoleParseException(Messages.UNKNOWN_ROLE.formatted(desiredRole));
        }catch(RoleParseException rpe){
            throw new CommandExecuteException(Messages.INVALID_COMMAND, rpe);
        }
    }

    public static String roleHelp() {
        StringBuilder roles = new StringBuilder();

        for (LemmingRole r : availableRoles) {

            if (r.helpText() != null) {
                roles.append("      " + r.helpText() + "\n");
            }
        }

        return roles.toString();
    }

}
