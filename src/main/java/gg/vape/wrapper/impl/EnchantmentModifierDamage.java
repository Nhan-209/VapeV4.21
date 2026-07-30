package gg.vape.wrapper.impl;

public class EnchantmentModifierDamage
extends EnchantmentModifier {
    public DamageSource n() {
        return new DamageSource(EnchantmentModifierDamage.vapeInstance.getMappingsMapperCompat().t.Q(this.I));
    }

    public void i(DamageSource ti_12) {
        EnchantmentModifierDamage.vapeInstance.getMappingsMapperCompat().t.P(this.I, ti_12.getObject());
    }

    public int E() {
        return EnchantmentModifierDamage.vapeInstance.getMappingsMapperCompat().t.U(this.I);
    }

    public void o(int n) {
        EnchantmentModifierDamage.vapeInstance.getMappingsMapperCompat().t.W(this.I, n);
    }

    public EnchantmentModifierDamage(Object object) {
        super(object);
    }
}

