package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MRegistryAccess;
import gg.vape.ui.click.component.GuiComponent;
import java.util.Optional;

public class MResourceKeyRegistryLookup
extends Mapping {
    private final MappingMethod F;
    private final MappingMethod J;


    public Object N(Object object, Object object2) {
        return this.J.L(object, object2);
    }

    public Optional<Object> W(Object object, Object object2) {
        return (Optional)this.F.L(object, object2);
    }

    public MResourceKeyRegistryLookup() {
        this(MRegistryAccess.d());
    }

    private MResourceKeyRegistryLookup(String string) {
        super(MappedClasses.u2);
        Class[] classArray = new Class[]{MappedClasses.qB};
        Class<Optional> clazz = Optional.class;
        boolean bl = true;
        String string2 = "get";
        MResourceKeyRegistryLookup mResourceKeyRegistryLookup = this;
        this.F = this.Y(string2, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{MappedClasses.qB};
        Class clazz2 = MappedClasses.qx;
        boolean bl2 = true;
        String string3 = "getOrThrow";
        MResourceKeyRegistryLookup mResourceKeyRegistryLookup2 = this;
        this.J = this.Y(string3, bl2, clazz2, classArray2);
        String string4 = string;
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MRegistryAccess.l("Th1Gp");
        }
    }
}

