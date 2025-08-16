package com.liquidfogbegone.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.Camera;
import net.minecraft.block.enums.CameraSubmersionType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.liquidfogbegone.LiquidFogBegone;
import com.liquidfogbegone.config.LavaConfig;
import com.liquidfogbegone.config.WaterConfig;
import com.liquidfogbegone.config.ModConfig;

/**
 * Note: Found these mixins aren't invoked if Sodium Extra mod is installed.
 * 
 * Set priority to 1001 to ensure these mixins apply after other mods.
 */
//@Mixin(value = BackgroundRenderer.class, priority = 1)
@Environment(EnvType.CLIENT)
public class BackgroundRendererMixin {
    
    // Override fog at the TAIL to avoid conflicts with other mods such as Origins that did redirection which I used to do
    /*
    @Inject(method = "applyFog", at = @At(value = "HEAD"))
    private static void changeForInLava(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable ci) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        WaterConfig waterConfig = ModConfig.get().waterConfig;
        LavaConfig lavaConfig = ModConfig.get().lavaConfig;

        // Default
        if (cameraSubmersionType == CameraSubmersionType.WATER) {
            if (waterConfig.shouldCompletelySeeThroughWater) {
                color.w = 0.0F;
            }
            else if (waterConfig.shouldOverrideWaterFogDensity) {
                color.w = ((float)(waterConfig.waterSeeThroughFactor)) / 100.0F;
            }
        }

        if (cameraSubmersionType == CameraSubmersionType.LAVA) {
            if (lavaConfig.shouldCompletelySeeThroughLava) {
                color.w = 0.0F;
            }
            else if (lavaConfig.shouldOverrideLavaFogDensity) {
                color.w = ((float)(lavaConfig.lavaSeeThroughFactor)) / 100.0F;
            }
        }
    }
    */
}
