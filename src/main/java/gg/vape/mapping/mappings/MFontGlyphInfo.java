package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MFontGlyphInfo
extends Mapping {
    private MappingMethod U;
    private MappingMethod a;
    private MappingMethod i;
    private MappingMethod s;
    private static int[] y;

    public static void M(int[] nArray) {
        y = nArray;
    }

    public float k(Object object) {
        if (this.s == null || this.s.hasResolutionFailed()) {
            return 1.0f;
        }
        return this.s.invokeFloat(object, new Object[0]);
    }

    public float v(Object object) {
        if (this.i == null || this.i.hasResolutionFailed()) {
            return 1.0f;
        }
        return this.i.invokeFloat(object, new Object[0]);
    }

    public static int[] T() {
        return y;
    }

    public float e(Object object, boolean bl) {
        if (this.a == null || this.a.hasResolutionFailed()) {
            float f = this.B(object);
            return bl ? f + this.v(object) : f;
        }
        return this.a.invokeFloat(object, bl);
    }

    static {
        MFontGlyphInfo.M((int[])null);
    }


    public float B(Object object) {
        if (this.U == null || this.U.hasResolutionFailed()) {
            return 0.0f;
        }
        return this.U.invokeFloat(object, new Object[0]);
    }

    public MFontGlyphInfo() {
        super(MappedClasses.g);
        Class[] classArray = new Class[]{};
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "getAdvance";
        MFontGlyphInfo mFontGlyphInfo = this;
        this.U = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{Boolean.TYPE};
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "getAdvance";
        MFontGlyphInfo mFontGlyphInfo2 = this;
        this.a = this.Y(string2, bl2, clazz2, classArray2);
        Class[] classArray3 = new Class[]{};
        Class<Float> clazz3 = Float.TYPE;
        boolean bl3 = true;
        String string3 = "getBoldOffset";
        MFontGlyphInfo mFontGlyphInfo3 = this;
        this.i = this.Y(string3, bl3, clazz3, classArray3);
        int[] nArray = MFontGlyphInfo.T();
        Class[] classArray4 = new Class[]{};
        Class<Float> clazz4 = Float.TYPE;
        boolean bl4 = true;
        String string4 = "getShadowOffset";
        MFontGlyphInfo mFontGlyphInfo4 = this;
        this.s = this.Y(string4, bl4, clazz4, classArray4);
    }
}

