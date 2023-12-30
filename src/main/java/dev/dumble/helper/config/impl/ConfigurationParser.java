package dev.dumble.helper.config.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationParser {

    public static Map<String, Node<Object>> parseConfiguration(Object object) {
        return ConfigurationParser.parseConfiguration(object, "", new HashMap<>());
    }

    public static Map<String, Node<Object>> parseConfiguration(Object object, String parent, Map<String, Node<Object>> nodeMap) {
        ConfigurationParser.processConfiguration(object, parent, nodeMap);
        return nodeMap;
    }

    private static void processConfiguration(Object object, String parent, Map<String, Node<Object>> nodeMap) {
        if (object instanceof Map) {
            ConfigurationParser.processMapConfiguration((Map<String, Object>) object, parent, nodeMap);
        } else if (object instanceof List) {
            ConfigurationParser.addNode(parent, object, nodeMap);
        } else {
            ConfigurationParser.addNode(parent, object, nodeMap);
        }
    }

    private static void processMapConfiguration(Map<String, Object> map, String parent, Map<String, Node<Object>> nodeMap) {
        map.forEach((key, value) -> ConfigurationParser.processConfiguration(value, parent.isEmpty() ? key : parent + "." + key, nodeMap));
    }

    private static void addNode(String path, Object value, Map<String, Node<Object>> nodeMap) {
        nodeMap.put(path, new Node<>(null, path, value));
    }
}
