package exceptions.validation;

import exceptions.GameException;

public class ValidationException extends GameException {
    public ValidationException(String message) {
        super(message);
    }
}
