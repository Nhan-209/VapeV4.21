package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureManager;
import gg.vape.wrapper.Wrapper;

public class TextureManager
extends Wrapper {

    public TextureManager(Object object) {
        super(object);
    }

    public void g(ResourceLocation resourceLocation) {
        if (ForgeVersion.MC_1_21_0.d()) {
            return;
        }
        MTextureManager.Y(TextureManager.vapeInstance.getMappingsMapperCompat().DW, this.I, resourceLocation.getObject());
    }

    public TextureObject G(ResourceLocation resourceLocation) {
        return new TextureObject(MTextureManager.a(TextureManager.vapeInstance.getMappingsMapperCompat().DW, this.I, resourceLocation.getObject()));
    }
}

