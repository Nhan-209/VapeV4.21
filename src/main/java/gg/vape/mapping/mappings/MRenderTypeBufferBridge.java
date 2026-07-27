package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;

public class MRenderTypeBufferBridge
extends Mapping {
    private static String D;
    private static final String c;
    private final MappingMethod S;

    public MRenderTypeBufferBridge() {
        this(MRenderTypeBufferBridge.e());
    }

    private MRenderTypeBufferBridge(String string) {
        super(MappedClasses.lC);
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = true;
        String string2 = c;
        MRenderTypeBufferBridge mRenderTypeBufferBridge = this;
        this.S = this.Y(string2, bl, clazz, classArray);
        String string3 = string;
    }

    static {
        MRenderTypeBufferBridge.L("bZI7xb");
        c = "onInputReceived";
    }

    public static MappingMethod d(MRenderTypeBufferBridge mRenderTypeBufferBridge) {
        return mRenderTypeBufferBridge.S;
    }


    public static String e() {
        return D;
    }

    public static void L(String string) {
        D = string;
    }
}

