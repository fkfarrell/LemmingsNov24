package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer implements Iterable<GameObject> {
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
	public List<GameObject> getExitDoors() {
        List<GameObject> exitDoors = new ArrayList<>();
        for (GameObject obj : objects) {
            if (obj.isExit()) {  
                exitDoors.add(obj);  
            }
        }
        return exitDoors;  
    }

	@Override
	public Iterator<GameObject> iterator() {
		return new Iterator<GameObject>() {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < objects.size();
        }

        @Override
        public GameObject next() {
            if (!hasNext()) { // Ensure there's a next element
                throw new NoSuchElementException(); // Throw exception if no elements left
            }
            return objects.get(index++);  // Return the object and increment the index
        }
    };
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
