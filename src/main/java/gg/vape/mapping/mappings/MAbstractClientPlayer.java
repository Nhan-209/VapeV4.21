package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MAbstractClientPlayer
extends Mapping {
    private MappingMethod f;

    public MAbstractClientPlayer() {
        super(MappedClasses.YM);
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE};
        MAbstractClientPlayer mAbstractClientPlayer = this;
        this.f = this.registerConstructor(classArray);
    }

    private Object I(int n, int n2, int n3) {
        return this.f.newInstance(n, n2, n3);
    }

    public static Object W(MAbstractClientPlayer mAbstractClientPlayer, int n, int n2, int n3) {
        return mAbstractClientPlayer.I(n, n2, n3);
    }
}

