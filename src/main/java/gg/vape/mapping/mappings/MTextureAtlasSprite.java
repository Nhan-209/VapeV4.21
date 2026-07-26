package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MTextureManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTextureAtlasSprite
extends Mapping {
    private MappingField l;
    private MappingField y;
    private MappingField a;
    private MappingField K;
    private MappingField P;
    private MappingField u;
    private MappingField m;
    private MappingField g;

    public static void I(MTextureAtlasSprite mTextureAtlasSprite, Object object, int n) {
        mTextureAtlasSprite.o(object, n);
    }

    public static void t(MTextureAtlasSprite mTextureAtlasSprite, Object object, float f) {
        mTextureAtlasSprite.P(object, f);
    }

    private void o(Object object, int n) {
        this.P.setInt(object, n);
    }

    private void P(Object object, float f) {
        this.K.setFloat(object, f);
    }

    private void j(Object object, int n) {
        this.g.setInt(object, n);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Object k(Object object) {
        return this.m.getObject(object);
    }

    private void p(Object object, float f) {
        this.a.setFloat(object, f);
    }

    private void J(Object object, float f) {
        this.u.setFloat(object, f);
    }

    public static Object L(MTextureAtlasSprite mTextureAtlasSprite, Object object) {
        return mTextureAtlasSprite.k(object);
    }

    public MTextureAtlasSprite() {
        this(MTextureManager.N());
    }

    private MTextureAtlasSprite(int n) {
        super(MappedClasses.Db);
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "u0";
        MTextureAtlasSprite mTextureAtlasSprite = this;
        this.y = this.J(string, bl, clazz);
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "u1";
        MTextureAtlasSprite mTextureAtlasSprite2 = this;
        this.a = this.J(string2, bl2, clazz2);
        if (n != 0) {
            Class<Float> clazz3 = Float.TYPE;
            boolean bl3 = true;
            String string3 = "v0";
            MTextureAtlasSprite mTextureAtlasSprite3 = this;
            this.K = this.J(string3, bl3, clazz3);
            Class<Float> clazz4 = Float.TYPE;
            boolean bl4 = true;
            String string4 = "v1";
            MTextureAtlasSprite mTextureAtlasSprite4 = this;
            this.u = this.J(string4, bl4, clazz4);
            Class clazz5 = MappedClasses.L;
            boolean bl5 = true;
            String string5 = "atlasTexture";
            MTextureAtlasSprite mTextureAtlasSprite5 = this;
            this.m = this.J(string5, bl5, clazz5);
            return;
        }
        Class<Float> clazz6 = Float.TYPE;
        boolean bl6 = true;
        String string6 = "v0";
        MTextureAtlasSprite mTextureAtlasSprite6 = this;
        this.K = this.J(string6, bl6, clazz6);
        Class<Float> clazz7 = Float.TYPE;
        boolean bl7 = true;
        String string7 = "v1";
        MTextureAtlasSprite mTextureAtlasSprite7 = this;
        this.u = this.J(string7, bl7, clazz7);
        if (ForgeVersion.MC_1_20_6.d()) {
            Class<Integer> clazz8 = Integer.TYPE;
            boolean bl8 = true;
            String string8 = "y";
            MTextureAtlasSprite mTextureAtlasSprite8 = this;
            this.P = this.J(string8, bl8, clazz8);
            Class<Integer> clazz9 = Integer.TYPE;
            boolean bl9 = true;
            String string9 = "x";
            MTextureAtlasSprite mTextureAtlasSprite9 = this;
            this.g = this.J(string9, bl9, clazz9);
            Class clazz10 = MappedClasses.V4;
            boolean bl10 = true;
            String string10 = "contents";
            MTextureAtlasSprite mTextureAtlasSprite10 = this;
            this.m = this.J(string10, bl10, clazz10);
            Class clazz11 = MappedClasses.zC;
            boolean bl11 = true;
            String string11 = "atlasLocation";
            MTextureAtlasSprite mTextureAtlasSprite11 = this;
            this.l = this.J(string11, bl11, clazz11);
        } else {
            Class<Integer> clazz12 = Integer.TYPE;
            boolean bl12 = true;
            String string12 = "x";
            MTextureAtlasSprite mTextureAtlasSprite12 = this;
            this.P = this.J(string12, bl12, clazz12);
            Class<Integer> clazz13 = Integer.TYPE;
            boolean bl13 = true;
            String string13 = "y";
            MTextureAtlasSprite mTextureAtlasSprite13 = this;
            this.g = this.J(string13, bl13, clazz13);
            Class clazz14 = MappedClasses.L;
            boolean bl14 = true;
            String string14 = "atlasTexture";
            MTextureAtlasSprite mTextureAtlasSprite14 = this;
            this.m = this.J(string14, bl14, clazz14);
        }
    }

    private float[] n$src$AF$1w7k5aq(Object object) {
        float[] fArray = new float[]{this.y.getFloat(object), this.a.getFloat(object), this.K.getFloat(object), this.u.getFloat(object)};
        return fArray;
    }

    private Object n(Object object) {
        return this.l.getObject(object);
    }

    public static float[] n(MTextureAtlasSprite mTextureAtlasSprite, Object object) {
        return mTextureAtlasSprite.n$src$AF$1w7k5aq(object);
    }

    private void k(Object object, float f) {
        this.y.setFloat(object, f);
    }

    public static Object F(MTextureAtlasSprite mTextureAtlasSprite, Object object) {
        return mTextureAtlasSprite.n(object);
    }

    public static void x(MTextureAtlasSprite mTextureAtlasSprite, Object object, float f) {
        mTextureAtlasSprite.p(object, f);
    }

    public static void I(MTextureAtlasSprite mTextureAtlasSprite, Object object, float f) {
        mTextureAtlasSprite.J(object, f);
    }

    public static void d(MTextureAtlasSprite mTextureAtlasSprite, Object object, float f) {
        mTextureAtlasSprite.k(object, f);
    }

    public static void r(MTextureAtlasSprite mTextureAtlasSprite, Object object, int n) {
        mTextureAtlasSprite.j(object, n);
    }
}

