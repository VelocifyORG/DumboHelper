package dev.dumble.helper.exceptions;

public class InvalidParseException extends Error {

	public InvalidParseException() {
		super("The code has attempted to cast an object to a subclass of which it is not an instance.");
	}
}
