package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MRegistryAccess
extends Mapping {
    private MappingMethod B;
    private static String y;
    private static final String c;

    static {
        MRegistryAccess.l("Wm5ntb");
        c = "lookupOrThrow";
    }

    public static void l(String string) {
        y = string;
    }

    public static String d() {
        return y;
    }

    public MRegistryAccess() {
        super(MappedClasses.Fd);
        Class[] classArray = new Class[]{MappedClasses.qB};
        Class clazz = MappedClasses.Fk;
        boolean bl = true;
        String string = c;
        MRegistryAccess mRegistryAccess = this;
        this.B = this.Y(string, bl, clazz, classArray);
    }

    public Object H(Object object, Object object2) {
        return this.B.L(object, object2);
    }
}

