package gg.vape.rotation;

import gg.vape.Vape;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.render.Freecam;
import gg.vape.rotation.PlayerMouseRotationApplier;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.SleepUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GuiScreen;
import java.util.Random;

public abstract class MouseRotationController {
    private int l;
    private boolean m;
    public float W = 3.0f;
    private int U;
    private TimerUtil e;
    public float Q;
    public float y;
    private boolean Y;
    float S;
    float s;
    public float c;
    private int t;
    public float n;
    private int f;
    private int V;
    private boolean x;
    public float B;
    public float o;
    public float b = 1.0f;
    private final Random v = new Random();
    private static String A;
    private static Freecam N;


    public boolean V$src$Z$lb4tvc() {
        return this.Y;
    }

    public float N() {
        return this.o;
    }

    public void R(EventPostRenderTick eventPostRenderTick) {
    }

    public void B(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
    }

    public void w(float f, float f2) {
        PlayerMouseRotationApplier.j(f, f2);
    }

    public void D(boolean bl) {
        this.m = bl;
    }

    public MouseRotationController t(float f) {
        this.W = f;
        return this;
    }

    public void w(boolean bl) {
        this.x = bl;
    }

    public void Q(EventPreRenderTick eventPreRenderTick) {
        EntityPlayerSP entityPlayerSP = eventPreRenderTick.getThePlayer();
        this.Q = entityPlayerSP.J();
        this.c = entityPlayerSP.V();
        this.s = entityPlayerSP.j();
        this.S = entityPlayerSP.D();
    }

    public float O() {
        return this.b;
    }

    public abstract boolean A();

    public float V() {
        return this.n;
    }

    public boolean f() {
        return this.m;
    }

    public float u() {
        return this.y;
    }

    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        if (entityPlayerSP.isNull() || guiScreen.isNotNull()) {
            return;
        }
        boolean bl = this.A();
        boolean bl2 = this.m();
        if (bl && bl2 && Math.abs(this.B) < 1.0f && Math.abs(this.y) < 1.0f) {
            this.Y = true;
        }
    }

    public boolean g(long l) {
        int n = 0;
        while (!this.V$src$Z$lb4tvc()) {
            SleepUtil.sleep(10L);
            if ((long)(++n) <= l / 10L) continue;
            return true;
        }
        return false;
    }

    public abstract boolean m();

    public boolean v() {
        return this.x;
    }

    public static String y$src$Ljava_lang_String_$6sg99z() {
        return A;
    }

    public MouseRotationController Y(float f) {
        this.b = f;
        return this;
    }

    public static void W(String string) {
        A = string;
    }

    public void u(boolean bl) {
        this.Y = bl;
    }

    private void r() {
        ++this.U;
        if (this.U >= 250 + this.v.nextInt(50)) {
            this.U = MathUtil.randomExclusiveUpper(this.v, -100, -50);
            this.l = MathUtil.randomExclusiveUpper(this.v, -1, 2);
            this.f = -MathUtil.randomExclusiveUpper(this.v, -1, 2);
        }
        int n = this.l;
        int n2 = this.f;
        if (this.v.nextInt(10) < 2) {
            n = 0;
        }
        if (this.v.nextInt(10) < 2) {
            n2 = 0;
        }
        if (this.U < 0) {
            n = 0;
            n2 = 0;
        }
        if (this.v.nextInt(20) == 1) {
            this.t += n;
            this.V += n2;
        }
        if (this.B > 0.0f && this.t < 0 || this.B < 0.0f && this.t > 0) {
            this.t = 0;
        }
    }

    public void o(GuiScreen guiScreen) {
        boolean bl;
        if (this.m) {
            if (this.e == null) {
                this.e = new TimerUtil();
                this.e.x(-1L);
            }
            long l = this.e.getLastMS();
            this.e.reset();
            while (l-- > 0L) {
                this.r();
            }
            this.B += (float)this.t;
            this.y += (float)this.V;
        }
        int n = (int)this.B;
        int n2 = (int)this.y;
        float f = this.B - (float)n;
        float f2 = this.y - (float)n2;
        boolean bl2 = Math.abs(n) > 0;
        boolean bl3 = bl = Math.abs(n2) > 0;
        if (!bl2) {
            n = 0;
        }
        if (!bl) {
            n2 = 0;
        }
        float f3 = RotationManager.b.E();
        float f4 = f3 * 0.6f + 0.2f;
        float f5 = f4 * f4 * f4 * 8.0f;
        float f6 = (float)n * f5;
        float f7 = (float)n2 * f5;
        int n3 = -1;
        if (N == null) {
            N = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        if ((N == null || !N.r$src$Z$14eylz9()) && guiScreen.isNull()) {
            this.w(f6, f7 * (float)n3);
        }
        this.o = (float)((double)this.o + Math.abs((double)f6 * 0.15));
        this.n = (float)((double)this.n + Math.abs((double)f7 * 0.15));
        this.B = f;
        this.y = f2;
        this.t = 0;
        this.V = 0;
    }

    static {
        if (MouseRotationController.y$src$Ljava_lang_String_$6sg99z() == null) {
            MouseRotationController.W("YmWxEb");
        }
    }

    public float G() {
        return this.B;
    }
}

