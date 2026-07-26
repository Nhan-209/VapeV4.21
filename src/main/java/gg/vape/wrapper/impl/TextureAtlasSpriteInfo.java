package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureAtlasSpriteInfoBridge;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;

public class TextureAtlasSpriteInfo
extends Wrapper {
    public TextureAtlasSpriteInfo(Object object) {
        super(object);
    }

    public ResourceLocation X() {
        return new ResourceLocation(MTextureAtlasSpriteInfoBridge.C(TextureAtlasSpriteInfo.c.getMappingsMapperCompat().Cz, this.I));
    }
}

