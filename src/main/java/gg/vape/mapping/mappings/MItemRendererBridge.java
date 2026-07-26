package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MItemRendererBridge
extends Mapping {
    private static final String c;
    private static String D;
    private MappingField X;

    public static void M(String string) {
        D = string;
    }

    public Object V() {
        return this.X.getObject(null);
    }

    public MItemRendererBridge() {
        this(MItemRendererBridge.y());
    }

    private MItemRendererBridge(String string) {
        super(MappedClasses.z0);
        String string2 = string;
        Class clazz = MappedClasses.z0;
        boolean bl = true;
        String string3 = c;
        MItemRendererBridge mItemRendererBridge = this;
        this.X = this.u(string3, bl, clazz);
    }

    static {
        MItemRendererBridge.M((String)null);
        c = "FIRST_PERSON";
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String y() {
        return D;
    }
}

