package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketEntityAction
extends Mapping {
    private MappingMethod N;
    private MappingMethod Q;

    public MCPacketEntityAction() {
        this(MPacketIdFactory.A());
    }

    private MCPacketEntityAction(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Dj);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray = new Class[]{MappedClasses.zc, MappedClasses.Do};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketEntityAction mCPacketEntityAction = this;
                this.Q = mCPacketEntityAction.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.zc, Integer.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MCPacketEntityAction mCPacketEntityAction = this;
                this.N = mCPacketEntityAction.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.zc, Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketEntityAction mCPacketEntityAction = this;
        this.N = mCPacketEntityAction.Y(string, bl, clazz, classArray); 
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Object u(Object object, int n) {
        return this.N.O(object, n);
    }

    public Object k(Object object, Object object2) {
        return this.Q.O(object, object2);
    }
}
