package tp1.logic;
import java.io.File;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {

    public boolean isFinished();

    public void update() throws OffBoardException, ObjectParseException, GameModelException;

    public void exit();

    public void reset() throws ObjectParseException, GameLoadException, OffBoardException;

    public void none() throws OffBoardException, ObjectParseException, GameModelException;

    public String help();

    public boolean checkLemmingPosition(Position rolePosition);

    public boolean setLemmingRole(Position rolePosition, LemmingRole role) throws OffBoardException, ObjectParseException, GameModelException;

    public boolean load(File file) throws GameLoadException, ObjectParseException, OffBoardException;

}
