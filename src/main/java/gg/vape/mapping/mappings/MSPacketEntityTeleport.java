package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketEntityTeleport
extends Mapping {
    private MappingField r;
    public MappingField Z;
    public MappingField H;
    public MappingField M;
    public MappingField G;
    public MappingField U;

    public int z(Object object) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return (int)this.M.getDouble(object);
        }
        return this.M.getInt(object);
    }

    public byte s(Object object) {
        return (byte)this.G.getInt(object);
    }


    public int a(Object object) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return (int)this.Z.getDouble(object);
        }
        return this.Z.getInt(object);
    }

    public byte v(Object object) {
        return (byte)this.U.getInt(object);
    }

    public int O(Object object) {
        return this.r.getInt(object);
    }

    public int J(Object object) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return (int)this.H.getDouble(object);
        }
        return this.H.getInt(object);
    }

    public MSPacketEntityTeleport() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketEntityTeleport(int[] nArray) {
        super(MappedClasses.uW);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_7_10.L()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_148957_a";
            MSPacketEntityTeleport mSPacketEntityTeleport = this;
            this.r = mSPacketEntityTeleport.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = Wrapper.G;
            String string2 = "field_148956_c";
            MSPacketEntityTeleport mSPacketEntityTeleport2 = this;
            this.H = this.J(string2, bl2, clazz2);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = Wrapper.G;
            String string3 = "field_148953_d";
            MSPacketEntityTeleport mSPacketEntityTeleport3 = this;
            this.M = this.J(string3, bl3, clazz3);
            Class<Integer> clazz4 = Integer.TYPE;
            boolean bl4 = Wrapper.G;
            String string4 = "field_148954_e";
            MSPacketEntityTeleport mSPacketEntityTeleport4 = this;
            this.Z = this.J(string4, bl4, clazz4);
            Class<Byte> clazz5 = Byte.TYPE;
            boolean bl5 = Wrapper.G;
            String string5 = "field_148951_f";
            MSPacketEntityTeleport mSPacketEntityTeleport5 = this;
            this.G = this.J(string5, bl5, clazz5);
            Class<Byte> clazz6 = Byte.TYPE;
            boolean bl6 = Wrapper.G;
            String string6 = "field_148952_g";
            MSPacketEntityTeleport mSPacketEntityTeleport6 = this;
            this.U = this.J(string6, bl6, clazz6);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityId";
            MSPacketEntityTeleport mSPacketEntityTeleport = this;
            this.r = mSPacketEntityTeleport.J(string, bl, clazz);
            if (ForgeVersion.MC_1_17.d()) {
                Class<Double> clazz7 = Double.TYPE;
                boolean bl7 = true;
                String string7 = "x";
                MSPacketEntityTeleport mSPacketEntityTeleport7 = this;
                this.H = this.J(string7, bl7, clazz7);
                Class<Double> clazz8 = Double.TYPE;
                boolean bl8 = true;
                String string8 = "y";
                MSPacketEntityTeleport mSPacketEntityTeleport8 = this;
                this.M = this.J(string8, bl8, clazz8);
                Class<Double> clazz9 = Double.TYPE;
                boolean bl9 = true;
                String string9 = "z";
                MSPacketEntityTeleport mSPacketEntityTeleport9 = this;
                this.Z = this.J(string9, bl9, clazz9);
            } else {
                Class<Double> clazz10 = Double.TYPE;
                boolean bl10 = true;
                String string10 = "x";
                MSPacketEntityTeleport mSPacketEntityTeleport10 = this;
                this.H = this.J(string10, bl10, clazz10);
                Class<Double> clazz11 = Double.TYPE;
                boolean bl11 = true;
                String string11 = "y";
                MSPacketEntityTeleport mSPacketEntityTeleport11 = this;
                this.M = this.J(string11, bl11, clazz11);
                Class<Double> clazz12 = Double.TYPE;
                boolean bl12 = true;
                String string12 = "z";
                MSPacketEntityTeleport mSPacketEntityTeleport12 = this;
                this.Z = this.J(string12, bl12, clazz12);
            }
            Class<Byte> clazz13 = Byte.TYPE;
            boolean bl13 = true;
            String string13 = "yRot";
            MSPacketEntityTeleport mSPacketEntityTeleport13 = this;
            this.G = this.J(string13, bl13, clazz13);
            Class<Byte> clazz14 = Byte.TYPE;
            boolean bl14 = true;
            String string14 = "xRot";
            MSPacketEntityTeleport mSPacketEntityTeleport14 = this;
            this.U = this.J(string14, bl14, clazz14);
        } else {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityId";
            MSPacketEntityTeleport mSPacketEntityTeleport = this;
            this.r = mSPacketEntityTeleport.J(string, bl, clazz);
            Class<Integer> clazz15 = Integer.TYPE;
            boolean bl15 = true;
            String string15 = "x";
            MSPacketEntityTeleport mSPacketEntityTeleport15 = this;
            this.H = this.J(string15, bl15, clazz15);
            Class<Integer> clazz16 = Integer.TYPE;
            boolean bl16 = true;
            String string16 = "y";
            MSPacketEntityTeleport mSPacketEntityTeleport16 = this;
            this.M = this.J(string16, bl16, clazz16);
            Class<Integer> clazz17 = Integer.TYPE;
            boolean bl17 = true;
            String string17 = "z";
            MSPacketEntityTeleport mSPacketEntityTeleport17 = this;
            this.Z = this.J(string17, bl17, clazz17);
            Class<Byte> clazz18 = Byte.TYPE;
            boolean bl18 = true;
            String string18 = "yaw";
            MSPacketEntityTeleport mSPacketEntityTeleport18 = this;
            this.G = this.J(string18, bl18, clazz18);
            Class<Byte> clazz19 = Byte.TYPE;
            boolean bl19 = true;
            String string19 = "pitch";
            MSPacketEntityTeleport mSPacketEntityTeleport19 = this;
            this.U = this.J(string19, bl19, clazz19);
        }
    }
}

