package de.redgames.bloodskulls.craftbook;

import com.sk89q.craftbook.bukkit.CraftBookPlugin;

public class CraftbookHooker {
    public static void hook() {
        CraftBookPlugin.availableMechanics.replace("HeadDrops", CraftbookHook.class);
    }
}
