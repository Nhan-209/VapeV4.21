package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.TileEntity;

public class TileEntityChest
extends TileEntity {
    public float D() {
        if (ForgeVersion.MC_1_17.d()) {
            return TileEntityChest.c.getMappingsMapperCompat().hI.t(this.getObject(), 0.0f);
        }
        return TileEntityChest.c.getMappingsMapperCompat().hI.p(this.getObject());
    }

    public TileEntityChest(Object object) {
        super(object);
    }

    public float b() {
        return TileEntityChest.c.getMappingsMapperCompat().hI.b(this.getObject());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

