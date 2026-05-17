package exceptions.validation;

public class InvalidBoardSizeException extends ValidationException {
    public InvalidBoardSizeException(String message) {
        super(message);
    }
}
