package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Vec3;

public class MSPacketEntityVelocity
extends Mapping {
    private MappingField a;
    private MappingMethod q;
    private final MappingField c;
    private MappingField Y;
    private MappingField j;
    private MappingField F;
    private static int[] A;

    public MSPacketEntityVelocity() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketEntityVelocity(int[] nArray) {
        super(MappedClasses.YX);
        int[] nArray2 = nArray;
        if (Vape.INSTANCE.isVanillaMinecraftPresent() && ForgeVersion.MC_1_7_10.Y()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityID";
            MSPacketEntityVelocity mSPacketEntityVelocity = this;
            this.c = mSPacketEntityVelocity.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "motionX";
            MSPacketEntityVelocity mSPacketEntityVelocity2 = this;
            this.j = this.J(string2, bl2, clazz2);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = true;
            String string3 = "motionY";
            MSPacketEntityVelocity mSPacketEntityVelocity3 = this;
            this.Y = this.J(string3, bl3, clazz3);
            Class<Integer> clazz4 = Integer.TYPE;
            boolean bl4 = true;
            String string4 = "motionZ";
            MSPacketEntityVelocity mSPacketEntityVelocity4 = this;
            this.a = this.J(string4, bl4, clazz4);
        } else {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_149417_a";
            MSPacketEntityVelocity mSPacketEntityVelocity = this;
            this.c = mSPacketEntityVelocity.J(string, bl, clazz);
            if (ForgeVersion.MC_1_21_10.d()) {
                Class clazz5 = MappedClasses.qP;
                boolean bl5 = true;
                String string5 = "movement";
                MSPacketEntityVelocity mSPacketEntityVelocity5 = this;
                this.F = this.J(string5, bl5, clazz5);
                Class[] classArray = new Class[]{Integer.TYPE, MappedClasses.qP};
                MSPacketEntityVelocity mSPacketEntityVelocity6 = this;
                this.q = this.g(classArray);
            } else {
                Class<Integer> clazz6 = Integer.TYPE;
                boolean bl6 = Wrapper.G;
                String string6 = "field_149415_b";
                MSPacketEntityVelocity mSPacketEntityVelocity7 = this;
                this.j = this.J(string6, bl6, clazz6);
                Class<Integer> clazz7 = Integer.TYPE;
                boolean bl7 = Wrapper.G;
                String string7 = "field_149416_c";
                MSPacketEntityVelocity mSPacketEntityVelocity8 = this;
                this.Y = this.J(string7, bl7, clazz7);
                Class<Integer> clazz8 = Integer.TYPE;
                boolean bl8 = Wrapper.G;
                String string8 = "field_149414_d";
                MSPacketEntityVelocity mSPacketEntityVelocity9 = this;
                this.a = this.J(string8, bl8, clazz8);
            }
        }
    }

    public static int a(MSPacketEntityVelocity mSPacketEntityVelocity, Object object) {
        return mSPacketEntityVelocity.W(object);
    }

    public static int g(MSPacketEntityVelocity mSPacketEntityVelocity, Object object) {
        return mSPacketEntityVelocity.J(object);
    }

    private void K(Object object, int n) {
        this.j.setInt(object, n);
    }

    private Object f(Object object) {
        return this.F.getObject(object);
    }

    private void m(Object object, Object object2) {
        this.F.setObject(object, object2);
    }

    public static Object I(MSPacketEntityVelocity mSPacketEntityVelocity, Object object) {
        return mSPacketEntityVelocity.f(object);
    }

    public Object Y(int n, double d, double d2, double d3) {
        Vec3 vec3 = Vec3.create(d, d2, d3);
        return this.q.O(n, vec3.getObject());
    }

    public static int X(MSPacketEntityVelocity mSPacketEntityVelocity, Object object) {
        return mSPacketEntityVelocity.A(object);
    }

    private void s(Object object, int n) {
        this.a.setInt(object, n);
    }

    public static void Q(MSPacketEntityVelocity mSPacketEntityVelocity, Object object, int n) {
        mSPacketEntityVelocity.T(object, n);
    }

    public static int V(MSPacketEntityVelocity mSPacketEntityVelocity, Object object) {
        return mSPacketEntityVelocity.Z(object);
    }

    public static int[] G() {
        return A;
    }

    static {
        MSPacketEntityVelocity.N(new int[3]);
    }

    private int A(Object object) {
        return this.a.getInt(object);
    }

    private void T(Object object, int n) {
        this.Y.setInt(object, n);
    }

    private int J(Object object) {
        return this.Y.getInt(object);
    }

    public static void E(MSPacketEntityVelocity mSPacketEntityVelocity, Object object, Object object2) {
        mSPacketEntityVelocity.m(object, object2);
    }

    private int W(Object object) {
        return this.j.getInt(object);
    }


    public static void k(MSPacketEntityVelocity mSPacketEntityVelocity, Object object, int n) {
        mSPacketEntityVelocity.s(object, n);
    }

    public static void q(MSPacketEntityVelocity mSPacketEntityVelocity, Object object, int n) {
        mSPacketEntityVelocity.K(object, n);
    }

    public static void N(int[] nArray) {
        A = nArray;
    }

    private int Z(Object object) {
        return this.c.getInt(object);
    }
}

