package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public interface LemmingRole {

    public static final String PARA_ICON = Messages.LEMMING_PARACHUTE;

    public static final String WALKER_ICON_RIGHT = Messages.LEMMING_RIGHT;
    public static final String WALKER_ICON_LEFT = Messages.LEMMING_LEFT;

    public String getIcon(Lemming lemming);

    public void play(Lemming lemming);

    public void advance(Lemming lemming);

    public String helpText();

    public String getShortcut();

}
