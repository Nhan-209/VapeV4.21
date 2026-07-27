package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityPlayerSP;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEntityOtherPlayerMP
extends Mapping {
    private final MappingMethod n;


    public Object T(Object object, Object object2) {
        return this.n.O(object, object2);
    }

    public MEntityOtherPlayerMP() {
        this(MEntityPlayerSP.r());
    }

    private MEntityOtherPlayerMP(GuiComponent[] guiComponentArray) {
        super(MappedClasses.lG);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{MappedClasses.Z, MappedClasses.VD};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MEntityOtherPlayerMP mEntityOtherPlayerMP = this;
                this.n = mEntityOtherPlayerMP.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.VD};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MEntityOtherPlayerMP mEntityOtherPlayerMP = this;
                this.n = mEntityOtherPlayerMP.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.VD};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MEntityOtherPlayerMP mEntityOtherPlayerMP = this;
        this.n = mEntityOtherPlayerMP.Y(string, bl, clazz, classArray); 
    }
}
