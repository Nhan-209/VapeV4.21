package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MTileEntityMobSpawner;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;

public class MMobSpawnerBaseLogic
extends Mapping {
    private MappingMethod X;
    private MappingMethod W;

    private Object getCachedEntity(Object object, Object object2) {
        if (ForgeVersion.MC_1_20_6.d()) {
            return this.W.L(object, object2, null);
        }
        if (ForgeVersion.MC_1_17.d()) {
            return this.W.L(object, object2);
        }
        return this.W.L(object, new Object[0]);
    }

    public static String getEntityNameToSpawn(MMobSpawnerBaseLogic mMobSpawnerBaseLogic, Object object) {
        return mMobSpawnerBaseLogic.getEntityNameToSpawn(object);
    }

    private String getEntityNameToSpawn(Object object) {
        return (String)this.X.L(object, new Object[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static Object getCachedEntity(MMobSpawnerBaseLogic mMobSpawnerBaseLogic, Object object, Object object2) {
        return mMobSpawnerBaseLogic.getCachedEntity(object, object2);
    }

    public MMobSpawnerBaseLogic() {
        this(MTileEntityMobSpawner.d());
    }

    private MMobSpawnerBaseLogic(int[] nArray) {
        super(MappedClasses.uj);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_12_2.d()) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.lf};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getOrCreateDisplayEntity";
                MMobSpawnerBaseLogic mMobSpawnerBaseLogic = this;
                this.W = mMobSpawnerBaseLogic.Y(string, bl, clazz, classArray);
            } else if (ForgeVersion.MC_1_17.d()) {
                Class[] classArray = new Class[]{MappedClasses.YU};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getOrCreateDisplayEntity";
                MMobSpawnerBaseLogic mMobSpawnerBaseLogic = this;
                this.W = mMobSpawnerBaseLogic.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getCachedEntity";
                MMobSpawnerBaseLogic mMobSpawnerBaseLogic = this;
                this.W = mMobSpawnerBaseLogic.Y(string, bl, clazz, classArray);
            }
        } else {
            Class[] classArray = new Class[]{};
            Class<String> clazz = String.class;
            boolean bl = true;
            String string = "getEntityNameToSpawn";
            MMobSpawnerBaseLogic mMobSpawnerBaseLogic = this;
            this.X = mMobSpawnerBaseLogic.Y(string, bl, clazz, classArray);
        }
    }
}

