package tp1.logic.file;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameWorld;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.DownCaverRole;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;

public class GameObjectFactory {
    // parses the text representation of the game objects in the text file and
    // creates game objects
    // in accordance to what is specified.
    private GameWorld game;

    private static final List<GameObject> availableObjects = Arrays.asList(
            new Lemming(),
            new Wall(),
            new MetalWall(),
            new ExitDoor());

    public GameObject parse(String line, Game game) throws ObjectParseException,
            OffBoardException {
        Position pos;
        String objectTitle;
        try {
            pos = parsePosition(line);
            objectTitle = parseObjectName(line);
        } catch (Exception e) {
            throw new ObjectParseException(
                    String.format(Messages.ERROR_PARSING_GAME_OBJECT, "Invalid position or object name: " + line), e);
        }
        if (objectTitle.equals("Lemming")) {
            Direction direction;
            int forceOfFall;
            LemmingRole role;

            try {
                direction = getLemmingDirectionFrom(line);
                forceOfFall = getLemmingHeigthFrom(line);
                role = getLemmingRoleFrom(line);
            } catch (Exception e) {
                throw new ObjectParseException(
                        String.format(Messages.ERROR_PARSING_GAME_OBJECT, "Invalid lemming attributes: " + line), e);
            }
            return new Lemming(game, pos, direction, role, forceOfFall);
        }

        else {

            try {
                GameObject newObject = null;

                for (GameObject go : availableObjects) {
                    newObject = go.parse(objectTitle, game, pos);

                    if (newObject != null) {
                        return newObject;
                    }
                }

                throw new ObjectParseException("Invalid object");
            } catch (Exception e) {
                throw new ObjectParseException(
                        String.format(Messages.ERROR_PARSING_GAME_OBJECT,
                                "Error parsing game object: " + line),
                        e);
            }
        }
    }

    private static Position parsePosition(String inputPos) throws ObjectParseException {
        int maxX = Game.DIM_X;
        int maxY = Game.DIM_Y;

        try {
            String positionString = inputPos.split(" ")[0];
            String[] coordinates = positionString
                    .replace("(", "")
                    .replace(")", "")
                    .split(",");

            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Coordinates cannot be negative");
            }
            if (x > maxX || y > maxY) {
                throw new IllegalArgumentException("Coordinates exceed maximum dimensions");
            }

            return new Position(x - 1, y - 1);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ObjectParseException(
                    String.format(Messages.ERROR_PARSING_GAME_OBJECT,
                            "Input string is not in the expected format (x,y): " + inputPos),
                    e);
        } catch (IllegalArgumentException e) {
            throw new ObjectParseException(
                    String.format(Messages.ERROR_PARSING_GAME_OBJECT, e.getMessage() + ": " +
                            inputPos),
                    e);
        } catch (Exception e) {
            throw new ObjectParseException(
                    String.format(Messages.ERROR_PARSING_GAME_OBJECT,
                            "Unexpected error occurred while parsing position: " + inputPos),
                    e);
        }
    }

    private static String parseObjectName(String inputName) {
        String objName = "";

        try {
            if (inputName == null) {
                throw new IllegalArgumentException("Input string is null: " + inputName);
            }

            String tmp;
            for (GameObject go : availableObjects) {
                tmp = go.parseName(inputName);
                if (tmp != null) {
                    return tmp;
                }
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Error: Input string is not in the expected format: " +
                    inputName);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred while parsing object name: " +
                    inputName);
        }

        return objName;
    }

    private static Direction getLemmingDirectionFrom(String inputDir) {
        String[] parts = inputDir.split("\\s+");
        try {
            return Direction.valueOf(parts[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            return Direction.RIGHT;
        }
    }

    private static int getLemmingHeigthFrom(String inputForceOfFall) {
        String[] parts = inputForceOfFall.split("\\s+");
        try {
            return Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static LemmingRole getLemmingRoleFrom(String inputRole) {
        String[] parts = inputRole.split("\\s+");
        String[] newParts = new String[2];

        try {
            // String roleStr = parts[4].toLowerCase();
            // char firstChar = roleStr.charAt(0);

            newParts[0] = parts[3];
            newParts[1] = parts[4];

            LemmingRole role = LemmingRoleFactory.parse(newParts);

            return role;

            // to add another role, just add it to the switch, between case p and w.
            // switch (firstChar) {
            // case 'd':
            // return new DownCaverRole();
            // case 'p':
            // return new ParachuterRole();
            // case 'w':
            // default:
            // return new WalkerRole();
            // }
        } catch (Exception e) {
            System.err.println("Error parsing role: " + e.getMessage());
            return new WalkerRole();
        }
    }

}
