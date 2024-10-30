package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	// TODO fill your code

	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public boolean isEqual(Position pos1) {
		if (this == pos1) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "(" + ")";
	}
}
