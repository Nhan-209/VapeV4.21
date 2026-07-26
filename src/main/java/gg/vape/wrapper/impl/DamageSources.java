package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.DamageSource;
import gg.vape.wrapper.impl.EntityPlayer;

public class DamageSources
extends Wrapper {
    public DamageSource G(EntityPlayer x_2) {
        return new DamageSource(DamageSources.c.getMappingsMapperCompat().h0.J(this.getObject(), x_2.getObject()));
    }

    public DamageSource H() {
        return new DamageSource(DamageSources.c.getMappingsMapperCompat().h0.B(this.getObject()));
    }

    public DamageSource O() {
        return new DamageSource(DamageSources.c.getMappingsMapperCompat().h0.L(this.getObject()));
    }

    public DamageSources(Object object) {
        super(object);
    }
}

