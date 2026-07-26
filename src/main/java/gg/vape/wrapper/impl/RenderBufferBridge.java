package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderBufferBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockStateBridge;
import gg.vape.wrapper.impl.BlockStateContainerBridge;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.TextureObjectHandle;
import java.util.Map;

public class RenderBufferBridge
extends Wrapper {
    public RenderBufferBridge(Object object) {
        super(object);
    }

    public TextureObjectHandle F() {
        if (ForgeVersion.MC_26_1.d()) {
            BlockStateContainerBridge blockStateContainerBridge = this.P();
            if (blockStateContainerBridge.isNull()) {
                return new TextureObjectHandle(null);
            }
            BlockStateBridge blockStateBridge = blockStateContainerBridge.C();
            return blockStateBridge.isNull() ? new TextureObjectHandle(null) : new TextureObjectHandle(blockStateBridge.d());
        }
        return new TextureObjectHandle(MRenderBufferBridge.P(RenderBufferBridge.c.getMappings().Ca, this.I));
    }

    public int L(int n) {
        return MRenderBufferBridge.J(RenderBufferBridge.c.getMappings().Ca, this.I, n);
    }

    public void P(Object object) {
        RenderBufferBridge.c.getMappings().Ca.o(this.I, object);
    }

    public BlockStateContainerBridge P() {
        if (ForgeVersion.MC_26_1.v()) {
            return new BlockStateContainerBridge(null);
        }
        return new BlockStateContainerBridge(MRenderBufferBridge.V(RenderBufferBridge.c.getMappings().Ca, this.I));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void j() {
        MRenderBufferBridge.v(RenderBufferBridge.c.getMappings().Ca, this.I);
    }

    public Map p() {
        return MRenderBufferBridge.P$src$Ljava_util_Map_$14yo47i(RenderBufferBridge.c.getMappings().Ca, this.I);
    }
}

