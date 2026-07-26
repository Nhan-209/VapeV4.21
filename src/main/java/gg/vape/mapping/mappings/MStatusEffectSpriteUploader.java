package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MStatusEffectSpriteUploader
extends Mapping {
    private MappingField b;
    private MappingMethod N;

    public static Object X(MStatusEffectSpriteUploader mStatusEffectSpriteUploader, Object object, Object object2) {
        return mStatusEffectSpriteUploader.S(object, object2);
    }

    private Object e(Object object) {
        return this.b.getObject(object);
    }

    public static Object C(MStatusEffectSpriteUploader mStatusEffectSpriteUploader, Object object) {
        return mStatusEffectSpriteUploader.e(object);
    }

    public MStatusEffectSpriteUploader() {
        super(MappedClasses.u4);
        Class[] classArray = new Class[]{MappedClasses.D3};
        Class clazz = MappedClasses.Db;
        boolean bl = true;
        String string = "getSprite";
        MStatusEffectSpriteUploader mStatusEffectSpriteUploader = this;
        this.N = this.Y(string, bl, clazz, classArray);
        Class clazz2 = MappedClasses.u4;
        boolean bl2 = true;
        String string2 = "potionSprites";
        Class clazz3 = MappedClasses.uP;
        MStatusEffectSpriteUploader mStatusEffectSpriteUploader2 = this;
        this.b = this.X(clazz3, string2, bl2, clazz2);
    }

    private Object S(Object object, Object object2) {
        return this.N.L(object, object2);
    }
}

