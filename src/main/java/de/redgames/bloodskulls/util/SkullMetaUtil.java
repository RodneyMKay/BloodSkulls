package de.redgames.bloodskulls.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public final class SkullMetaUtil {
    // Prevent instantiation
    private SkullMetaUtil() {}

    public static SkullMeta addValue(SkullMeta skullMeta, String value, UUID uuid, String playername) {
        PlayerProfile profile = Bukkit.createProfile(uuid, playername);
        profile.getProperties().add(new ProfileProperty("textures", value));
        skullMeta.setPlayerProfile(profile);
        return skullMeta;
    }
}
