package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class GameObject {
	protected Position pos;
	protected boolean isAlive;
	protected Game game;

	public GameObject(Game game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
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
}