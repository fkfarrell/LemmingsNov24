package tp1.logic;

import java.io.File;
import tp1.control.commands.OffBoardException;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {

    public boolean isFinished();

    public void update();

    public void exit();

    public void reset();

    public void none();

    public String help();

    public boolean checkLemmingPosition(Position rolePosition);

    public boolean setLemmingRole(Position rolePosition, LemmingRole role) throws OffBoardException;

    public boolean load(File file);

}
