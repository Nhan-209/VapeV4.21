package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MSPacketExplosion
extends Mapping {
    private MappingField D;
    private MappingField G;
    private MappingField S;

    private float Y(Object object) {
        return this.G.getFloat(object);
    }

    private float i(Object object) {
        return this.S.getFloat(object);
    }

    public static float z(MSPacketExplosion mSPacketExplosion, Object object) {
        return mSPacketExplosion.Y(object);
    }

    public static float V(MSPacketExplosion mSPacketExplosion, Object object) {
        return mSPacketExplosion.i(object);
    }

    public static void m(MSPacketExplosion mSPacketExplosion, Object object, float f) {
        mSPacketExplosion.i(object, f);
    }

    public static void K(MSPacketExplosion mSPacketExplosion, Object object, float f) {
        mSPacketExplosion.Z(object, f);
    }

    private float d(Object object) {
        return this.D.getFloat(object);
    }

    private void Z(Object object, float f) {
        this.D.setFloat(object, f);
    }

    private void i(Object object, float f) {
        this.G.setFloat(object, f);
    }

    private void f(Object object, float f) {
        this.S.setFloat(object, f);
    }

    public MSPacketExplosion() {
        super(MappedClasses.VB);
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "speedOld";
        MSPacketExplosion mSPacketExplosion = this;
        this.D = this.J(string, bl, clazz);
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "speed";
        MSPacketExplosion mSPacketExplosion2 = this;
        this.S = this.J(string2, bl2, clazz2);
        Class<Float> clazz3 = Float.TYPE;
        boolean bl3 = true;
        String string3 = "position";
        MSPacketExplosion mSPacketExplosion3 = this;
        this.G = this.J(string3, bl3, clazz3);
    }

    public static float v(MSPacketExplosion mSPacketExplosion, Object object) {
        return mSPacketExplosion.d(object);
    }
}

