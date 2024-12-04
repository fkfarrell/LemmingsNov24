package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.logic.Direction;
import tp1.logic.Position;

public interface GameWorld {

    public boolean isInAir(Position pos);

    public void lemmingArrived() throws ObjectParseException;

    public boolean receiveInteractionsFrom(GameItem obj);

}
