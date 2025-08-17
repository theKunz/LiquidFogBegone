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
        
        var userConfigs = ModConfig.GetUserConfigs();
        
        var currentFog = cir.getReturnValue();
        
        var opacity = currentFog.w;
        
        if (submersionType == CameraSubmersionType.WATER && userConfigs.waterEnabled.get()) {
            if (userConfigs.waterCompleteSeeThrough.get()) {
                opacity = 0.0F;
            }
            else {
                opacity = userConfigs.waterSeeThroughFactor.get() / 100.0F;
            }
        }
        else if (submersionType == CameraSubmersionType.LAVA && userConfigs.lavaEnabled.get()) {
            if (userConfigs.lavaCompleteSeeThrough.get()) {
                opacity = 0.0F;
            }
            else {
                opacity = userConfigs.lavaSeeThroughFactor.get() / 100.0F;
            }
        }

        cir.setReturnValue(new Vector4f(currentFog.x, currentFog.y, currentFog.z, opacity));
    }
}
