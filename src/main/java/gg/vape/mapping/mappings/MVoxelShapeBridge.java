package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MVoxelShapeBridge
extends Mapping {
    private static final String b = "bufferSource";
    private MappingMethod G;

    private Object j(Object object) {
        return this.G.L(object, new Object[0]);
    }

    public MVoxelShapeBridge() {
        super(MappedClasses.ZL);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.lp;
        boolean bl = true;
        String string = b;
        MVoxelShapeBridge mVoxelShapeBridge = this;
        this.G = this.Y(string, bl, clazz, classArray);
    }

    public static Object C(MVoxelShapeBridge mVoxelShapeBridge, Object object) {
        return mVoxelShapeBridge.j(object);
    }
}

