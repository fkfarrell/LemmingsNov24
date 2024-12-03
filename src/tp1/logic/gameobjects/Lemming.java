package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class Lemming extends GameObject {
	private LemmingRole role;
	private int fallForce = 0;
	private Direction dir;
	private final int MAX_FALL = 3;
	private boolean isFalling = false;

	public Lemming(Game game, Position pos, Direction dir, LemmingRole role) {
		super(game, pos);
		this.role = role;
		this.dir = dir;
	}

	public Lemming() {
	}

	@Override
	public boolean setRole(LemmingRole role) {
		if (this.role.equals(role)) {
			game.update();
			return false;
		} else if (!this.isFalling && role.toString().equals(new ParachuterRole().toString())) {
			game.update();
			return false;
		} else {
			this.role = role;
			return true;
		}
	};

	public void disableRole() {
		this.role = new WalkerRole();
	};

	public Direction getDirection() {
		return this.dir;
	}

	public void dig() {
		this.checkOffBoard();
		Position wallPos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
		MetalWall metalWall = new MetalWall(game, wallPos);

		if (!game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(metalWall.getIcon())) {
			this.pos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
			this.fallForce++;
		} else {
			this.checkFloor();
			this.disableRole();
		}
	}

	public void walkOrFall() {
		if (this.canMove()) {
			if (this.dir == Direction.LEFT) {
				this.pos = new Position(this.pos.getCol() - 1, this.pos.getRow());
			} else if (this.dir == Direction.RIGHT) {
				this.pos = new Position(this.pos.getCol() + 1, this.pos.getRow());
			} else if (this.dir == Direction.DOWN) {
				this.pos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
				fallForce++;
			}
		}
	}

	public boolean canMove() {
		this.checkOffBoard();

		Direction movDirection = this.getDirection();
		Position currentPosition = this.pos;
		final int WALL_RIGHT = 10;
		final int WALL_LEFT = 1;

		int nextCol = currentPosition.getCol() + (movDirection.getX());
		int nextRow = currentPosition.getRow() + movDirection.getY();

		if (this.dir == Direction.RIGHT) {
			if (nextCol >= WALL_RIGHT
					|| game.positionToString(nextCol, currentPosition.getRow()).equals(Messages.WALL)) { // game is null
																											// when a
																											// new game
																											// is loaded
																											// in ?
				this.reverseDir();
				return false;
			}
			checkFloor();
			this.fallForce = 0;
			return true;
		}

		else if (this.dir == Direction.LEFT) {
			if (nextCol <= WALL_LEFT
					|| game.positionToString(nextCol,
							currentPosition.getRow()).equals(Messages.WALL)
					||
					game.positionToString(nextCol,
							currentPosition.getRow()).equals(Messages.METALWALL)) {
				this.reverseDir();
				return false;
			}
			checkFloor();
			this.fallForce = 0;
			checkFloor();
			this.fallForce = 0;
			return true;
		} else if (this.dir == Direction.DOWN) {
			checkFloor();
			if (game.positionToString(currentPosition.getCol(),
					nextRow).equals(Messages.WALL)) {
			}
			return true;
		}
		return false;
	}

	private void checkFloor() {
		if (game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(" ")) {
			this.isFalling = true;
			this.dir = Direction.DOWN;

		}

		else if (game.positionToString(this.pos.getCol(), this.pos.getRow() +
				1).equals(Messages.WALL)
				|| (game.positionToString(this.pos.getCol(), this.pos.getRow() +
						1).equals(Messages.METALWALL) && isFalling))

		{

			if (this.fallForce >= MAX_FALL
					&& (this.getIcon().equals(Messages.LEMMING_RIGHT)
							|| this.getIcon().equals(Messages.DOWN_CAVER_ROL_SYMBOL)
							|| this.getIcon().equals(Messages.LEMMING_LEFT))) {
				this.isAlive = false;
			} else if (this.isFalling) {
				this.dir = Direction.RIGHT;
				this.isFalling = false;
				this.disableRole();
			}
		} 

	}

	private void checkOffBoard() {
		if (this.pos.getCol() < 0 || this.pos.getCol() > Game.DIM_X ||
				this.pos.getRow() < 0 || this.pos.getRow() > Game.DIM_Y - 2) {
			this.makeInvisible();
		}
	}

	private void checkExit() {
		if (game.positionToString(this.pos.getCol(), this.pos.getRow()).equals(Messages.EXIT_DOOR)) {
			this.makeInvisible();
			game.lemmingArrived();
		}
	}

	private void reverseDir() {
		if (this.dir == Direction.RIGHT) {
			this.dir = Direction.LEFT;
		} else if (this.dir == Direction.LEFT) {
			this.dir = Direction.RIGHT;
		}
	}

	@Override
	public void makeInvisible() {
		this.isAlive = false;
		this.dir = Direction.NONE;
	}

	@Override
	public void update() {
		if (this.isAlive()) {
			role.advance(this);
			checkExit();
		}
	}

	@Override
	public String getIcon() {
		if (role != null) {
			return role.getIcon(this);
		}
		return null;
	}

	@Override
	public String toString() {
		return role.getIcon(this);
	}

	@Override
	public boolean receiveInteraction(GameItem other) {
		try {
			boolean result = other.interactWith(this);
			System.out.println(other.toString() + " interacts with " + this.toString() + " : " + result);
			return result;
		} catch (Exception e) {
			System.err.println("Error during interaction: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean interactWith(Lemming lemming) {
		return true;
	}

	@Override
	public boolean interactWith(Wall wall) {
		return true;
	}

	@Override
	public boolean interactWith(ExitDoor door) {
		return true;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean interactWith(MetalWall metalWall) {
		return false;
	}
}
