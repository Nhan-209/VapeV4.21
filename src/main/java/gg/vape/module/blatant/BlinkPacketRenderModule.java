package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.PacketSendDispatchGuardCallback;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventTickBase;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ConditionalValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.opengl.GL11;

public class BlinkPacketRenderModule
extends Mod {
    private boolean b;
    private EntityOtherPlayerMP V;
    private boolean J;
    private boolean C = false;
    public RenderManager L;
    private final Queue<PacketSendDispatchGuardCallback> s;
    private static final long O = -5919086810783803073L;
    private int o;
    private final NumberValue j;
    private final BooleanValue v;
    private final BooleanValue p;
    private final List<Vec3d> P;
    private final TimerUtil I;
    private final TimerUtil Y;
    private final PacketDispatchGuard A;
    private final ModeOption K;
    private final ModeValue U;
    private final Runnable c;
    private final BooleanValue t;
    private final ModeOption a = new ModeOption("Outgoing only");
    int H = 0;

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl) {
            this.J = true;
            this.b = true;
        } else {
            super.s(true, bl2);
        }
    }

    private int b$src$I$1rexx7p() {
        return this.H;
    }

    @Override
    public void onDisable() {
        this.P.clear();
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.V = null;
        }
        if (this.V != null && Minecraft.theWorld().V(this.V.S()).isNotNull()) {
            Minecraft.theWorld().M(this.V);
        }
        this.V = null;
    }

    private void lambda$new$0() {
        while (this.C) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
    }

    private void m() {
        this.C = false;
        this.Y.reset();
        this.J = false;
        this.H = 0;
        for (PacketSendDispatchGuardCallback packetSendDispatchGuardCallback : this.s) {
            try {
                packetSendDispatchGuardCallback.O(this.A);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        this.s.clear();
        if (this.b) {
            super.s(false, true);
            this.b = false;
        }
    }

    private boolean R() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.t() != 0.0 || entityPlayerSP.T() != 0.0;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.v.L().booleanValue() || this.P.isEmpty()) {
            return;
        }
        eventRender3D.getEntityRenderer().B(1.0);
        RenderUtil.d();
        if (!GuiRenderPrimitives.d()) {
            OpenGlBackendHolder.d.l(3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.5f);
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.l(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            GL11.glDepthMask((boolean)false);
        }
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        boolean bl = true;
        for (Vec3d vec3d : this.P) {
            Color color = Color.RED;
            if (bl) {
                color = Color.YELLOW;
                bl = false;
            }
            RenderUtil.u(vec3d.H - 0.1, vec3d.B, vec3d.i - 0.1, 0.2, 0.0, 0.2, 1.0, Color.BLACK, color, d, d2, d3);
        }
        if (!GuiRenderPrimitives.d()) {
            GL11.glDepthMask((boolean)true);
            OpenGlBackendHolder.d.l(2929);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        RenderUtil.Y();
        eventRender3D.getEntityRenderer().O(1.0);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        GuiScreen guiScreen;
        int n;
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        String[] stringArray = ConditionalValue.o$src$ALjava_lang_String_$17s942p();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            this.C = false;
            this.s.clear();
            this.P.clear();
            this.H = 0;
            this.J = false;
            this.b = false;
            super.s(false, true);
            return;
        }
        if (((ModeSelection)this.U.K()).equals(this.K) && this.Y.hasTimeElapsed(50L) && !this.C && entityPlayerSP.isNotNull()) {
            this.Y.reset();
            this.C = true;
            entityPlayerSP.sendQueue().a().B().D().F(this.c);
        }
        if (!this.J && this.p.L().booleanValue() && (Double)this.j.K() > 0.0 && (double)(n = this.b$src$I$1rexx7p()) >= (Double)this.j.K()) {
            if (this.V != null && this.V.isNotNull()) {
                this.V.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
                this.V.z(entityPlayerSP.s());
            }
            this.P.clear();
            this.J = true;
            this.b = false;
        }
        if ((guiScreen = Minecraft.currentScreen()).isInstance(MappedClasses.u5) || guiScreen.isInstance(MappedClasses.D6) || guiScreen.isInstance(MappedClasses.F_) || Minecraft.thePlayer().isNull()) {
            this.J = true;
        }
        if (this.J) {
            this.m();
        }
    }

    public BlinkPacketRenderModule() {
        super("Blink", (int)O, Category.Y, "Chokes packets until disabled.");
        this.K = new ModeOption("Bi-directional");
        this.U = ModeValue.create((Object)this, "Direction", "Outgoing only - only chokes packets that you're sending\nBi-directional - additionally chokes incoming packets from the server", (ModeSelection)this.a, this.a, this.K);
        this.v = BooleanValue.create(this, "Breadcrumbs", false, "Drops breadcrumbs in case you forgot where you came from.");
        this.t = BooleanValue.create(this, "Spawn fake", false, "Spawns a fake player where you started/are on the servers side.");
        this.p = BooleanValue.create(this, "Auto send", false, "Automatically sends packets after a limit is reached");
        this.j = NumberValue.create(this, "Send threshold", "#", "", 0.0, 50.0, 100.0, 1.0, "Allows you to choke your packets for a custom set limit.\nAfter the limit is reached, all packets will be sent.\nNOTE: 0 = No Limit");
        this.A = PacketDispatchGuard.b;
        this.s = new ConcurrentLinkedQueue<PacketSendDispatchGuardCallback>();
        this.P = new CopyOnWriteArrayList<Vec3d>();
        this.I = new TimerUtil();
        this.Y = new TimerUtil();
        this.c = this::lambda$new$0;
        this.p.K(this.j);
        this.addValue(this.U, this.v, this.t, this.p, this.j);
    }

    @EventHandler(A=EventPriority.LOWEREST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        int n;
        this.I.reset();
        if (!Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
            return;
        }
        if (eventPacketSend.isCanceled()) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (this.A.R(packet)) {
            return;
        }
        ++this.H;
        if (packet.isInstance(MappedClasses.qD) && this.R() && (n = this.b$src$I$1rexx7p()) % 5 == 0) {
            int n2;
            double d;
            int n3;
            double d2;
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            boolean bl = n % 2 == 0;
            double d3 = 0.2;
            if (bl) {
                d2 = d3;
                n3 = -1;
            } else {
                d2 = d3;
                n3 = 1;
            }
            double d4 = d2 * (double)n3 * Math.cos(Math.toRadians(entityPlayerSP.J()));
            double d5 = 0.2;
            if (bl) {
                d = d5;
                n2 = -1;
            } else {
                d = d5;
                n2 = 1;
            }
            double d6 = d * (double)n2 * Math.sin(Math.toRadians(entityPlayerSP.J()));
            this.P.add(new Vec3d(entityPlayerSP.z() + d4, entityPlayerSP.N(), entityPlayerSP.h() + d6));
        }
        this.s.add(new PacketSendDispatchGuardCallback(eventPacketSend));
        eventPacketSend.setCancelled(true);
    }

    @Override
    public void onEnable() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            super.s(false, true);
            return;
        }
        if (this.t.L().booleanValue()) {
            this.V = EntityOtherPlayerMP.create(Minecraft.theWorld(), Minecraft.thePlayer().c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937());
            this.o = ClientSettings.f();
            this.V.M(entityPlayerSP, true);
            if (ForgeVersion.MC_1_17.d()) {
                this.V.Q(this.o);
                UUID uUID = UUID.nameUUIDFromBytes(("OfflinePlayer:" + entityPlayerSP.getName()).getBytes(StandardCharsets.UTF_8));
                this.V.y(uUID);
            }
            this.C$src$V$1qxwb4p();
            this.V.z(entityPlayerSP.s());
            Minecraft.theWorld().D(this.o, this.V);
        }
    }

    private void C$src$V$1qxwb4p() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || this.V.isNull() || !this.t.L().booleanValue()) {
            return;
        }
        this.V.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        this.V.z(entityPlayerSP.s());
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public String E() {
        int n = this.b$src$I$1rexx7p();
        if (n == 0) {
            return "";
        }
        return String.valueOf(n);
    }
}

