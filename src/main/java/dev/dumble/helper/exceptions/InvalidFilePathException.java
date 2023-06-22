package dev.dumble.helper.exceptions;

public class InvalidFilePathException extends Error {

	public InvalidFilePathException(String message) {
		super(message);
	}

	public InvalidFilePathException() {
		super("No matching paths were found in the resources file to save this default configuration.");
	}
}
