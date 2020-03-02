package de.redgames.bloodskulls;

import de.redgames.bloodskulls.craftbook.CraftbookHooker;
import de.redgames.bloodskulls.skull.SkullListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.logging.Logger;

public final class BloodSkullsPlugin extends JavaPlugin {
    public static Logger logger;
    public static Random random;

    @Override
    public void onLoad() {
        logger = getLogger();
        random = new Random();

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
        Bukkit.getPluginManager().registerEvents(new SkullListener(), this);

        logger.info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("Plugin disabled!");
    }
}
