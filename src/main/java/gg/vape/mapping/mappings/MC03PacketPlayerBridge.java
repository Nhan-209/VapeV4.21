package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MC03PacketPlayerBridge
extends Mapping {
    private static final String b = "<init>";
    private final MappingMethod d;

    public Object W(boolean bl, boolean bl2) {
        return this.d.O(bl, bl2);
    }

    public MC03PacketPlayerBridge() {
        super(MappedClasses.Dl);
        Class[] classArray = new Class[]{Boolean.TYPE, Boolean.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MC03PacketPlayerBridge mC03PacketPlayerBridge = this;
        this.d = this.Y(string, bl, clazz, classArray);
    }
}

