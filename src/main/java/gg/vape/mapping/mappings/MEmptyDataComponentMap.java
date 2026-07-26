package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MEmptyDataComponentMap.h(new String[5]);
        }
    }

    public Object L(Object object) {
        return this.x.O(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void h(String[] stringArray) {
        f = stringArray;
    }

    public static String[] X() {
        return f;
    }
}

