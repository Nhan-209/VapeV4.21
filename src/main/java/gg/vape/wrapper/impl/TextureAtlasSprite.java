package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTextureAtlasSprite;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceLocation;

public class TextureAtlasSprite
extends Wrapper {
    public ResourceLocation M() {
        return new ResourceLocation(MTextureAtlasSprite.F(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I));
    }

    public void g(float f) {
        MTextureAtlasSprite.d(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, f);
    }

    public void l(float f) {
        MTextureAtlasSprite.x(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, f);
    }

    public TextureAtlasSprite(Object object) {
        super(object);
    }

    public void o(int n) {
        MTextureAtlasSprite.r(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, n);
    }

    public void K(float f) {
        MTextureAtlasSprite.t(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, f);
    }

    public void X(float f) {
        MTextureAtlasSprite.I(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, f);
    }

    public float[] j() {
        return MTextureAtlasSprite.n(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I);
    }

    public Object e() {
        return MTextureAtlasSprite.L(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I);
    }

    public void b(int n) {
        MTextureAtlasSprite.I(TextureAtlasSprite.c.getMappingsMapperCompat().CO, this.I, n);
    }
}

