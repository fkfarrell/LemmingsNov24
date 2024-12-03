package tp1.logic.file;

import java.io.*;

import tp1.logic.Game;
import tp1.logic.GameObjectContainer;
import tp1.logic.file.GameObjectFactory;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;

public class FileGameConfig implements GameConfig {
    // Handles the reading, validating, and storing of game info from a text file.
    // This is where we'll make calls to the GameObjectFactory.

    private File inputFile;
    public String fullFilePath;
    private String content;
    public GameObjectFactory factory = new GameObjectFactory();
    private Game game;
    public GameObjectContainer newLoadContainer = new GameObjectContainer(game);

    // Game Config Variables
    public int gameCycle = 0;
    public int lemmingsOnBoard = 0;
    public int deadLemmings = 0;
    public int lemmingsExited = 0;
    public int lemmingsToExit = 0;

    public FileGameConfig(File filePath) throws IOException {
        this.inputFile = filePath;
        if (!inputFile.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    public void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new IOException("The file is empty.");
            }

            parseGameConfigInfo(firstLine);

            Game newGame = new Game(4); // trying to maje this.game != null

            while ((content = reader.readLine()) != null) {
                GameObject obj = factory.parse(content, newGame);
                System.out.println("GAME : " + newGame);
                // if (obj != null) {
                if (obj.game == null) {
                    obj.setGame(newGame);
                    System.out.println(">>> : " + newGame);
                }
                newLoadContainer.add(obj);
                // }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            throw e; // Rethrow exception to be handled by the caller.
        }
        // System.out.println("Contents after file reading");
        // System.out.println(newLoadContainer.toString());
    }

    public void parseGameConfigInfo(String firstLine) {
        // parse the first line of the file into the game info.
        String[] tokens = firstLine.trim().split("\\s+");
        if (tokens.length != 5) {
            throw new IllegalArgumentException("Invalid game configuration: Expected 5 values");
        }
        try {
            gameCycle = Integer.parseInt(tokens[0]);
            lemmingsOnBoard = Integer.parseInt(tokens[1]);
            deadLemmings = Integer.parseInt(tokens[2]);
            lemmingsExited = Integer.parseInt(tokens[3]);
            lemmingsToExit = Integer.parseInt(tokens[4]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid game configuration: All values must be integers.", e);
        }
    }
}
