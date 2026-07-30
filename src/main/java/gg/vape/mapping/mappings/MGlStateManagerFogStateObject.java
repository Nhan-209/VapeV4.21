package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MTextComponentTranslationBridge;
import gg.vape.ui.click.component.GuiComponent;

public class MGlStateManagerFogStateObject
extends Mapping {
    private MappingMethod a;
    private MappingField M;

    public Object t(Object object) {
        return this.M.getObject(object);
    }

    public MGlStateManagerFogStateObject() {
        this(MTextComponentTranslationBridge.I());
    }

    private MGlStateManagerFogStateObject(boolean bl) {
        super(MappedClasses.i);
        Class clazz = MappedClasses.zM;
        boolean bl2 = true;
        String string = "current";
        MGlStateManagerFogStateObject mGlStateManagerFogStateObject = this;
        this.M = this.J(string, bl2, clazz);
        Class[] classArray = new Class[]{};
        Class<Void> clazz2 = Void.TYPE;
        boolean bl3 = true;
        String string2 = "reset";
        MGlStateManagerFogStateObject mGlStateManagerFogStateObject2 = this;
        this.a = this.Y(string2, bl3, clazz2, classArray);
        if (bl) {
            return;
        }
        GuiComponent.setLegacyComponentState(new GuiComponent[4]);
    }


    public void m(Object object) {
        this.a.invokeVoidNoArgs(object);
    }
}

