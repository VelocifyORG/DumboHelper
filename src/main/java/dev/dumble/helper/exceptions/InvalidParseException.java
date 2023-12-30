package dev.dumble.helper.exceptions;

public class InvalidParseException extends RuntimeException {

    public InvalidParseException(final Throwable cause) {
        super(cause);
    }

    public InvalidParseException(final String message) {
        super(message);
    }

    public InvalidParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidParseException() {
        super("The code has attempted to cast an object to a subclass of which it is not an instance.");
    }
}
