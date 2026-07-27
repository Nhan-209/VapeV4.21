package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MLegacyEntityRenderPreHook;
import gg.vape.ui.click.component.GuiComponent;

public class MEvent
extends Mapping {
    private static final String c = "setCanceled";
    private final MappingMethod b;

    public MEvent() {
        this(MLegacyEntityRenderPreHook.N());
    }

    private MEvent(String[] stringArray) {
        super(MappedClasses.S);
        String[] stringArray2 = stringArray;
        Class[] classArray = new Class[]{Boolean.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = c;
        MEvent mEvent = this;
        this.b = this.Y(string, bl, clazz, classArray);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MLegacyEntityRenderPreHook.Q(new String[5]);
        }
    }

    public void setCanceled(Object object, boolean bl) {
        this.b.c(object, bl);
    }

}

