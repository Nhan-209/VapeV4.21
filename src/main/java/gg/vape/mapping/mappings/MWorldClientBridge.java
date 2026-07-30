package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPlayerControllerMP;
import gg.vape.wrapper.impl.ForgeVersion;

public class MWorldClientBridge
extends Mapping {
    private final MappingMethod M;
    private final MappingField r;
    private final MappingMethod I;

    public MWorldClientBridge() {
        this(MPlayerControllerMP.V());
    }

    private MWorldClientBridge(int n) {
        super(MappedClasses.ZT);
        if (n != 0) {
            if (ForgeVersion.MC_26_1.d()) {
                this.I = null;
                this.M = null;
                Class<Long> clazz = Long.TYPE;
                boolean bl = true;
                String string = "gameTime";
                MWorldClientBridge mWorldClientBridge = this;
                this.r = mWorldClientBridge.J(string, bl, clazz);
            } else {
                Class[] classArray = new Class[]{Long.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "setGameTime";
                MWorldClientBridge mWorldClientBridge = this;
                this.I = mWorldClientBridge.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{Long.TYPE};
                Class<Void> clazz2 = Void.TYPE;
                boolean bl2 = true;
                String string2 = "setDayTime";
                MWorldClientBridge mWorldClientBridge2 = this;
                this.M = this.Y(string2, bl2, clazz2, classArray2);
                this.r = null;
            }
            return;
        }
        this.r = null;
        this.I = null;
        this.M = null;
    }

    public void E(Object object, long l) {
        if (this.r != null) {
            this.r.setLong(object, l);
            return;
        }
        this.I.invokeVoid(object, l);
    }

    public void G(Object object, long l) {
        if (this.r != null) {
            this.r.setLong(object, l);
            return;
        }
        this.M.invokeVoid(object, l);
    }

}

