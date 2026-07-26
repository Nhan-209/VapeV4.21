package gg.vape.unmap;

import gg.vape.config.ClientSettings;
import gg.vape.input.BindActivationMode;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.Bendable;

public class ModBendable
extends Bendable {
    private static final String b;
    private BindActivationMode Z = BindActivationMode.TOGGLE;
    private static String[] k;
    private final Mod Q;

    public static void Q(String[] stringArray) {
        k = stringArray;
    }

    @Override
    public BindActivationMode G() {
        return this.Z;
    }

    @Override
    public void A() {
        this.Q.y();
    }

    public ModBendable(Mod mod) {
        this.Q = mod;
    }

    @Override
    public boolean U(int n, boolean bl) {
        if (this.G() == BindActivationMode.TOGGLE) {
            return super.U(n, bl);
        }
        if (!this.n(n)) {
            return false;
        }
        if (bl) {
            if (this.A(n)) {
                if (!this.Q.r$src$Z$14eylz9()) {
                    this.Q.Y(true);
                }
                return true;
            }
            return false;
        }
        if (this.Q.r$src$Z$14eylz9()) {
            this.Q.Y(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean A$src$Z$jg36ch() {
        return true;
    }

    @Override
    public boolean m() {
        return this.Q.k();
    }

    @Override
    public String y() {
        return String.format(b, ClientSettings.F, ClientSettings.F, this.h(), ClientSettings.F, ClientSettings.F, this.Q.getName());
    }

    static {
        ModBendable.Q(new String[1]);
        b = " %s7[%sr%s%s7]%sr %s";
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean A(int n) {
        for (int n2 : this.L()) {
            if (n2 == n || ClientSettings.l(n2)) continue;
            return false;
        }
        return true;
    }

    public static String[] e() {
        return k;
    }

    @Override
    public void Y(BindActivationMode bindActivationMode) {
        this.Z = bindActivationMode == null ? BindActivationMode.TOGGLE : bindActivationMode;
    }
}

