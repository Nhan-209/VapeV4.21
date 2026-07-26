package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityEnderPearl;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEntityItem
extends Mapping {
    private final MappingMethod l;

    public static Object z(MEntityItem mEntityItem, Object object) {
        return mEntityItem.Z(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Object Z(Object object) {
        return this.l.L(object, new Object[0]);
    }

    public MEntityItem() {
        this(MEntityEnderPearl.I());
    }

    private MEntityItem(GuiComponent[] guiComponentArray) {
        super(MappedClasses.zW);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.VK;
                boolean bl = true;
                String string = "getItem";
                MEntityItem mEntityItem = this;
                this.l = mEntityItem.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.VK;
                boolean bl = true;
                String string = "getEntityItem";
                MEntityItem mEntityItem = this;
                this.l = mEntityItem.Y(string, bl, clazz, classArray);
            }
            return;
        }
        GuiComponent.D(new GuiComponent[4]);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.VK;
        boolean bl = true;
        String string = "getEntityItem";
        MEntityItem mEntityItem = this;
        this.l = mEntityItem.Y(string, bl, clazz, classArray); 
    }
}
