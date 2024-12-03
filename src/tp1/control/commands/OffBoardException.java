package tp1.control.commands;

public class OffBoardException extends GameModelException {
    public OffBoardException(String message) {
        super(message);
    }

    public OffBoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
