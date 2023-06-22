package dev.dumble.helper.exceptions;

public class AnnotationNotRegisteredException extends Error {

	public AnnotationNotRegisteredException(String name) {
		super(String.format("Couldn't find any registered annotated classes by `%s`.", name));
	}
}
