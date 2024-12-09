package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Wall extends GameObject {

    public Wall(Game game, Position pos, Direction dir) {
        super(game, pos, dir);
    }

    public Wall() {
    }

    @Override
    public void update() {

    }

    @Override
    public String getIcon() {
        return Messages.WALL;
    }

    @Override
    public String toString() {
        return Messages.WALL;
    }

    @Override
    public void makeInvisible() {
    }

    @Override
    public boolean receiveInteraction(GameItem other) {
        try {
            boolean result = other.interactWith(this);
            System.out.println(other.toString() + " interacts with " + this.toString() + " : " + result);
            return result;
        } catch (Exception e) {
            System.err.println("Error during interaction: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        return true;
    }

    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean interactWith(MetalWall metalWall) {
        return false;
    }
}
