package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import java.util.Collection;

public class MResourceManager
extends Mapping {
    private static final String b;
    private final MappingMethod S;
    private static int G;

    public MResourceManager() {
        this(MResourceManager.E$src$I$1yt3vs5());
    }

    private MResourceManager(int n) {
        super(MappedClasses.FF);
        int n2 = n;
        Class[] classArray = new Class[]{};
        Class<Collection> clazz = Collection.class;
        boolean bl = true;
        String string = b;
        MResourceManager mResourceManager = this;
        this.S = this.Y(string, bl, clazz, classArray);
    }

    public static int E$src$I$1yt3vs5() {
        return G;
    }

    static {
        MResourceManager.T(0);
        b = "getSelectedIds";
    }

    public Object e(Object object) {
        return this.S.invokeObject(object, new Object[0]);
    }

    public static int j() {
        int n = MResourceManager.E$src$I$1yt3vs5();
        return 59;
    }


    public static void T(int n) {
        G = n;
    }
}

