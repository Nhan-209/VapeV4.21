package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSEntityPacket
extends Mapping {
    private MappingMethod m;
    private MappingField L;
    private MappingMethod f;
    private final MappingMethod q;
    private MappingField G;
    private MappingMethod Y;
    private MappingField p;

    private int R(Object object) {
        return this.G.getShort(object);
    }

    public static int m(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.m(object);
    }

    public static int b(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.s(object);
    }

    public MSEntityPacket() {
        this(MSPacketEntityVelocity.G());
    }

    private MSEntityPacket(int[] nArray) {
        super(MappedClasses.qz);
        int[] nArray2 = nArray;
        if (Vape.INSTANCE.isVanillaMinecraftPresent() && ForgeVersion.MC_1_7_10.Y()) {
            Class[] classArray = new Class[]{MappedClasses.YU};
            Class clazz = MappedClasses.zc;
            boolean bl = true;
            String string = "getEntity";
            MSEntityPacket mSEntityPacket = this;
            this.q = mSEntityPacket.Y(string, bl, clazz, classArray);
        } else {
            Class[] classArray = new Class[]{MappedClasses.YU};
            Class clazz = MappedClasses.zc;
            boolean bl = Wrapper.isNativeAvailable;
            String string = "func_149065_a";
            MSEntityPacket mSEntityPacket = this;
            this.q = mSEntityPacket.Y(string, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class<Short> clazz = Short.TYPE;
                boolean bl = true;
                String string = "posX";
                MSEntityPacket mSEntityPacket = this;
                this.G = mSEntityPacket.J(string, bl, clazz);
                Class<Short> clazz2 = Short.TYPE;
                boolean bl2 = true;
                String string2 = "posY";
                MSEntityPacket mSEntityPacket2 = this;
                this.p = this.J(string2, bl2, clazz2);
                Class<Short> clazz3 = Short.TYPE;
                boolean bl3 = true;
                String string3 = "posZ";
                MSEntityPacket mSEntityPacket3 = this;
                this.L = this.J(string3, bl3, clazz3);
            } else {
                Class[] classArray = new Class[]{};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "getX";
                MSEntityPacket mSEntityPacket = this;
                this.f = mSEntityPacket.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{};
                Class<Integer> clazz4 = Integer.TYPE;
                boolean bl4 = true;
                String string4 = "getY";
                MSEntityPacket mSEntityPacket4 = this;
                this.Y = this.Y(string4, bl4, clazz4, classArray2);
                Class[] classArray3 = new Class[]{};
                Class<Integer> clazz5 = Integer.TYPE;
                boolean bl5 = true;
                String string5 = "getZ";
                MSEntityPacket mSEntityPacket5 = this;
                this.m = this.Y(string5, bl5, clazz5, classArray3);
            }
        } else {
            Class[] classArray = new Class[]{};
            Class<Byte> clazz = Byte.TYPE;
            boolean bl = Wrapper.isNativeAvailable;
            String string = "func_149062_c";
            MSEntityPacket mSEntityPacket = this;
            this.f = mSEntityPacket.Y(string, bl, clazz, classArray);
            Class[] classArray4 = new Class[]{};
            Class<Byte> clazz6 = Byte.TYPE;
            boolean bl6 = Wrapper.isNativeAvailable;
            String string6 = "func_149061_d";
            MSEntityPacket mSEntityPacket6 = this;
            this.Y = this.Y(string6, bl6, clazz6, classArray4);
            Class[] classArray5 = new Class[]{};
            Class<Byte> clazz7 = Byte.TYPE;
            boolean bl7 = Wrapper.isNativeAvailable;
            String string7 = "func_149064_e";
            MSEntityPacket mSEntityPacket7 = this;
            this.m = this.Y(string7, bl7, clazz7, classArray5);
        }
    }

    private int O(Object object) {
        return this.L.getShort(object);
    }

    public static int P(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.g(object);
    }


    private int M(Object object) {
        return this.f.invokeByte(object, new Object[0]);
    }

    public static int O(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.M(object);
    }

    public static int t(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.O(object);
    }

    public static Object W(MSEntityPacket mSEntityPacket, Object object, Object object2) {
        return mSEntityPacket.l(object, object2);
    }

    private Object l(Object object, Object object2) {
        return this.q.invokeObject(object, object2);
    }

    private int s(Object object) {
        return this.p.getShort(object);
    }

    public static int U(MSEntityPacket mSEntityPacket, Object object) {
        return mSEntityPacket.R(object);
    }

    private int m(Object object) {
        return this.m.invokeByte(object, new Object[0]);
    }

    private int g(Object object) {
        return this.Y.invokeByte(object, new Object[0]);
    }
}

