package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MTickEventPhase;
import gg.vape.ui.click.component.GuiComponent;

public class MItems
extends Mapping {
    private MappingField g;
    private MappingField H;


    public static Object T(MItems mItems) {
        return mItems.g();
    }

    private Object g() {
        return this.H.getObject(null);
    }

    public MItems() {
        super(MappedClasses.zq);
        Class clazz = MappedClasses.zq;
        boolean bl = true;
        String string = "PERSPECTIVE";
        MItems mItems = this;
        this.H = this.u(string, bl, clazz);
        Class clazz2 = MappedClasses.zq;
        boolean bl2 = true;
        String string2 = "ORTHOGRAPHIC";
        MItems mItems2 = this;
        this.g = this.u(string2, bl2, clazz2);
        if (MTickEventPhase.P() != null) {
            GuiComponent.D(new GuiComponent[5]);
            return;
        }
    }

    private Object X() {
        return this.g.getObject(null);
    }

    public static Object d(MItems mItems) {
        return mItems.X();
    }
}

