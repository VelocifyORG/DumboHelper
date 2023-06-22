package dev.dumble.helper.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum NodeType {
	STRING(String.class, String::valueOf),
	INTEGER(Integer.class, Integer::parseInt),

	DOUBLE(Double.class, Double::parseDouble),
	FLOAT(Float.class, Float::parseFloat),

	SHORT(Short.class, Short::parseShort),
	LONG(Long.class, Long::parseLong),

	BIG_INTEGER(BigInteger.class, BigInteger::new),
	BIG_DECIMAL(BigDecimal.class, BigDecimal::new),

	BOOLEAN(Boolean.class, Boolean::parseBoolean);

	private final Class<?> clazz;
	private final Function<String, Object> function;

	public static NodeType findEnumByClass(Class<?> clazz) {
		for (NodeType type : NodeType.values()) {
			if (!type.getClazz().equals(clazz)) continue;
			return type;
		}
		return STRING;
	}
}
