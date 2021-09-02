package de.redgames.bloodskulls.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Base64;

public final class GameProfileUtil {
    // Prevent instantiation
    private GameProfileUtil() {}

    public static PlayerProfile getGameProfile(Player player) {
        return player.getPlayerProfile();
    }

    public static String getTextureURL(PlayerProfile profile) {
        try {
            for (ProfileProperty property : profile.getProperties()) {
                if (property.getName().equals("textures")) {
                    String value = new String(Base64.getDecoder().decode(property.getValue()));
                    return (String) ((JSONObject)((JSONObject)((JSONObject)new JSONParser().parse(value)).get("textures")).get("SKIN")).get("url");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
