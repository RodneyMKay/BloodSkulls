package de.redgames.bloodskulls.craftbook;

import com.google.common.collect.Lists;
import com.sk89q.craftbook.AbstractCraftBookMechanic;
import com.sk89q.craftbook.CraftBookPlayer;
import com.sk89q.craftbook.bukkit.CraftBookPlugin;
import com.sk89q.craftbook.util.EventUtil;
import com.sk89q.util.yaml.YAMLProcessor;
import de.redgames.bloodskulls.skull.SkullConfiguration;
import de.redgames.bloodskulls.skull.SkullType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.List;

public class CraftbookHook extends AbstractCraftBookMechanic {
    @Override
    public boolean enable() {
        return true;
    }

    private boolean showNameClick;
    private List<String> ignoredNames;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(!EventUtil.passesFilter(event) || event.getHand() != EquipmentSlot.HAND) return;

        if(event.getAction() != Action.RIGHT_CLICK_BLOCK || !showNameClick) return;

        if(event.getClickedBlock().getType() == Material.PLAYER_HEAD || event.getClickedBlock().getType() == Material.PLAYER_WALL_HEAD) {
            Skull skull = (Skull)event.getClickedBlock().getState();
            if(skull == null || !skull.hasOwner())
                return;

            CraftBookPlayer player = CraftBookPlugin.inst().wrapPlayer(event.getPlayer());

            if(skull.getOwner() == null) return;

            System.out.println(skull.getOwningPlayer().getUniqueId().toString());
            if(skull.getOwningPlayer().getUniqueId() != null) {
                SkullType skullType = SkullType.getSkullTypeByUUID(skull.getOwningPlayer().getUniqueId().toString());
                if(skullType != SkullType.PLAYER && skullType != null) return;
            }

            if(SkullType.getSkullTypeByCraftbookName(skull.getOwner()) == null) {
                if(ignoredNames.contains(skull.getOwningPlayer().getName())) return;
                player.printRaw(ChatColor.YELLOW + player.translate("mech.headdrops.click-message") + ' ' + skull.getOwner());
            }
        }
    }

    // Code from the original CraftBook implementation
    @Override
    public void loadConfiguration(YAMLProcessor config, String path) {
        config.setComment(path + "drop-mob-heads", "Allow the Head Drops mechanic to drop mob heads.");
        SkullConfiguration.enableMobs = config.getBoolean(path + "drop-mob-heads", true);

        config.setComment(path + "drop-mob-heads", "Allow the Head Drops mechanic to drop mob heads.");
        SkullConfiguration.enablePlayers = config.getBoolean(path + "drop-mob-heads", true);

        config.setComment(path + "require-mob-killed", "Only drop heads when killed by a mob. Otherwise they will drop heads on any death.");
        SkullConfiguration.playerKillsOnly = config.getBoolean(path + "require-mob-killed", true);

        config.setComment(path + "drop-head-when-mined", "When enabled, heads keep their current skin when mined and are dropped accordingly.");
        SkullConfiguration.miningDrops = config.getBoolean(path + "drop-head-when-mined", true);

        config.setComment(path + "override-natural-head-drops", "Override natural head drops, this will cause natural head drops to use the chances provided by CraftBook. (Eg, Wither Skeleton Heads)");
        SkullConfiguration.overrideNatural = config.getBoolean(path + "override-natural-head-drops", false);

        config.setComment(path + "drop-rate", "A value between 1 and 0 which dictates the global chance of heads being dropped. This can be overridden per-entity type.");
        SkullConfiguration.dropRate = config.getDouble(path + "drop-rate", 0.05);

        config.setComment(path + "looting-rate-modifier", "This amount is added to the chance for every looting level on an item. Eg, a chance of 0.05(5%) and a looting mod of 0.05(5%) on a looting 3 sword, would give a 0.20 chance (20%).");
        SkullConfiguration.rateModifier = config.getDouble(path + "looting-rate-modifier", 0.05);

        config.setComment(path + "show-name-right-click", "When enabled, right clicking a placed head will say the owner of the head's skin.");
        showNameClick = config.getBoolean(path + "show-name-right-click", true);

        SkullConfiguration.customDropRates = new HashMap<>();
        if(config.getKeys(path + "drop-rates") != null) {
            for(String key : config.getKeys(path + "drop-rates"))
                SkullConfiguration.customDropRates.put(key.toUpperCase(), config.getDouble(path + "drop-rates." + key));
        } else
            config.addNode(path + "drop-rates");
        SkullConfiguration.customSkins = new HashMap<>();
        if(config.getKeys(path + "mob-mob-skins") != null) {
            for(String key : config.getKeys(path + "mob-mob-skins"))
                SkullConfiguration.customSkins.put(key.toUpperCase(), config.getString(path + "mob-mob-skins." + key));
        } else
            config.addNode(path + "mob-mob-skins");

        config.setComment(path + "ignored-names", "List of usernames to ignore when the head is touched.");
        SkullConfiguration.ignoredNames = ignoredNames = config.getStringList(path + "ignored-names", Lists.newArrayList("cscorelib"));
    }
}
