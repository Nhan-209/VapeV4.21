package gg.vape.rotation;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventMotion;
import gg.vape.event.impl.EventMouseOverUpdate;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPostLocalPlayerTick;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostRenderWorldPass;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreLocalPlayerTick;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreRenderWorldPass;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventRenderPlayerPost;
import gg.vape.event.impl.EventRenderPlayerPre;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.event.impl.EventSendClickBlockToController;
import gg.vape.event.impl.EventThreadBoundPostTick;
import gg.vape.event.impl.EventThreadBoundPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.blatant.HitBoxes;
import gg.vape.module.blatant.InvWalk;
import gg.vape.module.combat.Reach;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.MouseButtonActionState;
import gg.vape.rotation.MouseRotationController;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MouseOverRayTraceUpdater;
import gg.vape.utils.MutableColor;
import gg.vape.utils.NanoTimerUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PlayerInteractEventAction;
import gg.vape.wrapper.impl.PlayerPositionLookPacketModern;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.S08PacketPlayerPosLook;
import gg.vape.wrapper.impl.ScaledResolution;
import gg.vape.wrapper.impl.World;
import java.awt.Color;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class RotationManager
implements EventListener {
    private boolean c;
    private MouseRotationController E;
    private float p;
    private float N;
    private static final RayTraceResult T;
    private float J;
    private float w;
    public static RotationManager b;
    private float G;
    private float r;
    private float u;
    private boolean s;
    private float v;
    private float g;
    private int S;
    private float P;
    private MouseButtonActionState U;
    private float e;
    private float L = 0.0f;
    private boolean t;
    private boolean M;
    private RayTraceResult o;
    private RayTraceResult z;
    private boolean K;
    private float W;
    private boolean a;
    private RayTraceResult V;
    private double C;
    private boolean Q;
    private float f;
    private boolean X;
    private final NanoTimerUtil d = new NanoTimerUtil();
    private static final float x = 0.4f;
    private boolean j;
    private float A;
    private float l;

    public float Y(float f, boolean bl) {
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.Y();
        KeyBinding keyBinding2 = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        KeyBinding keyBinding3 = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        KeyBinding keyBinding4 = gameSettings.s();
        boolean bl2 = bl ? keyBinding.isKeyDown() : ClientSettings.B(keyBinding);
        boolean bl3 = bl ? keyBinding2.isKeyDown() : ClientSettings.B(keyBinding2);
        boolean bl4 = bl ? keyBinding3.isKeyDown() : ClientSettings.B(keyBinding3);
        boolean bl5 = bl ? keyBinding4.isKeyDown() : ClientSettings.B(keyBinding4);
        return this.G(f, bl2, bl3, bl4, bl5);
    }

    private void m(EntityPlayerSP entityPlayerSP, float f, float f2) {
        boolean bl;
        float f3 = MathUtil.wrapAngleTo180(f - this.p);
        this.p += f3 * 0.3f;
        float f4 = MathUtil.wrapAngleTo180(this.r - this.p);
        boolean bl2 = bl = f4 < -90.0f || f4 >= 90.0f;
        if (f4 < -75.0f) {
            f4 = -75.0f;
        }
        if (f4 >= 75.0f) {
            f4 = 75.0f;
        }
        this.p = this.r - f4;
        if (f4 * f4 > 2500.0f) {
            this.p += f4 * 0.2f;
        }
        if (bl) {
            f2 *= -1.0f;
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void U(EventPostLocalPlayerTick eventPostLocalPlayerTick) {
        EntityPlayerSP entityPlayerSP = eventPostLocalPlayerTick.getPlayer();
        if (this.c) {
            entityPlayerSP.H(this.N);
            entityPlayerSP.z(this.N);
            entityPlayerSP.F((float)((double)this.l + (double)(this.N - this.l) * 0.5));
            this.c = false;
        }
        if (this.t) {
            this.R();
            this.t = false;
        }
        if (this.j) {
            this.J(entityPlayerSP);
        }
    }

    public void k() {
        this.v(this.E);
    }

    public static float s(EntityPlayerSP entityPlayerSP) {
        return FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : entityPlayerSP.J();
    }

    private void B(EntityPlayerSP entityPlayerSP) {
        if (this.j) {
            this.N = entityPlayerSP.J();
            this.u = entityPlayerSP.j();
            this.f = entityPlayerSP.V();
            this.W = entityPlayerSP.D();
            entityPlayerSP.z(this.r);
            entityPlayerSP.o(this.P);
            entityPlayerSP.X(this.p);
            entityPlayerSP.Y(this.g);
            entityPlayerSP.C(this.A);
            entityPlayerSP.l(this.e);
        }
    }

    private void a() {
        this.C = 0.0;
        this.S = 0;
        this.d.reset();
    }

    public MouseRotationController w() {
        return this.E;
    }

    private void Q(boolean bl) {
        EntityLivingBase entityLivingBase = Minecraft.F();
        if (bl) {
            this.G = entityLivingBase.J();
            this.w = entityLivingBase.V();
            entityLivingBase.H(this.v);
            entityLivingBase.z(this.v);
            entityLivingBase.C(this.J);
        } else {
            entityLivingBase.H(this.G);
            entityLivingBase.z(this.G);
            entityLivingBase.C(this.w);
        }
    }

    public void v(MouseRotationController mouseRotationController) {
        if (this.E != null && this.E.equals(mouseRotationController)) {
            if (this.E instanceof AdaptiveRotationController) {
                AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.E;
                adaptiveRotationController.b(true);
            } else {
                this.E.w(false);
                this.E.u(true);
            }
        }
    }

    public float V() {
        return this.u() ? this.v : Minecraft.F().J();
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (ForgeVersion.MC_1_21_4.d() && this.u() && eventPacketSend.getPacket().isInstance(MappedClasses.Dg)) {
            S08PacketPlayerPosLook s08PacketPlayerPosLook = new S08PacketPlayerPosLook(eventPacketSend.getPacket());
            s08PacketPlayerPosLook.setPitch(this.J);
            s08PacketPlayerPosLook.setYaw(this.v);
        }
    }

    public boolean u() {
        return this.E != null && this.E instanceof AdaptiveRotationController;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void f(EventMouseOverUpdate eventMouseOverUpdate) {
        if (this.u()) {
            this.b(false);
        }
    }

    private void p(double d, EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        this.C += d;
        int n = (int)Math.round(this.C);
        for (int i = 0; i < n; ++i) {
            try {
                this.E.J(entityPlayerSP, guiScreen);
                this.E.o(guiScreen);
            }
            catch (Exception exception) {
                // empty catch block
            }
            ++this.S;
        }
        this.C -= (double)n;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void H(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (this.Q && this.E != null) {
            this.E.B(eventPreEntityRendererMouseUpdate);
        }
    }

    public void A() {
        this.U = MouseButtonActionState.RELEASE;
    }

    private void N(EntityPlayerSP entityPlayerSP) {
        if (this.j) {
            entityPlayerSP.H(this.N);
            entityPlayerSP.D(this.u);
            entityPlayerSP.C(this.f);
            entityPlayerSP.l(this.W);
        }
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        boolean bl;
        EntityPlayerSP entityPlayerSP = eventRender2D.getThePlayer();
        if (!Vape.INSTANCE.getClientSettings().e.L().booleanValue() || entityPlayerSP.isNull() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        boolean bl2 = bl = Minecraft.gameSettings().x() > 0;
        if (this.u() && !bl) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.E;
            double d = 0.0;
            float f = 15.0f;
            float f2 = 2.0f;
            OpenGlBackendHolder.d.m();
            RenderUtils.g();
            ScaledResolution scaledResolution = new ScaledResolution();
            float f3 = 2.0f / (float)Minecraft.G().e();
            OpenGlBackendHolder.d.I(scaledResolution.U() / 2.0 / (double)f3, scaledResolution.X() / 2.0 / (double)f3, d);
            EntityLivingBase entityLivingBase = Minecraft.F();
            float f4 = Minecraft.getTimer().renderPartialTicks();
            float f5 = entityLivingBase.J() + (entityLivingBase.J() - entityLivingBase.j()) * f4;
            float f6 = entityLivingBase.V() + (entityLivingBase.V() - entityLivingBase.D()) * f4;
            float f7 = this.P + (this.r - this.P) * f4 + 90.0f;
            OpenGlBackendHolder.d.X(f6, -1.0f, 0.0f, 0.0f);
            OpenGlBackendHolder.d.X(90.0f, 0.0f, -1.0f, 0.0f);
            OpenGlBackendHolder.d.H(-1.0f, -1.0f, -1.0f);
            OpenGlBackendHolder.d.I(0.0, 5.0, 0.5);
            OpenGlBackendHolder.d.X(20.0f, 0.0f, 0.0f, 1.0f);
            this.m(new Color(-1442840576, true), 3.0f, 0.0, -5.0, 0.0, 0.0, 5.0, 0.0);
            float f8 = f7 - f5 - 90.0f;
            OpenGlBackendHolder.d.X(f8, 0.0f, -1.0f, 0.0f);
            this.m(new Color(-1441787888), f2 + 1.0f, 0.0, 0.0, 0.0, f, 0.0, 0.0);
            this.m(new MutableColor(adaptiveRotationController.M() ? adaptiveRotationController.C() : adaptiveRotationController.y()).withAlpha(255), f2, 0.0, 0.0, 0.0, f, 0.0, 0.0);
            OpenGlBackendHolder.d.X(f8, 0.0f, 1.0f, 0.0f);
            OpenGlBackendHolder.d.X(f5, 0.0f, 1.0f, 0.0f);
            boolean bl3 = OpenGlBackendHolder.d.L(3042);
            boolean bl4 = OpenGlBackendHolder.d.L(2896);
            if (!bl3) {
                OpenGlBackendHolder.d.l(3042);
            }
            if (bl4) {
                OpenGlBackendHolder.d.u$src$V$hntn98(2896);
            }
            GL11.glBlendFunc((int)770, (int)771);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            double d2 = 5.0;
            Color color = new Color(-1728053248, true);
            Color color2 = new Color(-1719631744, true);
            if (GuiRenderPrimitives.d()) {
                BufferedGuiRenderPrimitives.Z(-d2, -5.0, -d2, d2, -5.0, -d2, f2, color);
                BufferedGuiRenderPrimitives.Z(d2, -5.0, -d2, d2, -5.0, d2, f2, color);
                BufferedGuiRenderPrimitives.Z(d2, -5.0, d2, -d2, -5.0, d2, f2, color);
                BufferedGuiRenderPrimitives.Z(-d2, -5.0, d2, -d2, -5.0, -d2, f2, color);
                BufferedGuiRenderPrimitives.H(-d2, -5.0, -d2, d2, -5.0, -d2, d2, -5.0, d2, -d2, -5.0, d2, color2);
            } else {
                GL11.glLineWidth((float)1.0f);
                GL11.glBegin((int)2);
                RenderUtils.w(color);
                GL11.glVertex3d((double)(-d2), (double)-5.0, (double)(-d2));
                GL11.glVertex3d((double)d2, (double)-5.0, (double)(-d2));
                GL11.glVertex3d((double)d2, (double)-5.0, (double)d2);
                GL11.glVertex3d((double)(-d2), (double)-5.0, (double)d2);
                GL11.glEnd();
                GlStateManager.Y();
                GL11.glBegin((int)7);
                RenderUtils.w(color2);
                GL11.glVertex3d((double)(-d2), (double)-5.0, (double)(-d2));
                GL11.glVertex3d((double)d2, (double)-5.0, (double)(-d2));
                GL11.glVertex3d((double)d2, (double)-5.0, (double)d2);
                GL11.glVertex3d((double)(-d2), (double)-5.0, (double)d2);
                GL11.glEnd();
                GlStateManager.L();
            }
            if (bl4) {
                OpenGlBackendHolder.d.l(2896);
            }
            if (!bl3) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            }
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            RenderUtils.f();
            OpenGlBackendHolder.d.F();
        }
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        boolean bl;
        if (this.u()) {
            EventMotion.setRotationYaw(this.v);
            EventMotion.setRotationPitch(this.J);
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        this.P = entityPlayerSP.g();
        this.e = entityPlayerSP.a$src$F$1txy325();
        this.r = EventMotion.getRotationYaw();
        this.A = EventMotion.getRotationPitch();
        boolean bl2 = bl = this.r != entityPlayerSP.J() || this.A != entityPlayerSP.V();
        if (!this.j && bl) {
            this.p = entityPlayerSP.W$src$F$153nzpr();
            this.g = entityPlayerSP.S$src$F$151gtcb();
        }
        this.j = bl;
    }

    public float H() {
        return this.u() ? Minecraft.thePlayer().a$src$F$1txy325() : Minecraft.F().D();
    }

    public boolean A$src$Z$yoncmr() {
        return this.E != null && !this.E.V$src$Z$lb4tvc();
    }

    private void D$src$V$yqaqbe() {
        boolean bl;
        double d = Vape.INSTANCE.getModManager().getMod(Reach.class).e();
        double d2 = Vape.INSTANCE.getModManager().getMod(HitBoxes.class).z();
        boolean bl2 = bl = d > 3.0;
        if (!this.u() && FreeLookHudModule.z()) {
            this.v = FreeLookHudModule.L$src$F$1jnmc2m();
            this.J = FreeLookHudModule.U();
        }
        this.z = this.F(d, (float)d2, bl);
        this.o = this.F(3.0, 0.0f, false);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void m(EventRightClickMouse eventRightClickMouse) {
        if (this.u()) {
            this.b(true);
        }
    }

    public RotationManager() {
        this.U = MouseButtonActionState.NONE;
        this.z = T;
        this.o = T;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void m(EventThreadBoundPreTick eventThreadBoundPreTick) {
        if (this.u()) {
            this.Q(true);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void g(EventPreRenderWorldPass eventPreRenderWorldPass) {
        if (!Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.B(eventPreRenderWorldPass.getPlayer());
    }

    private void J(EntityPlayerSP entityPlayerSP) {
        this.g = this.p;
        double d = entityPlayerSP.z() - entityPlayerSP.f();
        double d2 = entityPlayerSP.h() - entityPlayerSP.R();
        float f = (float)(d * d + d2 * d2);
        float f2 = this.p;
        float f3 = 0.0f;
        if (f > 0.0025000002f) {
            f3 = (float)Math.sqrt(f) * 3.0f;
            f2 = (float)MathUtil.V(d2, d) * 180.0f / (float)Math.PI - 90.0f;
        }
        if (entityPlayerSP.i() > 0) {
            f2 = this.r;
        }
        this.m(entityPlayerSP, f2, f3);
        while (this.p - this.g < -180.0f) {
            this.g -= 360.0f;
        }
        while (this.p - this.g >= 180.0f) {
            this.g += 360.0f;
        }
    }

    @EventHandler
    public void l(EventPostRenderTick eventPostRenderTick) {
        if (this.Q) {
            if (this.E != null && eventPostRenderTick.getThePlayer().isNotNull()) {
                try {
                    this.E.R(eventPostRenderTick);
                }
                catch (NullPointerException nullPointerException) {
                    Vape.logThrowable(nullPointerException);
                }
            }
            this.Q = false;
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void t(EventPostRenderWorldPass eventPostRenderWorldPass) {
        if (!Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.N(eventPostRenderWorldPass.getPlayer());
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void E(EventSendClickBlockToController eventSendClickBlockToController) {
        if (this.u()) {
            this.b(true);
        }
    }

    public float E() {
        return this.L;
    }

    private void m(Color color, float f, double d, double d2, double d3, double d4, double d5, double d6) {
        boolean bl = OpenGlBackendHolder.d.L(3042);
        boolean bl2 = OpenGlBackendHolder.d.L(2896);
        GL11.glBlendFunc((int)770, (int)771);
        if (!bl) {
            OpenGlBackendHolder.d.l(3042);
        }
        if (bl2) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2896);
        }
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        if (GuiRenderPrimitives.d()) {
            BufferedGuiRenderPrimitives.Z(d, d2, d3, d4, d5, d6, f, color);
        } else {
            GL11.glLineWidth((float)f);
            GL11.glBegin((int)1);
            RenderUtils.w(color);
            GL11.glVertex3d((double)d, (double)d2, (double)d3);
            GL11.glVertex3d((double)d4, (double)d5, (double)d6);
            GL11.glEnd();
        }
        if (bl2) {
            OpenGlBackendHolder.d.l(2896);
        }
        if (!bl) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
    }

    public float X() {
        float f = b.E();
        float f2 = f * 0.6f + 0.2f;
        f2 = f2 * f2 * f2 * 8.0f;
        float f3 = 0.5f;
        f3 = f3 * f3 * f3 * 8.0f;
        return f3 / f2;
    }

    public float G(float f, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        float f2 = f;
        if (bl && bl2) {
            f2 += 45.0f;
        } else if (bl4 && bl2) {
            f2 += 135.0f;
        } else if (bl2) {
            f2 += 90.0f;
        } else if (bl && bl3) {
            f2 -= 45.0f;
        } else if (bl4 && bl3) {
            f2 -= 135.0f;
        } else if (bl3) {
            f2 -= 90.0f;
        } else if (bl4) {
            f2 += 180.0f;
        }
        return f2;
    }

    static {
        T = new RayTraceResult(null);
        b = new RotationManager();
    }

    private void b(boolean bl) {
        if (this.V == null || this.V.isNull()) {
            EntityLivingBase entityLivingBase = Minecraft.F();
            float f = entityLivingBase.J();
            float f2 = entityLivingBase.s();
            float f3 = entityLivingBase.V();
            entityLivingBase.H(this.v);
            entityLivingBase.z(this.v);
            entityLivingBase.C(this.J);
            double d = 3.0;
            if (Vape.INSTANCE.getClientSettings().C.L().booleanValue()) {
                d = Vape.INSTANCE.getModManager().getMod(Reach.class).e();
            }
            double d2 = 0.0;
            if (Vape.INSTANCE.getClientSettings().A.L().booleanValue()) {
                d2 = Vape.INSTANCE.getModManager().getMod(HitBoxes.class).z();
            }
            MouseOverRayTraceUpdater.s((float)d, (float)d2);
            this.V = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0();
            entityLivingBase.H(f);
            entityLivingBase.z(f2);
            entityLivingBase.C(f3);
        }
        Minecraft.O(this.V);
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (!this.u() || eventPacketReceive.isCanceled()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.E;
        Packet packet = eventPacketReceive.getPacket();
        if (packet.isInstance(MappedClasses.zw)) {
            if (adaptiveRotationController.O$src$Z$1lvi05g()) {
                adaptiveRotationController.u(true);
            } else {
                PlayerPositionLookPacketModern playerPositionLookPacketModern = new PlayerPositionLookPacketModern(packet);
                float f = playerPositionLookPacketModern.f();
                float f2 = playerPositionLookPacketModern.M();
                if (ForgeVersion.MC_1_7_10.Y()) {
                    Set set = playerPositionLookPacketModern.W();
                    for (Object e : set) {
                        PlayerInteractEventAction playerInteractEventAction = new PlayerInteractEventAction(e);
                        if (playerInteractEventAction.T() == PlayerInteractEventAction.e()) {
                            entityPlayerSP.C(this.J);
                            this.J += f2;
                        }
                        if (playerInteractEventAction.T() != PlayerInteractEventAction.t()) continue;
                        entityPlayerSP.H(this.v);
                        this.v += f;
                    }
                }
            }
        }
    }

    public RayTraceResult D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic() {
        return this.o;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void F(EventThreadBoundPostTick eventThreadBoundPostTick) {
        if (this.u()) {
            this.Q(false);
        }
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void d(EventPreLocalPlayerTick eventPreLocalPlayerTick) {
        boolean bl;
        if (Minecraft.thePlayer().isNull()) {
            this.t = false;
            return;
        }
        boolean bl2 = this.u();
        if (bl2) {
            boolean bl3;
            ModeSelection modeSelection = (ModeSelection)Vape.INSTANCE.getClientSettings().o.K();
            if (modeSelection.equals(ClientSettings.O)) {
                return;
            }
            EntityPlayerSP entityPlayerSP = eventPreLocalPlayerTick.getPlayer();
            boolean bl4 = bl3 = modeSelection.equals(ClientSettings.Y) || modeSelection.equals(ClientSettings.u);
            if (bl3) {
                GameSettings gameSettings = Minecraft.gameSettings();
                KeyBinding[] keyBindingArray = new KeyBinding[]{gameSettings.Y(), gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), gameSettings.s()};
                boolean bl5 = false;
                for (KeyBinding keyBinding : keyBindingArray) {
                    if (ClientSettings.B(keyBinding) == keyBinding.isKeyDown()) continue;
                    bl5 = true;
                    break;
                }
                boolean bl6 = !this.i() && (bl5 ? RotationUtil.x() : MovementInputHelper.k());
                AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.E;
                this.N = FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : entityPlayerSP.J();
                this.l = entityPlayerSP.q$src$F$1u6qsjx();
                float f = adaptiveRotationController.v$src$F$1mgxytb();
                float f2 = this.Y(f, bl5);
                float f3 = modeSelection.equals(ClientSettings.u) ? f2 + 180.0f : this.v;
                entityPlayerSP.H(f3);
                entityPlayerSP.z(f3);
                this.c = true;
                if (bl6) {
                    boolean bl7;
                    float f4 = MathUtil.wrapAngleTo180(MathUtil.wrapAngleTo180(f3) - f2);
                    float f5 = f4 * ((float)Math.PI / 180);
                    float f6 = (float)Math.cos(f5);
                    float f7 = (float)(-Math.sin(f5));
                    double d = PlayerMovementTaskManager.G.e() != null ? 0.075 : (double)0.4f;
                    boolean bl8 = (double)f6 >= d;
                    boolean bl9 = (double)f7 >= d;
                    boolean bl10 = (double)f7 <= -d;
                    boolean bl11 = bl7 = (double)f6 <= -d;
                    if (this.U == MouseButtonActionState.PRESS) {
                        bl8 = true;
                        bl7 = false;
                    } else if (this.U == MouseButtonActionState.RELEASE) {
                        bl8 = false;
                        bl7 = false;
                    }
                    this.a = gameSettings.Y().isKeyDown();
                    this.M = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().isKeyDown();
                    this.K = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().isKeyDown();
                    this.X = gameSettings.s().isKeyDown();
                    gameSettings.Y().setPressed(bl8);
                    gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().setPressed(bl9);
                    gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().setPressed(bl10);
                    gameSettings.s().setPressed(bl7);
                    this.t = true;
                }
                if (!bl6 && this.t) {
                    if (!bl5) {
                        this.R();
                    }
                    this.t = false;
                }
            }
            return;
        }
        ModeSelection modeSelection = (ModeSelection)Vape.INSTANCE.getClientSettings().o.K();
        if (modeSelection.equals(ClientSettings.O)) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPreLocalPlayerTick.getPlayer();
        boolean bl12 = bl = modeSelection.equals(ClientSettings.Y) || modeSelection.equals(ClientSettings.u);
        if (bl) {
            boolean bl13;
            GameSettings gameSettings = Minecraft.gameSettings();
            KeyBinding[] keyBindingArray = new KeyBinding[]{gameSettings.Y(), gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), gameSettings.s()};
            boolean bl14 = false;
            for (KeyBinding keyBinding : keyBindingArray) {
                if (ClientSettings.B(keyBinding) == keyBinding.isKeyDown()) continue;
                bl14 = true;
                break;
            }
            boolean bl15 = !this.i() && (bl14 ? RotationUtil.x() : MovementInputHelper.k()) ? true : (bl13 = false);
            if (this.t) {
                if (!bl14) {
                    this.R();
                }
                this.t = false;
            }
        }
    }

    public RayTraceResult F(double d, float f, boolean bl) {
        return this.f(d, f, bl, null);
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void t(EventRenderPlayerPre eventRenderPlayerPre) {
        if (!Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        EntityPlayer entityPlayer = eventRenderPlayerPre.getEntityPlayer();
        if (!entityPlayer.isInstance(MappedClasses.z5)) {
            return;
        }
        this.B(new EntityPlayerSP(entityPlayer));
    }

    public RayTraceResult F(boolean bl) {
        RayTraceResult rayTraceResult;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        World world = entityPlayerSP.getWorld();
        if (this.u() || FreeLookHudModule.z()) {
            EntityLivingBase entityLivingBase = Minecraft.F();
            float f = entityLivingBase.J();
            float f2 = entityLivingBase.s();
            float f3 = entityLivingBase.V();
            entityLivingBase.H(this.v);
            entityLivingBase.z(this.v);
            entityLivingBase.C(this.J);
            rayTraceResult = RayTraceUtil.p(entityPlayerSP.getWorld(), entityPlayerSP, bl);
            entityLivingBase.H(f);
            entityLivingBase.z(f2);
            entityLivingBase.C(f3);
        } else {
            rayTraceResult = RayTraceUtil.p(entityPlayerSP.getWorld(), entityPlayerSP, bl);
        }
        return rayTraceResult;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        GuiScreen guiScreen = eventPreTick.getCurrentScreen();
        this.L = Minecraft.gameSettings().y();
        this.s = true;
        this.V = T;
        if (entityPlayerSP.isNotNull()) {
            this.D$src$V$yqaqbe();
        }
        if (this.E == null || entityPlayerSP.isNull()) {
            this.E = null;
            this.a();
            return;
        }
        if (this.E.V$src$Z$lb4tvc() && !this.E.v()) {
            this.E = null;
        }
        if (this.E == null) {
            this.a();
            return;
        }
        this.d.reset();
        double d = Math.round(50.0f * this.X());
        double d2 = Math.max(d - (double)this.S, 0.0);
        this.p(d2, entityPlayerSP, guiScreen);
        this.S = 0;
        if (this.u()) {
            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)this.E;
            this.v = adaptiveRotationController.J();
            this.J = MathUtil.clamp(adaptiveRotationController.X(), -90.0f, 90.0f);
            this.r = this.v;
            this.A = this.J;
            this.b(false);
        }
        this.D$src$V$yqaqbe();
    }

    public static float g(EntityPlayerSP entityPlayerSP) {
        return FreeLookHudModule.z() ? FreeLookHudModule.U() : entityPlayerSP.V();
    }

    private boolean i() {
        if (Minecraft.currentScreen().isNull()) {
            return false;
        }
        InvWalk invWalk = Vape.INSTANCE.getModManager().getMod(InvWalk.class);
        return invWalk == null || !invWalk.r$src$Z$14eylz9() || !invWalk.g$src$Z$tdg77x();
    }

    public float x() {
        return this.u() ? this.J : Minecraft.F().V();
    }

    @EventHandler
    public void p(EventPreRenderTick eventPreRenderTick) {
        EntityPlayerSP entityPlayerSP = eventPreRenderTick.getThePlayer();
        if (this.E == null || entityPlayerSP.isNull()) {
            this.E = null;
            this.a();
            return;
        }
        if (this.E.V$src$Z$lb4tvc() && !this.E.v()) {
            this.E = null;
        }
        if (this.E == null) {
            this.a();
            return;
        }
        double d = Minecraft.getTimer().getTimerSpeed();
        double d2 = this.d.getElapsedMilliseconds() * d;
        this.d.reset();
        double d3 = d2 * (double)this.X();
        this.p(d3, entityPlayerSP, eventPreRenderTick.getCurrentScreen());
        this.Q = true;
        this.E.Q(eventPreRenderTick);
    }

    public RayTraceResult n() {
        return this.z;
    }

    public void S(@NotNull MouseRotationController mouseRotationController) {
        AdaptiveRotationController adaptiveRotationController;
        if (this.E == mouseRotationController) {
            return;
        }
        if (this.E instanceof AdaptiveRotationController && mouseRotationController instanceof AdaptiveRotationController) {
            adaptiveRotationController = (AdaptiveRotationController)this.E;
            adaptiveRotationController.b(true);
            adaptiveRotationController.u(true);
            AdaptiveRotationController adaptiveRotationController2 = (AdaptiveRotationController)mouseRotationController;
            adaptiveRotationController2.b(false);
            adaptiveRotationController2.T(adaptiveRotationController.J());
            adaptiveRotationController2.a(adaptiveRotationController.X());
        }
        if (this.E == null && mouseRotationController instanceof AdaptiveRotationController) {
            adaptiveRotationController = (AdaptiveRotationController)mouseRotationController;
            this.v = adaptiveRotationController.J();
            this.J = adaptiveRotationController.X();
        }
        this.E = mouseRotationController;
    }

    @EventHandler(A=EventPriority.LOW)
    public void g(EventMouseOverUpdate eventMouseOverUpdate) {
        if (!this.s) {
            return;
        }
        if (Minecraft.thePlayer().isNotNull()) {
            this.D$src$V$yqaqbe();
            this.s = false;
        }
    }

    public double D() {
        return this.C;
    }

    private void R() {
        GameSettings gameSettings = Minecraft.gameSettings();
        MovementInputHelper.P(gameSettings.Y(), this.a);
        MovementInputHelper.P(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), this.M);
        MovementInputHelper.P(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), this.K);
        MovementInputHelper.P(gameSettings.s(), this.X);
    }

    public RayTraceResult f(double d, float f, boolean bl, @Nullable Predicate<Entity> predicate) {
        RayTraceResult rayTraceResult;
        if (this.u() || FreeLookHudModule.z()) {
            EntityLivingBase entityLivingBase = Minecraft.F();
            float f2 = entityLivingBase.J();
            float f3 = entityLivingBase.s();
            float f4 = entityLivingBase.V();
            entityLivingBase.H(this.v);
            entityLivingBase.z(this.v);
            entityLivingBase.C(this.J);
            rayTraceResult = RayTraceUtil.U(entityLivingBase, d, f, bl, predicate);
            entityLivingBase.H(f2);
            entityLivingBase.z(f3);
            entityLivingBase.C(f4);
        } else {
            rayTraceResult = RayTraceUtil.q(d, f, bl, predicate);
        }
        return rayTraceResult;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void a(EventRenderPlayerPost eventRenderPlayerPost) {
        if (!Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        EntityPlayer entityPlayer = eventRenderPlayerPost.getEntityPlayer();
        if (!entityPlayer.isInstance(MappedClasses.z5)) {
            return;
        }
        this.N(new EntityPlayerSP(entityPlayer));
    }

    public void B(boolean bl) {
        this.U = bl ? MouseButtonActionState.PRESS : MouseButtonActionState.NONE;
    }

    public float s() {
        return this.u() ? Minecraft.thePlayer().g() : Minecraft.F().j();
    }
}
