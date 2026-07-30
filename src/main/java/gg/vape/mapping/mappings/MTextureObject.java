package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MTextureManager;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTextureObject
extends Mapping {
    private MappingMethod X;
    private MappingField U;
    private MappingMethod v;
    private MappingField W;


    public static void k(MTextureObject mTextureObject, Object object, boolean bl, boolean bl2) {
        mTextureObject.k(object, bl, bl2);
    }

    private void k(Object object, boolean bl, boolean bl2) {
        this.X.invokeVoid(object, bl, bl2);
    }

    private Object i(Object object) {
        return this.U.getObject(object);
    }

    public static int m(MTextureObject mTextureObject, Object object) {
        return mTextureObject.O(object);
    }

    public static Object B(MTextureObject mTextureObject, Object object) {
        return mTextureObject.i(object);
    }

    private int O(Object object) {
        if (ForgeVersion.MC_1_20_6.d()) {
            return this.W.getInt(object);
        }
        return this.v.invokeInt(object, new Object[0]);
    }

    public MTextureObject() {
        this(MTextureManager.N());
    }

    private MTextureObject(int n) {
        super(MappedClasses.ut);
        if (n != 0) {
            if (ForgeVersion.MC_1_21_6.d()) {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "glTextureId";
                MTextureObject mTextureObject = this;
                this.W = mTextureObject.J(string, bl, clazz);
                Class[] classArray = new Class[]{Boolean.TYPE, Boolean.TYPE};
                Class<Void> clazz2 = Void.TYPE;
                boolean bl2 = true;
                String string2 = "setFilter";
                MTextureObject mTextureObject2 = this;
                this.X = this.Y(string2, bl2, clazz2, classArray);
            }
            Class[] classArray = new Class[]{};
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "getGlTextureId";
            MTextureObject mTextureObject = this;
            this.v = mTextureObject.Y(string, bl, clazz, classArray);
            return;
        }
        if (ForgeVersion.MC_1_21_6.d()) {
            Class clazz = MappedClasses.DO;
            boolean bl = true;
            String string = "texture";
            MTextureObject mTextureObject = this;
            this.U = mTextureObject.J(string, bl, clazz);
        } else if (ForgeVersion.MC_1_20_6.d()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "glTextureId";
            MTextureObject mTextureObject = this;
            this.W = mTextureObject.J(string, bl, clazz);
            Class[] classArray = new Class[]{Boolean.TYPE, Boolean.TYPE};
            Class<Void> clazz3 = Void.TYPE;
            boolean bl3 = true;
            String string3 = "setFilter";
            MTextureObject mTextureObject3 = this;
            this.X = this.Y(string3, bl3, clazz3, classArray);
        } else {
            Class[] classArray = new Class[]{};
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "getGlTextureId";
            MTextureObject mTextureObject = this;
            this.v = mTextureObject.Y(string, bl, clazz, classArray);
        }
    }
}

