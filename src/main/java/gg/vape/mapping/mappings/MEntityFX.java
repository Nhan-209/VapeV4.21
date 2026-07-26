package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MEntityFX
extends Mapping {
    private static final String b;
    private static int A;
    private final MappingField G;

    static {
        MEntityFX.K(0);
        b = "packets";
    }

    public Iterable A(Object object) {
        return (Iterable)this.G.getObject(object);
    }

    public static int R() {
        int n = MEntityFX.l();
        return 45;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static int l() {
        return A;
    }

    public static void K(int n) {
        A = n;
    }

    public MEntityFX() {
        this(MEntityFX.l());
    }

    private MEntityFX(int n) {
        super(MappedClasses.ue);
        int n2 = n;
        Class<Iterable> clazz = Iterable.class;
        boolean bl = true;
        String string = b;
        MEntityFX mEntityFX = this;
        this.G = this.J(string, bl, clazz);
    }
}

