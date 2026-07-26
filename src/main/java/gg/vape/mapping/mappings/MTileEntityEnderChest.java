package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MItemStack;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTileEntityEnderChest
extends Mapping {
    private final MappingField P;
    private final MappingField S;

    private float j(Object object) {
        return this.S.getFloat(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private int w(Object object) {
        return this.P.getInt(object);
    }

    public static int b(MTileEntityEnderChest mTileEntityEnderChest, Object object) {
        return mTileEntityEnderChest.w(object);
    }

    public MTileEntityEnderChest() {
        this(MItemStack.f());
    }

    private MTileEntityEnderChest(int n) {
        super(MappedClasses.DL);
        if (n != 0) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "value";
                MTileEntityEnderChest mTileEntityEnderChest = this;
                this.P = mTileEntityEnderChest.J(string, bl, clazz);
                Class<Float> clazz2 = Float.TYPE;
                boolean bl2 = true;
                String string2 = "saturation";
                MTileEntityEnderChest mTileEntityEnderChest2 = this;
                this.S = this.J(string2, bl2, clazz2);
            } else {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "healAmount";
                MTileEntityEnderChest mTileEntityEnderChest = this;
                this.P = mTileEntityEnderChest.J(string, bl, clazz);
                Class<Float> clazz3 = Float.TYPE;
                boolean bl3 = true;
                String string3 = "saturationModifier";
                MTileEntityEnderChest mTileEntityEnderChest3 = this;
                this.S = this.J(string3, bl3, clazz3);
            }
            return;
        }
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "saturationModifier";
        MTileEntityEnderChest mTileEntityEnderChest = this;
        this.S = mTileEntityEnderChest.J(string, bl, clazz);
        this.P = null;
    }

    public static float G(MTileEntityEnderChest mTileEntityEnderChest, Object object) {
        return mTileEntityEnderChest.j(object);
    }
}

