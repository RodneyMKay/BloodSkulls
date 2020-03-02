package de.redgames.bloodskulls.util;

import java.lang.reflect.Field;

public final class ReflectionUtil {
    // Prevent instantiation
    private ReflectionUtil() {}

    public static void setValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
    }
}
