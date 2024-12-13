package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {

    private final String name = "ExitDoor";
    private final String shortcut = "ed";

    public ExitDoor(Game game, Position pos, Direction dir) {
        super(game, pos, dir);
    }

    public ExitDoor() {
    }

    @Override
    public void update() {

    }

    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR;
    }

    @Override
    public String toString() {
        return Messages.EXIT_DOOR;
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

    @Override
    public String getShortcut() {
        return this.shortcut;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public GameObject parse(String input, Game game, Position pos) {
        if (matchObjectName(input)) {
            return new ExitDoor(game, pos, null);
        } else {
            return null;
        }
    }

    @Override
    public String parseName(String input) {
        String[] parts = input.split(" ");
        if (matchObjectName(parts[1])) {
            return this.getName();
        } else {
            return null;
        }
    }

}
