package tp1.logic.gameobjects;

import tp1.exceptions.GameModelException;
import tp1.logic.Position;

public interface GameItem {
    public boolean receiveInteraction(GameItem other) throws GameModelException;

    public boolean interactWith(Lemming lemming);

    public boolean interactWith(Wall wall);

    public boolean interactWith(ExitDoor door);

    public boolean isSolid();

    public boolean isAlive();

    public boolean isExit();

    public boolean isInPosition(Position pos);

    public boolean interactWith(MetalWall metalWall);
}