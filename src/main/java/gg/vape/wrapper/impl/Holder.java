package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MHolder;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ResourceKey;
import java.util.Optional;

public class Holder
extends Wrapper {
    public boolean F(ResourceKey jy_12) {
        return MHolder.q(Holder.c.getMappingsMapperCompat().hN, this.I, jy_12.getObject());
    }

    public String Z() {
        return MHolder.F(Holder.c.getMappingsMapperCompat().hN, this.I);
    }

    public static Holder A(Object object) {
        return new Holder(MHolder.f(Holder.c.getMappingsMapperCompat().hN, object));
    }

    public Holder(Object object) {
        super(object);
    }

    public Object N() {
        return MHolder.E(Holder.c.getMappingsMapperCompat().hN, this.I);
    }

    public Optional f() {
        return (Optional)MHolder.m(Holder.c.getMappingsMapperCompat().hN, this.I);
    }
}

