package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MBlockStateWorldBridge
extends Mapping {
    private final MappingMethod S;
    private final MappingMethod W;
    private final MappingMethod M;
    private static boolean c;
    private final MappingMethod A;

    public MBlockStateWorldBridge() {
        super(MappedClasses.Dw);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.VR;
        boolean bl = true;
        String string = "getType";
        MBlockStateWorldBridge mBlockStateWorldBridge = this;
        this.S = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Boolean> clazz2 = Boolean.TYPE;
        boolean bl2 = true;
        String string2 = "isEmpty";
        MBlockStateWorldBridge mBlockStateWorldBridge2 = this;
        this.M = this.Y(string2, bl2, clazz2, classArray2);
        Class[] classArray3 = new Class[]{MappedClasses.qC};
        Class<Boolean> clazz3 = Boolean.TYPE;
        boolean bl3 = true;
        String string3 = "is";
        MBlockStateWorldBridge mBlockStateWorldBridge3 = this;
        this.W = this.Y(string3, bl3, clazz3, classArray3);
        boolean bl4 = MBlockStateWorldBridge.H();
        Class[] classArray4 = new Class[]{MappedClasses.zJ, MappedClasses.lf};
        Class<Float> clazz4 = Float.TYPE;
        boolean bl5 = true;
        String string4 = "getHeight";
        MBlockStateWorldBridge mBlockStateWorldBridge4 = this;
        this.A = this.Y(string4, bl5, clazz4, classArray4);
    }

    public static void O(boolean bl) {
        c = bl;
    }

    public Object E(Object object) {
        return this.S.L(object, new Object[0]);
    }

    public boolean e(Object object) {
        return this.M.e(object, new Object[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean Q() {
        boolean bl = MBlockStateWorldBridge.H();
        return false;
    }

    public static boolean H() {
        return c;
    }

    public float r(Object object, Object object2, Object object3) {
        return this.A.s(object, object2, object3);
    }

    public boolean b(Object object, Object object2) {
        return this.W.e(object, object2);
    }

    static {
        MBlockStateWorldBridge.O(true);
    }
}

