package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall extends GameObject {

    public Wall(Game game, Position pos) {
        super(game, pos);
    }

    @Override
    public void update() {

    }

    @Override
    public String getIcon() {
        return "W";
    }

    @Override
    public String toString() {
        return Messages.WALL;
    }

}
