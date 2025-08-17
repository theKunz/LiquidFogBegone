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
import de.maxhenkel.configbuilder.entry.ConfigEntry;

import com.liquidfogbegone.config.ModConfig;

@Mixin(InGameOverlayRenderer.class)
@Environment(value = EnvType.CLIENT)
public class InGameOverlayRendererMixin {
    @Inject(at = @At("HEAD"), method = "renderFireOverlay", cancellable = true)
    private static void removeFireOverlay(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        var userConfigs = ModConfig.GetUserConfigs();
        
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        
        if (player == null) {
            return;
        }
        
        boolean shouldRemoveLavaFireEffect = userConfigs.removeFireWhenInLava.get() && player.isInLava();
        boolean shouldRemoveFireEffect = userConfigs.removeFireWhenOnFire.get() && player.isOnFire();

        if (shouldRemoveFireEffect || shouldRemoveLavaFireEffect) {
            ci.cancel();
        }
    }
}