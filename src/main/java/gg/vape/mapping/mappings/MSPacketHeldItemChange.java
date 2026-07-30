package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MSPacketHeldItemChange
extends Mapping {
    private static final String b = "<init>";
    final MappingMethod p;

    public MSPacketHeldItemChange() {
        super(MappedClasses.l7);
        Class[] classArray = new Class[]{Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MSPacketHeldItemChange mSPacketHeldItemChange = this;
        this.p = this.Y(string, bl, clazz, classArray);
    }

    public Object h(int n) {
        return this.p.newInstance(n);
    }
}

