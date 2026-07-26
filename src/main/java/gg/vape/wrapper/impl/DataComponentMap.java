package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MDataComponentMap;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.DataComponentType;
import java.util.Set;

public class DataComponentMap
extends Wrapper {
    public Object E(DataComponentType jr_12) {
        return DataComponentMap.c.getMappingsMapperCompat().qk.j(this.I, jr_12.getObject());
    }

    public boolean V(DataComponentType jr_12) {
        return DataComponentMap.c.getMappingsMapperCompat().qk.V(this.getObject(), jr_12.getObject());
    }

    public static DataComponentMap u() {
        return new DataComponentMap(MDataComponentMap.m(DataComponentMap.c.getMappingsMapperCompat().qk));
    }

    public Set Z() {
        return (Set)MDataComponentMap.w(DataComponentMap.c.getMappingsMapperCompat().qk, this.I);
    }

    public DataComponentMap(Object object) {
        super(object);
    }
}

