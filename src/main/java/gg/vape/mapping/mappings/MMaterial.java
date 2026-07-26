package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MMaterial
extends Mapping {
    public MappingField c;
    private static int[] K;
    private final MappingMethod C;
    private MappingField u;
    public final MappingMethod S;
    public final MappingMethod p;
    private MappingField n;
    public final MappingMethod T;
    private MappingMethod B;
    private MappingField f;

    public boolean O(Object object) {
        return this.T.e(object, new Object[0]);
    }

    public static int[] u() {
        return K;
    }

    public boolean A(Object object) {
        return this.p.e(object, new Object[0]);
    }

    public boolean M(Object object) {
        return this.S.e(object, new Object[0]);
    }

    public boolean b(Object object) {
        return this.B.e(object, new Object[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MMaterial() {
        this(MMaterial.u());
    }

    private MMaterial(int[] nArray) {
        super(MappedClasses.ZN);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_20_6.v()) {
            if (ForgeVersion.MC_1_12_2.d()) {
                Class clazz = MappedClasses.ZN;
                boolean bl = true;
                String string = "AIR";
                MMaterial mMaterial = this;
                this.c = mMaterial.u(string, bl, clazz);
                Class clazz2 = MappedClasses.ZN;
                boolean bl2 = true;
                String string2 = "WATER";
                MMaterial mMaterial2 = this;
                this.n = this.u(string2, bl2, clazz2);
                Class clazz3 = MappedClasses.ZN;
                boolean bl3 = true;
                String string3 = "FIRE";
                MMaterial mMaterial3 = this;
                this.u = this.u(string3, bl3, clazz3);
                if (ForgeVersion.MC_1_16_5.d()) {
                    Class clazz4 = MappedClasses.ZN;
                    boolean bl4 = true;
                    String string4 = "TALL_PLANTS";
                    MMaterial mMaterial4 = this;
                    this.f = this.u(string4, bl4, clazz4);
                } else {
                    Class clazz5 = MappedClasses.ZN;
                    boolean bl5 = true;
                    String string5 = "VINE";
                    MMaterial mMaterial5 = this;
                    this.f = this.u(string5, bl5, clazz5);
                }
            } else {
                Class clazz = MappedClasses.ZN;
                boolean bl = true;
                String string = "air";
                MMaterial mMaterial = this;
                this.c = mMaterial.u(string, bl, clazz);
                Class clazz6 = MappedClasses.ZN;
                boolean bl6 = true;
                String string6 = "water";
                MMaterial mMaterial6 = this;
                this.n = this.u(string6, bl6, clazz6);
                Class clazz7 = MappedClasses.ZN;
                boolean bl7 = true;
                String string7 = "vine";
                MMaterial mMaterial7 = this;
                this.f = this.u(string7, bl7, clazz7);
                Class clazz8 = MappedClasses.ZN;
                boolean bl8 = true;
                String string8 = "fire";
                MMaterial mMaterial8 = this;
                this.u = this.u(string8, bl8, clazz8);
            }
        }
        if (ForgeVersion.MC_1_16_5.v()) {
            Class[] classArray = new Class[]{};
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = true;
            String string = "isToolNotRequired";
            MMaterial mMaterial = this;
            this.B = mMaterial.Y(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = true;
        String string = "isReplaceable";
        MMaterial mMaterial = this;
        this.S = mMaterial.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Boolean> clazz9 = Boolean.TYPE;
        boolean bl9 = true;
        String string9 = "blocksMovement";
        MMaterial mMaterial9 = this;
        this.T = this.Y(string9, bl9, clazz9, classArray2);
        Class[] classArray3 = new Class[]{};
        Class<Boolean> clazz10 = Boolean.TYPE;
        boolean bl10 = true;
        String string10 = "isSolid";
        MMaterial mMaterial10 = this;
        this.p = this.Y(string10, bl10, clazz10, classArray3);
        Class[] classArray4 = new Class[]{};
        Class<Boolean> clazz11 = Boolean.TYPE;
        boolean bl11 = true;
        String string11 = "isLiquid";
        MMaterial mMaterial11 = this;
        this.C = this.Y(string11, bl11, clazz11, classArray4);
    }

    public boolean z(Object object) {
        return this.C.e(object, new Object[0]);
    }

    public static void r(int[] nArray) {
        K = nArray;
    }

    static {
        MMaterial.r(null);
    }

    public Object q() {
        return this.c.getObject(null);
    }

    public Object H() {
        return this.u.getObject(null);
    }

    public Object m() {
        return this.n.getObject(null);
    }

    public Object A() {
        return this.f.getObject(null);
    }
}

