package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ResourceLocationConstantPair;

public class MModelManager
extends Mapping {
    private MappingMethod p;
    private static final String b = "getAtlasOrThrow";

    public MModelManager() {
        this(ResourceLocationConstantPair.b());
    }

    private MModelManager(GuiComponent[] guiComponentArray) {
        super(MappedClasses.q4);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        Class[] classArray = new Class[]{MappedClasses.zC};
        Class clazz = MappedClasses.L;
        boolean bl = true;
        String string = b;
        MModelManager mModelManager = this;
        this.p = this.Y(string, bl, clazz, classArray);
        if (GuiComponent.getLegacyComponentState() == null) {
            ResourceLocationConstantPair.P(new GuiComponent[1]);
        }
    }


    public Object t(Object object, Object object2) {
        return this.p.L(object, object2);
    }
}

