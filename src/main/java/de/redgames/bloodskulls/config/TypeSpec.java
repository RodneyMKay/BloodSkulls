package de.redgames.bloodskulls.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public abstract class TypeSpec<T> {
    private Type type;
    private Class<T> tClass;

    @SuppressWarnings("unchecked")
    public TypeSpec() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.type = type.getActualTypeArguments()[0];
        this.tClass = (Class<T>) getClass(this.type);
    }

    private TypeSpec(Type type, Class<T> tClass) {
        this.type = type;
        this.tClass = tClass;
    }

    public final Type getType() {
        return type;
    }

    public final Class<T> getRepresentingClass() {
        return tClass;
    }

    public static Class<?> getClass(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        } else {
            throw new UnsupportedOperationException("Unspecified instance of Type: " +
                    type.getClass().getCanonicalName());
        }
    }

    public static <T> TypeSpec<T> forClass(Class<T> tClass) {
        return new TypeSpec<T>(tClass.getGenericSuperclass(), tClass) {};
    }

    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<>();

    static {
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
    }

    public static Class<?> wrapPrimitive(Type type) {
        Class<?> typeClass = getClass(type);
        Class<?> wrapperClass = primitiveWrapperMap.get(typeClass);
        return wrapperClass == null ? typeClass : wrapperClass;
    }
}
