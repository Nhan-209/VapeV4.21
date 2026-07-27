package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MResourceLocation
extends Mapping {
    private MappingMethod O;
    private final MappingMethod A;

    public MResourceLocation() {
        this(BlockData.W());
    }

    private MResourceLocation(String[] stringArray) {
        super(MappedClasses.zC);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "getPath";
                MResourceLocation mResourceLocation = this;
                this.A = mResourceLocation.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "getResourcePath";
                MResourceLocation mResourceLocation = this;
                this.A = mResourceLocation.Y(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_1_21_0.d()) {
                Class[] classArray = new Class[]{String.class};
                Class clazz = MappedClasses.zC;
                boolean bl = true;
                String string = "parse";
                MResourceLocation mResourceLocation = this;
                this.O = mResourceLocation.x(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{String.class};
                Class<Void> clazz = Void.TYPE;
                boolean bl = false;
                String string = "<init>";
                MResourceLocation mResourceLocation = this;
                this.O = mResourceLocation.Y(string, bl, clazz, classArray);
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = "getResourcePath";
        MResourceLocation mResourceLocation = this;
        this.A = mResourceLocation.Y(string, bl, clazz, classArray);
        if (ForgeVersion.MC_1_21_0.d()) {
            Class[] classArray2 = new Class[]{String.class};
            Class clazz2 = MappedClasses.zC;
            boolean bl2 = true;
            String string2 = "parse";
            MResourceLocation mResourceLocation2 = this;
            mResourceLocation2.x(string2, bl2, clazz2, classArray2);
        }
        Class[] classArray3 = new Class[]{String.class};
        Class<Void> clazz3 = Void.TYPE;
        boolean bl3 = false;
        String string3 = "<init>";
        MResourceLocation mResourceLocation3 = this;
        this.O = this.Y(string3, bl3, clazz3, classArray3);
    }


    public String s(Object object) {
        return (String)this.A.L(object, new Object[0]);
    }

    public Object o(String string) {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.O.L(null, string);
        }
        return this.O.O(string);
    }
}

