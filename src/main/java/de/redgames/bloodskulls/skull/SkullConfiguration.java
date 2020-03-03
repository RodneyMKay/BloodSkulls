package de.redgames.bloodskulls.skull;

import de.redgames.bloodskulls.config.ConfigValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkullConfiguration {
    @ConfigValue("general.needPermission")
    public boolean needPermission = true;

    @ConfigValue("general.enableMobs")
    public boolean enableMobs = true;

    @ConfigValue("general.enablePlayers")
    public boolean enablePlayers = true;

    @ConfigValue("general.playerKillsOnly")
    public boolean playerKillsOnly = true;

    @ConfigValue("general.overrideNatural")
    public boolean overrideNatural = false;

    @ConfigValue("rates.default")
    public double dropRate = 0.05;

    @ConfigValue("rates.lootingModifier")
    public double rateModifier = 0.05;

    //@ConfigValue("custom.dropRates")
    public Map<String, Double> customDropRates = new HashMap<>();

    //@ConfigValue("custom.customSkins")
    public Map<String, String> customSkins = new HashMap<>();

    //@ConfigValue("custom.ignoredNames")
    public List<String> ignoredNames = new ArrayList<>();
}
