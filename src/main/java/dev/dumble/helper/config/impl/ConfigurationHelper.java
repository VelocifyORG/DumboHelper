package dev.dumble.helper.config.impl;

import dev.dumble.helper.DumbHelper;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ConfigurationHelper {

	public InputStream asResourcesStream(String path) {
		return DumbHelper.getClassLoader().getResourceAsStream(path);
	}

	public URL asResources(String path) {
		return DumbHelper.getClassLoader().getResource(path);
	}

	public List<String> getFiles(String path) {
		final File folder = new File(path);
		final List<String> foldersFiles = ConfigurationHelper.getFoldersFiles(folder);

		if (foldersFiles.isEmpty()) {
			final URL resourceUrl = ConfigurationHelper.asResources(path);
			if (resourceUrl == null) return Collections.emptyList();

			return ConfigurationHelper.getFiles(resourceUrl.getFile());
		}
		return foldersFiles;
	}

	private List<String> getFoldersFiles(File folder) {
		final File[] files = folder.listFiles();
		if (files == null) return Collections.emptyList();

		return Arrays.stream(files)
				.filter(file -> file.isFile() && file.getName().endsWith(".yml"))
				.map(File::getName)
				.collect(Collectors.toList());
	}
}
