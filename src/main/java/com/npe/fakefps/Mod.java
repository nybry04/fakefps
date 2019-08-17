package com.npe.fakefps;

import com.npe.fakefps.gson.Config;
import com.npe.fakefps.gson.ConfigLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

@Environment(EnvType.CLIENT)
public class Mod implements ModInitializer {

    public static Mod INSTANCE;

    public Config config;

    @Override
    public void onInitialize() {
        config = ConfigLoader.load();

        INSTANCE = this;
    }
}
