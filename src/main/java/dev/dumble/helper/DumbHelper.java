package dev.dumble.helper;

import lombok.Getter;

public class DumbHelper {

	@Getter
	public static final ClassLoader classLoader = DumbHelper.class.getClassLoader();

	public static void main(String[] args) {
	}
}