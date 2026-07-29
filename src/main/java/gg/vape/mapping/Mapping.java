package gg.vape.mapping;

import gg.vape.mapping.Mapper;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.ui.click.component.GuiComponent;

public class Mapping {
    private static String[] t;
    protected Class R;

    protected MappingField J(String string, boolean bl, Class clazz) {
        MappingField mappingField = new MappingField(this, this.R, string, bl, false, false, clazz, 0);
        return mappingField.O();
    }

    public Class E() {
        return this.R;
    }

    public static void x(String[] stringArray) {
        t = stringArray;
    }

    static {
        Mapping.x(new String[1]);
    }

    protected MappingMethod Y(String string, boolean bl, Class clazz, Class ... classArray) {
        MappingMethod mappingMethod = new MappingMethod(this, this.R, string, bl, false, false, clazz, classArray);
        return mappingMethod.g();
    }


    protected MappingField s(Class clazz, String string, boolean bl, Class clazz2) {
        MappingField mappingField = new MappingField(this, clazz, string, bl, true, false, clazz2, 0);
        return mappingField.O();
    }

    protected MappingField X(Class clazz, String string, boolean bl, Class clazz2) {
        MappingField mappingField = new MappingField(this, clazz, string, bl, false, false, clazz2, 0);
        return mappingField.O();
    }

    protected MappingMethod x(String string, boolean bl, Class clazz, Class ... classArray) {
        MappingMethod mappingMethod = new MappingMethod(this, this.R, string, bl, true, false, clazz, classArray);
        return mappingMethod.g();
    }

    protected MappingMethodBuilder u(String string, Class clazz, Class ... classArray) {
        return ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)new MappingMethodBuilder().e(this)).y(this.R)).S(true)).v(string)).l(clazz)).G(classArray);
    }

    public static String[] E$src$ALjava_lang_String_$1ja2hqz() {
        return t;
    }

    protected MappingMethod g(Class ... classArray) {
        MappingMethod mappingMethod = new MappingMethod(this, this.R, "<init>", false, false, false, Void.TYPE, classArray);
        return mappingMethod.g();
    }

    public Mapping(Class clazz) {
        this.R = clazz;
        Mapper.RF.add(this);
    }

    protected MappingMethodBuilder M(Class ... classArray) {
        String[] stringArray = Mapping.E$src$ALjava_lang_String_$1ja2hqz();
        MappingMethodBuilder mappingMethodBuilder = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)new MappingMethodBuilder().e(this)).y(this.R)).S(false)).v("<init>")).l(Void.TYPE)).G(classArray);
        if (GuiComponent.getLegacyComponentState() == null) {
            Mapping.x(new String[2]);
        }
        return mappingMethodBuilder;
    }

    protected MappingFieldBuilder T(String string, Class clazz) {
        return (MappingFieldBuilder)((MappingFieldBuilder)((MappingFieldBuilder)((MappingFieldBuilder)((MappingFieldBuilder)new MappingFieldBuilder().e(this)).y(this.R)).v(string)).S(true)).l(clazz);
    }

    protected MappingMethod K(Class clazz, String string, boolean bl, Class clazz2, Class ... classArray) {
        MappingMethod mappingMethod = new MappingMethod(this, clazz, string, bl, true, false, clazz2, classArray);
        return mappingMethod.g();
    }

    protected MappingField z(String string, boolean bl, boolean bl2, Class clazz) {
        MappingField mappingField = new MappingField(this, this.R, string, bl, false, bl2, clazz, 0);
        return mappingField.O();
    }

    protected MappingMethod W(Class clazz, String string, boolean bl, Class clazz2, Class ... classArray) {
        MappingMethod mappingMethod = new MappingMethod(this, clazz, string, bl, false, false, clazz2, classArray);
        return mappingMethod.g();
    }

    protected MappingField u(String string, boolean bl, Class clazz) {
        MappingField mappingField = new MappingField(this, this.R, string, bl, true, false, clazz, 0);
        return mappingField.O();
    }
}

