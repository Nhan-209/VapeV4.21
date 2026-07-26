package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MBlockStateBridge
extends Mapping {
    private MappingMethod y;
    private MappingField s;
    private MappingMethod G;
    private MappingField q;
    private static String[] M;
    private MappingField P;
    private MappingMethod J;

    public int H(Object object, int n) {
        if (this.J == null || this.J.h()) {
            return 0;
        }
        return this.J.Z(object, n);
    }

    public boolean Z(Object object) {
        if (this.y == null || this.y.h()) {
            return false;
        }
        return this.y.e(object, new Object[0]);
    }

    public static String[] Q() {
        return M;
    }

    public MBlockStateBridge() {
        this(MBlockStateBridge.Q());
    }

    private MBlockStateBridge(String[] stringArray) {
        super(MappedClasses.lu);
        String[] stringArray2 = stringArray;
        Class clazz = MappedClasses.DO;
        boolean bl = true;
        String string = "texture";
        MBlockStateBridge mBlockStateBridge = this;
        this.P = this.J(string, bl, clazz);
        Class<Integer> clazz2 = Integer.TYPE;
        boolean bl2 = true;
        String string2 = "baseMipLevel";
        MBlockStateBridge mBlockStateBridge2 = this;
        this.q = this.J(string2, bl2, clazz2);
        Class<Integer> clazz3 = Integer.TYPE;
        boolean bl3 = true;
        String string3 = "mipLevels";
        MBlockStateBridge mBlockStateBridge3 = this;
        this.s = this.J(string3, bl3, clazz3);
        Class[] classArray = new Class[]{Integer.TYPE};
        Class<Integer> clazz4 = Integer.TYPE;
        boolean bl4 = true;
        String string4 = "getWidth";
        MBlockStateBridge mBlockStateBridge4 = this;
        this.J = this.Y(string4, bl4, clazz4, classArray);
        Class[] classArray2 = new Class[]{Integer.TYPE};
        Class<Integer> clazz5 = Integer.TYPE;
        boolean bl5 = true;
        String string5 = "getHeight";
        MBlockStateBridge mBlockStateBridge5 = this;
        this.G = this.Y(string5, bl5, clazz5, classArray2);
        Class[] classArray3 = new Class[]{};
        Class<Boolean> clazz6 = Boolean.TYPE;
        boolean bl6 = true;
        String string6 = "isClosed";
        MBlockStateBridge mBlockStateBridge6 = this;
        this.y = this.Y(string6, bl6, clazz6, classArray3);
    }

    public int a(Object object) {
        if (this.s == null) {
            return 1;
        }
        return this.s.getInt(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int E(Object object) {
        if (this.q == null) {
            return 0;
        }
        return this.q.getInt(object);
    }

    static {
        MBlockStateBridge.r(null);
    }

    public static void r(String[] stringArray) {
        M = stringArray;
    }

    public Object p(Object object) {
        if (this.P == null) {
            return null;
        }
        return this.P.getObject(object);
    }

    public int v(Object object, int n) {
        if (this.G == null || this.G.h()) {
            return 0;
        }
        return this.G.Z(object, n);
    }
}

