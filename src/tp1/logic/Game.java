package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

	private GameObjectContainer container = new GameObjectContainer(this);
	private boolean gameFinished = false;
	private int lemmingsInGame = 0;
	private int cycleNum = 0;
	public boolean playerWins;
	private int numLemmingsExit = 0;

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
					container.add(new Lemming(this, pos, Direction.RIGHT));
					lemmingsInGame++;
				}

				Position[] wallsPos = {
						new Position(0, 1), new Position(0, 2), new Position(0, 3),
						new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3),
						new Position(4, 4), new Position(4, 5), new Position(4, 6),
						new Position(5, 5), new Position(6, 5), new Position(7, 5), new Position(8, 5),
						new Position(8, 7), new Position(8, 8), new Position(8, 9) };

				for (Position pos : wallsPos) {
					container.add(new Wall(this, pos));
				}

				Position ExitDoorPos = new Position(7, 4);
				container.add(new ExitDoor(this, ExitDoorPos));
				break;
			case 2:
				Position[] lemmingPos2 = {
						new Position(0, 0),
						new Position(2, 0),
						new Position(4, 0) };

				for (Position pos : lemmingPos2) {
					container.add(new Lemming(this, pos, Direction.RIGHT));
					lemmingsInGame++;
				}

				Position[] wallsPos2 = {
						new Position(0, 1), new Position(1, 1), new Position(2, 1),
						new Position(2, 3), new Position(3, 3), new Position(4, 3),
						new Position(4, 5), new Position(5, 5), new Position(6, 5),
						new Position(7, 5), new Position(8, 5), new Position(9, 5),

						new Position(10, 5),

						new Position(9, 4),

						new Position(4, 8), new Position(5, 8), new Position(6, 8),
						new Position(1, 8), new Position(2, 8), new Position(3, 8),
				};

				for (Position pos : wallsPos2) {
					container.add(new Wall(this, pos));
				}
				Position ExitDoorPos2 = new Position(1, 7);
				container.add(new ExitDoor(this, ExitDoorPos2));
				break;
			default:
				break;
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
		// TODO Auto-generated method stub
		return 0;
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
			gameFinished = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean playerLooses() {
		if (numLemmingsInBoard() == 0) {
			gameFinished = true;
			return true;
		}
		return false;
	}

	// GameModel methods
	// @Override
	public void update() {
		container.update();
		cycleNum++;
		// lemmingArrived();

	}

	// @Override
	public void exit() {
		gameFinished = true;
	}

	// @Override
	public void none() {
		update();
	}

	// @Override
	public void reset() {
		lemmingsInGame = 0;
		cycleNum = 0;
		playerWins = false;
		numLemmingsExit = 0;
		container = new GameObjectContainer(this);
		initLevel(1);

	}

	// @Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public boolean isFinished() {
		return gameFinished;
	}

	// GameWorld methods (callbacks)
	// @Override
	public boolean isInAir(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	//// @Override
	public void lemmingArrived() {
		if (container.checkExit() != 0) {
			numLemmingsExit += container.checkExit();
			lemmingsInGame -= container.checkExit();
		}
		playerWins();
		playerLooses();
	}

	public void updateLemmingStatus(int exitedCount) {
		numLemmingsExit += exitedCount;
		lemmingsInGame -= exitedCount;
		if (numLemmingsExit >= numLemmingsToWin()) {
			playerWins = true;
			gameFinished = true;
		} else if (lemmingsInGame == 0) {
			gameFinished = true;
		}
	}

	// Other methods
	// TODO you should write a toString method to return the string that represents
	// the object status
	// @Override
	// public String toString()
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
}