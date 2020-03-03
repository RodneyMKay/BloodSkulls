package de.redgames.bloodskulls.config.impl;

import de.redgames.bloodskulls.config.Config;
import de.redgames.bloodskulls.config.ConfigException;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class BukkitConfig extends Config {
    public static BukkitConfig load(Path path) throws ConfigException {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            FileConfiguration config = YamlConfiguration.loadConfiguration(path.toFile());
            return new BukkitConfig(path, config);
        } catch (IOException e) {
            throw new ConfigException("Could not load configuration!", e);
        }
    }

    private final FileConfiguration config;

    private BukkitConfig(Path path, FileConfiguration config) {
        super(path);
        this.config = config;
    }

    @Override
    protected Object get(String path) {
        return config.get(path);
    }

    @Override
    protected void set(String path, Object value) {
        config.set(path, value);
    }

    @Override
    public void save() throws ConfigException {
        try {
            config.save(getFile().toFile());
        } catch (IOException e) {
            throw new ConfigException("Could not save configuration!", e);
        }
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        toMap("", map, config);
        return map;
    }

    private void toMap(String prefix, Map<String, Object> map, Configuration path) {
        for (String key : path.getKeys(false)) {
            Object data = path.get(key);

            if (data instanceof Configuration) {
                toMap(prefix + key + ".", map, (Configuration) data);
            } else if (data != null) {
                map.put(prefix + key, data);
            }
        }
    }
}
