package dev.dumble.helper.exceptions;

public class InvalidConfigurationException extends Error {

	public InvalidConfigurationException(String path) {
		super(String.format("There is no configuration loaded called `%s`.", path));
	}
}
