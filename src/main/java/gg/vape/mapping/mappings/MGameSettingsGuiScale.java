package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MGameSettingsGuiScale
extends Mapping {
    private static int[] W;
    private static final String b;
    private MappingField s;

    static {
        MGameSettingsGuiScale.F(new int[1]);
        b = "base";
    }

    public static void F(int[] nArray) {
        W = nArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MGameSettingsGuiScale() {
        super(MappedClasses.Fg);
        Class clazz = MappedClasses.qP;
        boolean bl = true;
        String string = b;
        MGameSettingsGuiScale mGameSettingsGuiScale = this;
        this.s = this.J(string, bl, clazz);
        int[] nArray = MGameSettingsGuiScale.i();
    }

    private Object K(Object object) {
        return this.s.getObject(object);
    }

    public static int[] i() {
        return W;
    }

    public static Object C(MGameSettingsGuiScale mGameSettingsGuiScale, Object object) {
        return mGameSettingsGuiScale.K(object);
    }
}

