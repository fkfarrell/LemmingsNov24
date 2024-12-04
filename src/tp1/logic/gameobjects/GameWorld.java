package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Position;

public interface GameWorld {

    public boolean isInAir(Position pos);

    public void lemmingArrived();

    public boolean receiveInteractionsFrom(GameItem obj);

    public boolean wallBelow(Position pos);

    public boolean exitAhead(Position pos, Direction dir);

    public boolean exitBelow(Position pos, Direction dir);

    public boolean wallAhead(Position pos, Direction dir);

    public boolean metalWallAhead(Position pos, Direction dir);

    public boolean metalWallBelow(Position pos);
}