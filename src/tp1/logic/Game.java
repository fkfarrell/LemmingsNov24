package tp1.logic;

import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;

public class Game implements GameModel, GameStatus { // , GameWorld

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

	private GameObjectContainer conatiner = new GameObjectContainer();

	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
	}

	// GameStatus methods
	@Override
	public int getCycle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numLemmingsInBoard() {
		// TODO Auto-generated method stub
		return 0;
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
		return conatiner.getStringAt(pos);
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
	}

	// @Override
	public void exit() {
		// TODO Auto-generated method stub
	}

	// @Override
	public void none() {
		// TODO Auto-generated method stub
	}

	// @Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	// @Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	// this doesnt feel right . . .
	@Override
	public void createLevel(int levelNum) {
		Levels level1 = new Levels();
		Position starterPos = new Position(1, 1);
		Lemming patrick = new Lemming(this, starterPos, Direction.RIGHT);
		conatiner.add(patrick);
	}

	// @Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
}
