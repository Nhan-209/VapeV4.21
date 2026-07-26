package gg.vape.module.control;

import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModuleControlClaim {
    private static boolean j;
    private final AtomicBoolean R;
    private final HashMap<Mod, Integer> E = new HashMap();
    private final AtomicBoolean K = new AtomicBoolean();
    protected Mod v;
    protected Mod S;
    private final boolean O;

    public void l(Mod mod, int n) {
        this.E.put(mod, n);
    }

    public Mod N() {
        return this.S;
    }

    protected boolean l(Mod mod) {
        boolean bl = this.v != null && this.v.equals(mod);
        return bl;
    }

    public void M(boolean bl) {
        this.R.set(bl);
    }

    public Mod y() {
        return this.v;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ModuleControlClaim() {
        this(false);
    }

    public void A(Mod mod) {
        this.K.set(true);
        this.S = mod;
    }

    public boolean X() {
        return this.K.get();
    }

    protected boolean s(Mod mod) {
        if (this.O) {
            if (this.W(mod)) {
                this.void_v();
                return true;
            }
            if (!this.l(mod)) {
                return false;
            }
        }
        this.v = null;
        this.R.set(false);
        return true;
    }

    public ModuleControlClaim(boolean bl) {
        this.R = new AtomicBoolean();
        this.O = bl;
    }

    public void void_v() {
        this.K.set(false);
        this.S = null;
    }

    protected boolean r(Mod mod) {
        int n;
        int n2 = this.E.getOrDefault(mod, 0);
        boolean bl = n2 > (n = this.E.getOrDefault(this.v, 0).intValue());
        return bl;
    }

    public static boolean U() {
        return j;
    }

    protected boolean q(Mod mod) {
        if (this.O) {
            if (this.R.get() || this.X() && !this.W(mod)) {
                return false;
            }
            this.void_v();
        }
        this.v = mod;
        this.R.set(true);
        return true;
    }

    public HashMap<Mod, Integer> D() {
        return this.E;
    }

    public boolean boolean_v() {
        return this.R.get();
    }

    public static void u(boolean bl) {
        j = bl;
    }

    protected boolean W(Mod mod) {
        boolean bl = this.S != null && this.S.equals(mod);
        return bl;
    }

    public static boolean r() {
        boolean bl = ModuleControlClaim.U();
        return true;
    }

    public void Q() {
        this.R.set(false);
    }

    public void c() {
        this.R.set(true);
    }

    public boolean w(Mod mod) {
        if (this.q(mod)) {
            return true;
        }
        if (this.O) {
            boolean bl = this.X();
            boolean bl2 = this.W(mod);
            boolean bl3 = this.r(mod);
            if (this.boolean_v() && !this.l(mod)) {
                if (bl && !bl2) {
                    return false;
                }
                if (!bl3) {
                    return false;
                }
                this.A(mod);
            }
        }
        return false;
    }

    static {
        if (ModuleControlClaim.U()) {
            ModuleControlClaim.u(true);
        }
    }

    public /* synthetic */ void v() {
        this.void_v();
    }

    public /* synthetic */ boolean v$src$Z$1r7ksy2() {
        return this.boolean_v();
    }
}

