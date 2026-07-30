package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PlayerSkin
extends Wrapper {

    public TextureAtlasSpriteInfo c() {
        return new TextureAtlasSpriteInfo(PlayerSkin.vapeInstance.getMappingsMapperCompat().hU.S(this.I));
    }

    public PlayerSkin(Object object) {
        super(object);
    }

    public ResourceLocation W() {
        if (ForgeVersion.MC_1_21_10.d()) {
            return this.c().getTexturePath();
        }
        return new ResourceLocation(PlayerSkin.vapeInstance.getMappingsMapperCompat().hU.o(this.I));
    }
}

