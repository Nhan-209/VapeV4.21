package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.List;

public class MTextComponentBaseBridge
extends Mapping {
    private static final String c;
    public MappingMethod D;
    private static int[] C;
    public MappingMethod b;

    static {
        MTextComponentBaseBridge.C(new int[2]);
        c = "setStyle";
    }

    public static int[] W() {
        return C;
    }

    public Object Q(Object object, List list, Object object2) {
        return this.D.O(object, list, object2);
    }

    public MTextComponentBaseBridge() {
        this(MTextComponentBaseBridge.W());
    }

    private MTextComponentBaseBridge(int[] nArray) {
        super(MappedClasses.uM);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_20_6.d()) {
            Class[] classArray = new Class[]{MappedClasses.Va};
            Class<?> clazz = MappedClasses.uM;
            boolean bl = true;
            String string = c;
            MTextComponentBaseBridge mTextComponentBaseBridge = this;
            this.b = this.Y(string, bl, clazz, classArray);
            Class[] classArray2 = new Class[]{MappedClasses.YT, List.class, MappedClasses.Va};
            MTextComponentBaseBridge mTextComponentBaseBridge2 = this;
            this.D = this.g(classArray2);
        }
    }

    public static void C(int[] nArray) {
        C = nArray;
    }

    public Object X(Object object, Object object2) {
        return this.b.L(object, object2);
    }

}

