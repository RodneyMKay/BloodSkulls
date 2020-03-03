package de.redgames.bloodskulls.config;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Map;

public abstract class Config {
    private Path file;

    public Config(Path file) {
        this.file = file;
    }

    public final <T> T get(String path, Class<T> type) throws ConfigException {
        Object returnValue = get(path, (Type) type);
        return type.cast(returnValue);
    }

    public final <T> T require(String path, Class<T> type) throws ConfigException {
        Object returnValue = require(path, (Type) type);
        return type.cast(returnValue);
    }

    public final <T> void set(String path, T value, Class<T> type) throws ConfigException {
        set(path, value, (Type) type);
    }

    public final <T> T get(String path, TypeSpec<T> type) throws ConfigException {
        Object returnValue = get(path, type.getType());
        return type.getRepresentingClass().cast(returnValue);
    }

    public final <T> T require(String path, TypeSpec<T> type) throws ConfigException {
        Object returnValue = require(path, type.getType());
        return type.getRepresentingClass().cast(returnValue);
    }

    public final <T> void set(String path, T value, TypeSpec<T> type) throws ConfigException {
        set(path, value, type.getType());
    }

    @SuppressWarnings("unchecked")
    private <T> T get(String path, Type type) throws ConfigException {
        ConfigSerializer<T> serializer = ConfigSerializer.Storage.get(type);

        if (serializer != null) {
            return serializer.deserialize(this, path);
        } else {
            Object object = get(path);

            try {
                return (T) object;
            } catch (ClassCastException e) {
                throw new ConfigException(path + " in " + file + " is required to be " +
                        type.getTypeName() + " but is " + object.getClass().getSimpleName() + "!");
            }
        }
    }

    private <T> T require(String path, Type type) throws ConfigException {
        T result = get(path, type);

        if (result == null) {
            throw new ConfigException(path + " in " + file + " is required but cannot be found! (Type: " +
                    type.getTypeName() + ")");
        } else {
            return result;
        }
    }

    private <T> void set(String path, T value, Type type) throws ConfigException {
        ConfigSerializer<T> serializer = ConfigSerializer.Storage.get(type);

        if (serializer != null) {
            serializer.serialize(this, path, value);
        } else {
            try {
                set(path, value);
            } catch (Throwable e) {
                throw new ConfigException("Could not write a " + type.getTypeName() + " to " +
                        path + " in " + file + "! (Missing ConfigSerializer?)", e);
            }
        }
    }

    public final <T> void process(T object) throws ConfigException {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            ConfigValue settings = field.getAnnotation(ConfigValue.class);

            if (settings != null) {
                try {
                    Type type = field.getGenericType();

                    if (field.getType().isPrimitive()) {
                        type = TypeSpec.wrapPrimitive(field.getType());
                    }

                    Object value = get(settings.value(), type);

                    if (value == null) {
                        if (!settings.optional()) {
                            set(settings.value(), field.get(object), type);
                        }
                    } else {
                        field.set(object, value);
                    }
                } catch (IllegalAccessException e) {
                    throw new ConfigException("Could not access field annotated as ConfigValue!", e);
                }
            }
        }
    }

    protected abstract Object get(String path);

    protected abstract void set(String path, Object value);

    public abstract void save() throws ConfigException;

    public abstract Map<String, Object> toMap();

    public Path getFile() {
        return this.file;
    }
}
