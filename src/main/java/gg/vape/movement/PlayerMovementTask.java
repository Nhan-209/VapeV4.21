package gg.vape.movement;

import gg.vape.config.ClientSettings;
import gg.vape.event.impl.EventTickBase;
import gg.vape.movement.MovementInputHelper;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.SleepUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;

public abstract class PlayerMovementTask {
    public double T;
    private boolean E = false;
    private static String[] y;
    private boolean r = false;
    private ArrayList<KeyBinding> F = new ArrayList();
    private boolean u = false;
    private boolean q;
    public double s = 0.0;
    private boolean m = true;
    private double L = 0.2;
    private boolean U = false;
    private boolean n = false;
    private boolean i = false;

    public boolean n() {
        return this.m;
    }

    public void r() {
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        boolean bl = this.z();
        if (bl) {
            if (!this.r) {
                this.r = true;
            }
            this.s(true);
            return;
        }
        if (!this.r) {
            // empty if block
        }
        if (this.r && (!this.i || entityPlayerSP.b$src$Z$fqlxe4())) {
            this.s(true);
        }
    }

    public static void M(String[] stringArray) {
        y = stringArray;
    }

    public void v(double d) {
        this.L = d;
    }

    public void v(boolean bl) {
        this.i = bl;
    }

    public boolean G(long l) {
        int n = 0;
        while (!this.q$src$Z$naak2i()) {
            SleepUtil.sleep(10L);
            if ((long)(++n) <= l / 10L) continue;
            return true;
        }
        return false;
    }

    public void d(ArrayList<KeyBinding> arrayList) {
        this.F = arrayList;
    }

    static {
        if (PlayerMovementTask.G() != null) {
            PlayerMovementTask.M(new String[5]);
        }
    }

    public static String[] G() {
        return y;
    }

    public double q() {
        return this.s;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean b() {
        return this.U;
    }

    public abstract boolean z();

    public void g(boolean bl) {
        this.m = bl;
    }

    public boolean T() {
        return this.i;
    }

    public double g() {
        return this.T;
    }

    public ArrayList<KeyBinding> s() {
        return this.F;
    }

    public boolean I() {
        return this.E;
    }

    public void c(boolean bl) {
        this.n = bl;
    }

    public boolean q$src$Z$naak2i() {
        return this.q;
    }

    public void i(EventTickBase eventTickBase) {
        if (eventTickBase.getThePlayer().isNull() || eventTickBase.getWorld().isNull()) {
            return;
        }
        if (this.T != 0.0 || this.s != 0.0) {
            KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
            if (this.H() && Math.abs(this.T) < 2.0 && Math.abs(this.s) < 2.0) {
                MovementInputHelper.w(keyBinding, true);
            } else {
                boolean bl = ClientSettings.B(keyBinding);
                if (bl) {
                    MovementInputHelper.w(keyBinding, true);
                } else {
                    MovementInputHelper.w(keyBinding, false);
                }
            }
            MovementInputHelper.j(this.T, this.s, this.F, this.E);
        }
    }

    public boolean H() {
        return this.n;
    }

    public void x(boolean bl) {
        this.u = bl;
    }

    public boolean B() {
        return this.u;
    }

    public void s(boolean bl) {
        this.q = bl;
    }

    public double Y() {
        return this.L;
    }

    public void P(boolean bl) {
        this.U = bl;
    }

    public void l(boolean bl) {
        this.E = bl;
    }
}

