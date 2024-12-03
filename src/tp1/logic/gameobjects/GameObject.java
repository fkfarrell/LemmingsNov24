package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem {
	protected Position pos;
	protected boolean isAlive;
	public Game game;

	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
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

	public abstract String getIcon();

	public Position getPosition() {
		return this.pos;
	}

	public abstract void update();

	public void makeInvisible() {
		this.isAlive = false;
	}

	public boolean setRole(LemmingRole role) {
		return false;
	}

	public abstract boolean receiveInteraction(GameItem other);

	public GameObject parse(String input) {
		// take in string and match to constructor
		return this;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}