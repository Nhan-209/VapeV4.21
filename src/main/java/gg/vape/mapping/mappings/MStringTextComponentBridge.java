package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MTextComponentTranslationBridge;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MStringTextComponentBridge
extends Mapping {
    private MappingField O;
    private MappingField g;

    public MStringTextComponentBridge() {
        this(MTextComponentTranslationBridge.C());
    }

    private MStringTextComponentBridge(boolean bl) {
        super(MappedClasses.DE);
        if (bl) {
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MTextComponentTranslationBridge.n(false);
            }
            return;
        }
        if (ForgeVersion.MC_26_1.v()) {
            Class<String> clazz = String.class;
            boolean bl2 = true;
            String string = "name";
            MStringTextComponentBridge mStringTextComponentBridge = this;
            this.O = mStringTextComponentBridge.J(string, bl2, clazz);
        }
        Class clazz = MappedClasses.zE;
        boolean bl3 = true;
        String string = "itemStackRenderState";
        MStringTextComponentBridge mStringTextComponentBridge = this;
        this.g = mStringTextComponentBridge.J(string, bl3, clazz);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MTextComponentTranslationBridge.n(true);
        }
    }

    public String q(Object object) {
        if (this.O == null) {
            return "";
        }
        return (String)this.O.getObject(object);
    }


    public Object W(Object object) {
        return this.g.getObject(object);
    }
}
