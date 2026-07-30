package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MEquippable;
import gg.vape.ui.click.component.GuiComponent;

public class MResourceKeyHolder
extends Mapping {
    private MappingField u;
    private static final String b = "GOLD";


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
        this.u = this.registerStaticField(string3, bl, clazz);
        if (GuiComponent.getLegacyComponentState() == null) {
            MEquippable.z("Xwix5b");
        }
    }

    public Object d() {
        return this.u.getObject(null);
    }
}

