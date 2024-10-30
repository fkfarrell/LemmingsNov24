package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.WalkerRole;

public class Lemming extends GameObject {

	WalkerRole role;
	private int fallForce = 0;
	private Direction dir;

	public Lemming(Game game, Position pos, Direction dir) {
		super(game, pos);
		this.role = WalkerRole();
		this.dir = dir;
	}

	public Direction getDirection() {
		return this.dir;
	}

	private WalkerRole WalkerRole() {
		// TODO Auto-generated method stub
		return null;
	}

	// Not mandatory but recommended
	public void walkOrFall() {
		// TODO Auto-generated method stub
	}

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
		return role.getIcon(this);
	}

	// TODO you should write a toString method to return the string that represents
	// the object status
	@Override
	public String toString() {
		return null;
	}
}
