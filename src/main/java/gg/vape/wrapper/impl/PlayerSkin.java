package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureAtlasSpriteInfo;

public class PlayerSkin
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public TextureAtlasSpriteInfo c() {
        return new TextureAtlasSpriteInfo(PlayerSkin.c.getMappingsMapperCompat().hU.S(this.I));
    }

    public PlayerSkin(Object object) {
        super(object);
    }

    public ResourceLocation W() {
        if (ForgeVersion.MC_1_21_10.d()) {
            return this.c().X();
        }
        return new ResourceLocation(PlayerSkin.c.getMappingsMapperCompat().hU.o(this.I));
    }
}

