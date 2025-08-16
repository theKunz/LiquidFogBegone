package com.liquidfogbegone.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.liquidfogbegone.config.LavaConfig;
import com.liquidfogbegone.config.ModConfig;
import net.minecraft.client.render.RenderLayer;

@Mixin(InGameOverlayRenderer.class)
@Environment(value = EnvType.CLIENT)
public class InGameOverlayRendererMixin {
    @Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
    private static void removeFireOverlay(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        LavaConfig lavaConfig = ModConfig.get().lavaConfig;
        
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        
        if (player == null) {
            return;
        }
        
        boolean shouldRemoveLavaFireEffect = lavaConfig.removeFireScreenEffectInLava && player.isInLava();
        boolean shouldRemoveFireEffect = lavaConfig.removeFireScreenEffectIfOnFire && player.isOnFire();

        if (shouldRemoveFireEffect || shouldRemoveLavaFireEffect) {
            ci.cancel();
        }
    }
}