package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayer_PositionRotation
extends Mapping {
    private MappingMethod E;


    private Object l(double d, double d2, double d3, float f, float f2, boolean bl) {
        return this.E.O(d, d2, d3, Float.valueOf(f), Float.valueOf(f2), bl);
    }

    private Object U(double d, double d2, double d3, float f, float f2, boolean bl, boolean bl2) {
        return this.E.O(d, d2, d3, Float.valueOf(f), Float.valueOf(f2), bl, bl2);
    }

    public static Object b(MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation, double d, double d2, double d3, float f, float f2, boolean bl) {
        return mCPacketPlayer_PositionRotation.l(d, d2, d3, f, f2, bl);
    }

    public MCPacketPlayer_PositionRotation() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayer_PositionRotation(GuiComponent[] guiComponentArray) {
        super(MappedClasses.FK);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_21_4.d()) {
                Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation = this;
                this.E = mCPacketPlayer_PositionRotation.Y(string, bl, clazz, classArray);
            } else if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation = this;
                this.E = mCPacketPlayer_PositionRotation.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation = this;
                this.E = mCPacketPlayer_PositionRotation.Y(string, bl, clazz, classArray);
            }
            if (GuiComponent.getLegacyComponentState() == null) {
                MPacketIdFactory.x(new GuiComponent[2]);
            }
            return;
        }
        if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation = this;
            this.E = mCPacketPlayer_PositionRotation.Y(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation = this;
        this.E = mCPacketPlayer_PositionRotation.Y(string, bl, clazz, classArray); 
        if (GuiComponent.getLegacyComponentState() == null) {
            MPacketIdFactory.x(new GuiComponent[2]);
        }
    }

    public Object L(double d, double d2, double d3, double d4, float f, float f2, boolean bl) {
        return this.E.O(d, d2, d3, d4, Float.valueOf(f), Float.valueOf(f2), bl);
    }

    public static Object P(MCPacketPlayer_PositionRotation mCPacketPlayer_PositionRotation, double d, double d2, double d3, float f, float f2, boolean bl, boolean bl2) {
        return mCPacketPlayer_PositionRotation.U(d, d2, d3, f, f2, bl, bl2);
    }
}
