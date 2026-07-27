package gg.vape.click;

import gg.vape.Vape;
import gg.vape.click.AutoClickerTimingState;
import gg.vape.click.ClickButton;
import gg.vape.config.ClientSettings;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.AttackKeyController;
import gg.vape.input.KeyBindingHelper;
import gg.vape.input.KeyBindingInputState;
import gg.vape.module.Mod;
import gg.vape.rotation.PlayerMouseRotationApplier;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.MathUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.WorldClient;
import java.util.Random;

public class ClickEngine {
    public final Random w;
    int Y;
    double E;
    public final float c;
    boolean h;
    double r;
    private static GuiComponent[] D;
    private final BooleanValue v;
    int R;
    int i;
    int o;
    int a;
    double L;
    long X;
    private final ModeValue S;
    double q;
    int m;
    int Z;
    private final BooleanValue e;
    private final RandomValue G;
    private final Mod d;
    int V;
    float x;
    private final ClickButton u;
    private final AutoClickerTimingState B;
    int J;
    boolean W;
    private final LimitValue T;
    float l;
    int z;
    private final BooleanValue A;

    public ClickEngine(ClickButton clickButton, RandomValue randomValue, BooleanValue booleanValue, LimitValue limitValue, BooleanValue booleanValue2, ModeValue modeValue, BooleanValue booleanValue3, Mod mod) {
        this.J = 0;
        this.h = false;
        this.z = 0;
        this.W = true;
        this.m = 0;
        this.i = 0;
        this.o = 0;
        this.Y = 0;
        this.X = 0L;
        this.R = 0;
        this.r = 0.0;
        this.E = 0.0;
        this.w = new Random();
        this.c = 32767.0f;
        this.u = clickButton;
        this.G = randomValue;
        this.e = booleanValue;
        this.T = limitValue;
        this.A = booleanValue2;
        this.S = modeValue;
        this.d = mod;
        this.v = booleanValue3;
        this.e.K(this.T);
        if (clickButton == ClickButton.LEFT) {
            this.Z = 513;
            this.a = 514;
            this.V = 1;
        } else if (clickButton == ClickButton.RIGHT) {
            this.Z = 516;
            this.a = 517;
            this.V = 2;
        }
        this.B = new AutoClickerTimingState(Vape.INSTANCE.getAccountTier());
    }

    public ClickEngine(ClickButton clickButton, RandomValue randomValue, BooleanValue booleanValue, LimitValue limitValue, BooleanValue booleanValue2, ModeValue modeValue, BooleanValue booleanValue3) {
        this(clickButton, randomValue, booleanValue, limitValue, booleanValue2, modeValue, booleanValue3, null);
    }

    public static void B(GuiComponent[] guiComponentArray) {
        D = guiComponentArray;
    }

