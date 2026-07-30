package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MNonNullList
extends Mapping {
    private final MappingMethod g;

    private Object F() {
        if (ForgeVersion.MC_1_17.d()) {
            return this.g.invokeObject(null, new Object[0]);
        }
        return this.g.newInstance(new Object[0]);
    }

    public MNonNullList() {
        this(BlockData.W());
    }

    private MNonNullList(String[] stringArray) {
        super(MappedClasses.Vd);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_17.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.Vd;
                boolean bl = true;
                String string = "create";
                MNonNullList mNonNullList = this;
                this.g = mNonNullList.registerStaticMethod(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MNonNullList mNonNullList = this;
                this.g = mNonNullList.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MNonNullList mNonNullList = this;
        this.g = mNonNullList.Y(string, bl, clazz, classArray);
    }


    public static Object f(MNonNullList mNonNullList) {
        return mNonNullList.F();
    }
}

