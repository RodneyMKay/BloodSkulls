package de.redgames.bloodskulls.util;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;

public final class LanguageUtil {
    private static Object localeLanguage;
    private static Method translationMethod;

    // Prevent instantiation
    private LanguageUtil() {}

    private static void init() throws Exception {
        String namespace = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        Class<?> localeLanguageClass = Class.forName("net.minecraft.server." + namespace + ".LocaleLanguage");
        localeLanguage = localeLanguageClass.getDeclaredMethod("a").invoke(null);
        translationMethod = localeLanguageClass.getDeclaredMethod("a", String.class);
    }

    public static String getTranslation(String s) {
        try {
            if (translationMethod == null) init();
            Object translation = translationMethod.invoke(localeLanguage, s);
            return (String) translation;
        } catch (Throwable e) {
            throw new RuntimeException("Reflection for entity name failed!", e);
        }
    }
}
