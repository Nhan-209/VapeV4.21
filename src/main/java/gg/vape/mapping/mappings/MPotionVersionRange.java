package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import java.util.Set;

public class MPotionVersionRange
extends Mapping {
    private MappingMethod p;
    private MappingField N;

    private Object K() {
        return this.N.getObject(null);
    }

    public MPotionVersionRange() {
        super(MappedClasses.VX);
        Class[] classArray = new Class[]{};
        Class<Set> clazz = Set.class;
        boolean bl = true;
        String string = "entrySet";
        MPotionVersionRange mPotionVersionRange = this;
        this.p = this.Y(string, bl, clazz, classArray);
        Class clazz2 = MappedClasses.VX;
        boolean bl2 = true;
        String string2 = "EMPTY";
        MPotionVersionRange mPotionVersionRange2 = this;
        this.N = this.u(string2, bl2, clazz2);
    }

    public static Object F(MPotionVersionRange mPotionVersionRange, Object object) {
        return mPotionVersionRange.B(object);
    }

    private Object B(Object object) {
        return this.p.L(object, new Object[0]);
    }

    public static Object h(MPotionVersionRange mPotionVersionRange) {
        return mPotionVersionRange.K();
    }
}

