/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.liquidfogbegone.mixin.client;

import com.liquidfogbegone.config.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogRenderer;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.world.ClientWorld;

@Mixin(FogRenderer.class)
@Environment(EnvType.CLIENT)
public class FogRendererMixin {
    @Inject(method = "getFogColor", at = @At(value = "RETURN"), cancellable = true)
    private void getFogColorMixin(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, boolean thick, CallbackInfoReturnable<Vector4f> cir) {
        var submersionType = camera.getSubmersionType();
        var waterConfig = ModConfig.get().waterConfig;
        var lavaConfig = ModConfig.get().lavaConfig;
        
        var opacity = 1.0F;
        
        if (submersionType == CameraSubmersionType.WATER) {
            if (waterConfig.shouldCompletelySeeThroughWater) {
                opacity = 0.0F;
            }
            else if (waterConfig.shouldOverrideWaterFogDensity) {
                opacity = ((float)(waterConfig.waterSeeThroughFactor)) / 100.0F;
            }
        }

        if (submersionType == CameraSubmersionType.LAVA) {
            if (lavaConfig.shouldCompletelySeeThroughLava) {
                opacity = 0.0F;
            }
            else if (lavaConfig.shouldOverrideLavaFogDensity) {
                opacity = ((float)(lavaConfig.lavaSeeThroughFactor)) / 100.0F;
            }
        }

        var currentFog = cir.getReturnValue();
        cir.setReturnValue(new Vector4f(currentFog.x, currentFog.y, currentFog.z, opacity));
    }
}
