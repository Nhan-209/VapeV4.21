package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MVertexFormatElement
extends Mapping {
    private MappingMethod U;
    private static String a;
    private MappingMethod x;

    public static String E$src$Ljava_lang_String_$h04d18() {
        return a;
    }

    static {
        MVertexFormatElement.Y(null);
    }


    public static int w(MVertexFormatElement mVertexFormatElement, Object object) {
        return mVertexFormatElement.w(object);
    }

    private Object V(Object object) {
        return this.x.L(object, new Object[0]);
    }

    public MVertexFormatElement() {
        this(MVertexFormatElement.E$src$Ljava_lang_String_$h04d18());
    }

    private MVertexFormatElement(String string) {
        super(MappedClasses.ZE);
        Class[] classArray = new Class[]{};
        Class<Object> clazz = Object.class;
        boolean bl = false;
        String string2 = "getKey";
        MVertexFormatElement mVertexFormatElement = this;
        this.x = this.Y(string2, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Integer> clazz2 = Integer.TYPE;
        boolean bl2 = false;
        String string3 = "getIntValue";
        MVertexFormatElement mVertexFormatElement2 = this;
        this.U = this.Y(string3, bl2, clazz2, classArray2);
        String string4 = string;
    }

    public static void Y(String string) {
        a = string;
    }

    private int w(Object object) {
        return this.U.Z(object, new Object[0]);
    }

    public static Object d(MVertexFormatElement mVertexFormatElement, Object object) {
        return mVertexFormatElement.V(object);
    }
}

