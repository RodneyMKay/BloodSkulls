package de.redgames.bloodskulls.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public interface ConfigSerializer<T> {
    void serialize(Config config, String path, T object) throws ConfigException;

    T deserialize(Config config, String path) throws ConfigException;

    final class Storage {
        private static final Map<Type, ConfigSerializer<?>> configMap = new HashMap<>();

        @SuppressWarnings("unchecked")
        public static <T> ConfigSerializer<T> get(Type type) {
            return (ConfigSerializer<T>) Storage.configMap.get(type);
        }

        public static void register(ConfigSerializer<?> configSerializer) {
            Type[] interfaces = configSerializer.getClass().getGenericInterfaces();

            for (Type anInterface : interfaces) {
                if (anInterface instanceof ParameterizedType) {
                    ParameterizedType type = (ParameterizedType) anInterface;

                    if (ConfigSerializer.class.equals(type.getRawType())) {
                        configMap.put(type.getActualTypeArguments()[0], configSerializer);
                    }
                }
            }
        }
    }
}
