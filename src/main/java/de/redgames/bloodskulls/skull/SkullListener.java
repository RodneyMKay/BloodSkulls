package de.redgames.bloodskulls.skull;

import de.redgames.bloodskulls.BloodSkullsPlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class SkullListener implements Listener {
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        ItemStack fixedItem = Skulls.getFixedItem(event.getEntity().getItemStack());
        if(fixedItem != null) event.getEntity().setItemStack(fixedItem);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // Filter event
        if(SkullConfiguration.playerKillsOnly && event.getEntity().getKiller() == null) return;
        if(event.getEntityType() == null) return;
        if(event.getEntity().getKiller() != null && !event.getEntity().getKiller().hasPermission("craftbook.mech.headdrops.kill")) return;

        // Check if SkullType is customized
        SkullType skullType = SkullType.getSkullTypeByEntityType(event.getEntityType());
        if(skullType == null) return;

        // Calculate drop chance
        double chance = Math.min(1, SkullConfiguration.dropRate);
        if(SkullConfiguration.customDropRates.containsKey(skullType.name()))
            chance = Math.min(1, SkullConfiguration.customDropRates.get(skullType.name()));
        ItemStack item = event.getEntity().getKiller().getItemInHand();
        if(event.getEntity().getKiller() != null && item != null && item.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))
            chance = Math.min(1, chance + SkullConfiguration.rateModifier * item.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS));
        if(BloodSkullsPlugin.random.nextDouble() > chance)
            return;

        // Drop skull
        ItemStack toDrop;
        switch(event.getEntityType()) {
            case PLAYER:
                if(!SkullConfiguration.enablePlayers) return;
                String playername = event.getEntity().getName();
                if(SkullConfiguration.ignoredNames.contains(playername)) return;
                toDrop = Skulls.getSkullItemPlayer((Player) event.getEntity(), playername, 1);
                break;

            case ZOMBIE:
                if(!SkullConfiguration.enableMobs) return;
                toDrop = new ItemStack(Material.ZOMBIE_HEAD, 1);
                break;

            case CREEPER:
                if(!SkullConfiguration.enableMobs) return;
                toDrop = new ItemStack(Material.CREEPER_HEAD, 1);
                break;

            case SKELETON:
                if(!SkullConfiguration.enableMobs) return;
                toDrop = new ItemStack(Material.SKELETON_SKULL, 1);
                break;

            case WITHER_SKELETON:
                if (!SkullConfiguration.enableMobs || !SkullConfiguration.overrideNatural) return;
                toDrop = new ItemStack(Material.WITHER_SKELETON_SKULL, 1);
                break;

            case ENDER_DRAGON:
                if(!SkullConfiguration.enableMobs) return;
                toDrop = new ItemStack(Material.DRAGON_HEAD, 1);
                break;

            default:
                if(!SkullConfiguration.enableMobs) return;
                toDrop = Skulls.getSkullItemMob(skullType, 1);
                break;
        }
        event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), toDrop);
    }
}
