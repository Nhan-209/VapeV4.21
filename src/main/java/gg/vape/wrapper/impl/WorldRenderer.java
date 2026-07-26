package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MWorldRenderer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class WorldRenderer
extends Wrapper {
    public WorldRenderer(Object object) {
        super(object);
    }

    public void Q(boolean bl) {
        MWorldRenderer.X(WorldRenderer.c.getMappings().qZ, this.I, bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public IntBuffer O() {
        if (ForgeVersion.MC_1_21_0.d()) {
            Vape.notifyNativeStackTrace();
            return null;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            ByteBuffer byteBuffer = (ByteBuffer)MWorldRenderer.t(WorldRenderer.c.getMappings().qZ, this.I);
            return byteBuffer.asIntBuffer();
        }
        return (IntBuffer)MWorldRenderer.v(WorldRenderer.c.getMappings().qZ, this.I);
    }

    public int o(int n) {
        if (ForgeVersion.MC_1_16_5.d()) {
            Vape.notifyNativeStackTrace();
            return -1;
        }
        return MWorldRenderer.G(WorldRenderer.c.getMappings().qZ, this.I, n);
    }
}

