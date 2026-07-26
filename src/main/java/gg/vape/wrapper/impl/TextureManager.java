package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.TextureObject;

public class TextureManager
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public TextureManager(Object object) {
        super(object);
    }

    public void g(ResourceLocation resourceLocation) {
        if (ForgeVersion.MC_1_21_0.d()) {
            return;
        }
        MTextureManager.Y(TextureManager.c.getMappingsMapperCompat().DW, this.I, resourceLocation.getObject());
    }

    public TextureObject G(ResourceLocation resourceLocation) {
        return new TextureObject(MTextureManager.a(TextureManager.c.getMappingsMapperCompat().DW, this.I, resourceLocation.getObject()));
    }
}

