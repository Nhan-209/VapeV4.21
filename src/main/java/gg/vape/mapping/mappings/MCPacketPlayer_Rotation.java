package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketPlayer_Rotation
extends Mapping {
    private final MappingMethod o;

    public MCPacketPlayer_Rotation() {
        this(MPacketIdFactory.A());
    }

    private MCPacketPlayer_Rotation(GuiComponent[] guiComponentArray) {
        super(MappedClasses.qw);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_21_4.d()) {
                Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Boolean.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_Rotation mCPacketPlayer_Rotation = this;
                this.o = mCPacketPlayer_Rotation.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Boolean.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketPlayer_Rotation mCPacketPlayer_Rotation = this;
                this.o = mCPacketPlayer_Rotation.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{Float.TYPE, Float.TYPE, Boolean.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketPlayer_Rotation mCPacketPlayer_Rotation = this;
        this.o = mCPacketPlayer_Rotation.Y(string, bl, clazz, classArray); 
    }

    private Object w(float f, float f2, boolean bl) {
        return this.o.O(Float.valueOf(f), Float.valueOf(f2), bl);
    }

    private Object o(float f, float f2, boolean bl, boolean bl2) {
        return this.o.O(Float.valueOf(f), Float.valueOf(f2), bl, bl2);
    }


    public static Object B(MCPacketPlayer_Rotation mCPacketPlayer_Rotation, float f, float f2, boolean bl, boolean bl2) {
        return mCPacketPlayer_Rotation.o(f, f2, bl, bl2);
    }

    public static Object L(MCPacketPlayer_Rotation mCPacketPlayer_Rotation, float f, float f2, boolean bl) {
        return mCPacketPlayer_Rotation.w(f, f2, bl);
    }
}
