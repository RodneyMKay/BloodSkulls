package de.redgames.bloodskulls.util;

import com.mojang.authlib.GameProfile;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

public final class GameProfileUtil {
    // Prevent instantiation
    private GameProfileUtil() {}

    public static GameProfile getGameProfile(Player player) {
        try {
            return (GameProfile) player.getClass().getDeclaredMethod("getProfile").invoke(player);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Could not retrieve player GameProfile using Reflection", e);
        }
    }

    public static String getTextureURL(GameProfile profile) {
        try {
            String value = new String(Base64.getDecoder().decode(profile.getProperties().get("textures").iterator().next().getValue()));
            return (String) ((JSONObject)((JSONObject)((JSONObject)new JSONParser().parse(value)).get("textures")).get("SKIN")).get("url");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
