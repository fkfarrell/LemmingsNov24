package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.GameObject;

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

	public String getStringAt(Position position) {
		for (GameObject object : objects) {
			if (object.isInPosition(position)) {
				return object.toString();
			}
		}
		return " ";
	}

	public void update() {
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

	public int checkExit() {
		int numArrived = 0;
		for (Position exit : getExitDoorPositions()) {
			for (GameObject object : objects) {
				if (object.isAlive() && object.getPosition() != null && object.getPosition().isEqual(exit)
						&& !object.getIcon().equals("D")) {
					numArrived++;
					object.makeInvisible();
				}
			}
		}
		return numArrived;
	}

	public Position[] getExitDoorPositions() {
		int numDoors = 0;
		for (GameObject object : objects) {
			if (object.getIcon().equals("D")) {
				object.getPosition();
				numDoors++;
			}
		}

		Position[] exitDoorPositions = new Position[numDoors];
		int index = 0;
		for (GameObject object : objects) {
			if (object.getIcon().equals("D")) {
				exitDoorPositions[index] = object.getPosition();
				index++;
			}
		}
		return exitDoorPositions;
	}

	public int deadLemmings() {
		int deadLemmings = 0;
		for (GameObject object : objects) {
			if (!object.isAlive()) {
				deadLemmings++;
			}
		}
		return deadLemmings;
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