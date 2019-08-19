package com.npe.fakefps;

import com.npe.fakefps.gson.Config;
import com.npe.fakefps.gson.ConfigLoader;
import com.npe.fakefps.gui.FPSMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.impl.registry.FabricRegistryClientInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Mod implements ModInitializer {

    public static Mod INSTANCE;
    public Config config;

    public FabricKeyBinding key;

    @Override
    public void onInitialize() {
        key = FabricKeyBinding.Builder.create(
                new Identifier("fakefps", "openmenu"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                "FakeFPS"
        ).build();
        KeyBindingRegistry.INSTANCE.register(key);

        ClientTickCallback.EVENT.register(e -> {
            if(key.wasPressed()){
                MinecraftClient.getInstance().openScreen(new FPSMenu(MinecraftClient.getInstance().currentScreen, config.isFPSLocked()));
            }
        });

        config = ConfigLoader.load();
        INSTANCE = this;
    }
}
