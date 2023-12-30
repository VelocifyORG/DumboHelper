package dev.dumble.helper.config;

import dev.dumble.helper.config.impl.Node;
import lombok.AccessLevel;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter(value = AccessLevel.PROTECTED)
public abstract class AbstractConfiguration {

    protected final Yaml yaml = new Yaml();


    public abstract Map<String, Node<Object>> getNodeMap();

    protected abstract void reload();

    protected abstract void loadConfiguration();

    protected abstract void saveDefault();


    public <V> V getOrDefault(String path, V value, Class<V> clazz) {
        final Node<V> node = (Node<V>) this.getNode(path);
        final V result = (node != null) ? node.as(clazz) : null;

        return (result != null) ? result : value;
    }

    public <V> V get(String path, Class<V> clazz) {
        return this.getOrDefault(path, null, clazz);
    }

    public <V> List<V> getAsList(String path, Class<V> clazz) {
        return this.getNodeNotNull(path).asList(clazz);
    }

    public <V> HashMap<String, V> getAsMap(String path, Class<V> clazz) {
        final HashMap<String, V> map = new HashMap<>();

        this.getNodeMap().forEach((key, value) -> {
            if (!key.startsWith(path)) return;

            final String[] splitPath = path.split("\\.");
            final String[] splitKey = key.split("\\.");

            map.put(splitKey[splitPath.length], value.as(clazz));
        });
        return map;
    }

    public <V> Optional<V> getOrDefaultOptional(String path, V value, Class<V> clazz) {
        return Optional.ofNullable(this.getOrDefault(path, value, clazz));
    }


    public String getOrDefault(String path, String defaultValue) {
        final Node<Object> node = this.getNode(path);

        return node == null ? defaultValue : node.getValue();
    }

    public String get(String path) {
        return this.getOrDefault(path, null);
    }

    public List<String> getAsList(String path) {
        return this.getAsList(path, String.class);
    }

    public HashMap<String, String> getAsMap(String path) {
        return this.getAsMap(path, String.class);
    }


    public Node<Object> getNode(String path) {
        return this.getNodeMap().getOrDefault(path, null);
    }

    public Node<Object> getNodeNotNull(String path) {
        return Optional.ofNullable(this.getNodeMap().get(path)).orElse(Node.EMPTY_NODE);
    }


    public Optional<Node<Object>> getNodeOptional(String path) {
        return Optional.ofNullable(this.getNodeMap().get(path));
    }

    public Optional<String> getOrDefaultOptional(String path, String defaultValue) {
        return Optional.ofNullable(this.getOrDefault(path, defaultValue));
    }

    public Optional<String> getOptional(String path) {
        return Optional.ofNullable(this.get(path));
    }
}
