package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private final int COL;
	private final int ROW;

	// TODO fill your code

	public Position(int col, int row) {
		this.COL = col;
		this.ROW = row;
	}

	public boolean isEqual(Position pos) {
		if (pos == null) {
			return false;
		}
		return this.COL == pos.COL && this.ROW == pos.ROW;
	}

	public int getCol() {
		return this.COL;
	}

	public int getRow() {
		return this.ROW;
	}

	public String toString() {
		return "(" + COL + " , " + ROW + ")";
	}
}
