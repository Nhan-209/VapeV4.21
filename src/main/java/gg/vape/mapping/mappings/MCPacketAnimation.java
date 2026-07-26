package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketAnimation
extends Mapping {
    private MappingMethod r;
    private MappingMethod y;

    public Object H() {
        return this.r.O(new Object[0]);
    }

    public MCPacketAnimation() {
        this(MPacketIdFactory.A());
    }

    private MCPacketAnimation(GuiComponent[] guiComponentArray) {
        super(MappedClasses.VF);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_12_2.d()) {
                Class[] classArray = new Class[]{MappedClasses.Yf};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketAnimation mCPacketAnimation = this;
                this.y = mCPacketAnimation.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketAnimation mCPacketAnimation = this;
                this.r = mCPacketAnimation.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketAnimation mCPacketAnimation = this;
        this.r = mCPacketAnimation.Y(string, bl, clazz, classArray); 
    }

    private Object Z(Object object) {
        return this.y.O(object);
    }

    public static Object A(MCPacketAnimation mCPacketAnimation, Object object) {
        return mCPacketAnimation.Z(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
