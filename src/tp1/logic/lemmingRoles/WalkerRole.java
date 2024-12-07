package tp1.logic.lemmingRoles;

import tp1.exceptions.GameModelException;
import tp1.logic.Direction;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;
import tp1.logic.Game;

public class WalkerRole implements LemmingRole {
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;

	@Override
	public void advance(Lemming lemming) throws GameModelException {
		lemming.walkOrFall();
	}

	@Override
	public String getIcon(Lemming lemming) {
		if (lemming.isAlive()) {
			if (lemming.getDirection() == Direction.RIGHT) {
				return ICON_RIGHT;
			} else if (lemming.getDirection() == Direction.DOWN) {
				return ICON_RIGHT;
			} else {
				return ICON_LEFT;
			}
		} else
			return " ";
	}

	public String getName() {
		return NAME;
	}

	@Override
	public String helpText() {
		return HELP;
	}

	@Override
	public LemmingRole parse(String input) {
		System.out.println("parse input >>> " + input);
		if (input.equalsIgnoreCase(getName()) || input.equalsIgnoreCase(getShortcut()))
			return this;
		return null;
	}

	// String that represents the object status
	// for this simple class, the name is enough
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public void play(Lemming lemming) {
	}

	@Override
	public String getShortcut() {
		return "w";
	}

	@Override
	public boolean receiveInteraction(GameItem other, Lemming lemming) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'receiveInteraction'");
	}

	@Override
	public boolean interactWith(Lemming receiver, Lemming lemming) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'interactWith'");
	}

	@Override
	public boolean interactWith(Wall wall, Lemming lemming) {
		System.out.println("Walker and wall" + wall.receiveInteraction(lemming));
		return false;
	}

	@Override
	public boolean interactWith(ExitDoor door, Lemming lemming) {
		return true;
	}

	@Override
	public boolean interactWith(MetalWall metalWall, Lemming lemming) {
		return false;
	}

	@Override
	public boolean interactWithAir(Lemming lemming) {
		return true;
	}

}
