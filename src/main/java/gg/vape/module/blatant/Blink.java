package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventGuiOpen;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.Vec3d;
import gg.vape.utils.network.PacketDispatchTask;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
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
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lwjgl.opengl.GL11;

public class Blink
extends Mod {
    private final ModeOption P;
    private boolean D = false;
    private int Z;
    private int a;
    private final ModeValue J;
    private final Queue<PacketDispatchTask> H;
    private boolean o = false;
    private final BooleanValue p;
    private final List<Vec3d> F = new CopyOnWriteArrayList<Vec3d>();
    private final BooleanValue V;
    private final ModeValue c;
    private final BooleanValue C;
    private final NumberValue S;
    private final ModeOption r;
    private boolean v = false;
    private final ModeOption O = new ModeOption("Outgoing only");
    private static final long t = -1017515784348886721L;
    private EntityOtherPlayerMP L;
    public RenderManager I;
    private final ModeOption K;

    @EventHandler(A=EventPriority.LOW)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        try {
            EntityPlayerSP entityPlayerSP;
            if (eventPacketSend.isCanceled()) {
                return;
            }
            GuiScreen guiScreen = Minecraft.currentScreen();
            if (guiScreen.isInstance(MappedClasses.u5) || guiScreen.isInstance(MappedClasses.D6) || guiScreen.isInstance(MappedClasses.F_) || Minecraft.thePlayer().isNull()) {
                this.o = true;
                this.C$src$V$1j3s29b();
                if (!this.V.L().booleanValue()) {
                    super.Y(false);
                }
                return;
            }
            if (this.v) {
                return;
            }
            Packet packet = eventPacketSend.getPacket();
            if (this.Y(packet)) {
                return;
            }
            if (packet.isInstance(MappedClasses.qD)) {
                if (this.G() && this.a % 5 == 0) {
                    int n;
                    double d;
                    int n2;
                    double d2;
                    entityPlayerSP = Minecraft.thePlayer();
                    boolean bl = this.a % 2 == 0;
                    double d3 = 0.2;
                    if (bl) {
                        d2 = d3;
                        n2 = -1;
                    } else {
                        d2 = d3;
                        n2 = 1;
                    }
                    double d4 = d2 * (double)n2 * Math.cos(Math.toRadians(entityPlayerSP.J()));
                    double d5 = 0.2;
                    if (bl) {
                        d = d5;
                        n = -1;
                    } else {
                        d = d5;
                        n = 1;
                    }
                    double d6 = d * (double)n * Math.sin(Math.toRadians(entityPlayerSP.J()));
                    this.F.add(new Vec3d(entityPlayerSP.z() + d4, entityPlayerSP.N(), entityPlayerSP.h() + d6));
                }
            } else if (((ModeSelection)this.J.K()).equals(this.K)) {
                return;
            }
            ++this.a;
            if (this.V.L().booleanValue() && (Double)this.S.K() > 0.0 && (double)this.a >= (Double)this.S.K()) {
                this.o = true;
                if (this.L != null && this.L.isNotNull()) {
                    entityPlayerSP = Minecraft.thePlayer();
                    this.L.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
                    this.L.z(entityPlayerSP.s());
                }
                this.a = 0;
                this.F.clear();
            }
            this.H.add(new PacketDispatchTask(packet, true, eventPacketSend.getNetworkManager()));
            eventPacketSend.setCancelled(true);
            this.C$src$V$1j3s29b();
        }
        catch (Exception exception) {
            Vape.debugLog("ex1");
            Vape.logThrowable(exception);
        }
    }

    public Blink() {
        super("Blink", (int)t, Category.Y, "Chokes packets until disabled.");
        this.P = new ModeOption("Bi-directional");
        this.c = ModeValue.create((Object)this, "Direction", "Outgoing only - only chokes packets that you're sending\nBi-directional - additionally chokes incoming packets from the server", (ModeSelection)this.O, this.O, this.P);
        this.r = new ModeOption("All");
        this.K = new ModeOption("Movement only");
        this.J = ModeValue.create((Object)this, "Type", "All - chokes all packets of all types\nMovement only - Only chokes movement related packets", (ModeSelection)this.r, this.r, this.K);
        this.p = BooleanValue.create(this, "Breadcrumbs", false, "Drops breadcrumbs in case you forgot where you came from.");
        this.C = BooleanValue.create(this, "Spawn fake", false, "Spawns a fake player where you started/are on the servers side.");
        this.V = BooleanValue.create(this, "Auto send", false, "Automatically sends packets after a limit is reached");
        this.S = NumberValue.create(this, "Send threshold", "#", "", 0.0, 50.0, 100.0, 1.0, "Allows you to choke your packets for a custom set limit.\nAfter the limit is reached, all packets will be sent.\nNOTE: 0 = No Limit");
        this.H = new ConcurrentLinkedQueue<PacketDispatchTask>();
        this.I = Minecraft.D();
        this.V.K(this.S);
        this.addValue(this.c, this.J, this.p, this.C, this.V, this.S);
    }

    @Override
    public void onEnable() {
        this.U();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.C.L().booleanValue()) {
            this.L = EntityOtherPlayerMP.create(Minecraft.theWorld(), Minecraft.thePlayer().c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937());
            this.L.M(entityPlayerSP, true);
            if (ForgeVersion.MC_1_17.d()) {
                this.L.Q(-420);
                this.L.y(UUID.randomUUID());
            }
            this.H();
            this.L.z(entityPlayerSP.s());
            this.Z = ClientSettings.f();
            Minecraft.theWorld().D(this.Z, this.L);
        }
    }

    private boolean G() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.t() != 0.0 || entityPlayerSP.T() != 0.0;
    }

    @Override
    public String E() {
        if (this.a == 0) {
            return "";
        }
        return String.valueOf(this.a);
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        try {
            Packet packet = eventPacketReceive.getPacket();
            if (packet.isInstance(MappedClasses.zw)) {
                this.o = true;
                this.C$src$V$1j3s29b();
                if (!this.V.L().booleanValue()) {
                    super.Y(false);
                }
                return;
            }
            if (((ModeSelection)this.c.K()).equals(this.O)) {
                return;
            }
            if (this.v) {
                return;
            }
            ++this.a;
            if (this.V.L().booleanValue() && (Double)this.S.K() > 0.0 && (double)this.a >= (Double)this.S.K()) {
                this.o = true;
                this.H();
                this.a = 0;
                this.F.clear();
            }
            this.H.add(new PacketDispatchTask(packet, false, eventPacketReceive.getNetworkManager()));
            eventPacketReceive.setCancelled(true);
        }
        catch (Exception exception) {
            Vape.debugLog("ex2");
            Vape.logThrowable(exception);
        }
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.p.L().booleanValue() || this.F.isEmpty()) {
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
        for (Vec3d vec3d : this.F) {
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

    @Override
    public void Y(boolean bl) {
        super.Y(bl);
    }

    private void H() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() || this.L.isNotNull() || !this.C.L().booleanValue()) {
            return;
        }
        this.L.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        this.L.z(entityPlayerSP.s());
    }

    private void U() {
        this.a = 0;
        this.H.clear();
        this.F.clear();
    }

    private void C$src$V$1j3s29b() {
        if (this.o) {
            this.v = true;
            while (!this.H.isEmpty()) {
                PacketDispatchTask packetDispatchTask = this.H.poll();
                packetDispatchTask.t();
            }
            this.v = false;
            this.o = false;
            if (this.D) {
                this.D = false;
            }
        }
    }

    @Override
    public void onDisable() {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.F.clear();
            this.L = null;
            this.a = 0;
        }
        if (this.L != null && Minecraft.theWorld().V(this.Z).isNotNull()) {
            Minecraft.theWorld().M(this.L);
        }
        this.L = null;
    }

    @Override
    public void g() {
        this.o = true;
        this.D = true;
        this.C$src$V$1j3s29b();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        super.s(bl, bl2);
    }

    private boolean Y(Packet packet) {
        return packet.getObject().getClass().toString().contains("play.server") || packet.getObject().toString().contains("SPacket");
    }

    @EventHandler
    public void H(EventGuiOpen eventGuiOpen) {
    }
}

