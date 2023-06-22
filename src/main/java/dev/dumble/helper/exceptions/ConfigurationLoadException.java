package dev.dumble.helper.exceptions;

public class ConfigurationLoadException extends Error {

	public ConfigurationLoadException(String path) {
		super(String.format("Attempting to load the configuration again using the specified path `%s` is not allowed.", path));
	}
}
