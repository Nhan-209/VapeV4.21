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
    private boolean pendingReEnable;
    private EntityOtherPlayerMP fakePlayer;
    private boolean stopped;
    private boolean threadRunning = false;
    public RenderManager L;
    private final Queue<PacketSendDispatchGuardCallback> pendingCallbacks;
    private static final long MODULE_ID = -5919086810783803073L;
    private int fakePlayerEntityId;
    private final NumberValue sendThreshold;
    private final BooleanValue breadcrumbs;
    private final BooleanValue autoSend;
    private final List<Vec3d> breadcrumbTrail;
    private final TimerUtil packetTimer;
    private final TimerUtil fakePlayerTimer;
    private final PacketDispatchGuard dispatchGuard;
    private final ModeOption biDirectionalMode;
    private final ModeValue directionMode;
    private final Runnable fakePlayerTask;
    private final BooleanValue spawnFake;
    private final ModeOption outgoingOnlyMode = new ModeOption("Outgoing only");
    int H = 0;

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl) {
            this.stopped = true;
            this.pendingReEnable = true;
        } else {
            super.s(true, bl2);
        }
    }

    private int getChokeCount() {
        return this.H;
    }

    @Override
    public void onDisable() {
        this.breadcrumbTrail.clear();
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.fakePlayer = null;
        }
        if (this.fakePlayer != null && Minecraft.theWorld().V(this.fakePlayer.S()).isNotNull()) {
            Minecraft.theWorld().M(this.fakePlayer);
        }
        this.fakePlayer = null;
    }

    private void fakePlayerLoop() {
        while (this.threadRunning) {
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

    private void flushPackets() {
        this.threadRunning = false;
        this.fakePlayerTimer.reset();
        this.stopped = false;
        this.H = 0;
        for (PacketSendDispatchGuardCallback packetSendDispatchGuardCallback : this.pendingCallbacks) {
            try {
                packetSendDispatchGuardCallback.O(this.dispatchGuard);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        this.pendingCallbacks.clear();
        if (this.pendingReEnable) {
            super.s(false, true);
            this.pendingReEnable = false;
        }
    }

    private boolean isMoving() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.t() != 0.0 || entityPlayerSP.T() != 0.0;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.breadcrumbs.L().booleanValue() || this.breadcrumbTrail.isEmpty()) {
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
        for (Vec3d vec3d : this.breadcrumbTrail) {
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
            this.threadRunning = false;
            this.pendingCallbacks.clear();
            this.breadcrumbTrail.clear();
            this.H = 0;
            this.stopped = false;
            this.pendingReEnable = false;
            super.s(false, true);
            return;
        }
        if (((ModeSelection)this.directionMode.K()).equals(this.biDirectionalMode) && this.fakePlayerTimer.hasTimeElapsed(50L) && !this.threadRunning && entityPlayerSP.isNotNull()) {
            this.fakePlayerTimer.reset();
            this.threadRunning = true;
            entityPlayerSP.sendQueue().a().B().D().F(this.fakePlayerTask);
        }
        if (!this.stopped && this.autoSend.L().booleanValue() && (Double)this.sendThreshold.K() > 0.0 && (double)(n = this.getChokeCount()) >= (Double)this.sendThreshold.K()) {
            if (this.fakePlayer != null && this.fakePlayer.isNotNull()) {
                this.fakePlayer.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
                this.fakePlayer.z(entityPlayerSP.s());
            }
            this.breadcrumbTrail.clear();
            this.stopped = true;
            this.pendingReEnable = false;
        }
        if ((guiScreen = Minecraft.currentScreen()).isInstance(MappedClasses.u5) || guiScreen.isInstance(MappedClasses.D6) || guiScreen.isInstance(MappedClasses.F_) || Minecraft.thePlayer().isNull()) {
            this.stopped = true;
        }
        if (this.stopped) {
            this.flushPackets();
        }
    }

    public BlinkPacketRenderModule() {
        super("Blink", (int)MODULE_ID, Category.Y, "Chokes packets until disabled.");
        this.biDirectionalMode = new ModeOption("Bi-directional");
        this.directionMode = ModeValue.create((Object)this, "Direction", "Outgoing only - only chokes packets that you're sending\nBi-directional - additionally chokes incoming packets from the server", (ModeSelection)this.outgoingOnlyMode, this.outgoingOnlyMode, this.biDirectionalMode);
        this.breadcrumbs = BooleanValue.create(this, "Breadcrumbs", false, "Drops breadcrumbs in case you forgot where you came from.");
        this.spawnFake = BooleanValue.create(this, "Spawn fake", false, "Spawns a fake player where you started/are on the servers side.");
        this.autoSend = BooleanValue.create(this, "Auto send", false, "Automatically sends packets after a limit is reached");
        this.sendThreshold = NumberValue.create(this, "Send threshold", "#", "", 0.0, 50.0, 100.0, 1.0, "Allows you to choke your packets for a custom set limit.\nAfter the limit is reached, all packets will be sent.\nNOTE: 0 = No Limit");
        this.dispatchGuard = PacketDispatchGuard.b;
        this.pendingCallbacks = new ConcurrentLinkedQueue<PacketSendDispatchGuardCallback>();
        this.breadcrumbTrail = new CopyOnWriteArrayList<Vec3d>();
        this.packetTimer = new TimerUtil();
        this.fakePlayerTimer = new TimerUtil();
        this.fakePlayerTask = this::fakePlayerLoop;
        this.autoSend.K(this.sendThreshold);
        this.addValue(this.directionMode, this.breadcrumbs, this.spawnFake, this.autoSend, this.sendThreshold);
    }

    @EventHandler(A=EventPriority.LOWEREST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        int n;
        this.packetTimer.reset();
        if (!Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
            return;
        }
        if (eventPacketSend.isCanceled()) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (this.dispatchGuard.R(packet)) {
            return;
        }
        ++this.H;
        if (packet.isInstance(MappedClasses.qD) && this.isMoving() && (n = this.getChokeCount()) % 5 == 0) {
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
            this.breadcrumbTrail.add(new Vec3d(entityPlayerSP.z() + d4, entityPlayerSP.N(), entityPlayerSP.h() + d6));
        }
        this.pendingCallbacks.add(new PacketSendDispatchGuardCallback(eventPacketSend));
        eventPacketSend.setCancelled(true);
    }

    @Override
    public void onEnable() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            super.s(false, true);
            return;
        }
        if (this.spawnFake.L().booleanValue()) {
            this.fakePlayer = EntityOtherPlayerMP.create(Minecraft.theWorld(), Minecraft.thePlayer().c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937());
            this.fakePlayerEntityId = ClientSettings.f();
            this.fakePlayer.M(entityPlayerSP, true);
            if (ForgeVersion.MC_1_17.d()) {
                this.fakePlayer.Q(this.fakePlayerEntityId);
                UUID uUID = UUID.nameUUIDFromBytes(("OfflinePlayer:" + entityPlayerSP.getName()).getBytes(StandardCharsets.UTF_8));
                this.fakePlayer.y(uUID);
            }
            this.updateFakePlayerPosition();
            this.fakePlayer.z(entityPlayerSP.s());
            Minecraft.theWorld().D(this.fakePlayerEntityId, this.fakePlayer);
        }
    }

    private void updateFakePlayerPosition() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || this.fakePlayer.isNull() || !this.spawnFake.L().booleanValue()) {
            return;
        }
        this.fakePlayer.t(entityPlayerSP.z(), ClientSettings.H ? entityPlayerSP.N() : entityPlayerSP.N() - 1.5, entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        this.fakePlayer.z(entityPlayerSP.s());
    }

    private static Exception passException(Exception exception) {
        return exception;
    }

    @Override
    public String E() {
        int n = this.getChokeCount();
        if (n == 0) {
            return "";
        }
        return String.valueOf(n);
    }
}

