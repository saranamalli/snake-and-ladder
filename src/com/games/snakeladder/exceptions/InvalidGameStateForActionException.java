package exceptions;

public class InvalidGameStateForActionException extends GameException {
    public InvalidGameStateForActionException(String message) {
        super(message);
    }
}
