package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MEquippable;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MResourceKeyHolder
extends Mapping {
    private MappingField u;
    private static final String b = "GOLD";

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MResourceKeyHolder() {
        this(MEquippable.p());
    }

    private MResourceKeyHolder(String string) {
        super(MappedClasses.qL);
        String string2 = string;
        Class clazz = MappedClasses.qB;
        boolean bl = true;
        String string3 = b;
        MResourceKeyHolder mResourceKeyHolder = this;
        this.u = this.u(string3, bl, clazz);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MEquippable.z("Xwix5b");
        }
    }

    public Object d() {
        return this.u.getObject(null);
    }
}

