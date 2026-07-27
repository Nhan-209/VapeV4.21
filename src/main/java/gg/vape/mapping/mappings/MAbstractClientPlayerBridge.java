package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityPlayerSP;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MAbstractClientPlayerBridge
extends Mapping {
    private MappingField F;
    private MappingMethod O;

    public Object x(Object object) {
        return this.F.getObject(object);
    }

    public MAbstractClientPlayerBridge() {
        this(MEntityPlayerSP.r());
    }

    private MAbstractClientPlayerBridge(GuiComponent[] guiComponentArray) {
        super(MappedClasses.zt);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_21_10.d() && Vape.INSTANCE.isFabricMinecraftPresent()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.uZ;
                boolean bl = true;
                String string = "getSkin";
                Class clazz2 = MappedClasses.lB;
                MAbstractClientPlayerBridge mAbstractClientPlayerBridge = this;
                this.O = mAbstractClientPlayerBridge.W(clazz2, string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.zC;
                boolean bl = true;
                String string = "getLocationSkin";
                MAbstractClientPlayerBridge mAbstractClientPlayerBridge = this;
                this.O = mAbstractClientPlayerBridge.Y(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_1_21_10.d()) {
                Class clazz = MappedClasses.zT;
                boolean bl = true;
                String string = "clientAvatarState";
                MAbstractClientPlayerBridge mAbstractClientPlayerBridge = this;
                this.F = mAbstractClientPlayerBridge.J(string, bl, clazz);
            }
            return;
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.uZ;
            boolean bl = true;
            String string = "getSkin";
            Class clazz3 = MappedClasses.lB;
            MAbstractClientPlayerBridge mAbstractClientPlayerBridge = this;
            this.O = mAbstractClientPlayerBridge.W(clazz3, string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = "getLocationSkin";
        MAbstractClientPlayerBridge mAbstractClientPlayerBridge = this;
        this.O = mAbstractClientPlayerBridge.Y(string, bl, clazz, classArray); 
        if (ForgeVersion.MC_1_21_10.d()) {
            Class clazz4 = MappedClasses.zT;
            boolean bl2 = true;
            String string2 = "clientAvatarState";
            MAbstractClientPlayerBridge mAbstractClientPlayerBridge2 = this;
            this.F = this.J(string2, bl2, clazz4);
        }
    }

    private Object b(Object object) {
        return this.O.L(object, new Object[0]);
    }


    public static Object T(MAbstractClientPlayerBridge mAbstractClientPlayerBridge, Object object) {
        return mAbstractClientPlayerBridge.b(object);
    }
}

