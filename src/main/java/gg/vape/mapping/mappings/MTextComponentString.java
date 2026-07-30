package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;

public class MTextComponentString
extends Mapping {
    private final MappingField x;
    private final MappingMethod H;

    public static String E(MTextComponentString mTextComponentString, Object object) {
        return mTextComponentString.A(object);
    }

    public MTextComponentString() {
        super(MappedClasses.z9);
        Class[] classArray = new Class[]{String.class};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MTextComponentString mTextComponentString = this;
        this.H = this.Y(string, bl, clazz, classArray);
        Class<String> clazz2 = String.class;
        boolean bl2 = true;
        String string2 = "text";
        MTextComponentString mTextComponentString2 = this;
        this.x = this.J(string2, bl2, clazz2);
    }

    public static Object q(MTextComponentString mTextComponentString, String string) {
        return mTextComponentString.u(string);
    }

    public static void T(MTextComponentString mTextComponentString, Object object, String string) {
        mTextComponentString.Z(object, string);
    }

    private void Z(Object object, String string) {
        this.x.setObject(object, string);
    }

    private String A(Object object) {
        return this.x.getObject(object).toString();
    }

    private Object u(String string) {
        return this.H.newInstance(string);
    }
}

