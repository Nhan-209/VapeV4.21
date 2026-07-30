package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketUseEntityPacket
extends Mapping {
    private MappingField w;
    private MappingField I;
    private final MappingField g;
    private final MappingField u;

    private Object A(Object object) {
        if (this.w == null) {
            return null;
        }
        return this.w.getObject(object);
    }


    public static Object Q(MCPacketUseEntityPacket mCPacketUseEntityPacket, Object object) {
        return mCPacketUseEntityPacket.m(object);
    }

    public MCPacketUseEntityPacket() {
        this(MPacketIdFactory.A());
    }

    private MCPacketUseEntityPacket(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Fa);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_8_9.d()) {
            if (ForgeVersion.MC_1_17.d()) {
                if (ForgeVersion.MC_26_1.v()) {
                    Class clazz = MappedClasses.lw;
                    boolean bl = true;
                    String string = "action";
                    MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
                    this.w = mCPacketUseEntityPacket.J(string, bl, clazz);
                }
            } else {
                Class clazz = MappedClasses.D5;
                boolean bl = true;
                String string = "action";
                MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
                this.w = mCPacketUseEntityPacket.J(string, bl, clazz);
            }
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityId";
            MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
            this.g = mCPacketUseEntityPacket.J(string, bl, clazz);
        } else if (Wrapper.vapeInstance.isVanillaMinecraftPresent()) {
            Class clazz = MappedClasses.D5;
            boolean bl = true;
            String string = "action";
            MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
            this.w = mCPacketUseEntityPacket.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "entityId";
            MCPacketUseEntityPacket mCPacketUseEntityPacket2 = this;
            this.g = this.J(string2, bl2, clazz2);
        } else {
            Class clazz = MappedClasses.D5;
            boolean bl = Wrapper.isNativeAvailable;
            String string = "field_149566_b";
            MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
            this.w = mCPacketUseEntityPacket.J(string, bl, clazz);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = Wrapper.isNativeAvailable;
            String string3 = "field_149567_a";
            MCPacketUseEntityPacket mCPacketUseEntityPacket3 = this;
            this.g = this.J(string3, bl3, clazz3);
        }
        if (ForgeVersion.MC_26_1.d()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "entityId";
            Class clazz4 = MappedClasses.ZW;
            MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
            this.u = mCPacketUseEntityPacket.registerInstanceFieldForOwner(clazz4, string, bl, clazz);
        } else {
            this.u = null;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            if (ForgeVersion.MC_26_1.d()) {
                Class clazz = MappedClasses.qP;
                boolean bl = true;
                boolean bl4 = true;
                String string = "location";
                MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
                this.I = mCPacketUseEntityPacket.registerInstanceFieldWithSecondaryFlag(string, bl4, bl, clazz);
            } else if (ForgeVersion.MC_1_20_6.v()) {
                Class clazz = MappedClasses.qP;
                boolean bl = true;
                String string = "location";
                MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
                this.I = mCPacketUseEntityPacket.J(string, bl, clazz);
            }
        } else if (ForgeVersion.MC_1_8_9.d()) {
            Class clazz = MappedClasses.qP;
            boolean bl = true;
            String string = "hitVec";
            MCPacketUseEntityPacket mCPacketUseEntityPacket = this;
            this.I = mCPacketUseEntityPacket.J(string, bl, clazz); 
        }
    }

    public static Object k(MCPacketUseEntityPacket mCPacketUseEntityPacket, Object object) {
        return mCPacketUseEntityPacket.A(object);
    }

    public void O(Object object, int n) {
        if (ForgeVersion.MC_26_1.d() && MappedClasses.ZW != null && MappedClasses.ZW.isInstance(object)) {
            this.u.setInt(object, n);
            return;
        }
        this.g.setInt(object, n);
    }

    private Object m(Object object) {
        return this.I.getObject(object);
    }

    public int d(Object object) {
        if (ForgeVersion.MC_26_1.d() && MappedClasses.ZW != null && MappedClasses.ZW.isInstance(object)) {
            return this.u.getInt(object);
        }
        return this.g.getInt(object);
    }
}

