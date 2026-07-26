package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MPlayerEvent_NameFormat
extends Mapping {
    private MappingField Q;
    private MappingField a;

    public String u(Object object) {
        return (String)this.a.getObject(object);
    }

    public MPlayerEvent_NameFormat() {
        super(MappedClasses.l3);
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = "url";
        MPlayerEvent_NameFormat mPlayerEvent_NameFormat = this;
        this.a = this.J(string, bl, clazz);
        Class<String> clazz2 = String.class;
        boolean bl2 = true;
        String string2 = "hash";
        MPlayerEvent_NameFormat mPlayerEvent_NameFormat2 = this;
        this.Q = this.J(string2, bl2, clazz2);
    }

    public void c(Object object, String string) {
        this.a.setObject(object, string);
    }

    public String I(Object object) {
        return (String)this.Q.getObject(object);
    }
}

