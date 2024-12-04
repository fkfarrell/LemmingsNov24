package tp1.logic.file;

import java.io.File;
import java.io.IOException;

import tp1.logic.GameObjectContainer;

public interface GameConfig {
    // will contain the methods used by classes to update game status and represent
    // a valid state congig

    public void readFile() throws IOException;

    public void parseGameConfigInfo(String firstLine);

    // game status
    public int getCycle();

    public int numLemmingsInBoard();

    public int numLemmingsDead();

    public int numLemmingsExit();

    public int numLemmingsToWin();

    // game objects
    public GameObjectContainer getGameObjects();

}
