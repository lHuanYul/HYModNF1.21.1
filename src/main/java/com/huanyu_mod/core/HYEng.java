package com.huanyu_mod.core;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collection;

public interface HYEng {
    String MOD_ID = "huanyu_mod";
    String MOD_NAME = "HuanYu Mod";
    String MOD_AUTHER = "HuanYu_";
    Logger LOGGER = LogUtils.getLogger();

    static HYEng instance() {
        return HYEngBase.INSTANCE;
    }

    static String getCurrentClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName()
                .substring(Thread.currentThread().getStackTrace()[2].getClassName().lastIndexOf('.') + 1);
    }

    static void sysOut(String name, String string) {
        System.out.println(name + " O: " + string);
    }

    static ResourceLocation makeRL(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    Collection<ServerPlayer> getPlayers();

    @Nullable
    Level getClientLevel();

    @Nullable
    MinecraftServer getServer();


}
