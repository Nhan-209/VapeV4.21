package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MChunkSection
extends Mapping {
    private MappingField r;
    private MappingMethod y;
    private static String O;
    private final MappingMethod Q;
    private MappingField z;

    public int r(Object object) {
        return this.r.getInt(object);
    }

    public void K(Object object, int n, int n2, int n3, Object object2) {
        this.Q.c(object, n, n2, n3, object2);
    }

    public static void G(String string) {
        O = string;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public char[] L(Object object) {
        return this.z.getCharArray(object);
    }

    public static String k() {
        return O;
    }

    public MChunkSection() {
        this(MChunkSection.k());
    }

    private MChunkSection(String string) {
        super(MappedClasses.De);
        String string2 = string;
        if (ForgeVersion.MC_1_20_6.v()) {
            Class[] classArray = new Class[]{Integer.TYPE, Boolean.TYPE};
            MChunkSection mChunkSection = this;
            this.y = mChunkSection.M(classArray).v(ForgeVersion.MC_1_16_5.n(), Integer.TYPE).s();
            Class<Integer> clazz = Integer.TYPE;
            String string3 = "yBase";
            MChunkSection mChunkSection2 = this;
            this.r = ((MappingFieldBuilder)((MappingFieldBuilder)this.T(string3, clazz).A(ForgeVersion.MC_1_16_5.n(), "field_76684_a")).i(ForgeVersion.MC_1_16_5.n(), Wrapper.G)).z();
        }
        if (ForgeVersion.MC_1_12_2.v()) {
            Class<char[]> clazz = char[].class;
            boolean bl = true;
            String string4 = "data";
            MChunkSection mChunkSection = this;
            this.z = mChunkSection.J(string4, bl, clazz);
        }
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, MappedClasses.Vv};
        Class<Void> clazz = Void.TYPE;
        String string5 = "set";
        MChunkSection mChunkSection = this;
        this.Q = ((MappingMethodBuilder)((MappingMethodBuilder)mChunkSection.u(string5, clazz, classArray).X(ForgeVersion.MC_1_16_5.n(), MappedClasses.Zl)).A(ForgeVersion.MC_1_16_5.n(), "setBlockState")).v(ForgeVersion.MC_1_16_5.n(), Integer.TYPE, Integer.TYPE, Integer.TYPE, MappedClasses.Zl, Boolean.TYPE).s(); 
    }

    public Object J(Object object, int n, int n2, int n3, Object object2, boolean bl) {
        return this.Q.L(object, n, n2, n3, object2, bl);
    }

    static {
        MChunkSection.G(null);
    }

    public Object i(int n) {
        return this.y.O(n);
    }

    public Object U(int n, boolean bl) {
        return this.y.O(n, bl);
    }
}

