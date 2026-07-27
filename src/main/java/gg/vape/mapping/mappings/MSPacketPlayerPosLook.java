package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.Set;

public class MSPacketPlayerPosLook
extends Mapping {
    private MappingField i;
    private MappingField Q;
    private MappingMethod v;
    private MappingField o;
    private MappingField C;
    private MappingField K;
    private MappingField N;
    private MappingField z;
    private MappingField P;

    public int n(Object object) {
        return this.N.getInt(object);
    }

    public Object j(Object object) {
        return this.P.getObject(object);
    }


    public Object B(Object object) {
        return this.C.getObject(object);
    }

    public MSPacketPlayerPosLook() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketPlayerPosLook(int[] nArray) {
        super(MappedClasses.zw);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_21_4.d()) {
            Class clazz = MappedClasses.Dd;
            boolean bl = true;
            String string = "change";
            MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
            this.P = mSPacketPlayerPosLook.J(string, bl, clazz);
            Class<Set> clazz2 = Set.class;
            boolean bl2 = true;
            String string2 = "relatives";
            MSPacketPlayerPosLook mSPacketPlayerPosLook2 = this;
            this.C = this.J(string2, bl2, clazz2);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = true;
            String string3 = "id";
            MSPacketPlayerPosLook mSPacketPlayerPosLook3 = this;
            this.N = this.J(string3, bl3, clazz3);
        } else {
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class<Double> clazz = Double.TYPE;
                boolean bl = true;
                String string = "x";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.o = mSPacketPlayerPosLook.J(string, bl, clazz);
                Class<Double> clazz4 = Double.TYPE;
                boolean bl4 = true;
                String string4 = "y";
                MSPacketPlayerPosLook mSPacketPlayerPosLook4 = this;
                this.K = this.J(string4, bl4, clazz4);
                Class<Double> clazz5 = Double.TYPE;
                boolean bl5 = true;
                String string5 = "z";
                MSPacketPlayerPosLook mSPacketPlayerPosLook5 = this;
                this.Q = this.J(string5, bl5, clazz5);
            } else {
                Class<Double> clazz = Double.TYPE;
                boolean bl = Wrapper.G;
                String string = "field_148940_a";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.o = mSPacketPlayerPosLook.J(string, bl, clazz);
                Class<Double> clazz6 = Double.TYPE;
                boolean bl6 = Wrapper.G;
                String string6 = "field_148938_b";
                MSPacketPlayerPosLook mSPacketPlayerPosLook6 = this;
                this.K = this.J(string6, bl6, clazz6);
                Class<Double> clazz7 = Double.TYPE;
                boolean bl7 = Wrapper.G;
                String string7 = "field_148939_c";
                MSPacketPlayerPosLook mSPacketPlayerPosLook7 = this;
                this.Q = this.J(string7, bl7, clazz7);
            }
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray = new Class[]{};
                Class<Set> clazz = Set.class;
                boolean bl = Wrapper.G;
                String string = "func_179834_f";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.v = mSPacketPlayerPosLook.Y(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_1_8_9.d()) {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "teleportId";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.N = mSPacketPlayerPosLook.J(string, bl, clazz);
            }
            if (Vape.INSTANCE.isVanillaMinecraftPresent() && ForgeVersion.MC_1_7_10.Y()) {
                Class<Float> clazz = Float.TYPE;
                boolean bl = true;
                String string = "yaw";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.i = mSPacketPlayerPosLook.J(string, bl, clazz);
                Class<Float> clazz8 = Float.TYPE;
                boolean bl8 = true;
                String string8 = "pitch";
                MSPacketPlayerPosLook mSPacketPlayerPosLook8 = this;
                this.z = this.J(string8, bl8, clazz8);
            } else {
                Class<Float> clazz = Float.TYPE;
                boolean bl = Wrapper.G;
                String string = "field_148936_d";
                MSPacketPlayerPosLook mSPacketPlayerPosLook = this;
                this.i = mSPacketPlayerPosLook.J(string, bl, clazz);
                Class<Float> clazz9 = Float.TYPE;
                boolean bl9 = Wrapper.G;
                String string9 = "field_148937_e";
                MSPacketPlayerPosLook mSPacketPlayerPosLook9 = this;
                this.z = this.J(string9, bl9, clazz9);
            }
        }
    }

    public double Q(Object object) {
        return this.Q.getDouble(object);
    }

    public float g(Object object) {
        return this.i.getFloat(object);
    }

    public double e(Object object) {
        return this.o.getDouble(object);
    }

    public float d(Object object) {
        return this.z.getFloat(object);
    }

    public void o(Object object, float f) {
        this.z.setFloat(object, f);
    }

    public void F(Object object, float f) {
        this.i.setFloat(object, f);
    }

    public double O(Object object) {
        return this.K.getDouble(object);
    }

    public Object w(Object object) {
        return this.v.L(object, new Object[0]);
    }
}

