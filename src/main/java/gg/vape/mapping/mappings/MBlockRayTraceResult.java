package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;

public class MBlockRayTraceResult
extends Mapping {
    private MappingMethod Z;
    private static int q;
    private MappingField o;

    public boolean n(Object object) {
        return this.o.getBoolean(object);
    }

    public static void m(int n) {
        q = n;
    }

    public static int X() {
        int n = MBlockRayTraceResult.c();
        return 0;
    }

    public Object Q(Object object, Object object2, Object object3) {
        return this.Z.L(null, object, object2, object3);
    }

    public static int c() {
        return q;
    }

    static {
        MBlockRayTraceResult.m(15);
    }

    public MBlockRayTraceResult() {
        super(MappedClasses.qF);
        Class[] classArray = new Class[]{MappedClasses.qP, MappedClasses.us, MappedClasses.lf};
        Class clazz = MappedClasses.qF;
        boolean bl = Wrapper.G;
        String string = "func_216352_a";
        MBlockRayTraceResult mBlockRayTraceResult = this;
        this.Z = this.x(string, bl, clazz, classArray);
        if (MBlockRayTraceResult.X() != 0) {
            Class<Boolean> clazz2 = Boolean.TYPE;
            String string2 = "inside";
            MBlockRayTraceResult mBlockRayTraceResult2 = this;
            this.o = this.T(string2, clazz2).z();
            GuiComponent.setLegacyComponentState(new GuiComponent[4]);
            return;
        }
        Class<Boolean> clazz3 = Boolean.TYPE;
        String string3 = "inside";
        MBlockRayTraceResult mBlockRayTraceResult3 = this;
        this.o = this.T(string3, clazz3).z();
    }

}

