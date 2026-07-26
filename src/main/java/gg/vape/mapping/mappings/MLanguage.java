package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MMappedClassSlotFp;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MLanguage
extends Mapping {
    private MappingField i;

    private boolean C(Object object) {
        return this.i.getBoolean(object);
    }

    public static boolean n(MLanguage mLanguage, Object object) {
        return mLanguage.C(object);
    }

    public MLanguage() {
        this(MMappedClassSlotFp.l());
    }

    private MLanguage(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Fn);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_16_5.d()) {
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_239496_d_";
            Class clazz2 = MappedClasses.Vi;
            MLanguage mLanguage = this;
            this.i = mLanguage.X(clazz2, string, bl, clazz);
        } else if (ForgeVersion.MC_1_7_10.L()) {
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_135029_d";
            MLanguage mLanguage = this;
            this.i = mLanguage.J(string, bl, clazz);
        } else {
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = true;
            String string = "unicode";
            MLanguage mLanguage = this;
            this.i = mLanguage.J(string, bl, clazz);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

