package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MUtil
extends Mapping {
    private MappingMethod X;
    private MappingMethod S;

    public long b() {
        return this.S.n(null, new Object[0]);
    }

    public void v() {
        this.X.F(null);
    }

    public MUtil() {
        super(MappedClasses.zb);
        Class[] classArray = new Class[]{};
        Class<Long> clazz = Long.TYPE;
        boolean bl = false;
        String string = "glfwGetCurrentContext";
        MUtil mUtil = this;
        this.S = this.x(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Void> clazz2 = Void.TYPE;
        boolean bl2 = false;
        String string2 = "glfwPollEvents";
        MUtil mUtil2 = this;
        this.X = this.x(string2, bl2, clazz2, classArray2);
    }
}

