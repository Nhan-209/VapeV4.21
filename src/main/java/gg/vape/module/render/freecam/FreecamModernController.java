package gg.vape.module.render.freecam;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityRendererMouseUpdate;
import gg.vape.event.impl.EventKeyBindingState;
import gg.vape.event.impl.EventPostEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreLivingTravel;
import gg.vape.event.impl.EventPreLocalPlayerTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyboardInput;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.Freecam;
import gg.vape.module.render.freecam.FreecamController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MovementInput;
import gg.vape.wrapper.impl.Vec3;
import java.util.UUID;

public class FreecamModernController
extends FreecamController<Freecam> {
    private float f;
    private float x;
    private float b;
    private float w;
    private double D;
    private double q;
    private float l;
    private float z;
    private double Z;
    private double t;
    private double h;
    private float F;
    private float Q;
    private double C;
    private float g;
    private float j;
    private double I;
    private boolean M;
    private boolean S;
    private boolean o;
    private float a;
    private double R;
    private boolean K;
    private float d;

    private void Z() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        entityPlayerSP.E().W();
    }

    private void o() {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        this.C = this.D;
        this.h = this.Z;
        this.t = this.R;
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        double d6 = d5 = ClientSettings.B(Minecraft.gameSettings().Y()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().s())) {
            d5 -= 1.0;
        }
        double d7 = d4 = ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3())) {
            d4 -= 1.0;
        }
        double d8 = d3 = ClientSettings.B(Minecraft.gameSettings().O()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0())) {
            d3 -= 1.0;
        }
        if ((d2 = Math.sqrt(d5 * d5 + d4 * d4)) > 0.0) {
            d5 /= d2;
            d4 /= d2;
            d = (Double)((Freecam)this.n).Y.K() / 5.0;
            if (ClientSettings.B(Minecraft.gameSettings().r())) {
                d *= 2.0;
            }
            double d9 = Math.toRadians(this.z + 90.0f);
            this.D += (d5 * Math.cos(d9) + d4 * Math.sin(d9)) * d;
            this.R += (d5 * Math.sin(d9) - d4 * Math.cos(d9)) * d;
        }
        if (d3 != 0.0) {
            d = (Double)((Freecam)this.n).v.K() / 5.0 * 0.42;
            if (ClientSettings.B(Minecraft.gameSettings().r())) {
                d *= 2.0;
            }
            this.Z += d3 * d;
        }
    }

    public FreecamModernController(Freecam freecam) {
        super(freecam);
    }

    private void v() {
        EntityPlayerSP entityPlayerSP;
        if (!((Freecam)this.n).A.L().booleanValue()) {
            ((Freecam)this.n).Z();
            return;
        }
        if (((Freecam)this.n).Z != null && (((Freecam)this.n).Z.M$src$Z$ff28xj() || ((Freecam)this.n).H == 0 || Minecraft.theWorld().V(((Freecam)this.n).H).isNull())) {
            ClientSettings.I(((Freecam)this.n).H);
            ((Freecam)this.n).H = 0;
            ((Freecam)this.n).Z = null;
        }
        if (((Freecam)this.n).Z == null) {
            this.x();
        }
        if ((entityPlayerSP = Minecraft.thePlayer()).isNull() || ((Freecam)this.n).Z == null) {
            return;
        }
        ((Freecam)this.n).Z.t(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        ((Freecam)this.n).Z.n(entityPlayerSP.f());
        ((Freecam)this.n).Z.w(entityPlayerSP.H());
        ((Freecam)this.n).Z.A(entityPlayerSP.R());
        ((Freecam)this.n).Z.C(entityPlayerSP.M());
        ((Freecam)this.n).Z.L(entityPlayerSP.W());
        ((Freecam)this.n).Z.s(entityPlayerSP.m$src$D$fwnne5());
        ((Freecam)this.n).Z.H(entityPlayerSP.J());
        ((Freecam)this.n).Z.D(entityPlayerSP.j());
        ((Freecam)this.n).Z.z(entityPlayerSP.s());
        ((Freecam)this.n).Z.o(entityPlayerSP.P$src$F$14ztfk8());
        ((Freecam)this.n).Z.C(entityPlayerSP.V());
        ((Freecam)this.n).Z.l(entityPlayerSP.D());
        ((Freecam)this.n).Z.U(entityPlayerSP.b$src$Z$fqlxe4());
        ((Freecam)this.n).Z.E(entityPlayerSP.t(), entityPlayerSP.q(), entityPlayerSP.T());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void R(EntityPlayerSP entityPlayerSP) {
        if (!this.K) {
            this.b = entityPlayerSP.q$src$F$1u6qsjx();
            this.a = entityPlayerSP.t$src$F$1u8e6c0();
            this.x = entityPlayerSP.x$src$F$1ualcpg();
            this.g = entityPlayerSP.n$src$F$1u53eru();
            this.K = true;
        }
        entityPlayerSP.F(this.z);
        entityPlayerSP.d(this.f);
        entityPlayerSP.E(this.z);
        entityPlayerSP.a(this.f);
    }

    private void W() {
        if (!this.E()) {
            this.L();
            return;
        }
        this.Z();
        if (!this.M) {
            this.S = Minecraft.gameSettings().k();
            this.M = true;
        }
        Minecraft.gameSettings().O(false);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull() || Minecraft.thePlayer().w$src$F$15l9epb() <= 0.0f) {
            ((Freecam)this.n).s(false, false);
        }
        if (((Freecam)this.n).C) {
            this.K();
            ((Freecam)this.n).Z();
            ((Freecam)this.n).M$src$V$nre1v6();
            return;
        }
        ((Freecam)this.n).c.d(this.n);
        if (((Freecam)this.n).S || !this.o) {
            this.z();
        }
        this.o();
        this.v();
        this.c();
    }

    private double m() {
        double d = (double)Minecraft.gameSettings().y() * 0.6 + 0.2;
        return d * d * d * 8.0;
    }

    @EventHandler
    public void t(EventPreRenderTick eventPreRenderTick) {
        if (!this.o) {
            return;
        }
        this.W();
        this.b();
        if (Minecraft.currentScreen().isNotNull()) {
            this.I = Minecraft.s().R();
            this.q = Minecraft.s().b();
            return;
        }
        this.n();
    }

    @EventHandler
    public void w(EventEntityRendererMouseUpdate eventEntityRendererMouseUpdate) {
        if (!this.o) {
            return;
        }
        this.J(eventEntityRendererMouseUpdate.getPartialTicks());
    }

    private void x() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        GameProfile gameProfile = GameProfile.create(UUID.randomUUID(), entityPlayerSP.getName());
        EntityOtherPlayerMP entityOtherPlayerMP = EntityOtherPlayerMP.create(Minecraft.theWorld(), gameProfile);
        entityOtherPlayerMP.M(entityPlayerSP, true);
        ((Freecam)this.n).H = ClientSettings.f();
        entityOtherPlayerMP.Q(((Freecam)this.n).H);
        entityOtherPlayerMP.y(UUID.randomUUID());
        entityOtherPlayerMP.t(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        entityOtherPlayerMP.z(entityPlayerSP.s());
        entityOtherPlayerMP.o(entityPlayerSP.P$src$F$14ztfk8());
        ((Freecam)this.n).Z = entityOtherPlayerMP;
        Minecraft.theWorld().D(((Freecam)this.n).H, entityOtherPlayerMP);
    }

    @Override
    public void B() {
        this.o = false;
        ((Freecam)this.n).S = true;
        this.z();
    }

    private void n() {
        double d = Minecraft.s().R();
        double d2 = Minecraft.s().b();
        double d3 = d - this.I;
        double d4 = d2 - this.q;
        this.I = d;
        this.q = d2;
        double d5 = d3 * this.m() * 0.15;
        double d6 = d4 * this.m() * 0.15;
        this.z += (float)d5;
        this.f += (float)d6;
        this.f = MathUtil.clamp(this.f, -90.0f, 90.0f);
    }

    @EventHandler
    public void T(EventPostEntityRendererMouseUpdate eventPostEntityRendererMouseUpdate) {
        if (!this.o) {
            return;
        }
        this.b();
    }

    @EventHandler
    public void t(EventPreLocalPlayerTick eventPreLocalPlayerTick) {
        if (!this.o) {
            return;
        }
        this.c();
    }

    private void J(float f) {
        ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
        if (activeRenderInfo.isNull()) {
            return;
        }
        double d = this.C + (this.D - this.C) * (double)f;
        double d2 = this.h + (this.Z - this.h) * (double)f;
        double d3 = this.t + (this.R - this.t) * (double)f;
        activeRenderInfo.g(Vec3.create(d, d2, d3));
    }

    @EventHandler
    public void r(EventKeyBindingState eventKeyBindingState) {
        if (!this.o || !eventKeyBindingState.isPressed() || eventKeyBindingState.getKeyBinding() == null) {
            return;
        }
        if (this.W(eventKeyBindingState.getKeyBinding())) {
            eventKeyBindingState.setCancelled(true);
        }
    }

    @EventHandler
    public void D(EventPreLivingTravel eventPreLivingTravel) {
        if (!this.o) {
            return;
        }
        this.c();
    }

    @EventHandler
    public void Z(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (!this.o) {
            return;
        }
        this.i();
    }

    private boolean l$src$Z$6p4mvw() {
        return ((Freecam)this.n).A.L() != false && ((Freecam)this.n).r.L() != false && Minecraft.currentScreen().isNull() && (KeyboardInput.isKeyDown(38) || KeyboardInput.isKeyDown(40) || KeyboardInput.isKeyDown(37) || KeyboardInput.isKeyDown(39));
    }

    private void i() {
        if (SharedModuleControlClaims.p.I()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.z);
        entityPlayerSP.D(this.z);
        entityPlayerSP.C(this.f);
        entityPlayerSP.l(this.f);
        this.R(entityPlayerSP);
    }

    private void b() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.j);
        entityPlayerSP.D(this.j);
        entityPlayerSP.z(this.j);
        entityPlayerSP.o(this.j);
        entityPlayerSP.C(this.w);
        entityPlayerSP.l(this.w);
        this.e(entityPlayerSP);
    }

    private void z() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        this.F = entityPlayerSP.J();
        this.l = entityPlayerSP.V();
        this.d = entityPlayerSP.j();
        this.Q = entityPlayerSP.D();
        this.j = this.F;
        this.w = this.l;
        this.z = this.F;
        this.f = this.l;
        this.D = entityPlayerSP.z();
        this.Z = entityPlayerSP.N() + (double)entityPlayerSP.X();
        this.R = entityPlayerSP.h();
        this.C = this.D;
        this.h = this.Z;
        this.t = this.R;
        this.I = Minecraft.s().R();
        this.q = Minecraft.s().b();
        this.o = true;
        ((Freecam)this.n).S = false;
    }

    private void L() {
        if (!this.M) {
            return;
        }
        Minecraft.gameSettings().O(this.S);
        this.M = false;
    }

    private void K() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.F);
        entityPlayerSP.D(this.d);
        entityPlayerSP.z(this.F);
        entityPlayerSP.o(this.d);
        entityPlayerSP.C(this.l);
        entityPlayerSP.l(this.Q);
        this.e(entityPlayerSP);
    }

    @Override
    public void I() {
        this.L();
        this.b();
        ((Freecam)this.n).Z();
        this.o = false;
        ((Freecam)this.n).S = false;
        ((Freecam)this.n).C = false;
    }

    private boolean W(Object object) {
        KeyBinding keyBinding = new KeyBinding(object);
        return keyBinding.equals(Minecraft.gameSettings().Y()) || keyBinding.equals(Minecraft.gameSettings().s()) || keyBinding.equals(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()) || keyBinding.equals(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()) || keyBinding.equals(Minecraft.gameSettings().O()) || keyBinding.equals(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0()) || keyBinding.equals(Minecraft.gameSettings().r());
    }

    private void c() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        if (((Freecam)this.n).A.L().booleanValue() && ((Freecam)this.n).r.L().booleanValue() && Minecraft.currentScreen().isNull()) {
            if (KeyboardInput.isKeyDown(38)) {
                f += 1.0f;
            }
            if (KeyboardInput.isKeyDown(40)) {
                f -= 1.0f;
            }
            if (KeyboardInput.isKeyDown(37)) {
                f2 += 1.0f;
            }
            if (KeyboardInput.isKeyDown(39)) {
                f2 -= 1.0f;
            }
        }
        MovementInput movementInput = entityPlayerSP.movementInput();
        movementInput.B(f);
        movementInput.M(f2);
        movementInput.V(false);
        movementInput.setCancelled(false);
        movementInput.b().N(false);
        entityPlayerSP.M(f);
        entityPlayerSP.k$src$V$5315b7(f2);
        entityPlayerSP.R(false);
    }

    @EventHandler
    public void w(EventPostRenderTick eventPostRenderTick) {
        if (!this.o) {
            return;
        }
        this.b();
        this.L();
    }

    private void e(EntityPlayerSP entityPlayerSP) {
        if (!this.K) {
            return;
        }
        entityPlayerSP.F(this.b);
        entityPlayerSP.d(this.a);
        entityPlayerSP.E(this.x);
        entityPlayerSP.a(this.g);
        this.K = false;
    }

    private boolean E() {
        return this.o && ((Freecam)this.n).A.L() != false && ((Freecam)this.n).r.L() != false;
    }

    @EventHandler
    public void r(EventPostTick eventPostTick) {
        if (!this.o) {
            return;
        }
        this.c();
    }
}