    public void r(EventPreRenderTick eventPreRenderTick) {
        if (!this.v.L().booleanValue() || this.x == 0.0f && this.l == 0.0f) {
            return;
        }
        WorldClient worldClient = eventPreRenderTick.getWorld();
        EntityPlayerSP entityPlayerSP = eventPreRenderTick.getThePlayer();
        if (worldClient.isNull() || entityPlayerSP.isNull() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        float f = Minecraft.gameSettings().y();
        int n = (int)this.x;
        int n2 = (int)(-this.l);
        float f2 = f * 0.6f + 0.2f;
        float f3 = f2 * f2 * f2 * 8.0f;
        float f4 = (float)n * f3;
        float f5 = (float)n2 * f3;
        PlayerMouseRotationApplier.j(f4, f5);
        this.x = MathUtil.floor(MathUtil.c(this.x, 1.0f));
        this.l = MathUtil.floor(MathUtil.c(this.l, 1.0f));
    }

    public ClickButton s() {
        return this.u;
    }

    public void p() {
        if (this.d != null) {
            AttackKeyController.u(this.d);
            return;
        }
        KeyBindingInputState.d(true);
    }

    public RandomValue z() {
        return this.G;
    }

    public static GuiComponent[] O() {
        return D;
    }

    public void F() {
        if (this.d != null) {
            KeyBindingHelper.v(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362(), true, true);
            return;
        }
        KeyBindingInputState.o(true);
    }

    public void g() {
        if (this.u == ClickButton.LEFT) {
            this.p();
        } else {
            this.F();
        }
        this.Z();
    }

    public long u() {
        int n;
        RandomValue randomValue = this.G;
        int n2 = randomValue.s$src$I$vi2lk8();
        int n3 = randomValue.y();
        int n4 = this.S == null ? 2 : this.S.w$src$I$15qcf2k();
        int n5 = n3 - n2;
        int n6 = n = n5 <= 0 ? n2 : this.w.nextInt(n5) + n2 + 1;
        if (n4 == 0) {
            this.X = 1000 / n;
            return this.X;
        }
        if (n == 0) {
            n = 1;
        }
        if (n4 == 1) {
            if (!this.h) {
                this.X = 1000 / n;
                if (this.w.nextInt(4) == 1) {
                    this.h = true;
                    this.z = 1 + this.w.nextInt(5);
                } else if (this.w.nextInt(10) != 1 && this.w.nextInt(10) == 1) {
                    this.h = true;
                    this.z = 5 + this.w.nextInt(10);
                }
            }
            if (this.h) {
                ++this.J;
                if (this.J >= this.z) {
                    this.J = 0;
                    this.h = false;
                }
            }
            if (this.w.nextInt(48) % (this.W ? 6 : 10) == 0 && !this.h) {
                n2 = 40;
                n3 = 85;
                n5 = n3 - n2;
                this.X += (long)(this.w.nextInt(n5) + n2);
            }
            if (this.W) {
                ++this.i;
                if (this.i >= this.o) {
                    this.Y = 75 + this.w.nextInt(125);
                    this.W = false;
                    this.i = 0;
                }
                int n7 = this.w.nextInt(5) == 3 ? 50 : 25;
                return this.X + (long)n7;
            }
            ++this.m;
            if (this.m >= this.Y) {
                this.W = true;
                this.o = 7 + this.w.nextInt(8);
                this.m = 0;
            }
            return this.X;
        }
        this.B.Z(n2, n3);
        return this.B.Y();
    }

    public void N() {
        if (this.u == ClickButton.LEFT) {
            this.A();
        } else {
            this.d();
        }
    }

    static {
        ClickEngine.B(null);
    }

    public void d() {
        if (this.d != null) {
            KeyBindingHelper.v(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362(), false, false);
            return;
        }
        KeyBindingInputState.f(true);
    }

    private KeyBinding e() {
        if (this.u == ClickButton.LEFT) {
            return Minecraft.gameSettings().F();
        }
        return Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
    }

    private boolean e$src$Z$172de9o() {
        if (Minecraft.currentScreen().isNotNull()) {
            return true;
        }
        if (!this.e.L().booleanValue()) {
            return true;
        }
        return this.T.y();
    }

    public void A() {
        if (this.d != null) {
            AttackKeyController.Q();
            return;
        }
        KeyBindingInputState.M(true);
    }

    public int P(EntityPlayerSP entityPlayerSP) {
        Object[] objectArray = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
        int n = 0;
        int n2 = -1;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = new ItemStack(objectArray[i]);
            if (!itemStack.isNull()) continue;
            ++n;
        }
        return n;
    }

    public boolean R(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.thePlayer().isNull()) {
            return false;
        }
        return this.e$src$Z$172de9o();
    }

    public boolean G() {
        if (!this.A.L().booleanValue()) {
            return Minecraft.a();
        }
        return ClientSettings.H$src$Z$9w16bz(this.e());
    }

    public void Z() {
        if (!this.v.L().booleanValue()) {
            return;
        }
        float f = 7.0f;
        this.L = MathUtil.random(this.w, -f, f);
        this.q = MathUtil.random(this.w, -f, f);
        this.E = this.r = (MathUtil.H(this.L) + MathUtil.H(this.q)) * 0.45;
    }

    public void J(EventPreTick eventPreTick) {
        if (!this.v.L().booleanValue()) {
            return;
        }
        if (this.E > 0.0) {
            this.x = (float)((double)this.x + this.L / this.r);
            this.l = (float)((double)this.l + this.q / this.r);
            this.E -= 1.0;
        } else {
            this.x = MathUtil.floor(MathUtil.c(this.x, 1.0f));
            this.l = MathUtil.floor(MathUtil.c(this.l, 1.0f));
        }
    }

}
