package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {
	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}

	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	}

	public String getStringAt(Position position) { // nothing is being detected??
		for (GameObject object : objects) {
			if (object.isInPosition(position)) {
				return object.toString();
			}
		}
		return " ";
	}

	public void update() {
		for (GameObject object : objects) {
			object.update();
		}
	}

	// TODO you should write a toString method to return the string that represents
	// the object status
	@Override
	public String toString() {
		StringBuilder containerString = new StringBuilder();
		for (GameObject object : objects) {
			containerString.append(object.toString()).append("\n");
		}
		return containerString.toString();
	}
}
