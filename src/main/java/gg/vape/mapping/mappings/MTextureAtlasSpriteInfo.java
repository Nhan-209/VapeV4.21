package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MTextureManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTextureAtlasSpriteInfo
extends Mapping {
    private MappingMethod a;
    private MappingField d;
    private MappingField j;

    public static Object Y(MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo) {
        return mTextureAtlasSpriteInfo.d();
    }

    public MTextureAtlasSpriteInfo() {
        this(MTextureManager.N());
    }

    private MTextureAtlasSpriteInfo(int n) {
        super(MappedClasses.L);
        if (n != 0) {
            Class clazz = MappedClasses.zC;
            boolean bl = true;
            String string = "textureLocation";
            MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo = this;
            this.d = mTextureAtlasSpriteInfo.J(string, bl, clazz);
            if (ForgeVersion.MC_1_17.d()) {
                Class[] classArray = new Class[]{MappedClasses.zC};
                Class clazz2 = MappedClasses.Db;
                boolean bl2 = true;
                String string2 = "getSprite";
                MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo2 = this;
                this.a = this.Y(string2, bl2, clazz2, classArray);
            }
            return;
        }
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = "textureLocation";
        MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo = this;
        this.d = mTextureAtlasSpriteInfo.J(string, bl, clazz);
        if (ForgeVersion.MC_1_17.d()) {
            Class clazz3 = MappedClasses.zC;
            boolean bl3 = true;
            String string3 = "LOCATION_BLOCKS";
            MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo3 = this;
            this.j = this.u(string3, bl3, clazz3);
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Class[] classArray = new Class[]{MappedClasses.zC};
            Class clazz4 = MappedClasses.Db;
            boolean bl4 = true;
            String string4 = "getSprite";
            MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo4 = this;
            this.a = this.Y(string4, bl4, clazz4, classArray);
        }
    }

    public static Object U(MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo, Object object, Object object2) {
        return mTextureAtlasSpriteInfo.Y(object, object2);
    }

    private Object d() {
        return this.j.getObject(null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Object h(Object object) {
        return this.d.getObject(object);
    }

    private Object Y(Object object, Object object2) {
        return this.a.L(object, object2);
    }

    public static Object x(MTextureAtlasSpriteInfo mTextureAtlasSpriteInfo, Object object) {
        return mTextureAtlasSpriteInfo.h(object);
    }
}

