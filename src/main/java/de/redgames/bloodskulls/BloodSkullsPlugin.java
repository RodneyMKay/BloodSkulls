package de.redgames.bloodskulls;

import de.redgames.bloodskulls.config.Config;
import de.redgames.bloodskulls.config.ConfigException;
import de.redgames.bloodskulls.config.impl.BukkitConfig;
import de.redgames.bloodskulls.craftbook.CraftbookHooker;
import de.redgames.bloodskulls.skull.SkullConfiguration;
import de.redgames.bloodskulls.skull.SkullListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BloodSkullsPlugin extends JavaPlugin {
    public static Logger logger;
    public static Random random;

    private SkullConfiguration skullConfiguration;

    @Override
    public void onLoad() {
        logger = getLogger();
        random = new Random();
        skullConfiguration = new SkullConfiguration();
        try {
            Config config = BukkitConfig.load(getDataFolder().toPath().resolve("config.yml"));
            config.process(skullConfiguration);
            config.save();
        } catch (ConfigException e) {
            logger.log(Level.SEVERE, "Error reading config!", e);
            e.printStackTrace();
        }

        try {
            Class.forName("com.sk89q.craftbook.AbstractCraftBookMechanic");
            CraftbookHooker.hook();
            logger.info("Successfully hooked into CraftBook.");
        } catch (Exception e) {
            logger.warning("Craftbook not found! Continuing with own integration ...");
        }

        logger.info("Plugin loaded!");
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new SkullListener(skullConfiguration), this);

        logger.info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("Plugin disabled!");
    }
}
