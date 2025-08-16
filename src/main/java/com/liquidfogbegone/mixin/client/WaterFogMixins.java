/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.liquidfogbegone.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.fog.WaterFogModifier;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.entity.Entity;
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

@Mixin(value = WaterFogModifier.class, priority = 1)
@Environment(EnvType.CLIENT)
public class WaterFogMixins {

    @Inject(method = "shouldApply", at = @At("RETURN"), cancellable = true)
    private static void overrideShouldApply(CameraSubmersionType submersionType, Entity cameraEntity, CallbackInfoReturnable<Boolean> cir) {
        WaterConfig waterConfig = ModConfig.get().waterConfig;
        WaterFogModifier waterMod = new WaterFogModifier();
        waterMod.shouldApply(submersionType, cameraEntity);
        // Something tells me that this check isn't necessary since we're
        // overriding a method in the "WaterFodModifier" class, but double
        // check anyway
        if (submersionType == CameraSubmersionType.WATER) {
            if (waterConfig.shouldCompletelySeeThroughWater) {
                cir.setReturnValue(false);
            }
        }
    }
}
