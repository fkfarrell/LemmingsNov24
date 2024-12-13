package tp1.logic.gameobjects;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.WalkerRole;

public class Lemming extends GameObject {
	private LemmingRole role;
	private WalkerRole walkerRole;
	private int fallForce = 0;
	private Direction dir;
	private final int MAX_FALL = 3;
	private boolean isFalling = false;
	private final String name = "Lemming";
	private final String shortcut = "l";

	public Lemming(Game game, Position pos, Direction dir, LemmingRole role, int fallForce) {
		super(game, pos, dir);
		this.role = role;
		this.dir = dir;
		this.fallForce = fallForce;
	}

	public Lemming() {
	}

	public Lemming(Game game, Position pos, Direction dir) {
		super(game, pos, dir);
	}

	@Override
	public String getShortcut() {
		return this.shortcut;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean setRole(LemmingRole role) throws GameModelException, CommandExecuteException {
		if (this.role.equals(role)) {
			game.update();
			return false;
		} else if (!this.isFalling && !role.interactWithAir(this)) {
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

	public void dig() throws OffBoardException, ObjectParseException {
		this.checkOffBoard();
		Position wallPos = new Position(this.pos.getCol(), this.pos.getRow());
		MetalWall metalWall = new MetalWall(game, wallPos, null);
		Wall diggable = new Wall(game, wallPos, null);

		if (game.wallBelow(wallPos) && this.role.interactWith(diggable, this)) {
			this.pos = new Position(this.pos.getCol(), this.pos.getRow() + 1);
			this.fallForce++;
		} else {
			this.checkFloor();
			this.disableRole();
		}
	}

	public void walkOrFall() throws OffBoardException, GameModelException {
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

	public boolean canMove() throws OffBoardException, ObjectParseException {
		this.checkOffBoard();

		Direction movDirection = this.getDirection();
		Position currentPosition = this.pos;
		final int WALL_RIGHT = 10;
		final int WALL_LEFT = 1;

		int nextCol = currentPosition.getCol() + movDirection.getX();
		int nextRow = currentPosition.getRow() + movDirection.getY();

		if (this.dir == Direction.RIGHT) {
			if ((nextCol >= WALL_RIGHT || game.wallAhead(currentPosition, movDirection))) {
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

	private void checkFloor() throws ObjectParseException {
		if (!game.wallBelow(this.pos) && !game.metalWallBelow(this.pos)) {
			this.isFalling = true;
			this.dir = Direction.DOWN;
		} else {
			if (this.isFalling) {
				if (this.fallForce >= MAX_FALL && (this.role.interactWithAir(this))) {
					this.isAlive = false;
				} else {
					this.dir = Direction.RIGHT;
					this.isFalling = false;
					this.disableRole();
				}
			}
		}
	}

	private void checkOffBoard() throws OffBoardException {
		if (this.pos.getCol() < 0 || this.pos.getCol() > Game.DIM_X ||
				this.pos.getRow() < 0 || this.pos.getRow() > Game.DIM_Y - 2) {
			this.makeInvisible();
			throw new OffBoardException("Lemming is out of bounds at: " + this.pos);
		}
	}

	public void checkExit() throws ObjectParseException {
		if (game.exitAhead(this.pos, this.dir) || game.exitBelow(this.pos, this.dir)) {
			this.makeInvisible();
			game.lemmingArrived();
		}
	}

	private void reverseDir() throws ObjectParseException {
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
	public void update() throws GameModelException {
		if (this.isAlive()) {
			role.advance(this);
			checkExit();
		}
	}

	@Override
	public String getIcon() throws ObjectParseException {
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
	public boolean receiveInteraction(GameItem other) throws GameModelException {
		try {
			boolean result = other.interactWith(this);
			return result;
		} catch (Exception e) {
			System.err.println("Error during interaction: " + e.getMessage());
			return false;
		}
	}

	@Override
	public GameObject parse(String input, Game game, Position pos) {
		if (matchObjectName(input)) {
			return new Lemming(game, pos, null);
		} else {
			return null;
		}
	}

	@Override
	public String parseName(String input) {
		String[] parts = input.split(" ");
		if (matchObjectName(parts[1])) {
			return this.getName();
		} else {
			return null;
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
