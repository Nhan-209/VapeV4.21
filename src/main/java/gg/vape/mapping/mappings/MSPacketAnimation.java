package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketAnimation
extends Mapping {
    private MappingField L;
    private MappingField H;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int h(Object object) {
        return this.H.getInt(object);
    }

    public MSPacketAnimation() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketAnimation(int[] nArray) {
        super(MappedClasses.ZQ);
        if (nArray != null) {
            if (ForgeVersion.MC_1_7_10.L() && !Wrapper.c.isVanillaMinecraftPresent()) {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = Wrapper.G;
                String string = "field_148981_a";
                MSPacketAnimation mSPacketAnimation = this;
                this.H = mSPacketAnimation.J(string, bl, clazz);
                Class<Integer> clazz2 = Integer.TYPE;
                boolean bl2 = Wrapper.G;
                String string2 = "field_148980_b";
                MSPacketAnimation mSPacketAnimation2 = this;
                this.L = this.J(string2, bl2, clazz2);
            } else {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "entityId";
                MSPacketAnimation mSPacketAnimation = this;
                this.H = mSPacketAnimation.J(string, bl, clazz);
                Class<Integer> clazz3 = Integer.TYPE;
                boolean bl3 = true;
                String string3 = "type";
                MSPacketAnimation mSPacketAnimation3 = this;
                this.L = this.J(string3, bl3, clazz3);
            }
            return;
        }
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "type";
        MSPacketAnimation mSPacketAnimation = this;
        this.L = mSPacketAnimation.J(string, bl, clazz);
    }

    public int y(Object object) {
        return this.L.getInt(object);
    }
}

