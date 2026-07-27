package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.BacktrackPacketQueueEntry;
import gg.vape.module.blatant.BacktrackPacketReplayState;
import gg.vape.module.blatant.BacktrackPacketState;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.datas.PlayerLocationSnapshot;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.NetworkPlayerInfo;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.SEntityPacket;
import gg.vape.wrapper.impl.SPacketEntity;
import gg.vape.wrapper.impl.SPacketEntityTeleport;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

public class Backtrack
extends Mod {
    private double F;
    private final Map<Integer, BacktrackPacketState> A;
    private double O;
    private double k;
    private double Z;
    private int Y = -1;
    private final BooleanValue o;
    public final RandomValue v = RandomValue.create(this, "Latency", "#", "ms", 0.0, 50.0, 100.0, 500.0);
    private boolean D;
    private double r;
    private double b;
    private int s;
    private final PacketDispatchGuard H;
    private final Map<Packet, Long> t;
    private PlayerLocationSnapshot I;
    private double U;
    private double K;
    private final ColorValue V;
    private int a = -1;
    private double S;

    @Override
    public String r() {
        return this.v.c();
    }

    @EventHandler
    public void d(EventPreAttack eventPreAttack) {
        this.J(eventPreAttack.getTarget());
    }

    private boolean A(Packet packet) {
        if (packet.isInstance(MappedClasses.zw)) {
            return true;
        }
        if (packet.isInstance(MappedClasses.YX)) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (entityPlayerSP.isNull()) {
                return true;
            }
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet);
            return sPacketEntityVelocity.getEntityId() == entityPlayerSP.S();
        }
        return false;
    }

    public Backtrack() {
        super("BackTrack", -57312, Category.Y);
        this.o = BooleanValue.create(this, "Render server pos", true);
        this.V = ColorValue.b(this, "Color", new Color(5, 134, 105), 100);
        this.H = PacketDispatchGuard.b;
        this.t = new LinkedHashMap<Packet, Long>();
        this.A = new HashMap<Integer, BacktrackPacketState>();
        this.o.K(this.V);
        this.addValue(this.v, this.o, this.V);
    }

    private void lambda$reset$0() {
        Backtrack backtrack = this;
        backtrack.z();
        this.A.clear();
        this.Y = -1;
        this.a = -1;
        this.I = null;
        this.t(0.0, 0.0, 0.0);
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.o.L().booleanValue()) {
            return;
        }
        if (this.Y == -1) {
            return;
        }
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        Entity entity = Minecraft.theWorld().V(this.Y);
        if (entity == null || !entity.isInstance(MappedClasses.zm)) {
            return;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(entity);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        float f = Minecraft.getTimer().renderPartialTicks();
        double d4 = this.r + (this.S - this.r) * (double)f;
        double d5 = this.k + (this.F - this.k) * (double)f;
        double d6 = this.O + (this.K - this.O) * (double)f;
        eventRender3D.getEntityRenderer().B(1.0);
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        if (ForgeVersion.MC_1_21_4.v()) {
            GL11.glLineWidth((float)1.5f);
        }
        double d7 = entityLivingBase.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMaxX() - entityLivingBase.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinX() + (double)entityLivingBase.b();
        double d8 = d7 / 2.0;
        RenderUtil.d();
        RenderUtil.u(d4 - d8, d5 + 0.01, d6 - d8, d7, entityLivingBase.Y(), d7, 1.0, this.V.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().darker().darker(), this.V.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), d, d2, d3);
        RenderUtil.Y();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        eventRender3D.getEntityRenderer().O(1.0);
    }

    private void Q$src$V$1grknad() {
        if (this.a != -1) {
            Entity entity;
            this.Y = this.a;
            this.a = -1;
            this.I = null;
            BacktrackPacketState backtrackPacketState = this.A.get(this.Y);
            if (backtrackPacketState != null) {
                PlayerLocationSnapshot playerLocationSnapshot = backtrackPacketState.g();
                this.t(playerLocationSnapshot.getX(), playerLocationSnapshot.getY(), playerLocationSnapshot.getZ());
            } else if (Minecraft.theWorld().isNotNull() && (entity = Minecraft.theWorld().V(this.Y)) != null && entity.isNotNull()) {
                this.t(entity.z(), entity.N(), entity.h());
            }
            Backtrack backtrack = this;
            backtrack.z();
        }
    }

    private void z() {
        String string = WorldClient.b();
        if (this.t.isEmpty()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (string != null) {
            NetHandlerPlayClientImpl netHandlerPlayClientImpl;
            if (entityPlayerSP.isNotNull() && (netHandlerPlayClientImpl = entityPlayerSP.sendQueue()).isNotNull()) {
                for (Packet packet : this.t.keySet()) {
                    this.H.l(packet, netHandlerPlayClientImpl);
                }
            }
            this.t.clear();
        }
    }

    private void J(Entity entity) {
        if (entity == null) {
            return;
        }
        int n = entity.S();
        if (n != this.Y && n != this.a) {
            this.a = n;
        }
    }

    private boolean G(PlayerLocationSnapshot playerLocationSnapshot) {
        if (this.I == null) {
            return false;
        }
        PlayerLocationSnapshot playerLocationSnapshot2 = this.P();
        if (playerLocationSnapshot2 == null) {
            return false;
        }
        double d = PlayerLocationSnapshot.rayIntersectionDistance(playerLocationSnapshot2, this.I);
        double d2 = PlayerLocationSnapshot.rayIntersectionDistance(playerLocationSnapshot2, playerLocationSnapshot);
        return d2 > d + 0.001;
    }

    private PlayerLocationSnapshot C(Packet packet, EventPacketReceive eventPacketReceive) {
        BacktrackPacketState backtrackPacketState = null;
        int n = -1;
        if (packet.isInstance(MappedClasses.qz)) {
            SEntityPacket sEntityPacket = new SEntityPacket(packet);
            Entity entity = sEntityPacket.V(eventPacketReceive.getWorld());
            if (entity.isNotNull()) {
                n = entity.S();
                backtrackPacketState = this.A.get(n);
                if (backtrackPacketState != null) {
                    backtrackPacketState.Z(sEntityPacket);
                } else {
                    BacktrackPacketState backtrackPacketState2 = BacktrackPacketState.J(entity);
                    backtrackPacketState2.Z(sEntityPacket);
                    this.A.put(n, backtrackPacketState2);
                    backtrackPacketState = backtrackPacketState2;
                }
            }
        } else if (packet.isInstance(MappedClasses.s)) {
            SPacketEntity sPacketEntity = new SPacketEntity(packet);
            n = sPacketEntity.k();
            backtrackPacketState = this.A.get(n);
            if (backtrackPacketState != null) {
                backtrackPacketState.K(sPacketEntity);
            } else {
                backtrackPacketState = new BacktrackPacketState(sPacketEntity.H(), sPacketEntity.M(), sPacketEntity.B());
                this.A.put(n, backtrackPacketState);
            }
        } else {
            if (packet.isInstance(MappedClasses.uW)) {
                SPacketEntityTeleport sPacketEntityTeleport = new SPacketEntityTeleport(packet);
                this.A.put(sPacketEntityTeleport.u(), new BacktrackPacketState(sPacketEntityTeleport.m$src$I$1g30xfs(), sPacketEntityTeleport.I(), sPacketEntityTeleport.d()));
                return null;
            }
            if (packet.isInstance(MappedClasses.Yv)) {
                BacktrackPacketReplayState backtrackPacketReplayState = new BacktrackPacketReplayState(packet);
                for (int n2 : backtrackPacketReplayState.n()) {
                    this.A.remove(n2);
                    if (n2 != this.Y) continue;
                    this.Y = -1;
                    this.I = null;
                    Backtrack backtrack = this;
                    backtrack.z();
                }
                return null;
            }
            if (packet.isInstance(MappedClasses.FE)) {
                Backtrack backtrack = this;
                backtrack.w$src$V$1hcgtu3();
                return null;
            }
        }
        if (ForgeVersion.MC_1_21_4.d() && packet.isInstance(MappedClasses.ly)) {
            NetworkPlayerInfo networkPlayerInfo = new NetworkPlayerInfo(packet);
            if ((backtrackPacketState = this.A.get(n = networkPlayerInfo.C())) != null) {
                backtrackPacketState.W(networkPlayerInfo);
            }
        }
        if (n == this.Y && backtrackPacketState != null) {
            PlayerLocationSnapshot playerLocationSnapshot = backtrackPacketState.g();
            this.U(playerLocationSnapshot.getX(), playerLocationSnapshot.getY(), playerLocationSnapshot.getZ());
            return playerLocationSnapshot;
        }
        return null;
    }

    private void N(Packet packet) {
        long l = (long)this.v.B();
        long l2 = System.currentTimeMillis() + l;
        String string = WorldClient.b();
        Map<Packet, Long> queuedPackets = this.t;
        if (string != null) {
            long l3;
            if (!queuedPackets.isEmpty() && l2 < (l3 = ((Long)((Map.Entry)this.t.entrySet().toArray()[this.t.size() - 1]).getValue()).longValue())) {
                l2 = l3 + 1L;
            }
            queuedPackets.put(packet, l2);
        }
    }

    private void w$src$V$1hcgtu3() {
        PacketDispatchGuard.B(this::lambda$reset$0);
    }

    private void U(double d, double d2, double d3) {
        this.U = d;
        this.b = d2;
        this.Z = d3;
        this.s = 3;
    }

    @Override
    public void onDisable() {
        Backtrack backtrack = this;
        backtrack.w$src$V$1hcgtu3();
    }

    @EventHandler
    public void N(EventWorldChange eventWorldChange) {
        Backtrack backtrack = this;
        backtrack.w$src$V$1hcgtu3();
    }


    @EventHandler(A=EventPriority.LOW)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        List<Packet> list;
        Wrapper wrapper;
        String string = WorldClient.b();
        if (!this.t.isEmpty()) {
            wrapper = Minecraft.N();
            if (wrapper.isNotNull()) {
                Map.Entry<Packet, Long> object2;
                long l = System.currentTimeMillis();
                Iterator<Map.Entry<Packet, Long>> iterator = this.t.entrySet().iterator();
                while (iterator.hasNext() && l >= (object2 = iterator.next()).getValue()) {
                    this.H.l(object2.getKey(), (NetHandlerPlayClientImpl)wrapper);
                    iterator.remove();
                }
            } else {
                this.t.clear();
            }
        }
        this.Q$src$V$1grknad();
        if (eventPacketReceive.getWorld().isNull() || eventPacketReceive.getThePlayer().isNull()) {
            Backtrack backtrack = this;
            backtrack.z();
            this.Y = -1;
            return;
        }
        wrapper = eventPacketReceive.getPacket();
        if (this.H.R((Packet)wrapper)) {
            return;
        }
        if (ForgeVersion.MC_1_21_4.d() && wrapper.isInstance(MappedClasses.ue)) {
            BacktrackPacketQueueEntry backtrackPacketQueueEntry = new BacktrackPacketQueueEntry(wrapper);
            list = backtrackPacketQueueEntry.s();
        } else {
            list = Collections.singletonList((Packet)wrapper);
        }
        PlayerLocationSnapshot latestSnapshot = null;
        for (Packet packet : list) {
            PlayerLocationSnapshot playerLocationSnapshot = this.C(packet, eventPacketReceive);
            if (playerLocationSnapshot != null) {
                latestSnapshot = playerLocationSnapshot;
            }
            if (!this.A(packet)) continue;
            Backtrack backtrack = this;
            backtrack.z();
            return;
        }
        if (this.Y == -1) {
            if (!this.t.isEmpty()) {
                Backtrack backtrack = this;
                backtrack.z();
            }
            return;
        }
        if (latestSnapshot != null) {
            this.D = this.G(latestSnapshot);
            this.I = latestSnapshot;
        }
        if (this.D) {
            eventPacketReceive.setCancelled(true);
            Wrapper wrapper2 = wrapper;
            Backtrack backtrack = this;
            backtrack.N((Packet)wrapper2);
        } else if (!this.t.isEmpty()) {
            Backtrack backtrack = this;
            backtrack.z();
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        block6: {
            int n;
            block5: {
                this.r = this.S;
                this.k = this.F;
                this.O = this.K;
                String string = WorldClient.b();
                n = this.Y;
                if (string == null) break block5;
                if (n == -1) break block6;
                n = this.s;
            }
            if (n > 0) {
                this.S += (this.U - this.S) / (double)this.s;
                this.F += (this.b - this.F) / (double)this.s;
                this.K += (this.Z - this.K) / (double)this.s;
                --this.s;
            }
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            WorldClient.J("R8ThM");
        }
    }

    private PlayerLocationSnapshot P() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        return new PlayerLocationSnapshot(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h());
    }

    private void t(double d, double d2, double d3) {
        this.S = this.r = d;
        this.U = this.r;
        this.F = this.k = d2;
        this.b = this.k;
        this.K = this.O = d3;
        this.Z = this.O;
        this.s = 0;
    }
}
