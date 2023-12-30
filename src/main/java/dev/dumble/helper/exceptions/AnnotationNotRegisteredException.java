package dev.dumble.helper.exceptions;

public class AnnotationNotRegisteredException extends RuntimeException {

    public AnnotationNotRegisteredException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnnotationNotRegisteredException(final Throwable cause) {
        super(cause);
    }

    public AnnotationNotRegisteredException(String message) {
        super(String.format("Couldn't find any registered annotated classes by `%s`.", message));
    }
}
