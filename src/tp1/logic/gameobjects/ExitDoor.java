package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public class ExitDoor extends GameObject {

    public ExitDoor(Game game, Position pos) {
        super(game, pos);
    }

    @Override
    public void update() {

    }

    @Override
    public String getIcon() {
        return "D";
    }
}
