package dev.dumble.helper.helper;

import dev.dumble.helper.exceptions.AnnotationNotRegisteredException;
import lombok.experimental.UtilityClass;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class AnnotationHelper {

	private final Reflections reflections = new Reflections(ClasspathHelper.forClassLoader());
	private final Map<Class<?>, Set<Class<?>>> CLASS_MAP = new ConcurrentHashMap<>();

	@SafeVarargs
	public void registerAnnotations(Class<? extends Annotation>... annotations) {
		for (Class<? extends Annotation> clazz : annotations) {

			final Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(clazz);
			if (annotatedClasses.isEmpty()) continue;

			CLASS_MAP.putIfAbsent(clazz, annotatedClasses);
		}
	}

	public Set<Class<?>> getAnnotatedClasses(Class<? extends Annotation> annotation) {
		return Optional
				.ofNullable(CLASS_MAP.get(annotation))
				.orElseThrow(() -> new AnnotationNotRegisteredException(annotation.getName()));
	}
}
