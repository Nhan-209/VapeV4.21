package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketEntity
extends Mapping {
    private MappingField L;
    private MappingField f;
    private MappingField X;
    private MappingField D;
    private MappingField c;
    private MappingField H;
    private MappingField g;

    public Object K(Object object) {
        return this.L.getObject(object);
    }

    public int g(Object object) {
        return this.H.getInt(object);
    }

    public byte C(Object object) {
        return (byte)this.X.getInt(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int U(Object object) {
        return this.g.getInt(object);
    }

    public int m(Object object) {
        return this.D.getInt(object);
    }

    public int R(Object object) {
        return this.f.getInt(object);
    }

    public MSPacketEntity() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketEntity(int[] nArray) {
        super(MappedClasses.s);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_21_4.d()) {
            Class clazz = MappedClasses.Dd;
            boolean bl = true;
            String string = "change";
            MSPacketEntity mSPacketEntity = this;
            this.L = mSPacketEntity.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "id";
            MSPacketEntity mSPacketEntity2 = this;
            this.f = this.J(string2, bl2, clazz2);
        } else if (ForgeVersion.MC_1_7_10.L()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_149458_a";
            MSPacketEntity mSPacketEntity = this;
            this.f = mSPacketEntity.J(string, bl, clazz);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = Wrapper.G;
            String string3 = "field_149456_b";
            MSPacketEntity mSPacketEntity3 = this;
            this.H = this.J(string3, bl3, clazz3);
            Class<Integer> clazz4 = Integer.TYPE;
            boolean bl4 = Wrapper.G;
            String string4 = "field_149457_c";
            MSPacketEntity mSPacketEntity4 = this;
            this.g = this.J(string4, bl4, clazz4);
            Class<Integer> clazz5 = Integer.TYPE;
            boolean bl5 = Wrapper.G;
            String string5 = "field_149454_d";
            MSPacketEntity mSPacketEntity5 = this;
            this.D = this.J(string5, bl5, clazz5);
            Class<Byte> clazz6 = Byte.TYPE;
            boolean bl6 = Wrapper.G;
            String string6 = "field_149455_e";
            MSPacketEntity mSPacketEntity6 = this;
            this.X = this.J(string6, bl6, clazz6);
            Class<Byte> clazz7 = Byte.TYPE;
            boolean bl7 = Wrapper.G;
            String string7 = "field_149453_f";
            MSPacketEntity mSPacketEntity7 = this;
            this.c = this.J(string7, bl7, clazz7);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "id";
            MSPacketEntity mSPacketEntity = this;
            this.f = mSPacketEntity.J(string, bl, clazz);
            if (ForgeVersion.MC_1_17.d()) {
                Class<Double> clazz8 = Double.TYPE;
                boolean bl8 = true;
                String string8 = "x";
                MSPacketEntity mSPacketEntity8 = this;
                this.H = this.J(string8, bl8, clazz8);
                Class<Double> clazz9 = Double.TYPE;
                boolean bl9 = true;
                String string9 = "y";
                MSPacketEntity mSPacketEntity9 = this;
                this.g = this.J(string9, bl9, clazz9);
                Class<Double> clazz10 = Double.TYPE;
                boolean bl10 = true;
                String string10 = "z";
                MSPacketEntity mSPacketEntity10 = this;
                this.D = this.J(string10, bl10, clazz10);
            } else {
                Class<Double> clazz11 = Double.TYPE;
                boolean bl11 = true;
                String string11 = "x";
                MSPacketEntity mSPacketEntity11 = this;
                this.H = this.J(string11, bl11, clazz11);
                Class<Double> clazz12 = Double.TYPE;
                boolean bl12 = true;
                String string12 = "y";
                MSPacketEntity mSPacketEntity12 = this;
                this.g = this.J(string12, bl12, clazz12);
                Class<Double> clazz13 = Double.TYPE;
                boolean bl13 = true;
                String string13 = "z";
                MSPacketEntity mSPacketEntity13 = this;
                this.D = this.J(string13, bl13, clazz13);
            }
            Class<Byte> clazz14 = Byte.TYPE;
            boolean bl14 = true;
            String string14 = "yRot";
            MSPacketEntity mSPacketEntity14 = this;
            this.X = this.J(string14, bl14, clazz14);
            Class<Byte> clazz15 = Byte.TYPE;
            boolean bl15 = true;
            String string15 = "xRot";
            MSPacketEntity mSPacketEntity15 = this;
            this.c = this.J(string15, bl15, clazz15);
        } else {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityId";
            MSPacketEntity mSPacketEntity = this;
            this.f = mSPacketEntity.J(string, bl, clazz);
            Class<Integer> clazz16 = Integer.TYPE;
            boolean bl16 = true;
            String string16 = "posX";
            MSPacketEntity mSPacketEntity16 = this;
            this.H = this.J(string16, bl16, clazz16);
            Class<Integer> clazz17 = Integer.TYPE;
            boolean bl17 = true;
            String string17 = "posY";
            MSPacketEntity mSPacketEntity17 = this;
            this.g = this.J(string17, bl17, clazz17);
            Class<Integer> clazz18 = Integer.TYPE;
            boolean bl18 = true;
            String string18 = "posZ";
            MSPacketEntity mSPacketEntity18 = this;
            this.D = this.J(string18, bl18, clazz18);
            Class<Byte> clazz19 = Byte.TYPE;
            boolean bl19 = true;
            String string19 = "yaw";
            MSPacketEntity mSPacketEntity19 = this;
            this.X = this.J(string19, bl19, clazz19);
            Class<Byte> clazz20 = Byte.TYPE;
            boolean bl20 = true;
            String string20 = "pitch";
            MSPacketEntity mSPacketEntity20 = this;
            this.c = this.J(string20, bl20, clazz20);
        }
    }

    public byte u(Object object) {
        return (byte)this.c.getInt(object);
    }
}

