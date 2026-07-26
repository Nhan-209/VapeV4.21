package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.DamageSource;
import gg.vape.wrapper.impl.EnchantmentModifier;

public class EnchantmentModifierDamage
extends EnchantmentModifier {
    public DamageSource n() {
        return new DamageSource(EnchantmentModifierDamage.c.getMappingsMapperCompat().t.Q(this.I));
    }

    public void i(DamageSource ti_12) {
        EnchantmentModifierDamage.c.getMappingsMapperCompat().t.P(this.I, ti_12.getObject());
    }

    public int E() {
        return EnchantmentModifierDamage.c.getMappingsMapperCompat().t.U(this.I);
    }

    public void o(int n) {
        EnchantmentModifierDamage.c.getMappingsMapperCompat().t.W(this.I, n);
    }

    public EnchantmentModifierDamage(Object object) {
        super(object);
    }
}

