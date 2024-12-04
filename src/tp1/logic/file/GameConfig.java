package tp1.logic.file;


import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;

public interface GameConfig {
    // will contain the methods used by classes to update game status and represent
    // a valid state congig

    public void readFile() throws ObjectParseException, OffBoardException, GameLoadException;

    public void parseGameConfigInfo(String firstLine) throws GameLoadException;

}
