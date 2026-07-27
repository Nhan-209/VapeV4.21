package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MGuiContainer;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MGuiChest
extends Mapping {
    private final MappingField f;

    private Object M(Object object) {
        return this.f.getObject(object);
    }

    public static Object F(MGuiChest mGuiChest, Object object) {
        return mGuiChest.M(object);
    }

    public MGuiChest() {
        this(MGuiContainer.l());
    }

    private MGuiChest(String[] stringArray) {
        super(MappedClasses.qs);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class clazz = MappedClasses.l0;
                boolean bl = true;
                String string = "lowerChestInventory";
                Class clazz2 = MappedClasses.zZ;
                MGuiChest mGuiChest = this;
                this.f = mGuiChest.X(clazz2, string, bl, clazz);
            } else {
                Class clazz = MappedClasses.l0;
                boolean bl = true;
                String string = "lowerChestInventory";
                MGuiChest mGuiChest = this;
                this.f = mGuiChest.J(string, bl, clazz);
            }
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MGuiContainer.r(new String[4]);
            }
            return;
        }
        Class clazz = MappedClasses.l0;
        boolean bl = true;
        String string = "lowerChestInventory";
        MGuiChest mGuiChest = this;
        this.f = mGuiChest.J(string, bl, clazz); 
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MGuiContainer.r(new String[4]);
        }
    }

}
