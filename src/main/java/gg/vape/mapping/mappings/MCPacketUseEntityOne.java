package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MCPacketUseEntityOne
extends Mapping {
    private final MappingMethod W;
    private static final String b = "<init>";

    public static Object e(MCPacketUseEntityOne mCPacketUseEntityOne, Object object) {
        return mCPacketUseEntityOne.X(object);
    }

    public MCPacketUseEntityOne() {
        super(MappedClasses.z1);
        Class[] classArray = new Class[]{MappedClasses.q9};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MCPacketUseEntityOne mCPacketUseEntityOne = this;
        this.W = this.Y(string, bl, clazz, classArray);
    }

    private Object X(Object object) {
        return this.W.O(object);
    }
}

