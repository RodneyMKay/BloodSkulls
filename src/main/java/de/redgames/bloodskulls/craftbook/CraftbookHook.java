package de.redgames.bloodskulls.craftbook;

import com.sk89q.craftbook.AbstractCraftBookMechanic;
import com.sk89q.craftbook.CraftBookPlayer;
import com.sk89q.craftbook.bukkit.CraftBookPlugin;
import com.sk89q.craftbook.util.EventUtil;
import com.sk89q.util.yaml.YAMLProcessor;
import de.redgames.bloodskulls.skull.SkullType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;

public class CraftbookHook extends AbstractCraftBookMechanic {

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public void loadConfiguration(YAMLProcessor yamlProcessor, String s) {}

    private boolean showNameClick;
    private List<String> ignoredNames;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!EventUtil.passesFilter(event) || event.getHand() != EquipmentSlot.HAND) return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || !showNameClick || event.getClickedBlock() == null) return;

        if (event.getClickedBlock().getType() == Material.PLAYER_HEAD || event.getClickedBlock().getType() == Material.PLAYER_WALL_HEAD) {
            Skull skull = (Skull) event.getClickedBlock().getState();
            if (!skull.hasOwner())
                return;

            CraftBookPlayer player = CraftBookPlugin.inst().wrapPlayer(event.getPlayer());

            if (skull.getOwner() == null || skull.getOwningPlayer() == null) return;

            SkullType skullType = SkullType.getSkullTypeByUUID(skull.getOwningPlayer().getUniqueId().toString());
            if (skullType != SkullType.PLAYER && skullType != null) return;


            if (SkullType.getSkullTypeByCraftbookName(skull.getOwner()) == null) {
                if (ignoredNames.contains(skull.getOwningPlayer().getName())) return;
                player.printRaw(ChatColor.YELLOW + player.translate("mech.headdrops.click-message") + ' ' + skull.getOwner());
            }
        }
    }
}
