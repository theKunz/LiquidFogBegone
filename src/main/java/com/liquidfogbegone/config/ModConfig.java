package com.liquidfogbegone.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import com.liquidfogbegone.LiquidFogBegone;

@Config(name = LiquidFogBegone.MOD_ID)
@Environment(value = EnvType.CLIENT)
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public WaterConfig waterConfig = new WaterConfig();

    @ConfigEntry.Gui.CollapsibleObject
    public LavaConfig lavaConfig = new LavaConfig();

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
