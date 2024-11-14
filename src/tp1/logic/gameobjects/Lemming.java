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
	private WalkerRole walkerRole;
	private int fallForce = 0;
	private Direction dir;
	private final int MAX_FALL = 3;
	private boolean isFalling = false;
	


	public Lemming(Game game, Position pos, Direction dir, LemmingRole role) {
		super(game, pos);
		this.role = role;
		this.dir = dir;
	}

	@Override
	public boolean setRole(LemmingRole role) {
		if (this.role.equals(role)) {
			System.out.println("SAME ROLE");
			return false;
		} else {
			this.role = role;
			return true;
		}

		// if(!this.isFalling)}
		// {}

	};

	public void disableRole() {
		this.role = new WalkerRole();
	};

	public Direction getDirection() {
		return this.dir;
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

		Direction movDirection = this.getDirection();  
		Position currentPosition = this.pos;
		final int WALL_RIGHT = 10;
		final int WALL_LEFT = 1;   
		
		int nextCol = currentPosition.getCol() + (movDirection.getX());
		int nextRow = currentPosition.getRow() + movDirection.getY();
		
	
		if (this.dir == Direction.RIGHT) {
			if (nextCol >= WALL_RIGHT || game.positionToString(nextCol, currentPosition.getRow()).equals(Messages.WALL)) {
				this.reverseDir(); 
				return false; 
			}
			checkFloor(); 
			this.fallForce = 0; 
			return true;
		}

		else if (this.dir == Direction.LEFT) {
			if (nextCol <= WALL_LEFT
					|| game.positionToString(nextCol, currentPosition.getRow()).equals(Messages.WALL)) {
				this.reverseDir();
				return false;

			}
			checkFloor(); 
			this.fallForce = 0; 
			return true;
		}
		else if (this.dir == Direction.DOWN) {
			checkFloor();
			if (game.positionToString(currentPosition.getCol(), nextRow).equals(Messages.WALL)) {
			}
			return true;
		}
		return false;
	}

	private void checkFloor() {
		if (game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(" ")) {
			this.isFalling = true;
			this.dir = Direction.DOWN;
      
		} else if (game.positionToString(this.pos.getCol(), this.pos.getRow() + 1).equals(Messages.WALL) && isFalling) {
			this.dir = Direction.RIGHT;
			this.isFalling = false;
		}

		this.role = new WalkerRole();
		this.disableRole();

		if (this.fallForce >= MAX_FALL) { // && (this.role !=
			// ParachuterRole(), none
			// parachuters will die from
			// a
			// fall
			this.isAlive = false;


		}
	

	private void checkExit() {
		if (game.positionToString(this.pos.getCol(), this.pos.getRow()).equals("D")) {
			this.makeInvisible();
			game.lemmingArrived();
		}
	}
	/// //////////////////////////////////////////////////////////////////////////////////////

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
}