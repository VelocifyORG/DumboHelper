package dev.dumble.helper.config.impl;

import dev.dumble.helper.exceptions.InvalidConfigurationException;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class ConfigurationMap {

	private final Map<String, Configuration> CONFIGURATION_MAP = new ConcurrentHashMap<>();

	public Configuration get(String path) {
		return Optional
				.ofNullable(CONFIGURATION_MAP.get(path))
				.orElseThrow(() -> new InvalidConfigurationException(path));
	}

	boolean contains(String path) {
		return CONFIGURATION_MAP.containsKey(path);
	}

	void add(String path, Configuration configuration) {
		CONFIGURATION_MAP.put(path, configuration);
	}
}
