package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ModelManager
extends Wrapper {
    public TextureAtlas q(ResourceLocation jx_22) {
        return new TextureAtlas(ModelManager.vapeInstance.getMappingsMapperCompat().CZ.getAtlas(this.I, jx_22.getObject()));
    }

    public ModelManager(Object object) {
        super(object);
    }
}

