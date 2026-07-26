package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MItemAttributeModifiersComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MItemAttributeModifiersComponent$Entry
extends Mapping {
    private MappingField e;
    private MappingField C;

    public Object q(Object object) {
        return this.C.getObject(object);
    }

    public Object P(Object object) {
        return this.e.getObject(object);
    }

    public MItemAttributeModifiersComponent$Entry() {
        this(MItemAttributeModifiersComponent.F());
    }

    private MItemAttributeModifiersComponent$Entry(GuiComponent[] guiComponentArray) {
        super(MappedClasses.zP);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        Class clazz = MappedClasses.Vo;
        boolean bl = true;
        String string = "attribute";
        MItemAttributeModifiersComponent$Entry mItemAttributeModifiersComponent$Entry = this;
        this.e = this.J(string, bl, clazz);
        Class clazz2 = MappedClasses.z_;
        boolean bl2 = true;
        String string2 = "modifier";
        MItemAttributeModifiersComponent$Entry mItemAttributeModifiersComponent$Entry2 = this;
        this.C = this.J(string2, bl2, clazz2);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MItemAttributeModifiersComponent.M(new GuiComponent[5]);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

