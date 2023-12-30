package dev.dumble.helper.exceptions;

public class ConfigurationLoadException extends RuntimeException {

    public ConfigurationLoadException(String path) {
        super(String.format("Attempting to load the configuration again using the specified path `%s` is not allowed.", path));
    }

    public ConfigurationLoadException(final Throwable cause) {
        super(cause);
    }
}
