package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MItemActionResult
extends Mapping {
    private final MappingMethod V;
    private final MappingMethod N;
    private final MappingMethod Z;
    private final MappingMethod u;
    private final MappingMethod c;
    private final MappingMethod K;

    public boolean Z(Object object) {
        if (this.K == null) {
            return true;
        }
        return this.K.invokeBoolean(object, new Object[0]);
    }

    public MItemActionResult() {
        this(BlockData.W());
    }

    private MItemActionResult(String[] stringArray) {
        super(MappedClasses.zO);
        if (stringArray != null) {
            Class[] classArray = new Class[]{MappedClasses.Zi, MappedClasses.lj};
            MItemActionResult mItemActionResult = this;
            this.u = mItemActionResult.registerConstructor(classArray);
            if (ForgeVersion.MC_1_21_10.v()) {
                Class[] classArray2 = new Class[]{};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = true;
                String string = "consumesAction";
                MItemActionResult mItemActionResult2 = this;
                this.K = this.Y(string, bl, clazz, classArray2);
            } else {
                this.K = null;
            }
            Class[] classArray3 = new Class[]{MappedClasses.VK};
            Class clazz = MappedClasses.zO;
            boolean bl = true;
            String string = "heldItemTransformedTo";
            MItemActionResult mItemActionResult3 = this;
            this.c = this.Y(string, bl, clazz, classArray3);
            Class[] classArray4 = new Class[]{};
            Class clazz2 = MappedClasses.VK;
            boolean bl2 = true;
            String string2 = "heldItemTransformedTo";
            MItemActionResult mItemActionResult4 = this;
            this.N = this.Y(string2, bl2, clazz2, classArray4);
            Class[] classArray5 = new Class[]{};
            Class clazz3 = MappedClasses.zO;
            boolean bl3 = true;
            String string3 = "withoutItem";
            MItemActionResult mItemActionResult5 = this;
            this.V = this.Y(string3, bl3, clazz3, classArray5);
            Class[] classArray6 = new Class[]{};
            Class<Boolean> clazz4 = Boolean.TYPE;
            boolean bl4 = true;
            String string4 = "wasItemInteraction";
            MItemActionResult mItemActionResult6 = this;
            this.Z = this.Y(string4, bl4, clazz4, classArray6);
            return;
        }
        this.K = null;
        Class[] classArray = new Class[]{MappedClasses.VK};
        Class clazz = MappedClasses.zO;
        boolean bl = true;
        String string = "heldItemTransformedTo";
        MItemActionResult mItemActionResult = this;
        this.c = mItemActionResult.Y(string, bl, clazz, classArray); 
        Class[] classArray7 = new Class[]{};
        Class clazz5 = MappedClasses.VK;
        boolean bl5 = true;
        String string5 = "heldItemTransformedTo";
        MItemActionResult mItemActionResult7 = this;
        this.N = this.Y(string5, bl5, clazz5, classArray7);
        Class[] classArray8 = new Class[]{};
        Class clazz6 = MappedClasses.zO;
        boolean bl6 = true;
        String string6 = "withoutItem";
        MItemActionResult mItemActionResult8 = this;
        this.V = this.Y(string6, bl6, clazz6, classArray8);
        Class[] classArray9 = new Class[]{};
        Class<Boolean> clazz7 = Boolean.TYPE;
        boolean bl7 = true;
        String string7 = "wasItemInteraction";
        MItemActionResult mItemActionResult9 = this;
        this.Z = this.Y(string7, bl7, clazz7, classArray9);
        this.u = null;
    }

    public Object T(Object object) {
        return this.N.invokeObject(object, new Object[0]);
    }

    public Object d(Object object, Object object2) {
        return this.u.invokeObject(null, object, object2);
    }

    public boolean P(Object object) {
        return this.Z.invokeBoolean(object, new Object[0]);
    }

    public Object Z(Object object, Object object2) {
        return this.c.invokeObject(object, object2);
    }

    public Object z(Object object) {
        return this.V.invokeObject(object, new Object[0]);
    }

}

