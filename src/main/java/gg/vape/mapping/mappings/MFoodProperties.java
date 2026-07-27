package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MFoods;
import gg.vape.ui.click.component.GuiComponent;

public class MFoodProperties
extends Mapping {
    private final MappingField g;
    private final MappingField b;
    private final MappingField y;

    public boolean p(Object object) {
        return this.b.getBoolean(object);
    }

    public float G(Object object) {
        return this.g.getFloat(object);
    }

    public MFoodProperties() {
        super(MappedClasses.zj);
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "nutrition";
        MFoodProperties mFoodProperties = this;
        this.y = this.J(string, bl, clazz);
        Class<Float> clazz2 = Float.TYPE;
        boolean bl2 = true;
        String string2 = "saturation";
        MFoodProperties mFoodProperties2 = this;
        this.g = this.J(string2, bl2, clazz2);
        Class<Boolean> clazz3 = Boolean.TYPE;
        boolean bl3 = true;
        String string3 = "canAlwaysEat";
        MFoodProperties mFoodProperties3 = this;
        this.b = this.J(string3, bl3, clazz3);
        String string4 = MFoods.A();
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MFoods.h("P9qc6b");
        }
    }

    public int J(Object object) {
        return this.y.getInt(object);
    }

}

