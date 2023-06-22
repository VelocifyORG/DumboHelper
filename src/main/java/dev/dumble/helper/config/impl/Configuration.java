package dev.dumble.helper.config.impl;

import dev.dumble.helper.DumbHelper;
import dev.dumble.helper.config.AbstractConfiguration;
import dev.dumble.helper.exceptions.ConfigurationLoadException;
import dev.dumble.helper.exceptions.InvalidConfigurationException;
import dev.dumble.helper.exceptions.InvalidFilePathException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Map;
import java.util.Objects;

@Getter @Setter(value = AccessLevel.PROTECTED)
@Builder(setterPrefix = "set")
public class Configuration extends AbstractConfiguration {

	public Map<String, Node<Object>> nodeMap;

	protected String configurationPath;
	protected InputStream resourcesStream;

	private static class ModifiedConfigurationBuilder extends ConfigurationBuilder {

		@Override
		public Configuration build() {
			Configuration configuration;

			try {
				if (super.configurationPath == null)
					throw new InvalidConfigurationException("You haven't provided any configuration details for retrieving the configuration file!");

				if (ConfigurationMap.contains(super.configurationPath))
					throw new ConfigurationLoadException(super.configurationPath);

				if (super.resourcesStream == null)
					super.resourcesStream = DumbHelper.getClassLoader().getResourceAsStream(super.configurationPath);

				configuration = super.build();
				ConfigurationMap.add(super.configurationPath, configuration);

				if (Files.exists(Paths.get(super.configurationPath))) {
					configuration.loadConfiguration();

					return configuration;
				}
				configuration.saveDefault();

			} catch (InvalidPathException | NullPointerException exception) {
				throw new InvalidConfigurationException(super.configurationPath);
			}
			return configuration;
		}
	}

	public static ConfigurationBuilder builder() {
		return new ModifiedConfigurationBuilder();
	}

	@Override
	public void reload() {
		this.getNodeMap().clear();

		this.loadConfiguration();
	}

	@Override
	public void saveDefault() {
		try {
			final Path filePath = Paths.get(this.getConfigurationPath());

			final Path parent = filePath.getParent();
			if (Objects.nonNull(parent)) Files.createDirectories(parent);

			Files.copy(resourcesStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			this.loadConfiguration();

		} catch (InvalidPathException | IOException exception) {
			exception.printStackTrace();

			throw new InvalidFilePathException();
		}
	}

	@Override
	public void loadConfiguration() {
		try {
			final InputStream stream = Files.newInputStream(Paths.get(this.getConfigurationPath()));
			final Iterable<Object> objects = super.getYaml().loadAll(stream);

			for (Object object : objects)
				this.setNodeMap(ConfigurationParser.parseConfiguration(object));

		} catch (IOException exception) {
			exception.printStackTrace();

			throw new InvalidFilePathException("The path contains invalid characters, or it is invalid for other file system reasons.");
		}
	}
}