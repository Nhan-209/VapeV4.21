package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.impl.ForgeVersion;

public class MI18n
extends Mapping {
    private final MappingField j;
    private final MappingMethod c;


    public MI18n() {
        this(TimerUtil.p());
    }

    private MI18n(String string) {
        super(MappedClasses.Vm);
        String string2 = string;
        if (ForgeVersion.MC_1_16_5.d()) {
            Class clazz = MappedClasses.Fn;
            boolean bl = true;
            String string3 = "field_239501_a_";
            MI18n mI18n = this;
            this.j = mI18n.registerStaticField(string3, bl, clazz);
        } else {
            Class clazz = MappedClasses.Fn;
            boolean bl = true;
            String string4 = "i18nLocale";
            MI18n mI18n = this;
            this.j = mI18n.registerStaticField(string4, bl, clazz);
        }
        Class[] classArray = new Class[]{String.class, Object[].class};
        Class<String> clazz = String.class;
        boolean bl = true;
        String string5 = "format";
        MI18n mI18n = this;
        this.c = mI18n.registerStaticMethod(string5, bl, clazz, classArray);
    }

    public String R(String string, Object ... objectArray) {
        return (String)this.c.invokeObject(null, string, objectArray);
    }

    public Object S() {
        return this.j.getObject(null);
    }
}
