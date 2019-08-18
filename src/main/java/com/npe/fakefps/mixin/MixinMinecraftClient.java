package com.npe.fakefps.mixin;

import com.npe.fakefps.Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin({ MinecraftClient.class })
public class MixinMinecraftClient {

    @Shadow
    private static int currentFps;

    @Inject(method = {"render"}, at = {@At(value = "FIELD", target = "net/minecraft/client/MinecraftClient.currentFps:I")})
    private void render(CallbackInfo callbackInfo) {
        if(Mod.INSTANCE.config.isFPSLocked()){
            currentFps = Mod.INSTANCE.config.getTarget();
        }else{
            currentFps = new Random().nextInt(Mod.INSTANCE.config.getMax()) + Mod.INSTANCE.config.getMin();
        }
    }

}
