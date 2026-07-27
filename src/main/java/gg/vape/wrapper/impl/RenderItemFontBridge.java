package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderTypeBuffer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.WorldRenderer;

public class RenderItemFontBridge
extends Wrapper {

    public static RenderItemFontBridge V(WorldRenderer wg_12) {
        if (ForgeVersion.MC_1_21_0.d()) {
            return null;
        }
        return new RenderItemFontBridge(MRenderTypeBuffer.t(RenderItemFontBridge.c.getMappingsMapperCompat().c, wg_12.getObject()));
    }

    public void X() {
        MRenderTypeBuffer.Q(RenderItemFontBridge.c.getMappingsMapperCompat().c, this.I);
    }

    public RenderItemFontBridge(Object object) {
        super(object);
    }

    public void q() {
        MRenderTypeBuffer.n(RenderItemFontBridge.c.getMappingsMapperCompat().c, this.I);
    }
}

