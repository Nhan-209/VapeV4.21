package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MBiomeRegistrySwitch;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBiome
extends Mapping {
    private MappingField w;
    private MappingField g;

    public MBiome() {
        this(MBiomeRegistrySwitch.L());
    }

    private MBiome(int[] nArray) {
        super(MappedClasses.uK);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_16_5.d() && ForgeVersion.MC_1_20_6.v()) {
            Class clazz = MappedClasses.h;
            boolean bl = true;
            String string = "category";
            MBiome mBiome = this;
            this.w = mBiome.J(string, bl, clazz);
        } else if (ForgeVersion.MC_1_16_5.v()) {
            Class<String> clazz = String.class;
            boolean bl = true;
            String string = "biomeName";
            MBiome mBiome = this;
            this.g = mBiome.J(string, bl, clazz); 
        }
        if (GuiComponent.getLegacyComponentState() == null) {
            MBiomeRegistrySwitch.R(new int[1]);
        }
    }

    public static Object D(MBiome mBiome, Object object) {
        return mBiome.o(object);
    }

    public String q(Object object) {
        return (String)this.g.getObject(object);
    }

    private Object o(Object object) {
        return this.w.getObject(object);
    }

}
