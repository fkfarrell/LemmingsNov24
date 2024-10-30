package tp1.logic;

public interface GameModel {

    public boolean isFinished();

    public void update();

    public void reset();

    public void none();

    public String help();

    public void createLevel(int levelNum);
}
