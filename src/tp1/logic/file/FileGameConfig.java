package tp1.logic.file;

import java.io.*;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Game;
import tp1.logic.GameObjectContainer;
import tp1.logic.file.GameObjectFactory;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;
import tp1.view.Messages;

public class FileGameConfig implements GameConfig {

    private File inputFile;
    public String fullFilePath;
    private String content;
    public GameObjectFactory factory = new GameObjectFactory();
    private Game game;
    public GameObjectContainer newLoadContainer;
    private static int LoadLevel = 4;

    // Game Config Variables
    public int gameCycle = 0;
    public int lemmingsOnBoard = 0;
    public int deadLemmings = 0;
    public int lemmingsExited = 0;
    public int lemmingsToExit = 0;

    public FileGameConfig(File filePath) throws GameLoadException {
        this.inputFile = filePath;
        if (!inputFile.exists()) {
            throw new GameLoadException(String.format(Messages.FILE_NOT_FOUND, filePath));

        }
    }

    public void readFile() throws GameLoadException, ObjectParseException, OffBoardException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new GameLoadException("The file is empty.");
            }

            parseGameConfigInfo(firstLine);
            Game newGame = new Game(LoadLevel);
            newLoadContainer = new GameObjectContainer(newGame);

            while ((content = reader.readLine()) != null) {
                try{
                GameObject obj = factory.parse(content, newGame);
                if (obj.game == null) {
                    obj.setGame(newGame);
                }
                newLoadContainer.add(obj);

                } catch (ObjectParseException | OffBoardException e) {
                    throw new ObjectParseException(String.format(Messages.ERROR_PARSING_GAME_OBJECT, content), e);
                }
            }
        } catch (IOException e) {
            throw new GameLoadException(String.format(Messages.READ_ERROR, inputFile.getAbsolutePath()), e);

        }
    }

    public void parseGameConfigInfo(String firstLine) throws GameLoadException {
        // parse the first line of the file into the game info.
        String[] tokens = firstLine.trim().split("\\s+");
        if (tokens.length != 5) {
            throw new GameLoadException("Invalid game configuration: Expected 5 values");
        }
        try {
            gameCycle = Integer.parseInt(tokens[0]);
            lemmingsOnBoard = Integer.parseInt(tokens[1]);
            deadLemmings = Integer.parseInt(tokens[2]);
            lemmingsExited = Integer.parseInt(tokens[3]);
            lemmingsToExit = Integer.parseInt(tokens[4]);
        } catch (NumberFormatException e) {
            throw new GameLoadException(Messages.INVALID_INIT_CONF, e);
        }
    }

    @Override
    public int getCycle() {
        return gameCycle;
    }

    @Override
    public int numLemmingsInBoard() {
        return lemmingsOnBoard;
    }

    @Override
    public int numLemmingsDead() {
        return deadLemmings;
    }

    @Override
    public int numLemmingsExit() {
        return lemmingsExited;
    }

    @Override
    public int numLemmingsToWin() {
        return lemmingsToExit;
    }

    @Override
    public GameObjectContainer getGameObjects() {
        return newLoadContainer;
    }
}
