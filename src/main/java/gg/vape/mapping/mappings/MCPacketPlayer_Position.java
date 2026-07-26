package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayer_Position
extends Mapping {
    private MappingMethod S;

    private Object L(double d, double d2, double d3, boolean bl) {
        return this.S.O(d, d2, d3, bl);
    }

    public MCPacketPlayer_Position() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayer_Position(GuiComponent[] guiComponentArray) {
        super(MappedClasses.ul);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_7_10.Y()) {
                if (ForgeVersion.MC_1_21_4.d()) {
                    Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE, Boolean.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = false;
                    String string = "<init>";
                    MCPacketPlayer_Position mCPacketPlayer_Position = this;
                    this.S = mCPacketPlayer_Position.Y(string, bl, clazz, classArray);
                } else {
                    Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = false;
                    String string = "<init>";
                    MCPacketPlayer_Position mCPacketPlayer_Position = this;
                    this.S = mCPacketPlayer_Position.Y(string, bl, clazz, classArray);
                }
            } else {
                Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_Position mCPacketPlayer_Position = this;
                this.S = mCPacketPlayer_Position.Y(string, bl, clazz, classArray);
            }
            return;
        }
        if (ForgeVersion.MC_1_7_10.Y()) {
            Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE, Boolean.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MCPacketPlayer_Position mCPacketPlayer_Position = this;
            this.S = mCPacketPlayer_Position.Y(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketPlayer_Position mCPacketPlayer_Position = this;
        this.S = mCPacketPlayer_Position.Y(string, bl, clazz, classArray); 
        Class[] classArray2 = new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Boolean.TYPE};
        Class<Void> clazz2 = Void.TYPE;
        boolean bl2 = false;
        String string2 = "<init>";
        MCPacketPlayer_Position mCPacketPlayer_Position2 = this;
        this.S = this.Y(string2, bl2, clazz2, classArray2);
    }

    public Object R(double d, double d2, double d3, boolean bl, boolean bl2) {
        return this.S.O(d, d2, d3, bl, bl2);
    }

    public static Object T(MCPacketPlayer_Position mCPacketPlayer_Position, double d, double d2, double d3, boolean bl) {
        return mCPacketPlayer_Position.L(d, d2, d3, bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Object U(double d, double d2, double d3, double d4, boolean bl) {
        return this.S.O(d, d2, d3, d4, bl);
    }
}

