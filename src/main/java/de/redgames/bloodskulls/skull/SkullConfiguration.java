package de.redgames.bloodskulls.skull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkullConfiguration {
    public static boolean enableMobs = true;
    public static boolean enablePlayers = true;
    public static boolean playerKillsOnly = true;
    public static boolean miningDrops = true;
    public static boolean overrideNatural = false;
    public static double dropRate = 0.05;
    public static double rateModifier = 0.05;
    public static HashMap<String, Double> customDropRates = new HashMap<>();
    public static HashMap<String, String> customSkins = new HashMap<>();
    public static List<String> ignoredNames = new ArrayList<>();
}
