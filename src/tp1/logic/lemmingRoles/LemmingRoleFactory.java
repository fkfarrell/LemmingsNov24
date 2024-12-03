package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.control.commands.Command;
import tp1.logic.lemmingRoles.*;

public class LemmingRoleFactory {

    private static final List<LemmingRole> availableRoles = Arrays.asList(
            new WalkerRole(),
            new ParachuterRole(),
            new DownCaverRole());

    public static LemmingRole parse(String input[]) {

        String desiredRole = input[1];
        char drLetter = desiredRole.charAt(0);
        String drShortLetter = String.valueOf(drLetter);

        for (LemmingRole r : availableRoles) {
            // Run parse here for the roles and have them decetect theier own names
            if (desiredRole.equalsIgnoreCase(r.toString()) || drShortLetter.equalsIgnoreCase(r.getShortcut())) {
                return r;
            }
        }

        return null;
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
