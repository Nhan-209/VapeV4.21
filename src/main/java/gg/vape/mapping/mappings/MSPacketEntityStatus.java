package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketEntityStatus
extends Mapping {
    private MappingField E;
    private MappingField x;

    public MSPacketEntityStatus() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketEntityStatus(int[] nArray) {
        super(MappedClasses.lU);
        if (nArray != null) {
            if (ForgeVersion.MC_1_7_10.L()) {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "field_149164_a";
                MSPacketEntityStatus mSPacketEntityStatus = this;
                this.E = mSPacketEntityStatus.J(string, bl, clazz);
                Class<Byte> clazz2 = Byte.TYPE;
                boolean bl2 = true;
                String string2 = "field_149163_b";
                MSPacketEntityStatus mSPacketEntityStatus2 = this;
                this.x = this.J(string2, bl2, clazz2);
            } else {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "entityId";
                MSPacketEntityStatus mSPacketEntityStatus = this;
                this.E = mSPacketEntityStatus.J(string, bl, clazz);
                Class<Byte> clazz3 = Byte.TYPE;
                boolean bl3 = true;
                String string3 = "logicOpcode";
                MSPacketEntityStatus mSPacketEntityStatus3 = this;
                this.x = this.J(string3, bl3, clazz3);
            }
            return;
        }
        Class<Byte> clazz = Byte.TYPE;
        boolean bl = true;
        String string = "logicOpcode";
        MSPacketEntityStatus mSPacketEntityStatus = this;
        this.x = mSPacketEntityStatus.J(string, bl, clazz);
    }

    public byte n(Object object) {
        return (byte)this.x.getInt(object);
    }

    public int e(Object object) {
        return this.E.getInt(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

