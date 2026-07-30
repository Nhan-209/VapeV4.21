package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayerDigging
extends Mapping {
    private MappingField N;
    private MappingField J;
    private MappingField K;
    private MappingField W;

    public static Object o(MCPacketPlayerDigging mCPacketPlayerDigging, Object object) {
        return mCPacketPlayerDigging.m(object);
    }

    private Object K(Object object) {
        return this.W.getObject(object);
    }

    private Object m(Object object) {
        return this.J.getObject(object);
    }

    public static int a(MCPacketPlayerDigging mCPacketPlayerDigging, Object object) {
        return mCPacketPlayerDigging.A(object);
    }


    private int A(Object object) {
        return this.N.getInt(object);
    }

    private Object I(Object object) {
        return this.K.getObject(object);
    }

    public MCPacketPlayerDigging() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayerDigging(GuiComponent[] guiComponentArray) {
        super(MappedClasses.DN);
        if (guiComponentArray != null) {
            Class clazz = MappedClasses.lf;
            boolean bl = true;
            String string = "position";
            MCPacketPlayerDigging mCPacketPlayerDigging = this;
            this.J = mCPacketPlayerDigging.J(string, bl, clazz);
            Class clazz2 = MappedClasses.q0;
            boolean bl2 = true;
            String string2 = "facing";
            MCPacketPlayerDigging mCPacketPlayerDigging2 = this;
            this.W = this.J(string2, bl2, clazz2);
            if (ForgeVersion.MC_1_8_9.d()) {
                if (ForgeVersion.MC_1_8_9.L()) {
                    Class clazz3 = MappedClasses.FL;
                    boolean bl3 = true;
                    String string3 = "status";
                    MCPacketPlayerDigging mCPacketPlayerDigging3 = this;
                    this.K = this.J(string3, bl3, clazz3);
                }
            } else {
                Class<Integer> clazz4 = Integer.TYPE;
                boolean bl4 = Wrapper.isNativeAvailable;
                String string4 = "field_149508_e";
                MCPacketPlayerDigging mCPacketPlayerDigging4 = this;
                this.N = this.J(string4, bl4, clazz4);
            }
            return;
        }
        Class clazz = MappedClasses.lf;
        boolean bl = true;
        String string = "position";
        MCPacketPlayerDigging mCPacketPlayerDigging = this;
        this.J = mCPacketPlayerDigging.J(string, bl, clazz); 
        Class clazz5 = MappedClasses.q0;
        boolean bl5 = true;
        String string5 = "facing";
        MCPacketPlayerDigging mCPacketPlayerDigging5 = this;
        this.N = this.J(string5, bl5, clazz5);
    }

    public static Object K(MCPacketPlayerDigging mCPacketPlayerDigging, Object object) {
        return mCPacketPlayerDigging.I(object);
    }

    public static Object B(MCPacketPlayerDigging mCPacketPlayerDigging, Object object) {
        return mCPacketPlayerDigging.K(object);
    }
}

