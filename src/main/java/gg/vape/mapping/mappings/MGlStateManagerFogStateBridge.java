package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import java.util.List;

public class MGlStateManagerFogStateBridge
extends Mapping {
    private static final String b = "itemStates";
    private MappingField j;

    public MGlStateManagerFogStateBridge() {
        super(MappedClasses.zM);
        Class<List> clazz = List.class;
        boolean bl = true;
        String string = b;
        MGlStateManagerFogStateBridge mGlStateManagerFogStateBridge = this;
        this.j = this.J(string, bl, clazz);
    }

    public List y(Object object) {
        return (List)this.j.getObject(object);
    }
}

