package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Framebuffer;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.ShaderGroupState;
import gg.vape.wrapper.impl.TextureManager;
import java.util.List;

public class ShaderGroup
extends Wrapper {
    public ShaderGroup(Object object) {
        super(object);
    }

    public void resize(int n, int n2) {
        ShaderGroup.c.getMappingsMapperCompat().Qv.G(this.I, n, n2);
    }

    public static ShaderGroup create(TextureManager textureManager, ShaderGroupState shaderGroupState, Framebuffer framebuffer, ResourceLocation resourceLocation) {
        return new ShaderGroup(ShaderGroup.c.getMappingsMapperCompat().Qv.k(textureManager.getObject(), shaderGroupState.getObject(), framebuffer.getObject(), resourceLocation.getObject()));
    }

    public List getFramebuffers() {
        return ShaderGroup.c.getMappingsMapperCompat().Qv.h(this.I);
    }
}

