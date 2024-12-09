package tp1.logic.lemmingRoles;

import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public interface LemmingRole {

    public static final String PARA_ICON = Messages.LEMMING_PARACHUTE;

    public static final String WALKER_ICON_RIGHT = Messages.LEMMING_RIGHT;
    public static final String WALKER_ICON_LEFT = Messages.LEMMING_LEFT;

    public String getIcon(Lemming lemming);

    public String getName();

    public void play(Lemming lemming);

    public void advance(Lemming lemming) throws OffBoardException, GameModelException;

    public String helpText();

    public String getShortcut();

    public boolean receiveInteraction(GameItem other, Lemming lemming);

    public boolean interactWith(Lemming receiver, Lemming lemming);

    public boolean interactWith(Wall wall, Lemming lemming);

    public boolean interactWith(MetalWall metalWall, Lemming lemming);

    public boolean interactWith(ExitDoor door, Lemming lemming);

    public boolean interactWithAir(Lemming lemming);

    public LemmingRole parse(String input);
}
