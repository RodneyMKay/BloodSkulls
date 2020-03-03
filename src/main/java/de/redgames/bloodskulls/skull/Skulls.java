package de.redgames.bloodskulls.skull;

import de.redgames.bloodskulls.util.GameProfileUtil;
import de.redgames.bloodskulls.util.SkullMetaUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public final class Skulls {
    // Prevent instantiation
    private Skulls() {}

    public static ItemStack getSkullItemMob(SkullType skullType, int amount) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        if (skullMeta != null) {
            skullMeta.setDisplayName(skullType.getDisplayName());
            SkullMetaUtil.addValue(skullMeta, skullType.getTextureValue(), skullType.getUUID(), skullType.getName());
            itemStack.setItemMeta(skullMeta);
        }
        return itemStack;
    }

    public static ItemStack getSkullItemPlayer(Player playerKilled, String playername, int amount) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        String textureValue = SkullType.PLAYER.getTextureValue(GameProfileUtil.getTextureURL(GameProfileUtil.getGameProfile(playerKilled)));
        SkullMetaUtil.addValue(skullMeta, textureValue, SkullType.PLAYER.getUUID(), playername);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public static ItemStack getFixedItem(ItemStack itemStack) {
        // Prepare data
        if (itemStack.getType() != Material.PLAYER_HEAD) return null;
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        if (skullMeta != null) {
            if (skullMeta.getOwningPlayer() == null) return null;
            String playername = skullMeta.getOwner();
            UUID uuid = skullMeta.getOwningPlayer().getUniqueId();


            SkullType skullType;

            // Craftbook fix
            if (playername != null) {
                skullType = SkullType.getSkullTypeByCraftbookName(playername);
                if (skullType != null) {
                    return getSkullItemMob(skullType, itemStack.getAmount());
                }
            }

            // Displayname fix
            skullType = SkullType.getSkullTypeByUUID(uuid.toString());
            if (skullType != null) {
                String displayName = skullMeta.getDisplayName() + "";
                if (!displayName.equals(skullType.getDisplayName() + "")) {
                    skullMeta.setDisplayName(skullType.getDisplayName());
                    itemStack.setItemMeta(skullMeta);
                    return itemStack;
                }
            }
        }

        // Return null if nothing is broke
        return null;
    }
}
