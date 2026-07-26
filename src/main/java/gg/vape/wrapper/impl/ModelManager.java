package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureAtlas;

public class ModelManager
extends Wrapper {
    public TextureAtlas q(ResourceLocation jx_22) {
        return new TextureAtlas(ModelManager.c.getMappingsMapperCompat().CZ.t(this.I, jx_22.getObject()));
    }

    public ModelManager(Object object) {
        super(object);
    }
}

