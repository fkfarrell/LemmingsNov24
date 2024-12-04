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
		Wall diggable = new Wall(game, wallPos);

		if (game.wallBelow(wallPos) && this.role.interactWith(diggable, this)) {
			System.out.println("WALL BELOW");
			this.pos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
			this.fallForce++;
			System.out.println("FALLING");
		} else {
			System.out.println("No wall detected @ " + wallPos.toString());
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

	// this can move has the double turn bug on the second level
	public boolean canMove() {
		this.checkOffBoard();

		Direction movDirection = this.getDirection();
		Position currentPosition = this.pos;
		final int WALL_RIGHT = 10;
		final int WALL_LEFT = 1;

		int nextCol = currentPosition.getCol() + movDirection.getX();
		int nextRow = currentPosition.getRow() + movDirection.getY();

		if (this.dir == Direction.RIGHT) {
			// Use wallAhead to check for walls
			if (nextCol >= WALL_RIGHT || game.wallAhead(currentPosition, movDirection)) {
				this.reverseDir();
				return false;
			}
			checkFloor();
			this.fallForce = 0;
			return true;
		} else if (this.dir == Direction.LEFT) {
			if (nextCol <= WALL_LEFT || game.wallAhead(currentPosition, movDirection)) {
				this.reverseDir();
				return false;
			}
			checkFloor();
			this.fallForce = 0;
			return true;
		} else if (this.dir == Direction.DOWN) {
			checkFloor();
			if (game.wallBelow(currentPosition)) {
				return false;
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

		else if (game.wallBelow(pos)
				|| (game.metalWallBelow(pos)
						&& isFalling)) {

			if (this.fallForce >= MAX_FALL
					&& (this.getIcon().equals(Messages.LEMMING_RIGHT)
							|| this.getIcon().equals(Messages.DOWN_CAVER_ROL_SYMBOL)
							|| this.getIcon().equals(Messages.LEMMING_LEFT))) // !parachuter
			{
				this.isAlive = false;
			} else if (this.isFalling) {
				this.dir = Direction.RIGHT;
				this.isFalling = false;
				this.disableRole();
			}
		} else {
			System.out.println("NULL @ " + this.pos.toString());
		}

	}

	private void checkOffBoard() {
		if (this.pos.getCol() < 0 || this.pos.getCol() > Game.DIM_X ||
				this.pos.getRow() < 0 || this.pos.getRow() > Game.DIM_Y - 2) {
			this.makeInvisible();
		}
	}

	public void checkExit() {
		if (game.exitAhead(this.pos, this.dir) || game.exitBelow(this.pos, this.dir)) {
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
