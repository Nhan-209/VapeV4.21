package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MBiomeRegistrySwitch;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MBiomeRegistryName
extends Mapping {
    private static final String b = "name";
    private MappingField l;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MBiomeRegistryName() {
        this(MBiomeRegistrySwitch.L());
    }

    private MBiomeRegistryName(int[] nArray) {
        super(MappedClasses.h);
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = b;
        MBiomeRegistryName mBiomeRegistryName = this;
        this.l = this.J(string, bl, clazz);
        if (nArray != null) {
            return;
        }
        GuiComponent.D(new GuiComponent[5]);
    }

    private String J(Object object) {
        return (String)this.l.getObject(object);
    }

    public static String h(MBiomeRegistryName mBiomeRegistryName, Object object) {
        return mBiomeRegistryName.J(object);
    }
}

