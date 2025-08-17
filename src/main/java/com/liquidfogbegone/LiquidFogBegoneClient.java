package com.liquidfogbegone;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import com.liquidfogbegone.config.ModConfig;

@Environment(EnvType.CLIENT)
public class LiquidFogBegoneClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
            ModConfig.InitUserConfigs();
	}
}