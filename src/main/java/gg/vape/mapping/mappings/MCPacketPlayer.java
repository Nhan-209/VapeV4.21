package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayer
extends Mapping {
    private final MappingField U;
    private final MappingField n;
    private final MappingMethod G;
    private final MappingField L;
    private final MappingField K;
    private final MappingField w;
    private final MappingField i;

    public MCPacketPlayer() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayer(GuiComponent[] guiComponentArray) {
        super(MappedClasses.qD);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MCPacketPlayer mCPacketPlayer = this;
            this.G = mCPacketPlayer.Y(string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_20_6.d()) {
            Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MCPacketPlayer mCPacketPlayer = this;
            this.G = mCPacketPlayer.Y(string, bl, clazz, classArray);
        } else {
            Class[] classArray = new Class[]{Boolean.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MCPacketPlayer mCPacketPlayer = this;
            this.G = mCPacketPlayer.Y(string, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            if (Wrapper.c.isVanillaMinecraftPresent()) {
                Class<Double> clazz = Double.TYPE;
                boolean bl = true;
                String string = "x";
                MCPacketPlayer mCPacketPlayer = this;
                this.K = mCPacketPlayer.J(string, bl, clazz);
                Class<Double> clazz2 = Double.TYPE;
                boolean bl2 = true;
                String string2 = "y";
                MCPacketPlayer mCPacketPlayer2 = this;
                this.w = this.J(string2, bl2, clazz2);
                Class<Double> clazz3 = Double.TYPE;
                boolean bl3 = true;
                String string3 = "z";
                MCPacketPlayer mCPacketPlayer3 = this;
                this.U = this.J(string3, bl3, clazz3);
                Class<Float> clazz4 = Float.TYPE;
                boolean bl4 = true;
                String string4 = "yaw";
                MCPacketPlayer mCPacketPlayer4 = this;
                this.i = this.J(string4, bl4, clazz4);
                Class<Float> clazz5 = Float.TYPE;
                boolean bl5 = true;
                String string5 = "pitch";
                MCPacketPlayer mCPacketPlayer5 = this;
                this.L = this.J(string5, bl5, clazz5);
            } else {
                Class<Double> clazz = Double.TYPE;
                boolean bl = Wrapper.G;
                String string = "field_149479_a";
                MCPacketPlayer mCPacketPlayer = this;
                this.K = mCPacketPlayer.J(string, bl, clazz);
                Class<Double> clazz6 = Double.TYPE;
                boolean bl6 = Wrapper.G;
                String string6 = "field_149477_b";
                MCPacketPlayer mCPacketPlayer6 = this;
                this.w = this.J(string6, bl6, clazz6);
                Class<Double> clazz7 = Double.TYPE;
                boolean bl7 = Wrapper.G;
                String string7 = "field_149475_d";
                MCPacketPlayer mCPacketPlayer7 = this;
                this.U = this.J(string7, bl7, clazz7);
                Class<Float> clazz8 = Float.TYPE;
                boolean bl8 = Wrapper.G;
                String string8 = "field_149476_e";
                MCPacketPlayer mCPacketPlayer8 = this;
                this.i = this.J(string8, bl8, clazz8);
                Class<Float> clazz9 = Float.TYPE;
                boolean bl9 = Wrapper.G;
                String string9 = "field_149473_f";
                MCPacketPlayer mCPacketPlayer9 = this;
                this.L = this.J(string9, bl9, clazz9);
            }
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_149480_h";
            MCPacketPlayer mCPacketPlayer = this;
            this.n = mCPacketPlayer.J(string, bl, clazz);
        } else {
            Class<Double> clazz = Double.TYPE;
            boolean bl = true;
            String string = "x";
            MCPacketPlayer mCPacketPlayer = this;
            this.K = mCPacketPlayer.J(string, bl, clazz); 
            Class<Double> clazz10 = Double.TYPE;
            boolean bl10 = true;
            String string10 = "y";
            MCPacketPlayer mCPacketPlayer10 = this;
            this.w = this.J(string10, bl10, clazz10);
            Class<Double> clazz11 = Double.TYPE;
            boolean bl11 = true;
            String string11 = "z";
            MCPacketPlayer mCPacketPlayer11 = this;
            this.U = this.J(string11, bl11, clazz11);
            if (ForgeVersion.MC_1_16_5.d()) {
                Class<Float> clazz12 = Float.TYPE;
                boolean bl12 = true;
                String string12 = "yRot";
                MCPacketPlayer mCPacketPlayer12 = this;
                this.i = this.J(string12, bl12, clazz12);
                Class<Float> clazz13 = Float.TYPE;
                boolean bl13 = true;
                String string13 = "xRot";
                MCPacketPlayer mCPacketPlayer13 = this;
                this.L = this.J(string13, bl13, clazz13);
            } else {
                Class<Float> clazz14 = Float.TYPE;
                boolean bl14 = true;
                String string14 = "yaw";
                MCPacketPlayer mCPacketPlayer14 = this;
                this.i = this.J(string14, bl14, clazz14);
                Class<Float> clazz15 = Float.TYPE;
                boolean bl15 = true;
                String string15 = "pitch";
                MCPacketPlayer mCPacketPlayer15 = this;
                this.L = this.J(string15, bl15, clazz15);
            }
            Class<Boolean> clazz16 = Boolean.TYPE;
            boolean bl16 = true;
            String string16 = "moving";
            MCPacketPlayer mCPacketPlayer16 = this;
            this.n = this.J(string16, bl16, clazz16);
        }
    }

    public static Object T(MCPacketPlayer mCPacketPlayer, boolean bl) {
        return mCPacketPlayer.S(bl);
    }

    private Object o(double d, double d2, double d3, float f, float f2, boolean bl, boolean bl2, boolean bl3) {
        return this.G.O(d, d2, d3, Float.valueOf(f), Float.valueOf(f2), bl, bl2, bl3);
    }

    public boolean g(Object object) {
        return this.n.getBoolean(object);
    }

    public float j(Object object) {
        return this.L.getFloat(object);
    }

    public float A(Object object) {
        return this.i.getFloat(object);
    }

    public double x(Object object) {
        return this.w.getDouble(object);
    }

    private Object S(double d, double d2, double d3, float f, float f2, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        return this.G.O(d, d2, d3, Float.valueOf(f), Float.valueOf(f2), bl, bl2, bl3, bl4);
    }

    public static Object s(MCPacketPlayer mCPacketPlayer, double d, double d2, double d3, float f, float f2, boolean bl, boolean bl2, boolean bl3) {
        return mCPacketPlayer.o(d, d2, d3, f, f2, bl, bl2, bl3);
    }

    public double b(Object object) {
        return this.U.getDouble(object);
    }


    public double i(Object object) {
        return this.K.getDouble(object);
    }

    private Object S(boolean bl) {
        return this.G.O(bl);
    }
}

