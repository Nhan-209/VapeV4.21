package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MServerData
extends Mapping {
    private final MappingField H;
    private static final String b = "serverIP";

    private String x(Object object) {
        return (String)this.H.getObject(object);
    }

    public static String T(MServerData mServerData, Object object) {
        return mServerData.x(object);
    }

    public MServerData() {
        super(MappedClasses.uR);
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = b;
        MServerData mServerData = this;
        this.H = this.J(string, bl, clazz);
    }
}

