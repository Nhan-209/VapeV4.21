package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MFontSet
extends Mapping {
    private MappingMethod W;
    private MappingField f;
    private static String c;

    public static void c(String string) {
        c = string;
    }

    public Object v(Object object) {
        return this.f.getObject(object);
    }

    static {
        MFontSet.c("A5Xu0b");
    }


    public MFontSet() {
        this(MFontSet.A());
    }

    private MFontSet(String string) {
        super(MappedClasses.D9);
        Class clazz = MappedClasses.qd;
        boolean bl = true;
        String string2 = "missingGlyph";
        MFontSet mFontSet = this;
        this.f = this.J(string2, bl, clazz);
        if (string != null) {
            Class[] classArray = new Class[]{Boolean.TYPE};
            Class clazz2 = MappedClasses.Yx;
            boolean bl2 = true;
            String string3 = "source";
            MFontSet mFontSet2 = this;
            this.W = this.Y(string3, bl2, clazz2, classArray);
            return;
        }
        Class[] classArray = new Class[]{Boolean.TYPE};
        Class clazz3 = MappedClasses.Yx;
        boolean bl3 = true;
        String string4 = "source";
        MFontSet mFontSet3 = this;
        this.W = this.Y(string4, bl3, clazz3, classArray);
        GuiComponent.D(new GuiComponent[5]);
    }

    public static String A() {
        return c;
    }

    public Object t(Object object, boolean bl) {
        return this.W.L(object, bl);
    }
}

