package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MGameSettingsGuiScaleValue
extends Mapping {
    private static final String c;
    private static String s;
    private MappingField d;

    public int V() {
        if (this.d.x()) {
            return 1;
        }
        return this.d.getInt(null);
    }

    static {
        MGameSettingsGuiScaleValue.z("x0yBJc");
        c = "antialiasingLevel";
    }


    public static String T() {
        return s;
    }

    public MGameSettingsGuiScaleValue() {
        this(MGameSettingsGuiScaleValue.T());
    }

    private MGameSettingsGuiScaleValue(String string) {
        super(MappedClasses.Ym);
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = false;
        String string2 = c;
        MGameSettingsGuiScaleValue mGameSettingsGuiScaleValue = this;
        this.d = this.u(string2, bl, clazz);
        String string3 = string;
    }

    public static void z(String string) {
        s = string;
    }
}

