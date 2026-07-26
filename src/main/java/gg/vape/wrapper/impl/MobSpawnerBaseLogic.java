package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MMobSpawnerBaseLogic;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class MobSpawnerBaseLogic
extends Wrapper {
    public MobSpawnerBaseLogic(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String Q() {
        if (ForgeVersion.MC_1_12_2.d()) {
            Entity entity = new Entity(MMobSpawnerBaseLogic.getCachedEntity(MobSpawnerBaseLogic.c.getMappings().DA, this.I, ForgeVersion.MC_1_17.d() ? Minecraft.theWorld().getObject() : null));
            if (entity.isNotNull()) {
                return entity.getName();
            }
            return "";
        }
        return MMobSpawnerBaseLogic.getEntityNameToSpawn(MobSpawnerBaseLogic.c.getMappings().DA, this.I);
    }
}

