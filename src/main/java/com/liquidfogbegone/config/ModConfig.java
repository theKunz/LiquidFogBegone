package com.liquidfogbegone.config;

import de.maxhenkel.configbuilder.entry.BooleanConfigEntry;
import de.maxhenkel.configbuilder.entry.ConfigEntry;
import de.maxhenkel.configbuilder.entry.DoubleConfigEntry;
import de.maxhenkel.configbuilder.entry.FloatConfigEntry;
import de.maxhenkel.configbuilder.entry.IntegerConfigEntry;
import de.maxhenkel.configbuilder.entry.StringConfigEntry;
import java.nio.file.Path;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

// This configuration implementation is heavily based on the implementation
// in Max Henkel's voice chat mod
public class ModConfig{
    private static UserConfig CONFIG;
    
    public static UserConfig GetUserConfigs() {
        if (CONFIG == null) {
          CONFIG = de.maxhenkel.configbuilder.ConfigBuilder.builder(UserConfig::new).path(Path.of(".").resolve("config").resolve("liquidfogbegone.config")).build();
        }
        
        return CONFIG;
    }
    
    public static Screen CreateConfigScreen(Screen parent) {
        GetUserConfigs();
        
        ConfigBuilder builder = ConfigBuilder
            .create()
            .setParentScreen(parent)
            .setTitle(MakeText("Liquid Fog Begone Settings"));
        
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
      
        ConfigCategory waterCategory = builder.getOrCreateCategory(MakeText("Water Fog Settings"));
        
        waterCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.waterEnabled));
        waterCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.waterCompleteSeeThrough));
        waterCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.waterSeeThroughFactor));
        
        ConfigCategory lavaCategory = builder.getOrCreateCategory(MakeText("Lava Fog Settings"));
        
        lavaCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.lavaEnabled));
        lavaCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.lavaCompleteSeeThrough));
        lavaCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.lavaSeeThroughFactor));
        lavaCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.removeFireWhenInLava));
        lavaCategory.addEntry(fromConfigEntry(entryBuilder, CONFIG.removeFireWhenOnFire));
        
        return builder.build();
    }
    
    private static MutableText MakeText(String text) {
        var txtContent = new TranslatableTextContent(text, text, new Object[0]);
        return MutableText.of(txtContent);
    }
    
    protected static <T> AbstractConfigListEntry<T> fromConfigEntry(ConfigEntryBuilder entryBuilder, ConfigEntry<T> entry) {
        var name = MakeText(entry.getComments()[0]);
        var description = MakeText(entry.getComments()[1]);

        switch (entry) {
            case DoubleConfigEntry e -> {
                return (AbstractConfigListEntry<T>) entryBuilder
                        .startDoubleField(name, e.get())
                        .setTooltip(description)
                        .setMin(e.getMin())
                        .setMax(e.getMax())
                        .setDefaultValue(e::getDefault)
                        .setSaveConsumer(d -> {
                            e.set(d);
                            e.save();
                        })
                        .build();
            }
            case FloatConfigEntry e -> {
                return (AbstractConfigListEntry<T>) entryBuilder
                        .startFloatField(name, e.get())
                        .setTooltip(description)
                        .setMin(e.getMin())
                        .setMax(e.getMax())
                        .setDefaultValue(e::getDefault)
                        .setSaveConsumer(d -> {
                            e.set(d);
                            e.save();
                        })
                        .build();
            }
            case IntegerConfigEntry e -> {
                return (AbstractConfigListEntry<T>) entryBuilder
                        .startIntField(name, e.get())
                        .setTooltip(description)
                        .setMin(e.getMin())
                        .setMax(e.getMax())
                        .setDefaultValue(e::getDefault)
                        .setSaveConsumer(d -> e.set(d).save())
                        .build();
            }
            case BooleanConfigEntry e -> {
                return (AbstractConfigListEntry<T>) entryBuilder
                        .startBooleanToggle(name, e.get())
                        .setTooltip(description)
                        .setDefaultValue(e::getDefault)
                        .setSaveConsumer(d -> e.set(d).save())
                        .build();
            }
            case StringConfigEntry e -> {
                return (AbstractConfigListEntry<T>) entryBuilder
                        .startStrField(name, e.get())
                        .setTooltip(description)
                        .setDefaultValue(e::getDefault)
                        .setSaveConsumer(d -> e.set(d).save())
                        .build();
            }
            default -> {
            }
        }

        throw new IllegalArgumentException("Unknown config entry type %s".formatted(entry.getClass().getName()));
    }
}
