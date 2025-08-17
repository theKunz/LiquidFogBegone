package com.liquidfogbegone.config;

import de.maxhenkel.configbuilder.ConfigBuilder;
import de.maxhenkel.configbuilder.entry.ConfigEntry;

public class UserConfig {
    // Water Configs
    public ConfigEntry<Boolean> waterEnabled;
    public ConfigEntry<Boolean> waterCompleteSeeThrough;
    public ConfigEntry<Float> waterSeeThroughFactor;
    
    // Lava Configs
    public ConfigEntry<Boolean> lavaEnabled;
    public ConfigEntry<Boolean> lavaCompleteSeeThrough;
    public ConfigEntry<Float> lavaSeeThroughFactor;
    public ConfigEntry<Boolean> removeFireWhenInLava;
    public ConfigEntry<Boolean> removeFireWhenOnFire;
    
    public UserConfig(ConfigBuilder builder) {
        // Water Configs
        waterEnabled = builder.booleanEntry(
            "waterEnabled",
            true,
            "Enabled",
            "");
        
        waterCompleteSeeThrough = builder.booleanEntry(
            "waterCompleteSeeThrough",
            false, 
            "See completely through",
            "True if water should have no fog at all. False to control fog density directly [default = false]");
        
        waterSeeThroughFactor = builder.floatEntry(
            "waterSeeThroughFactor",
            100.0f, // default
            0.0f,  // min
            100.0f, // max
            "% see through factor", 
            "% opacity for water fog. The lower the value, the lower the opacity");
        
        // Lava Configs
        lavaEnabled = builder.booleanEntry(
            "lavaEnabled",
            true, 
            "Enabled", 
            "");
        
        lavaCompleteSeeThrough = builder.booleanEntry(
            "lavaCompleteSeeThrough",
            false, 
            "See completely through",
            "True if lava should have no fog at all. False to control fog density directly [default = false]");
        
        lavaSeeThroughFactor = builder.floatEntry(
            "lavaSeeThroughFactor",
            100.0f, // default
            0.0f,  // min
            100.0f, // max
            "% see through factor", 
            "% opacity for lava fog. The lower the value, the lower the opacity");
        
        removeFireWhenInLava = builder.booleanEntry(
            "removeFireWhenInLava",
            false, 
            "Remove fire effect when in lava", 
            "True to remove the fire particle screen effect when IN LAVA ONLY [default = false]");
        
        removeFireWhenOnFire = builder.booleanEntry(
            "removeFireWhenOnFire",
            false, 
            "Remove fire effect when on fire", 
            "True to remove the fire particle screen effect when on fire (including in lava) [default = false]");
    }
}
