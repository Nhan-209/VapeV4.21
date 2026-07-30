package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTileEntityMobSpawner
extends Mapping {
    private static int[] E;
    private final MappingMethod q;

    public static int[] d() {
        return E;
    }

    public static void N(int[] nArray) {
        E = nArray;
    }

    private Object y(Object object) {
        return this.q.invokeObject(object, new Object[0]);
    }

    static {
        MTileEntityMobSpawner.N(null);
    }

    public static Object H(MTileEntityMobSpawner mTileEntityMobSpawner, Object object) {
        return mTileEntityMobSpawner.y(object);
    }


    public MTileEntityMobSpawner() {
        this(MTileEntityMobSpawner.d());
    }

    private MTileEntityMobSpawner(int[] nArray) {
        super(MappedClasses.uO);
        if (nArray != null) {
            if (Vape.INSTANCE.isVanillaMinecraftPresent()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.uj;
                boolean bl = true;
                String string = "getSpawnerBaseLogic";
                MTileEntityMobSpawner mTileEntityMobSpawner = this;
                mTileEntityMobSpawner.Y(string, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.uj;
            boolean bl = Wrapper.isNativeAvailable;
            String string = "func_145881_a";
            MTileEntityMobSpawner mTileEntityMobSpawner = this;
            this.q = mTileEntityMobSpawner.Y(string, bl, clazz, classArray);
            return;
        }
        if (Vape.INSTANCE.isVanillaMinecraftPresent() && ForgeVersion.MC_1_7_10.Y()) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.uj;
            boolean bl = true;
            String string = "getSpawnerBaseLogic";
            MTileEntityMobSpawner mTileEntityMobSpawner = this;
            this.q = mTileEntityMobSpawner.Y(string, bl, clazz, classArray);
        } else {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.uj;
            boolean bl = Wrapper.isNativeAvailable;
            String string = "func_145881_a";
            MTileEntityMobSpawner mTileEntityMobSpawner = this;
            this.q = mTileEntityMobSpawner.Y(string, bl, clazz, classArray);
        }
    }
}
