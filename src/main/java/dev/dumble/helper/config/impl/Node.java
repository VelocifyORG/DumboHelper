package dev.dumble.helper.config.impl;

import dev.dumble.helper.config.AbstractConfigurationNode;
import dev.dumble.helper.exceptions.InvalidParseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@Getter(value = AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
public class Node<Value> extends AbstractConfigurationNode {

    public static final Node<Object> EMPTY_NODE = new Node<>(null, null, null);
    private Configuration configuration;
    private String path;
    private Value value;

    @Override
    public String getValue() {
        return this.value == null ? null : this.value.toString();
    }

    public String getSignature() {
        final String[] splitPath = this.getPath().split("\\.");

        return splitPath[splitPath.length - 1];
    }

    public <T> List<T> asList(Class<T> clazz) {
        try {
            if (value == null)
                throw new InvalidParseException();

            return value instanceof List ? (List<T>) value : Collections.emptyList();

        } catch (ClassCastException exception) {
            throw new InvalidParseException(exception);
        }
    }
}
