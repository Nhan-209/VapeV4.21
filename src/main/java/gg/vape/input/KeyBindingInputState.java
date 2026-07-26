package gg.vape.input;

import gg.vape.config.ClientSettings;
import gg.vape.input.InputEventDispatcher;
import gg.vape.runtime.NativeBridge;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class KeyBindingInputState {
    public static final int l;
    public static final int a = 1;
    public static final int s;
    public static final int N;
    public static final int G;
    public static final int Z;
    public static final int M;
    public static final int P;
    public static final int d;
    public static final int f;
    public static final int k = 1;
    public static final int R;
    public static final int p;
    public static final int z;
    public static final int U;
    public static final int O = 1;
    public static final int D;
    public static final int K;

    public static void d() {
        KeyBindingInputState.W(false);
    }

    public static boolean W() {
        return (NativeBridge.gks(5) & 0x100) != 0;
    }

    public static boolean l() {
        return (NativeBridge.gks(1) & 0x100) != 0;
    }

    public static void a(KeyBinding keyBinding, boolean bl) {
        int n;
        int n2 = ClientSettings.H(keyBinding);
        int n3 = n = ForgeVersion.MC_1_21_4.d() ? 4 : 0;
        if (n2 > n) {
            ClientSettings.b(keyBinding, true);
        } else {
            if (ForgeVersion.MC_1_21_4.v()) {
                n2 += 100;
            }
            if (n2 == 0) {
                KeyBindingInputState.C(bl);
            } else {
                KeyBindingInputState.i(bl);
            }
        }
    }

    public static void c() {
        KeyBindingInputState.R(false);
    }

    public static void X(int n, boolean bl, boolean bl2) {
        switch (n) {
            case 0: {
                if (bl) {
                    KeyBindingInputState.C(bl2);
                } else {
                    KeyBindingInputState.Z(bl2);
                }
                return;
            }
            case 1: {
                if (bl) {
                    KeyBindingInputState.i(bl2);
                } else {
                    KeyBindingInputState.J(bl2);
                }
                return;
            }
            case 2: {
                if (bl) {
                    KeyBindingInputState.W(bl2);
                } else {
                    KeyBindingInputState.r(bl2);
                }
                return;
            }
            case 3: {
                if (bl) {
                    KeyBindingInputState.T(bl2);
                } else {
                    KeyBindingInputState.O(bl2);
                }
                return;
            }
            case 4: {
                if (bl) {
                    KeyBindingInputState.X(bl2);
                } else {
                    KeyBindingInputState.R(bl2);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Unsupported mouse button: " + n);
    }

    public static void J(boolean bl) {
        if (bl) {
            NativeBridge.smdp(2, 517);
        } else {
            NativeBridge.smd(2, 517);
        }
    }

    public static void k() {
        KeyBindingInputState.C(false);
    }

    public static boolean X() {
        return (NativeBridge.gks(4) & 0x100) != 0;
    }

    public static void r(KeyBinding keyBinding, boolean bl) {
        int n;
        int n2 = ClientSettings.H(keyBinding);
        int n3 = n = ForgeVersion.MC_1_21_4.d() ? 4 : 0;
        if (n2 > n) {
            ClientSettings.b(keyBinding, false);
        } else {
            if (ForgeVersion.MC_1_21_4.v()) {
                n2 += 100;
            }
            if (n2 == 0) {
                KeyBindingInputState.Z(bl);
            } else {
                KeyBindingInputState.J(bl);
            }
        }
    }

    public static boolean n(int n) {
        switch (n) {
            case 0: {
                return KeyBindingInputState.l();
            }
            case 1: {
                return KeyBindingInputState.q$src$Z$1enyqt3();
            }
            case 2: {
                return KeyBindingInputState.X();
            }
            case 3: {
                return KeyBindingInputState.W();
            }
            case 4: {
                return KeyBindingInputState.x();
            }
        }
        throw new IllegalArgumentException("Unsupported mouse button: " + n);
    }

    public static void T(boolean bl) {
        KeyBindingInputState.l(bl, true, 1);
    }

    public static void T(int n, boolean bl) {
        KeyBindingInputState.r(n, bl);
    }

    public static void W(boolean bl) {
        if (bl) {
            NativeBridge.smdp(16, 519);
        } else {
            NativeBridge.smd(16, 519);
        }
    }

    public static void Z(boolean bl) {
        if (bl) {
            NativeBridge.smdp(1, 514);
        } else {
            NativeBridge.smd(1, 514);
        }
    }

    public static void L() {
        KeyBindingInputState.i(false);
    }

    public static void X(boolean bl) {
        KeyBindingInputState.l(bl, true, 2);
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public static void C(boolean bl) {
        if (bl) {
            NativeBridge.smdp(1, 513);
        } else {
            NativeBridge.smd(1, 513);
        }
    }

    public static void q() {
        KeyBindingInputState.X(false);
    }

    public static void V() {
        KeyBindingInputState.f(false);
    }

    public static boolean x() {
        return (NativeBridge.gks(6) & 0x100) != 0;
    }

    public static void r(boolean bl) {
        if (bl) {
            NativeBridge.smdp(16, 520);
        } else {
            NativeBridge.smd(16, 520);
        }
    }

    public static void U() {
        KeyBindingInputState.O(false);
    }

    public static void M() {
        KeyBindingInputState.M(false);
    }

    public static void K() {
        KeyBindingInputState.d(false);
    }

    public static void O(boolean bl) {
        KeyBindingInputState.l(bl, false, 1);
    }

    public static void M(boolean bl) {
        KeyBindingInputState.r(Minecraft.gameSettings().F(), bl);
    }

    public static void R(boolean bl) {
        KeyBindingInputState.l(bl, false, 2);
    }

    public static boolean q$src$Z$1enyqt3() {
        return (NativeBridge.gks(2) & 0x100) != 0;
    }

    public static void r(int n, boolean bl) {
        KeyBindingInputState.X(n, false, bl);
    }

    public static void a() {
        KeyBindingInputState.r(false);
    }

    public static void o(boolean bl) {
        KeyBindingInputState.a(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362(), bl);
    }

    public static void z(int n, boolean bl) {
        KeyBindingInputState.Q(n, bl);
    }

    private static void l(boolean bl, boolean bl2, int n) {
        int n2 = bl2 ? 523 : 524;
        long l = (long)n << 16;
        NativeBridge.smpm(bl, InputEventDispatcher.getInstance().getWindowHandle(), n2, l, 0L);
    }

    public static void f() {
        KeyBindingInputState.J(false);
    }

    public static void p() {
        KeyBindingInputState.T(false);
    }

    public static void Q(int n, boolean bl) {
        KeyBindingInputState.X(n, true, bl);
    }

    public static void i(boolean bl) {
        if (bl) {
            NativeBridge.smdp(2, 516);
        } else {
            NativeBridge.smd(2, 516);
        }
    }

    public static void r() {
        KeyBindingInputState.Z(false);
    }

    static {
        long[] lArray = new long[]{5268082283977376261L, 635020552620212748L, 4531861087703793666L, -8592204563820314101L, 4665297638462063112L, -5201470760010382847L, 1670101142605922823L, 3726329813741338628L, -2116712616800812542L, -2995678575870344700L, -5386562856667840496L, 120866242339799045L, 5034933093870338054L};
        p = (int)lArray[3];
        G = (int)lArray[8];
        M = (int)lArray[11];
        K = (int)lArray[12];
        z = (int)lArray[7];
        s = (int)lArray[0];
        l = (int)lArray[5];
        Z = (int)lArray[2];
        D = (int)lArray[1];
        f = (int)lArray[10];
        P = (int)lArray[6];
        d = (int)lArray[9];
        U = (int)lArray[4];
        N = (int)lArray[2];
        R = (int)lArray[2];
    }

    public static void d(boolean bl) {
        KeyBindingInputState.a(Minecraft.gameSettings().F(), bl);
    }

    public static void D() {
        KeyBindingInputState.o(false);
    }

    public static void f(boolean bl) {
        KeyBindingInputState.r(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362(), bl);
    }
}

