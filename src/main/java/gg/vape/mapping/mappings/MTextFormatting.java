package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTextFormatting
extends Mapping {
    private MappingMethod P;
    private MappingMethod O;
    private MappingMethod j;

    public Integer e(Object object) {
        if (object == null) {
            return null;
        }
        return (Integer)this.P.invokeObject(object, new Object[0]);
    }

    public MTextFormatting() {
        this(BlockData.W());
    }

    private MTextFormatting(String[] stringArray) {
        super(MappedClasses.l5);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_21_4.d()) {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "getName";
                MTextFormatting mTextFormatting = this;
                this.O = mTextFormatting.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{};
                Class<Integer> clazz2 = Integer.class;
                boolean bl2 = true;
                String string2 = "getColor";
                MTextFormatting mTextFormatting2 = this;
                this.P = this.Y(string2, bl2, clazz2, classArray2);
                Class[] classArray3 = new Class[]{String.class};
                Class clazz3 = MappedClasses.l5;
                boolean bl3 = true;
                String string3 = "getByName";
                MTextFormatting mTextFormatting3 = this;
                this.j = this.registerStaticMethod(string3, bl3, clazz3, classArray3);
            }
            return;
        }
        Class[] classArray = new Class[]{String.class};
        Class clazz = MappedClasses.l5;
        boolean bl = true;
        String string = "getByName";
        MTextFormatting mTextFormatting = this;
        this.j = mTextFormatting.registerStaticMethod(string, bl, clazz, classArray);
    }


    public Object h(String string) {
        if (string == null) {
            return null;
        }
        return this.j.invokeObject(null, string);
    }

    public String m(Object object) {
        if (object == null) {
            return null;
        }
        return (String)this.O.invokeObject(object, new Object[0]);
    }
}

