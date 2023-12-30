package dev.dumble.helper.exceptions;

public class InvalidFilePathException extends RuntimeException {

    public InvalidFilePathException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidFilePathException(final Throwable cause) {
        super(cause);
    }

    public InvalidFilePathException(String message) {
        super(message);
    }

    public InvalidFilePathException() {
        super("No matching paths were found in the resources file to save this default configuration.");
    }
}
