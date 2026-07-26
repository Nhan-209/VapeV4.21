package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;

public class MEnchantmentModifierDamage
extends Mapping {
    private final MappingField X;
    private final MappingField B;

    public void W(Object object, int n) {
        this.B.setInt(object, n);
    }

    public Object Q(Object object) {
        return this.X.getObject(object);
    }

    public int U(Object object) {
        return this.B.getInt(object);
    }

    public void P(Object object, Object object2) {
        this.X.setObject(object, object2);
    }

    public MEnchantmentModifierDamage() {
        super(MappedClasses.l8);
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "damageModifier";
        MEnchantmentModifierDamage mEnchantmentModifierDamage = this;
        this.B = this.J(string, bl, clazz);
        Class clazz2 = MappedClasses.uB;
        boolean bl2 = true;
        String string2 = "source";
        MEnchantmentModifierDamage mEnchantmentModifierDamage2 = this;
        this.X = this.J(string2, bl2, clazz2);
    }
}

