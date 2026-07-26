package gg.vape.rotation;

import gg.vape.module.Mod;
import gg.vape.module.control.ModuleControlClaim;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class RotationControlClaim
extends ModuleControlClaim {
    private static String Y;

    static {
        if (RotationControlClaim.S() != null) {
            RotationControlClaim.g("xwxdz");
        }
    }

    public static String S() {
        return Y;
    }

    public boolean T() {
        return this.v$src$Z$1r7ksy2();
    }

    public boolean e(Mod mod) {
        boolean bl = false;
        if (this.v$src$Z$1r7ksy2() && !this.l(mod) && !this.r(mod)) {
            bl = true;
        }
        if (this.X() && !this.W(mod) && this.r(this.S)) {
            bl = true;
        }
        return bl;
    }

    public RotationControlClaim() {
        super(true);
    }

    public boolean X(Mod mod) {
        return this.s(mod);
    }

    public static void g(String string) {
        Y = string;
    }

    public boolean U(Mod mod) {
        return this.v$src$Z$1r7ksy2() && this.l(mod) && !this.X();
    }

    public boolean d(Mod mod) {
        return this.h(mod, false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean h(Mod mod, boolean bl) {
        if (bl && this.v$src$Z$1r7ksy2() && !this.l(mod) && RotationManager.b.u()) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
            if (this.r(mod) || adaptiveRotationController.O$src$Z$1lvi05g()) {
                this.v();
                this.X(this.v);
            }
        }
        return this.w(mod);
    }
}

