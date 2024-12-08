package tp1.logic.gameobjects;

import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.Direction;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem {
	protected Position pos;
	protected boolean isAlive;
	public Game game;
	protected Direction dir;

	public GameObject(Game game, Position pos, Direction dir) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.dir = dir;
	}

	public GameObject() {
	}

	public boolean isInPosition(Position p) {
		if (this.isAlive && this.pos.isEqual(p)) {
			return true;
		}
		return false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDead() {
		return !isAlive;
	}

	public boolean isExit() {
		return false;
	}

	public abstract String getIcon() throws ObjectParseException;

	public Position getPosition() {
		return this.pos;
	}

	public Direction getDirection() {
		return this.dir;
	}

	public abstract void update() throws OffBoardException, ObjectParseException, GameModelException;

	public void makeInvisible() {
		this.isAlive = false;
	}

	public boolean setRole(LemmingRole role) throws OffBoardException, ObjectParseException, GameModelException {
		return false;
	}

	public abstract boolean receiveInteraction(GameItem other) throws GameModelException;

	public GameObject parse(String input) {
		return this;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}