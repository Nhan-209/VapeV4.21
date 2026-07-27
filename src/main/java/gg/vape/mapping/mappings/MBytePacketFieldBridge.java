package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBytePacketFieldBridge
extends Mapping {
    private final MappingField P;

    public MBytePacketFieldBridge() {
        this(MSPacketEntityVelocity.G());
    }

    private MBytePacketFieldBridge(int[] nArray) {
        super(MappedClasses.D7);
        if (nArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class<Byte> clazz = Byte.TYPE;
                boolean bl = true;
                String string = "yHeadRot";
                MBytePacketFieldBridge mBytePacketFieldBridge = this;
                this.P = mBytePacketFieldBridge.J(string, bl, clazz);
            } else if (ForgeVersion.MC_1_7_10.L()) {
                Class<Byte> clazz = Byte.TYPE;
                boolean bl = true;
                String string = "field_149383_b";
                MBytePacketFieldBridge mBytePacketFieldBridge = this;
                this.P = mBytePacketFieldBridge.J(string, bl, clazz);
            } else {
                Class<Byte> clazz = Byte.TYPE;
                boolean bl = true;
                String string = "yaw";
                MBytePacketFieldBridge mBytePacketFieldBridge = this;
                this.P = mBytePacketFieldBridge.J(string, bl, clazz);
            }
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            Class<Byte> clazz = Byte.TYPE;
            boolean bl = true;
            String string = "field_149383_b";
            MBytePacketFieldBridge mBytePacketFieldBridge = this;
            mBytePacketFieldBridge.J(string, bl, clazz);
        }
        Class<Byte> clazz = Byte.TYPE;
        boolean bl = true;
        String string = "yaw";
        MBytePacketFieldBridge mBytePacketFieldBridge = this;
        this.P = mBytePacketFieldBridge.J(string, bl, clazz); 
    }

    public byte L(Object object) {
        return (byte)this.P.getInt(object);
    }

}
