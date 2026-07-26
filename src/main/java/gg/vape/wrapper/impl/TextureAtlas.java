package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureAtlasSpriteInfo;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureAtlasSprite;

public class TextureAtlas
extends Wrapper {
    public TextureAtlasSprite a(ResourceLocation resourceLocation) {
        return new TextureAtlasSprite(MTextureAtlasSpriteInfo.U(TextureAtlas.c.getMappings().R6, this.I, resourceLocation.getObject()));
    }

    public TextureAtlas(Object object) {
        super(object);
    }

    public static ResourceLocation m$src$Lgg_vape_wrapper_impl_ResourceLocation_$4fmn0t() {
        return new ResourceLocation(MTextureAtlasSpriteInfo.Y(TextureAtlas.c.getMappings().R6));
    }

    public ResourceLocation K() {
        return new ResourceLocation(MTextureAtlasSpriteInfo.x(TextureAtlas.c.getMappings().R6, this.I));
    }
}

