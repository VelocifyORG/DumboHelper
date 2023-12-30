package dev.dumble.helper.config.impl;

import dev.dumble.helper.DumbHelper;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class ConfigurationHelper {

    public InputStream asResourcesStream(String path) {
        return DumbHelper.CLASS_LOADER.getResourceAsStream(path);
    }

    public URL asResources(String path) {
        return DumbHelper.CLASS_LOADER.getResource(path);
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

        final List<String> list = new ArrayList<>();
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".yml")) continue;

            list.add(file.getName());
        }
        return list;
    }
}
