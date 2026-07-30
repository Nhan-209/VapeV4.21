package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketSoundEffect
extends Mapping {
    private final MappingField M;
    private final MappingField u;
    private final MappingField N;
    private MappingField b;
    private MappingField K;

    private double B(Object object) {
        return this.u.getDouble(object);
    }

    private String o(Object object) {
        return (String)this.b.getObject(object);
    }

    private double F(Object object) {
        return this.N.getDouble(object);
    }

    private double q(Object object) {
        return this.M.getDouble(object);
    }

    public static double u(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.B(object);
    }

    public static float m$src$F$1cm4rrw(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.X(object);
    }

    public static float S(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.S(object);
    }

    private float S(Object object) {
        return this.M.getFloat(object);
    }

    public static String B(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.o(object);
    }

    private Object e(Object object) {
        return this.K.getObject(object);
    }

    public static double d(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.q(object);
    }

    public static Object g(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.e(object);
    }

    public MSPacketSoundEffect() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketSoundEffect(int[] nArray) {
        super(MappedClasses.Dk);
        if (nArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class clazz = MappedClasses.lZ;
                boolean bl = true;
                String string = "particle";
                MSPacketSoundEffect mSPacketSoundEffect = this;
                this.K = mSPacketSoundEffect.J(string, bl, clazz);
                Class<Double> clazz2 = Double.TYPE;
                boolean bl2 = true;
                String string2 = "xCoord";
                MSPacketSoundEffect mSPacketSoundEffect2 = this;
                this.u = this.J(string2, bl2, clazz2);
                Class<Double> clazz3 = Double.TYPE;
                boolean bl3 = true;
                String string3 = "yCoord";
                MSPacketSoundEffect mSPacketSoundEffect3 = this;
                this.N = this.J(string3, bl3, clazz3);
                Class<Double> clazz4 = Double.TYPE;
                boolean bl4 = true;
                String string4 = "zCoord";
                MSPacketSoundEffect mSPacketSoundEffect4 = this;
                this.M = this.J(string4, bl4, clazz4);
            } else if (ForgeVersion.MC_1_8_9.d()) {
                Class clazz = MappedClasses.qi;
                boolean bl = true;
                String string = "particleType";
                MSPacketSoundEffect mSPacketSoundEffect = this;
                this.K = mSPacketSoundEffect.J(string, bl, clazz);
                Class<Float> clazz5 = Float.TYPE;
                boolean bl5 = true;
                String string5 = "xCoord";
                MSPacketSoundEffect mSPacketSoundEffect5 = this;
                this.u = this.J(string5, bl5, clazz5);
                Class<Float> clazz6 = Float.TYPE;
                boolean bl6 = true;
                String string6 = "yCoord";
                MSPacketSoundEffect mSPacketSoundEffect6 = this;
                this.N = this.J(string6, bl6, clazz6);
                Class<Float> clazz7 = Float.TYPE;
                boolean bl7 = true;
                String string7 = "zCoord";
                MSPacketSoundEffect mSPacketSoundEffect7 = this;
                this.M = this.J(string7, bl7, clazz7);
            } else {
                Class<String> clazz = String.class;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "field_149236_a";
                MSPacketSoundEffect mSPacketSoundEffect = this;
                this.b = mSPacketSoundEffect.J(string, bl, clazz);
                Class<Float> clazz8 = Float.TYPE;
                boolean bl8 = Wrapper.isNativeAvailable;
                String string8 = "field_149234_b";
                MSPacketSoundEffect mSPacketSoundEffect8 = this;
                this.u = this.J(string8, bl8, clazz8);
                Class<Float> clazz9 = Float.TYPE;
                boolean bl9 = Wrapper.isNativeAvailable;
                String string9 = "field_149235_c";
                MSPacketSoundEffect mSPacketSoundEffect9 = this;
                this.N = this.J(string9, bl9, clazz9);
                Class<Float> clazz10 = Float.TYPE;
                boolean bl10 = Wrapper.isNativeAvailable;
                String string10 = "field_149232_d";
                MSPacketSoundEffect mSPacketSoundEffect10 = this;
                this.M = this.J(string10, bl10, clazz10);
            }
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            Class clazz = MappedClasses.qi;
            boolean bl = true;
            String string = "particleType";
            MSPacketSoundEffect mSPacketSoundEffect = this;
            this.K = mSPacketSoundEffect.J(string, bl, clazz);
            Class<Float> clazz11 = Float.TYPE;
            boolean bl11 = true;
            String string11 = "xCoord";
            MSPacketSoundEffect mSPacketSoundEffect11 = this;
            mSPacketSoundEffect11.J(string11, bl11, clazz11);
            Class<Float> clazz12 = Float.TYPE;
            boolean bl12 = true;
            String string12 = "yCoord";
            MSPacketSoundEffect mSPacketSoundEffect12 = this;
            mSPacketSoundEffect12.J(string12, bl12, clazz12);
            Class<Float> clazz13 = Float.TYPE;
            boolean bl13 = true;
            String string13 = "zCoord";
            MSPacketSoundEffect mSPacketSoundEffect13 = this;
            mSPacketSoundEffect13.J(string13, bl13, clazz13);
        }
        Class<String> clazz = String.class;
        boolean bl = Wrapper.isNativeAvailable;
        String string = "field_149236_a";
        MSPacketSoundEffect mSPacketSoundEffect = this;
        this.b = mSPacketSoundEffect.J(string, bl, clazz);
        Class<Float> clazz14 = Float.TYPE;
        boolean bl14 = Wrapper.isNativeAvailable;
        String string14 = "field_149234_b";
        MSPacketSoundEffect mSPacketSoundEffect14 = this;
        this.u = this.J(string14, bl14, clazz14);
        Class<Float> clazz15 = Float.TYPE;
        boolean bl15 = Wrapper.isNativeAvailable;
        String string15 = "field_149235_c";
        MSPacketSoundEffect mSPacketSoundEffect15 = this;
        this.N = this.J(string15, bl15, clazz15);
        Class<Float> clazz16 = Float.TYPE;
        boolean bl16 = Wrapper.isNativeAvailable;
        String string16 = "field_149232_d";
        MSPacketSoundEffect mSPacketSoundEffect16 = this;
        this.M = this.J(string16, bl16, clazz16);
    }

    private float Q(Object object) {
        return this.u.getFloat(object);
    }


    public static float p(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.Q(object);
    }

    private float X(Object object) {
        return this.N.getFloat(object);
    }

    public static double m(MSPacketSoundEffect mSPacketSoundEffect, Object object) {
        return mSPacketSoundEffect.F(object);
    }
}

