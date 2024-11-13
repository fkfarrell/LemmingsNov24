package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class ParachuterRole implements LemmingRole {
    private static final String NAME = Messages.WALKER_ROL_NAME;
    private static final String HELP = Messages.WALKER_ROL_HELP;

    private static final String ICON = Messages.LEMMING_PARACHUTE;

    @Override
    public void advance(Lemming lemming) {
        lemming.walkOrFall();
    }

    @Override
    public String getIcon(Lemming lemming) {
        if (lemming.isAlive()) {
            return ICON;
        } else
            return " ";
    }

    @Override
    public void play(Lemming lemming) {
    }

}