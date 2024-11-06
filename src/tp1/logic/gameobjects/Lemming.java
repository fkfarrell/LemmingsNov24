package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class Lemming extends GameObject {

	WalkerRole role;
	private int fallForce = 0;
	private Direction dir;
	private final int MAX_FALL = 3;
	private boolean isFalling = false;

	public Lemming(Game game, Position pos, Direction dir) {
		super(game, pos);
		this.role = new WalkerRole();
		this.dir = dir;
	}

	public Direction getDirection() {
		return this.dir;
	}

	// Not mandatory but recommended
	public void walkOrFall() {

		if (canMove()) {

			if (this.dir == Direction.RIGHT) {
				// move RIGHT
				this.pos = new Position(this.pos.getCol() + 1, this.pos.getRow());
				// checkFloor();
			}
			if (this.dir == Direction.LEFT) {
				// move LEFT
				this.pos = new Position(this.pos.getCol() - 1, this.pos.getRow());
			}
			if (this.dir == Direction.DOWN) {
				// move DOWN
				this.pos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
				fallForce++;
			}

		}

	}

	private boolean canMove() {
		Direction movDirection = this.getDirection();
		Position currePosition = this.pos;
		final int WALL_RIGHT = 10;
		final int WALL_LEFT = 0;

		// CHECK RIGHT MOVEMENT
		if (this.dir == Direction.RIGHT) {
			int nextCol = currePosition.getCol() + movDirection.getX();
			if (nextCol == WALL_RIGHT || game.positionToString(nextCol, currePosition.getRow()).equals(Messages.WALL)) {
				reverseDir();
			}
			checkFloor();
			return true;
		} else if (this.dir == Direction.LEFT) {
			int nextCol = currePosition.getCol() + movDirection.getX();
			if (nextCol == WALL_LEFT || game.positionToString(nextCol, currePosition.getRow()).equals(Messages.WALL)) {
				reverseDir();
			}
			return true;
		} else if (this.dir == Direction.DOWN) {
			checkFloor();
			int nextRow = currePosition.getCol() + movDirection.getX();
			if (nextRow == WALL_RIGHT || game.positionToString(currePosition.getCol(), nextRow).equals(Messages.WALL)) {
				// what should happen here? can the lemmings walk on the bottom or should they
				// die?
			}
			return true;
		} else
			return false;
	}

	private void checkFloor() {
		if (game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(" ")) { // Messages.WALL
			this.isFalling = true;
			this.dir = Direction.DOWN;
		}

		if (game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(Messages.WALL)) { //
			this.isFalling = false;
			this.dir = Direction.RIGHT;

			if (this.fallForce >= MAX_FALL) {
				this.isAlive = false;
			}
		}

	}

	private void reverseDir() {
		if (this.dir == Direction.RIGHT) {
			this.dir = Direction.LEFT;
		} else {
			this.dir = Direction.RIGHT;
		}
	}

	// @Override
	// private boolean isAlive(){
	// return
	// }

	/**
	 * Implements the automatic update
	 */
	public void update() {
		if (isAlive())
			role.play(this);
		// TODO fill your code
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		if (role != null) {
			return role.getIcon(this);
		} else
			return null;
	}

	// TODO you should write a toString method to return the string that represents
	// the object status
	@Override
	public String toString() {
		return role.getIcon(this);
	}
}
