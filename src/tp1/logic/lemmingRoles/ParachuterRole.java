package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class ParachuterRole implements LemmingRole {
    private static final String NAME = Messages.PARACHUTER_ROL_NAME;
    private static final String HELP = Messages.PARACHUTER_ROL_HELP;

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

    @Override
    public String helpText() {
        return HELP;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public LemmingRole parse(String input) {
        System.out.println("parse input >>> " + input);
        if (input.equalsIgnoreCase(getName()) || input.equalsIgnoreCase(getShortcut()))
            return this;
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getShortcut() {
        return "p";
    }

    @Override
    public boolean receiveInteraction(GameItem other, Lemming lemming) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receiveInteraction'");
    }

    @Override
    public boolean interactWith(Lemming receiver, Lemming lemming) {
        return true;
    }

    @Override
    public boolean interactWith(Wall wall, Lemming lemming) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door, Lemming lemming) {
        return true;
    }

    @Override
    public boolean interactWith(MetalWall metalWall, Lemming lemming) {
        return false;
    }

}