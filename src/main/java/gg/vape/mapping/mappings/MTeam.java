package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MTeam
extends Mapping {
    private final MappingMethod f;
    private static final String b = "isSameTeam";

    public static boolean s(MTeam mTeam, Object object, Object object2) {
        return mTeam.Y(object, object2);
    }

    private boolean Y(Object object, Object object2) {
        return this.f.invokeBoolean(object, object2);
    }

    public MTeam() {
        super(MappedClasses.Yh);
        Class[] classArray = new Class[]{MappedClasses.Yh};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = true;
        String string = b;
        MTeam mTeam = this;
        this.f = this.Y(string, bl, clazz, classArray);
    }
}

