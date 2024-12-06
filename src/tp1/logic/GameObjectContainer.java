package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> objects;
	private Game game;

	public GameObjectContainer(Game game) {
		this.objects = new ArrayList<GameObject>();
		this.game = game;
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public int numObjects() {
		return objects.size();
	}

	public void setGame(Game game2) {
		for (GameObject object : objects) {
			object.setGame(game);
		}
	}

	public String getStringAt(Position position) {
		for (GameObject object : objects) {
			if (object.isInPosition(position)) {
				return object.toString();
			}
		}
		return " ";
	}

	public void update() throws GameModelException {
		for (GameObject object : objects) {
			if (object.isAlive()) {
				object.update();
			}

		}
		int numExited = checkExit();
		if (numExited > 0) {
			game.updateLemmingStatus(numExited);
		}
	}

	public int checkExit() throws ObjectParseException {
		int numArrived = 0;
		for (Position exit : getExitDoorPositions()) {
			for (GameObject object : objects) {
				if (object.isAlive() && object.getPosition() != null && object.getPosition().isEqual(exit)
						&& !object.getIcon().equals(Messages.EXIT_DOOR)) {
					numArrived++;
					object.makeInvisible();
				}
			}
		}
		return numArrived;
	}

	public Position[] getExitDoorPositions() throws ObjectParseException {
		List<Position> exitDoorPositionsList = new ArrayList<>();

		for (GameObject object : objects) {
			if (object.getIcon().equals(Messages.EXIT_DOOR)) {
				Position pos = object.getPosition();
				if (pos != null) {
					exitDoorPositionsList.add(pos);
				}
			}
		}
		return exitDoorPositionsList.toArray(new Position[0]);
	}

	public Position[] getWallPositions() throws ObjectParseException {
		List<Position> wallPositionsList = new ArrayList<>();
		for (GameObject object : objects) {
			if (object.getIcon().equals(Messages.WALL)) {
				Position pos = object.getPosition();
				if (pos != null) {
					wallPositionsList.add(pos);
				}
			}
		}
		return wallPositionsList.toArray(new Position[0]);
	}

	public Position[] getMetalWallPositions() throws ObjectParseException {
		List<Position> metalWallPositionsList = new ArrayList<>();
		for (GameObject object : objects) {
			if (object.getIcon().equals(Messages.METALWALL)) {
				Position pos = object.getPosition();
				if (pos != null) {
					metalWallPositionsList.add(pos);
				}
			}
		}
		return metalWallPositionsList.toArray(new Position[0]);
	}

	// public boolean checkLemmingPosition(Position pos) {

	// for (GameObject object : objects) {
	// if (object.isInPosition(pos)) {
	// return true;
	// }
	// }
	// return false;
	// }

	// public boolean setLemmingRole(Position pos, LemmingRole role) throws
	// GameModelException {

	// for (GameObject object : objects) {
	// if (object.isInPosition(pos)) {
	// object.setRole(role);
	// return true;
	// }
	// }

	// return false;
	// }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean checkLemmingPosition(Position pos) {
		// First validate the position
		if (!Game.isValidPosition(pos)) {
			return false;
		}

		// Then check if there's a game object at the position
		for (GameObject object : objects) {
			if (object.isInPosition(pos)) {
				return true;
			}
		}
		return false;
	}

	public boolean setLemmingRole(Position pos, LemmingRole role) throws GameModelException {
		// First validate the position

		if (!Game.isValidPosition(pos)) {
			System.out.println("Is it a valid position ? " + Game.isValidPosition(pos));
			System.out.println("Invalid position " + pos);
			return false;
		}

		// Then attempt to set the role
		for (GameObject object : objects) {
			if (object.isInPosition(pos)) {
				object.setRole(role);
				return true;
			}
		}

		return false;
	}
	/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int deadLemmings() {
		int deadLemmings = 0;
		for (GameObject object : objects) {
			if (!object.isAlive()) {
				deadLemmings++;
			}
		}
		return deadLemmings;
	}

	public boolean receiveInteractionsFrom(GameItem obj) throws GameModelException {
		for (GameObject object : objects) {
			if (object.receiveInteraction(obj)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder containerString = new StringBuilder();
		for (GameObject object : objects) {
			containerString.append(object.toString()).append("\n");
		}
		return containerString.toString();
	}
}