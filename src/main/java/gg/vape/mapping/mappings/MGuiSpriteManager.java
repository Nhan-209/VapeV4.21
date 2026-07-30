package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MGuiSpriteManager
extends Mapping {
    private static final String b = "getSprite";
    private MappingMethod Z;

    private Object K(Object object, Object object2) {
        return this.Z.invokeObject(object, object2);
    }

    public MGuiSpriteManager() {
        super(MappedClasses.D_);
        Class[] classArray = new Class[]{MappedClasses.zC};
        Class clazz = MappedClasses.Db;
        boolean bl = true;
        String string = b;
        MGuiSpriteManager mGuiSpriteManager = this;
        this.Z = this.Y(string, bl, clazz, classArray);
    }

    public static Object F(MGuiSpriteManager mGuiSpriteManager, Object object, Object object2) {
        return mGuiSpriteManager.K(object, object2);
    }
}

