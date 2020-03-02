package de.redgames.bloodskulls.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public final class SkullMetaUtil {
    // Prevent instantiation
    private SkullMetaUtil() {}

    public static SkullMeta addValue(SkullMeta skullMeta, String value, UUID uuid, String playername) {
        GameProfile gameProfile = new GameProfile(uuid, playername);
        PropertyMap propertyMap = gameProfile.getProperties();
        propertyMap.clear();
        propertyMap.put("textures", new Property("textures", value));
        ReflectionUtil.setValue(skullMeta, "profile", gameProfile);
        return skullMeta;
    }
}
