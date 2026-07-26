package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MQuaternion
extends Mapping {
    private MappingField I;
    private MappingField G;
    private MappingMethod c;
    private MappingField v;
    private MappingField A;
    private MappingMethod e;

    public float O(Object object) {
        return this.I.getFloat(object);
    }

    public float V(Object object) {
        return this.A.getFloat(object);
    }

    public MQuaternion() {
        this(BlockData.W());
    }

    private MQuaternion(String[] stringArray) {
        super(MappedClasses.qI);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_20_6.v()) {
                Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MQuaternion mQuaternion = this;
                this.c = mQuaternion.Y(string, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MQuaternion mQuaternion = this;
            this.e = mQuaternion.Y(string, bl, clazz, classArray);
            Class<Float> clazz2 = Float.TYPE;
            boolean bl2 = true;
            String string2 = "x";
            MQuaternion mQuaternion2 = this;
            this.A = this.J(string2, bl2, clazz2);
            Class<Float> clazz3 = Float.TYPE;
            boolean bl3 = true;
            String string3 = "y";
            MQuaternion mQuaternion3 = this;
            this.v = this.J(string3, bl3, clazz3);
            Class<Float> clazz4 = Float.TYPE;
            boolean bl4 = true;
            String string4 = "z";
            MQuaternion mQuaternion4 = this;
            this.I = this.J(string4, bl4, clazz4);
            Class<Float> clazz5 = Float.TYPE;
            boolean bl5 = true;
            String string5 = "w";
            MQuaternion mQuaternion5 = this;
            this.G = this.J(string5, bl5, clazz5);
            return;
        }
    }

    public float h(Object object) {
        return this.G.getFloat(object);
    }

    public Object v(float f, float f2, float f3, boolean bl) {
        return this.c.O(Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), bl);
    }

    public float v(Object object) {
        return this.v.getFloat(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Object N(float f, float f2, float f3, float f4) {
        return this.e.O(Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4));
    }
}

