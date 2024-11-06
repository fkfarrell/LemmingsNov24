package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public class Game implements GameModel, GameStatus { // , GameWorld

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

	private GameObjectContainer container = new GameObjectContainer();
	private boolean gameFinished = false;
	private int lemmingsInGame = 0;
	private int cycleNum = 0;

	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		initLevel(nLevel);
	}

	public void initLevel(int lvl) {

		switch (lvl) {

			case 1:
				Position[] lemmingPos = {
						// new Position(1, 1),
						// new Position(2, 2),
						new Position(2, 2) };

				for (Position pos : lemmingPos) {
					container.add(new Lemming(this, pos, Direction.RIGHT));
					lemmingsInGame++;
				}

				Position[] wallsPos = {
						new Position(0, 1), new Position(0, 2), new Position(0, 3),
						new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(4, 3),
						new Position(4, 4), new Position(4, 5), new Position(4, 6),
						new Position(5, 6), new Position(6, 6), new Position(7, 6), new Position(8, 6),
						new Position(8, 7), new Position(8, 8), new Position(8, 9) };

				for (Position pos : wallsPos) {
					container.add(new Wall(this, pos));
				}

				Position ExitDoorPos = new Position(9, 9);
				container.add(new ExitDoor(this, ExitDoorPos));
				break;
			case 2:
				Position[] lemmingPos2 = {
						new Position(1, 1),
						new Position(2, 2),
						new Position(3, 3) };

				for (Position pos : lemmingPos2) {
					container.add(new Lemming(this, pos, Direction.RIGHT));
					lemmingsInGame++;
				}

				Position[] wallsPos2 = {
						new Position(0, 2), new Position(1, 2), new Position(2, 2), // Upper left section
						new Position(3, 3), new Position(3, 4), new Position(3, 5), // Vertical segment in the middle
						new Position(4, 5), new Position(5, 5), new Position(6, 5), // Horizontal middle row
						new Position(7, 6), new Position(8, 6), new Position(9, 6), // Bottom segment
						new Position(9, 7), new Position(9, 8) // Right section near the end
				};

				for (Position pos : wallsPos2) {
					container.add(new Wall(this, pos));
				}
				Position ExitDoorPos2 = new Position(9, 9);
				container.add(new ExitDoor(this, ExitDoorPos2));
				break;
			default:
				break;
		}
	}

	// GameStatus methods
	@Override
	public int getCycle() {
		// TODO Auto-generated method stub
		return cycleNum; // breaking ecapsulation by returning a private int?
	}

	@Override
	public int numLemmingsInBoard() {
		// TODO Auto-generated method stub
		return lemmingsInGame;
	}

	@Override
	public int numLemmingsDead() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numLemmingsExit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		return container.getStringAt(pos);
	}

	@Override
	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean playerLooses() {
		// TODO Auto-generated method stub
		return false;
	}

	// GameModel methods
	// @Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("UPDATING ...");
		container.update();
		cycleNum++;
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
		// container.clear();

	}

	// @Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return gameFinished;
	}

	// TODO Auto-generated method stub

	// GameWorld methods (callbacks)
	// @Override
	public boolean isInAir(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	// @Override
	public void lemmingArrived() {
		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

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