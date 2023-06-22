package dev.dumble.helper.config;

public abstract class AbstractConfigurationNode {

	public abstract String getValue();

	public <T> T as(Class<T> clazz) {
		try {
			final String value = this.getValue();
			final NodeType type = NodeType.findEnumByClass(clazz);

			final Object appliedFunction = type.getFunction().apply(value);

			return clazz.cast(appliedFunction);

		} catch (Throwable throwable) {
			return null;
		}
	}
}