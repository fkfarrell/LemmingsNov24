package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {

    public boolean isFinished();

    public void update();

    public void exit();

    public void reset();

    public void none();

    public String help();

    public boolean checkLemmingPosition(Position rolePosition);

    public boolean setLemmingRole(Position rolePosition, LemmingRole role);

}
