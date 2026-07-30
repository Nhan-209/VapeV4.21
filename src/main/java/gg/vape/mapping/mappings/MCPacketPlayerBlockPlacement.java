package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayerBlockPlacement
extends Mapping {
    private MappingField Q;
    private MappingField K;
    private MappingField j;
    private MappingField a;
    private MappingField T;
    private MappingField P;
    private MappingField y;
    private MappingField x;
    private MappingField B;

    public float u(Object object) {
        return this.Q.getFloat(object);
    }

    public Object N(Object object) {
        return this.y.getObject(object);
    }

    public MCPacketPlayerBlockPlacement() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayerBlockPlacement(GuiComponent[] guiComponentArray) {
        super(MappedClasses.YB);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_16_5_ACTUAL.v()) {
            if (ForgeVersion.MC_1_7_10.L() && !Wrapper.vapeInstance.isVanillaMinecraftPresent()) {
                Class clazz = MappedClasses.VK;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "field_149580_e";
                MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement = this;
                this.a = mCPacketPlayerBlockPlacement.J(string, bl, clazz);
            } else if (!ForgeVersion.MC_1_12_2.d()) {
                Class clazz = MappedClasses.VK;
                boolean bl = true;
                String string = "stack";
                MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement = this;
                this.a = mCPacketPlayerBlockPlacement.J(string, bl, clazz);
            }
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class clazz = MappedClasses.lf;
                boolean bl = true;
                String string = "position";
                MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement = this;
                this.x = mCPacketPlayerBlockPlacement.J(string, bl, clazz);
            }
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "placedBlockDirection";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement = this;
            this.P = mCPacketPlayerBlockPlacement.J(string, bl, clazz);
            Class<Float> clazz2 = Float.TYPE;
            boolean bl2 = true;
            String string2 = "facingX";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement2 = this;
            this.Q = this.J(string2, bl2, clazz2);
            Class<Float> clazz3 = Float.TYPE;
            boolean bl3 = true;
            String string3 = "facingY";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement3 = this;
            this.B = this.J(string3, bl3, clazz3);
            Class<Float> clazz4 = Float.TYPE;
            boolean bl4 = true;
            String string4 = "facingZ";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement4 = this;
            this.j = this.J(string4, bl4, clazz4);
        } else {
            Class clazz = MappedClasses.qF;
            boolean bl = true;
            String string = "blockHit";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement = this;
            this.T = mCPacketPlayerBlockPlacement.J(string, bl, clazz); 
            Class clazz5 = MappedClasses.Yf;
            boolean bl5 = true;
            String string5 = "hand";
            MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement5 = this;
            this.y = this.J(string5, bl5, clazz5);
            if (ForgeVersion.MC_1_21_11.d()) {
                Class<Integer> clazz6 = Integer.TYPE;
                boolean bl6 = true;
                String string6 = "sequence";
                MCPacketPlayerBlockPlacement mCPacketPlayerBlockPlacement6 = this;
                this.K = this.J(string6, bl6, clazz6);
            }
        }
    }

    public int I(Object object) {
        return this.K.getInt(object);
    }

    public Object b(Object object) {
        return this.T.getObject(object);
    }

    public float j(Object object) {
        return this.j.getFloat(object);
    }


    public float h(Object object) {
        return this.B.getFloat(object);
    }

    public Object p(Object object) {
        return this.a.getObject(object);
    }

    public int s(Object object) {
        return this.P.getInt(object);
    }

    public Object V(Object object) {
        return this.x.getObject(object);
    }
}

