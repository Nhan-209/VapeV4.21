package gg.vape.module.render.hud;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPostEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostRenderWorldPass;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreRenderWorldPass;
import gg.vape.event.impl.EventRenderPlayerPost;
import gg.vape.event.impl.EventRenderPlayerPre;
import gg.vape.event.impl.EventTickBase;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class FreeLookHudModule
extends HudModule {
    private static float c;
    private final ModeOption F = new ModeOption("Hold");
    private final ModeOption os;
    private static float H;
    private static float oh;
    private final ModeOption s;
    private static float Y;
    private final NumberValue O;
    private static boolean o;
    private double b;
    private EntityLivingBase U;
    private final ModeOption C = new ModeOption("Toggle");
    private static float K;
    private double v;
    private final BooleanValue oN;
    private static float oR;
    private final ModeOption P;
    private static float j;
    private boolean L = false;
    public final ModeValue oO;
    private static float o4;
    private static float S;
    private static float A;
    private double r;
    public final ModeValue t = ModeValue.create((Object)this, "Activate Freelook", this.F, this.F, this.C);
    public final ModeValue p;
    private final ModeOption oQ;
    private int k = -1;
    private double J;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static float c() {
        return j;
    }

    @EventHandler
    public void Q(EventPostRenderTick eventPostRenderTick) {
        if (!this.L) {
            return;
        }
        this.w$src$V$1kb9hyx();
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void m(EventPreRenderWorldPass eventPreRenderWorldPass) {
        this.u$src$V$1ka5ws7();
    }

    private double c$src$D$1k09lo7() {
        if (!this.oN.L().booleanValue()) {
            return (double)Minecraft.gameSettings().y() * 0.6 * 0.2 * 8.0;
        }
        return (Double)this.O.K();
    }

    private void M$src$V$1jo651r() {
        if (!this.L) {
            return;
        }
        if (RotationManager.b.u() && Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.i();
    }

    @Override
    public void y() {
        if (!this.r$src$Z$14eylz9() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            this.r = Minecraft.s().R();
            this.v = Minecraft.s().b();
        }
        if (this.t.K() == this.C) {
            EventTickBase.p.execute(this::lambda$executeBind$0);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void L(EventRenderPlayerPost eventRenderPlayerPost) {
        this.M$src$V$1jo651r();
    }

    @EventHandler
    public void G(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (!o) {
            return;
        }
        if (!this.L) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        this.i();
    }

    @EventHandler
    public void o(EventPostTick eventPostTick) {
        if (this.t.K() != this.F) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        this.L = this.a().K();
        if (!this.L) {
            o = false;
            if (this.k == -1) {
                return;
            }
            Minecraft.gameSettings().I(this.k);
            this.k = -1;
            this.w$src$V$1kb9hyx();
        }
    }

    @EventHandler
    public void h(EventPostEntityRendererMouseUpdate eventPostEntityRendererMouseUpdate) {
        if (!this.L) {
            return;
        }
        this.w$src$V$1kb9hyx();
    }

    public static boolean z() {
        return o;
    }

    private void lambda$executeBind$0() {
        boolean bl = this.L = !this.L;
        if (!this.L) {
            o = false;
            if (this.k == -1) {
                return;
            }
            Minecraft.gameSettings().I(this.k);
            this.k = -1;
            this.w$src$V$1kb9hyx();
        }
    }

    static {
        o = false;
    }

    private void w$src$V$1kb9hyx() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(c);
        entityPlayerSP.D(c);
        entityPlayerSP.z(c);
        entityPlayerSP.o(c);
        entityPlayerSP.C(K);
        entityPlayerSP.l(K);
    }

    public void G(float f, float f2, float f3, float f4) {
        c = f;
        K = f2;
    }

    public static float w$src$F$1kb9hl5() {
        return H;
    }

    private void u$src$V$1ka5ws7() {
        if (!this.L) {
            return;
        }
        if (RotationManager.b.u() && Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.w$src$V$1kb9hyx();
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void w(EventRenderPlayerPre eventRenderPlayerPre) {
        this.u$src$V$1ka5ws7();
    }

    private void i() {
        if (SharedModuleControlClaims.p.I()) {
            return;
        }
        this.U.H(H);
        this.U.D(H);
        this.U.C(j);
        this.U.l(j);
    }

    public static float L$src$F$1jnmc2m() {
        return c;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void d(EventPostRenderWorldPass eventPostRenderWorldPass) {
        this.M$src$V$1jo651r();
    }

    public FreeLookHudModule() {
        super("Freelook", HudModuleGroup.T, "freelook2");
        this.oN = BooleanValue.create(this, "Use Custom Sensitivity", false, "Enable to set a separate sensitivity from Minecraft using a slider");
        this.O = NumberValue.create(this, "Sensitivity", "#.#", "", 0.001, 0.5, 1.0);
        this.s = new ModeOption("3rd Person");
        this.P = new ModeOption("1st Person");
        this.p = ModeValue.create((Object)this, "Perspective", this.s, this.s, this.P);
        this.os = new ModeOption("Forward");
        this.oQ = new ModeOption("Backward");
        this.oO = ModeValue.create((Object)this, "Starting Position", this.os, this.os, this.oQ);
        this.setSuffix("Freely rotates your perspective");
        this.q$src$V$1apmftw(true);
        this.addValue(this.t, this.oO, this.oN, this.O);
        this.oN.K(this.O);
    }

    public static float U() {
        return K;
    }

    private void J$src$V$1jmir9o() {
        if (ForgeVersion.MC_1_16_5.d()) {
            this.J = this.r - Minecraft.s().R();
            this.b = this.v - Minecraft.s().b();
            this.r = Minecraft.s().R();
            this.v = Minecraft.s().b();
            return;
        }
        this.J = -Minecraft.s().d();
        this.b = Minecraft.s().z();
    }

    @EventHandler
    public void u(EventPreRenderTick eventPreRenderTick) {
        int n;
        this.U = eventPreRenderTick.getThePlayer();
        if (!this.L) {
            return;
        }
        if (!o) {
            oh = this.U.V();
            o4 = this.U.J();
            Y = this.U.D();
            oR = this.U.j();
            this.k = Minecraft.gameSettings().x();
            A = ((ModeSelection)this.oO.K()).equals(this.os) ? 0.0f : 180.0f;
            S = 0.0f;
            this.J = 0.0;
            this.b = 0.0;
            H = o4;
            j = oh;
            K = oh;
            c = o4;
            o = true;
            return;
        }
        int n2 = n = ((ModeSelection)this.p.K()).equals(this.P) ? 0 : 1;
        if (Minecraft.gameSettings().x() != n) {
            Minecraft.gameSettings().I(n);
        }
        if (Minecraft.currentScreen().isNotNull()) {
            A = 0.0f;
            S = 0.0f;
            this.J = this.b = (double)0.0f;
        }
        this.J$src$V$1jmir9o();
        double d = this.J * this.c$src$D$1k09lo7() * 0.15;
        double d2 = this.b * this.c$src$D$1k09lo7() * 0.15;
        H = (float)(d - (double)A + (double)o4);
        j = (float)(d2 - (double)S + (double)oh);
        j = MathUtil.clamp(j, -90.0f, 90.0f);
        A = (float)(d + (double)A);
        S = (float)(d2 + (double)S);
        S = MathUtil.clamp(S, -(90.0f - oh), 90.0f + oh);
    }
}

