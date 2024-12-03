package tp1.control.commands;

public class ObjectParseException extends GameParseException{
    public ObjectParseException(String message) {
        super(message);
    }

    public ObjectParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
