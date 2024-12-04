package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.control.commands.Command;
import tp1.control.commands.RoleParseException;
import tp1.exceptions.CommandParseException;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;

public class LemmingRoleFactory {

    private static final List<LemmingRole> availableRoles = Arrays.asList(
            new WalkerRole(),
            new ParachuterRole(),
            new DownCaverRole());

    public static LemmingRole parse(String input[]) throws CommandParseException {
          if (input.length < 2 || input[1].isEmpty()) {
        throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
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

        throw new CommandParseException(Messages.UNKNOWN_ROLE.formatted(desiredRole));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public static LemmingRole parse(String input) { // throws RoleParseException
    // System.out.println("input >>> " + input);
    // LemmingRole result;
    // for (LemmingRole role : availableRoles) {
    // result = role.parse(input);
    // if (result != null)
    // return result;
    // System.out.println("role >>> " + result);
    // }
    // // throw new RoleParseException(String.format(Messages.INVALID_ROLE, input));
    // return null;
    // }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
