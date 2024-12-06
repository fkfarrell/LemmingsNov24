// package tp1.control.commands;

// import tp1.logic.GameModel;
// import tp1.view.GameView;
// import tp1.view.Messages;
// //import tp1.logic.gameobjects.*;
// import tp1.logic.lemmingRoles.*;
// import tp1.logic.Position;
// import exceptions.CommandExecuteExceptions;
// import exceptions.CommandParseException;
// import exceptions.OffBoardException;
// import exceptions.RoleParseException;
// import tp1.logic.Game;

// public class SetRoleCommand extends Command {

// // Forman parte de atributos de estado
// private static final String NAME = Messages.COMMAND_SET_ROLE_NAME;
// private static final String SHORTCUT = Messages.COMMAND_SET_ROLE_SHORTCUT;
// private static final String DETAILS = Messages.COMMAND_SET_ROLE_DETAILS;

// private static final String HELP =LemmingRoleFactory.commandHelp();
// private Position pos;
// private LemmingRole role;
// private LemmingRole gameRole;
// private Game game;
// private Position rolePosition;

// public SetRoleCommand() {
// super(NAME, SHORTCUT, DETAILS, HELP);
// }

// public SetRoleCommand(LemmingRole role, Position pos) {
// super(NAME, SHORTCUT, DETAILS, HELP);
// this.role = role;
// this.pos = pos;
// }

// @Override
// public void execute(Game game, GameView view) throws CommandExecuteExceptions
// {
// try {
// game.setLemmingRole(rolePosition, gameRole);
// } catch (OffBoardException obe) {
// throw new CommandExecuteExceptions(Messages.ERROR_COMMAND_EXECUTE, obe);
// }
// view.showGame();
// }

// public Command parse(String[] commandWords) throws CommandParseException{
// // parse info taken in and return cmd.

// if(!(commandWords[0].toLowerCase().equals(this.NAME)||commandWords[0].toLowerCase().equals(this.getShortcut())))return
// null;

// try {
// // LEMMING ROLE
// LemmingRole role = LemmingRoleFactory.parse(commandWords[1]);
// gameRole = role;
// String rowPos = commandWords[2].toLowerCase();
// int rowNum = rowPos.charAt(0) - 'a';
// int colNum = Integer.parseInt(commandWords[3]);
// rolePosition = new Position(rowNum, colNum -1);

// return this;
// } catch (NumberFormatException e) {
// throw new CommandParseException(Messages.INVALID_POSITION.formatted
// (Messages.POSITION.formatted( commandWords[2], commandWords[3])));

// }catch (RoleParseException e) {
// throw new CommandParseException(e.getMessage());

// }
//        
//     }
// }