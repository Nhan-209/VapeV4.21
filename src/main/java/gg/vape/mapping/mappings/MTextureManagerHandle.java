package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MTextureManagerHandle
extends Mapping {
    private final MappingMethod j;
    private final MappingMethod z;
    private static boolean f;


    public static boolean t() {
        return f;
    }

    public Object C(Object object) {
        return this.j.L(object, new Object[0]);
    }

    public static void W(boolean bl) {
        f = bl;
    }

    static {
        MTextureManagerHandle.W(true);
    }

    public static boolean K() {
        boolean bl = MTextureManagerHandle.t();
        return false;
    }

    public Object A(Object object) {
        return this.z.L(object, new Object[0]);
    }

    public MTextureManagerHandle() {
        this(MTextureManagerHandle.t());
    }

    private MTextureManagerHandle(boolean bl) {
        super(MappedClasses.DA);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.DO;
        boolean bl2 = true;
        String string = "getColorTexture";
        MTextureManagerHandle mTextureManagerHandle = this;
        this.z = this.Y(string, bl2, clazz, classArray);
        boolean bl3 = bl;
        Class[] classArray2 = new Class[]{};
        Class clazz2 = MappedClasses.DO;
        boolean bl4 = true;
        String string2 = "getDepthTexture";
        MTextureManagerHandle mTextureManagerHandle2 = this;
        this.j = this.Y(string2, bl4, clazz2, classArray2);
    }
}

