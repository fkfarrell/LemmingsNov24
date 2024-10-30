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

	public String getStringAt(Position position) {
		for (GameObject object : objects) {
			if (object.isInPosition(position)) {
				return object.getIcon();
			}

		}
		return " ";
	}

	// TODO you should write a toString method to return the string that represents
	// the object status
	@Override
	public String toString() {
		// should call to string on the object in container?
		// would that breach incapsulation
		return null;
	}
}
