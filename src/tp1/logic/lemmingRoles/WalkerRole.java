package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class WalkerRole implements LemmingRole {
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;

	@Override
	public void advance(Lemming lemming) {
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

	private String getName() {
		return NAME;
	}

	public String getHelp() {
		return HELP;
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

}
