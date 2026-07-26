package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MWorldInfo
extends Mapping {
    private MappingField g;
    private static String[] a;
    public MappingMethod m;

    public long R(Object object) {
        return this.g.getLong(object);
    }

    static {
        MWorldInfo.C(null);
    }

    public static void C(String[] stringArray) {
        a = stringArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String[] T() {
        return a;
    }

    public MWorldInfo() {
        this(MWorldInfo.T());
    }

    private MWorldInfo(String[] stringArray) {
        super(MappedClasses.FP);
        String[] stringArray2 = stringArray;
        Class<Long> clazz = Long.TYPE;
        boolean bl = true;
        String string = "worldTime";
        MWorldInfo mWorldInfo = this;
        this.g = this.J(string, bl, clazz);
        Class[] classArray = new Class[]{};
        Class<Long> clazz2 = Long.TYPE;
        boolean bl2 = true;
        String string2 = "getWorldTime";
        MWorldInfo mWorldInfo2 = this;
        this.m = this.Y(string2, bl2, clazz2, classArray);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MWorldInfo.C(new String[1]);
        }
    }
}

