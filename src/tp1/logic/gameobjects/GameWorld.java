package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.logic.Direction;
import tp1.logic.Position;

public interface GameWorld {

    public boolean isInAir(Position pos);

    public void lemmingArrived() throws ObjectParseException;

    public boolean receiveInteractionsFrom(GameItem obj);

    public boolean wallBelow(Position pos) throws ObjectParseException;

    public boolean exitAhead(Position pos, Direction dir) throws ObjectParseException;

    public boolean exitBelow(Position pos, Direction dir) throws ObjectParseException;

    public boolean wallAhead(Position pos, Direction dir) throws ObjectParseException;

    public boolean metalWallAhead(Position pos, Direction dir) throws ObjectParseException;

    public boolean metalWallBelow(Position pos) throws ObjectParseException;
}