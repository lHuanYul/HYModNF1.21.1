package com.huanyu_mod.core;

import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = HYEng.MOD_ID, dist = Dist.DEDICATED_SERVER)
public class HYEngServer extends HYEngBase{
    public HYEngServer(IEventBus modEventBus, ModContainer modContainer) {
        super(modEventBus, modContainer);
    }
    @Override
    public Level getClientLevel() {
        return null;
    }
}
