package tp1.logic;

import java.io.File;
import java.io.IOException;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.file.FileGameConfig;
import tp1.logic.file.GameConfig;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameWorld;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class Game implements GameModel, GameStatus, GameWorld, GameConfig {

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

	private GameObjectContainer container = new GameObjectContainer(this);
	private boolean gameFinished = false;
	private int lemmingsInGame = 0;
	private int cycleNum = 0;
	public boolean playerWins;
	private int numLemmingsExit = 0;
	private int currentLvl = 1;
	private int deadLemmings;

	public Game(int nLevel) {
		initLevel(nLevel);
	}

	public void initLevel(int lvl) {

		switch (lvl) {
			case 1:
				Position[] lemmingPos = {
						// new Position(1, 1),
						new Position(4, 2),
						new Position(2, 2) };

				for (Position pos : lemmingPos) {
					container.add(new Lemming(this, pos, Direction.RIGHT, new WalkerRole()));
					lemmingsInGame++;
				}

				Position[] wallsPos = {
						new Position(0, 1), new Position(0, 2), new Position(0, 3),
						new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3),
						new Position(4, 4), new Position(4, 5), new Position(4, 6),
						new Position(5, 5), new Position(6, 5), new Position(7, 5), new Position(8, 5),
						new Position(8, 7), new Position(8, 8), new Position(8, 9) };

				for (Position pos : wallsPos) {
					container.add(new Wall(this, pos, null));
				}

				Position ExitDoorPos = new Position(7, 4);
				container.add(new ExitDoor(this, ExitDoorPos, null));
				break;
			case 2:
				Position[] lemmingPos2 = {
						new Position(0, 0),
						new Position(2, 0),
						new Position(4, 0) };

				for (Position pos : lemmingPos2) {
					container.add(new Lemming(this, pos, Direction.RIGHT, new WalkerRole()));
					lemmingsInGame++;
				}

				Position[] wallsPos2 = {
						new Position(0, 1), new Position(1, 1), new Position(2, 1),
						// new Position(2, 3), new Position(3, 3), new Position(4, 3),
						new Position(4, 5), new Position(5, 5), new Position(6, 5),
						new Position(7, 5), new Position(8, 5), new Position(9, 5),

						new Position(10, 5),

						new Position(9, 4),

				};
				for (Position pos : wallsPos2) {
					container.add(new Wall(this, pos, null));
				}

				Position[] metalWalls = {
						new Position(2, 3), new Position(3, 3), new Position(4, 3),

						new Position(4, 8), new Position(5, 8), new Position(6, 8),
						new Position(1, 8), new Position(2, 8), new Position(3, 8), };

				for (Position pos : metalWalls) {
					container.add(new MetalWall(this, pos, null));
				}

				Position ExitDoorPos2 = new Position(5, 7);
				container.add(new ExitDoor(this, ExitDoorPos2, null));
				break;

			case 3:

				Position[] wallsPos3 = {
						new Position(1, 3), new Position(1, 4), new Position(1, 5), new Position(1, 6),
						new Position(1, 7),
						new Position(2, 3),
						new Position(2, 5),

						new Position(4, 3), new Position(4, 5), new Position(4, 6),
						new Position(4, 7),

						new Position(6, 7), new Position(6, 5), new Position(6, 6),
						new Position(7, 5),
						new Position(8, 7), new Position(8, 5), new Position(8, 6),

				};

				for (Position pos : wallsPos3) {
					container.add(new Wall(this, pos, null));
				}
				break;

			case 4:
				// Space for laoded level
				break;

			default:
				throw new IllegalArgumentException("Invalid level: " + lvl);
		}
	}

	// GameStatus methods
	@Override
	public int getCycle() {
		return cycleNum;
	}

	@Override
	public int numLemmingsInBoard() {
		return lemmingsInGame;
	}

	@Override
	public int numLemmingsDead() {
		return container.deadLemmings() - numLemmingsExit();
	}

	@Override
	public int numLemmingsExit() {
		return numLemmingsExit;
	}

	@Override
	public int numLemmingsToWin() {
		return 2;
	}

	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		return container.getStringAt(pos);
	}

	@Override
	public boolean playerWins() {
		if (numLemmingsExit() >= numLemmingsToWin()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean playerLooses() {

		if (numLemmingsInBoard() == 0 || (numLemmingsDead() == 2)) {
			return true;
		}
		return false;
	}

	// GameModel methods
	@Override
	public void update() throws GameModelException, CommandExecuteException {

		container.update();
		cycleNum++;
		playerWins();
		playerLooses();

		if (playerWins()) {
			if (currentLvl < 4) {
				currentLvl++;
				reset();
			}
			if (currentLvl == 3)
				gameFinished = true;

		}

		if (playerLooses()) {
			reset();
		}
	}

	@Override
	public void exit() {
		gameFinished = true;
	}

	@Override
	public void none() throws GameModelException, CommandExecuteException {
		update();
	}

	// @Override
	public String help() {
		return null;
	}

	// @Override
	public boolean isFinished() {
		return gameFinished;
	}

	// GameWorld methods (callbacks)
	// @Override
	public boolean isInAir(Position pos) {
		return false;
	}

	//// @Override
	public void lemmingArrived() throws ObjectParseException {
		if (container.checkExit() != 0) {
			numLemmingsExit += container.checkExit();
			lemmingsInGame -= container.checkExit();
		}
	}

	public void updateLemmingStatus(int exitedCount) {
		numLemmingsExit += exitedCount;
		lemmingsInGame -= exitedCount;
		if (numLemmingsExit >= numLemmingsToWin()) {
			playerWins = true;
		} else if (lemmingsInGame == 0) {
			gameFinished = true;
		}
	}

	public static boolean isValidPosition(Position pos) {

		if (pos == null) {
			return false;
		} else if (pos.getCol() >= 0 && pos.getCol() < DIM_X &&
				pos.getRow() >= 0 && pos.getRow() < DIM_Y) {
			return true;
		} else
			return false;

	}

	public boolean checkLemmingPosition(Position pos) {
		if (container.checkLemmingPosition(pos)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setLemmingRole(Position pos, LemmingRole role) throws GameModelException {

		if (container.setLemmingRole(pos, role)) {
			return true;
		} else {
			return false;
		}
	}

	private FileGameConfig fileLoader = null;

	@Override
	public void reset() throws CommandExecuteException {
		if (fileLoader != null) {
			try {
				load(loadFile);
			} catch (GameLoadException e) {
				throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
			}

		} else {
			lemmingsInGame = 0;
			cycleNum = 0;
			playerWins = false;
			numLemmingsExit = 0;
			deadLemmings = 0;
			container = new GameObjectContainer(this);
			initLevel(currentLvl);
		}
	}

	private File loadFile;

	public boolean load(File inputFile) throws GameLoadException, CommandExecuteException {

		try {
			fileLoader = new FileGameConfig(inputFile);
			loadFile = inputFile;

			fileLoader.readFile();
			lemmingsInGame = fileLoader.numLemmingsInBoard();
			cycleNum = fileLoader.getCycle();
			playerWins = false;
			numLemmingsExit = fileLoader.numLemmingsExit();
			deadLemmings = fileLoader.numLemmingsDead();
			container = fileLoader.getGameObjects();
			container.setGame(this);

			return true;
		} catch (GameLoadException e) {
			System.err.println("Error loading game configuration: " + e.getMessage());
			throw e;
		} catch (ObjectParseException e) {
			System.err.println("Error parsing game objects: " + e.getMessage());
			throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			throw new GameLoadException(String.format(Messages.READ_ERROR, inputFile.getAbsolutePath()), e);
		}
	}

	public boolean exitAhead(Position pos, Direction dir) throws ObjectParseException {
		Position ahead = new Position(pos.getCol() + dir.getX(), pos.getRow());
		Position[] exitPositions = container.getExitDoorPositions();
		for (Position exitDoor : exitPositions) {
			if (exitDoor.equals(ahead)) {
				return true;
			}
		}
		return false;
	}

	public boolean exitBelow(Position pos, Direction dir) throws ObjectParseException {
		Position below = new Position(pos.getCol(), pos.getRow() + 1);
		Position[] exitPositions = container.getExitDoorPositions();
		for (Position exitDoor : exitPositions) {
			if (exitDoor.equals(below)) {
				return true;
			}
		}
		return false;
	}

	public boolean wallAhead(Position pos, Direction dir) throws ObjectParseException {
		Position ahead = new Position(pos.getCol() + dir.getX(), pos.getRow());
		Position[] wallPositions = container.getWallPositions();
		for (Position wall : wallPositions) {
			if (wall.toString().equals(ahead.toString())) {
				return true;
			}
		}
		return false;
	}

	public boolean wallBelow(Position pos) throws ObjectParseException {
		Position below = new Position(pos.getCol(), pos.getRow() + 1);
		Position[] wallPositions = container.getWallPositions();
		for (Position wall : wallPositions) {
			if (wall.toString().equals(below.toString())) {
				return true;
			}
		}
		return false;
	}

	public boolean metalWallAhead(Position pos, Direction dir) throws ObjectParseException {
		Position ahead = new Position(pos.getCol() + dir.getX(), pos.getRow());
		Position[] wallPositions = container.getMetalWallPositions();
		for (Position wall : wallPositions) {
			if (wall.toString().equals(ahead.toString())) {
				return true;
			}
		}
		return false;
	}

	public boolean metalWallBelow(Position pos) throws ObjectParseException {
		Position below = new Position(pos.getCol(), pos.getRow() + 1);
		Position[] wallPositions = container.getMetalWallPositions();
		for (Position wall : wallPositions) {
			if (wall.toString().equals(below.toString())) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder gameString = new StringBuilder();
		for (int row = 0; row < DIM_Y; row++) {
			for (int col = 0; col < DIM_X; col++) {
				gameString.append(positionToString(col, row)).append(" ");
			}
			gameString.append("\n");
		}
		return gameString.toString();
	}

	@Override
	public boolean receiveInteractionsFrom(GameItem obj) {
		throw new UnsupportedOperationException("Unimplemented method 'receiveInteractionsFrom'");
	}

	@Override
	public void parseGameConfigInfo(String firstLine) {
		throw new UnsupportedOperationException("Unimplemented method 'parseGameConfigInfo'");
	}

	@Override
	public GameObjectContainer getGameObjects() {
		return container;
	}

	@Override
	public void readFile() throws IOException {
		throw new UnsupportedOperationException("Unimplemented method 'readFile'");
	}

}