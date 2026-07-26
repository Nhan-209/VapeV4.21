package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTextureManager
extends Mapping {
    private static int r;
    public MappingMethod L;
    private final MappingMethod U;

    private void k(Object object, Object object2) {
        this.L.c(object, object2);
    }

    public static int W() {
        return r;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        MTextureManager.T(69);
    }

    public static void Y(MTextureManager mTextureManager, Object object, Object object2) {
        mTextureManager.k(object, object2);
    }

    public MTextureManager() {
        this(MTextureManager.N());
    }

    private MTextureManager(int n) {
        super(MappedClasses.Dt);
        if (n != 0) {
            Class[] classArray = new Class[]{MappedClasses.zC};
            Class clazz = MappedClasses.ut;
            boolean bl = true;
            String string = "getTexture";
            MTextureManager mTextureManager = this;
            this.L = mTextureManager.Y(string, bl, clazz, classArray);
            this.U = null;
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.zC};
        Class clazz = MappedClasses.ut;
        boolean bl = true;
        String string = "getTexture";
        MTextureManager mTextureManager = this;
        this.U = mTextureManager.Y(string, bl, clazz, classArray);
        if (ForgeVersion.MC_1_21_0.v()) {
            Class[] classArray2 = new Class[]{MappedClasses.zC};
            Class<Void> clazz2 = Void.TYPE;
            boolean bl2 = true;
            String string2 = "bindTexture";
            MTextureManager mTextureManager2 = this;
            this.L = this.Y(string2, bl2, clazz2, classArray2);
        }
    }

    public static int N() {
        int n = MTextureManager.W();
        if (n == 0) {
            return 90;
        }
        return 0;
    }

    private Object C(Object object, Object object2) {
        return this.U.L(object, object2);
    }

    public static void T(int n) {
        r = n;
    }

    public static Object a(MTextureManager mTextureManager, Object object, Object object2) {
        return mTextureManager.C(object, object2);
    }
}

