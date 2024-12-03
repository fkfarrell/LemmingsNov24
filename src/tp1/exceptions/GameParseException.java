package tp1.control.commands;

public class GameParseException extends GameModelException{
    public GameParseException(String message) {
        super(message);
    }

    public GameParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
