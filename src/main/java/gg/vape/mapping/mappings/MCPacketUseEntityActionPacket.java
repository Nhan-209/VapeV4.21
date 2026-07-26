package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MCPacketUseEntityActionPacket
extends Mapping {
    private final MappingField u;
    private static final String b = "location";

    public MCPacketUseEntityActionPacket() {
        super(MappedClasses.uL);
        Class clazz = MappedClasses.qP;
        boolean bl = true;
        String string = b;
        MCPacketUseEntityActionPacket mCPacketUseEntityActionPacket = this;
        this.u = this.J(string, bl, clazz);
    }

    public Object c(Object object) {
        return this.u.getObject(object);
    }
}

