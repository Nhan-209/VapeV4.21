package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MEmptyDataComponentMap
extends Mapping {
    private static final String b;
    private final MappingMethod x;
    private static String[] f;

    static {
        MEmptyDataComponentMap.h(null);
        b = "<init>";
    }

    public MEmptyDataComponentMap() {
        this(MEmptyDataComponentMap.X());
    }

    private MEmptyDataComponentMap(String[] stringArray) {
        super(MappedClasses.DP);
        String[] stringArray2 = stringArray;
        Class[] classArray = new Class[]{MappedClasses.zD};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MEmptyDataComponentMap mEmptyDataComponentMap = this;
        this.x = this.Y(string, bl, clazz, classArray);
        if (GuiComponent.getLegacyComponentState() == null) {
            MEmptyDataComponentMap.h(new String[5]);
        }
    }

    public Object L(Object object) {
        return this.x.newInstance(object);
    }


    public static void h(String[] stringArray) {
        f = stringArray;
    }

    public static String[] X() {
        return f;
    }
}

