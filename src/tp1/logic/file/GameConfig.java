package tp1.logic.file;

import java.io.IOException;

public interface GameConfig {
    // will contain the methods used by classes to update game status and represent
    // a valid state congig

    public void readFile() throws IOException;

    public void parseGameConfigInfo(String firstLine);

}
