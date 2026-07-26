package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MTextureAtlasSpriteInfoBridge
extends Mapping {
    private static final String b = "texturePath";
    private MappingMethod L;

    private Object Q(Object object) {
        return this.L.L(object, new Object[0]);
    }

    public static Object C(MTextureAtlasSpriteInfoBridge mTextureAtlasSpriteInfoBridge, Object object) {
        return mTextureAtlasSpriteInfoBridge.Q(object);
    }

    public MTextureAtlasSpriteInfoBridge() {
        super(MappedClasses.zI);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = b;
        MTextureAtlasSpriteInfoBridge mTextureAtlasSpriteInfoBridge = this;
        this.L = this.Y(string, bl, clazz, classArray);
    }
}

