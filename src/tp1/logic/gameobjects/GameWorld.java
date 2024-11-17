package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Position;

public interface GameWorld {

    public boolean isInAir(Position pos);

    public void lemmingArrived();

    public boolean receiveInteractionsFrom(GameItem obj);

}
