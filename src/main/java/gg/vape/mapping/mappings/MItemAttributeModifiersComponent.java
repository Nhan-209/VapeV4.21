package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;
import java.util.List;

public class MItemAttributeModifiersComponent
extends Mapping {
    private static GuiComponent[] U;
    private MappingField G;
    private static final String b;

    public MItemAttributeModifiersComponent() {
        super(MappedClasses.Dp);
        Class<List> clazz = List.class;
        boolean bl = true;
        String string = b;
        MItemAttributeModifiersComponent mItemAttributeModifiersComponent = this;
        this.G = this.J(string, bl, clazz);
    }

    public static void M(GuiComponent[] guiComponentArray) {
        U = guiComponentArray;
    }

    static {
        MItemAttributeModifiersComponent.M(new GuiComponent[4]);
        b = "modifiers";
    }

    public static GuiComponent[] F() {
        return U;
    }

    public List<Object> p(Object object) {
        return (List)this.G.getObject(object);
    }
}

