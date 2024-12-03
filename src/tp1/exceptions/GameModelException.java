package tp1.control.commands;

public class GameModelException extends Exception{
    public GameModelException(String message) {
        super(message);
    }

    public GameModelException(String message, Throwable cause) {
        super(message, cause);
    }
}

