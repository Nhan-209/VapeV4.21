package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MTrajectoriesItemBridge
extends Mapping {
    private static final String b = "isCharged";
    public final MappingMethod T;

    public MTrajectoriesItemBridge() {
        super(MappedClasses.YA);
        Class[] classArray = new Class[]{MappedClasses.VK};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = true;
        String string = b;
        MTrajectoriesItemBridge mTrajectoriesItemBridge = this;
        this.T = this.x(string, bl, clazz, classArray);
    }

    public boolean g(Object object) {
        return this.T.e(null, object);
    }
}

