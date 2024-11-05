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
		this.role = new WalkerRole();
		this.dir = dir;
	}

	public Direction getDirection() {
		return this.dir;
	}

	// Not mandatory but recommended
	public void walkOrFall() {
		// should decide whether a move can be made,
		//
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
