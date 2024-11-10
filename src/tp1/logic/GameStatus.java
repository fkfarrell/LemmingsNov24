package tp1.logic;

public interface GameStatus {

	public int getCycle();

	public int numLemmingsInBoard();

	public int numLemmingsDead();

	public int numLemmingsExit();

	public int numLemmingsToWin();

	public void initLevel(int lvl);

	public String toString();

	public String positionToString(int col, int row);

	public boolean playerWins();

	public boolean playerLooses();

	public void updateLemmingStatus(int exitedCount);
}
