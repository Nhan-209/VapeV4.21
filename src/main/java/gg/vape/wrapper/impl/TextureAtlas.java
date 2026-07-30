package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureAtlasSpriteInfo;
import gg.vape.wrapper.Wrapper;

public class TextureAtlas
extends Wrapper {
    public TextureAtlasSprite a(ResourceLocation resourceLocation) {
        return new TextureAtlasSprite(MTextureAtlasSpriteInfo.U(TextureAtlas.vapeInstance.getMappings().R6, this.I, resourceLocation.getObject()));
    }

    public TextureAtlas(Object object) {
        super(object);
    }

    public static ResourceLocation m$src$Lgg_vape_wrapper_impl_ResourceLocation_$4fmn0t() {
        return new ResourceLocation(MTextureAtlasSpriteInfo.Y(TextureAtlas.vapeInstance.getMappings().R6));
    }

    public ResourceLocation K() {
        return new ResourceLocation(MTextureAtlasSpriteInfo.x(TextureAtlas.vapeInstance.getMappings().R6, this.I));
    }
}

