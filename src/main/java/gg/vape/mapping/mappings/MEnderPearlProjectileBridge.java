package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntity;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEnderPearlProjectileBridge
extends Mapping {
    private MappingMethod E;

    public MEnderPearlProjectileBridge() {
        this(MEntity.P());
    }

    private MEnderPearlProjectileBridge(int n) {
        super(MappedClasses.lv);
        if (n != 0) {
            if (ForgeVersion.MC_1_21_4.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getOwner";
                MEnderPearlProjectileBridge mEnderPearlProjectileBridge = this;
                this.E = mEnderPearlProjectileBridge.Y(string, bl, clazz, classArray);
            }
            return;
        }
        if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.zc;
            boolean bl = true;
            String string = "getOwner";
            Class<?> clazz2 = MappedClasses.YV;
            MEnderPearlProjectileBridge mEnderPearlProjectileBridge = this;
            this.E = mEnderPearlProjectileBridge.W(clazz2, string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.zc;
            boolean bl = true;
            String string = "getOwner";
            MEnderPearlProjectileBridge mEnderPearlProjectileBridge = this;
            this.E = mEnderPearlProjectileBridge.Y(string, bl, clazz, classArray); 
        }
    }

    public static Object w(MEnderPearlProjectileBridge mEnderPearlProjectileBridge, Object object) {
        return mEnderPearlProjectileBridge.a(object);
    }

    private Object a(Object object) {
        return this.E.L(object, new Object[0]);
    }

}
