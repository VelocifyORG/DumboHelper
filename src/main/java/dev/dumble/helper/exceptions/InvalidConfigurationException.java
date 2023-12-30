package dev.dumble.helper.exceptions;

public class InvalidConfigurationException extends RuntimeException {

    public InvalidConfigurationException(final Throwable cause) {
        super(cause);
    }

    public InvalidConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidConfigurationException(String path) {
        super(String.format("There is no configuration loaded called `%s`.", path));
    }
}
