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

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public void makeInvisible() {
    }

    //////////////////////////////////////////////////////////////////////////////////

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

    // @Override
    // public Position getPosition(){
    // return this.pos;
    // }
}
