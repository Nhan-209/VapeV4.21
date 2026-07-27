package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ResourceLocationConstantPair;

public class MResourceLocationConstantsBridge
extends Mapping {
    private MappingField G;
    private MappingField i;

    public Object s() {
        return this.i.getObject(null);
    }

    public Object o() {
        return this.G.getObject(null);
    }


    public MResourceLocationConstantsBridge() {
        this(ResourceLocationConstantPair.b());
    }

    private MResourceLocationConstantsBridge(GuiComponent[] guiComponentArray) {
        super(MappedClasses.qq);
        if (guiComponentArray != null) {
            Class clazz = MappedClasses.zC;
            boolean bl = true;
            String string = "GUI";
            MResourceLocationConstantsBridge mResourceLocationConstantsBridge = this;
            this.G = mResourceLocationConstantsBridge.u(string, bl, clazz);
            Class clazz2 = MappedClasses.zC;
            boolean bl2 = true;
            String string2 = "ITEMS";
            MResourceLocationConstantsBridge mResourceLocationConstantsBridge2 = this;
            this.i = this.u(string2, bl2, clazz2);
            GuiComponent.D(new GuiComponent[5]);
            return;
        }
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = "GUI";
        MResourceLocationConstantsBridge mResourceLocationConstantsBridge = this;
        this.G = mResourceLocationConstantsBridge.u(string, bl, clazz);
        Class clazz3 = MappedClasses.zC;
        boolean bl3 = true;
        String string3 = "ITEMS";
        MResourceLocationConstantsBridge mResourceLocationConstantsBridge3 = this;
        this.i = this.u(string3, bl3, clazz3);
    }
}

