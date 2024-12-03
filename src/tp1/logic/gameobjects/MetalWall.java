package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject {

    public MetalWall(Game game, Position pos) {
        super(game, pos);
    }

    public MetalWall() {
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
        return false;
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
        return true;
    }

    @Override
    public boolean interactWith(MetalWall metalWall) {
        return false;
    }

    @Override
    public String getIcon() {
        return Messages.METALWALL;
    }

    @Override
    public String toString() {
        return Messages.METALWALL;
    }

    @Override
    public void update() {
    }
}
