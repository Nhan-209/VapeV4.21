package gg.vape.module.combat.crystalaura;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;

public class ExplosionType {
    public static final ExplosionType z;
    public static final ExplosionType k;
    private static int r;
    private final String P;
    public static final ExplosionType Q;
    private final float H;

    static {
        long l = ZkmLongKeyState.a(-1089357904095783605L, -7382431098578438232L, MethodHandles.lookup().lookupClass()).a(22887129760831L) ^ 0x344C6F544B82L;
        if (ExplosionType.q() == 0) {
            ExplosionType.G(55);
        }
        String[] stringArray = new String[]{"Bed", "Crystal", "Anchor"};
        Q = new ExplosionType(6.0f, stringArray[1]);
        z = new ExplosionType(5.0f, stringArray[2]);
        k = new ExplosionType(5.0f, stringArray[0]);
    }

    public static void G(int n) {
        r = n;
    }

    private ExplosionType(float f, String string) {
        this.H = f;
        this.P = string;
    }

    public String G() {
        return this.P;
    }

    public static int q() {
        int n = ExplosionType.R();
        if (n == 0) {
            return 87;
        }
        return 0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public float I() {
        return this.H;
    }

    public static int R() {
        return r;
    }
}

