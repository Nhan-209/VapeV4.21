package gg.vape.module.render.freecam;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreLivingTravel;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.Freecam;
import gg.vape.module.render.freecam.FreecamController;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.C06PacketPlayerPositionLook;
import gg.vape.wrapper.impl.CPacketPlayerPosition;
import gg.vape.wrapper.impl.CPacketPlayer_Rotation;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PacketVelocityBridge;
import gg.vape.wrapper.impl.PlayerInteractEventAction;
import gg.vape.wrapper.impl.PlayerPositionLookPacketModern;
import gg.vape.wrapper.impl.SPacketEntityVelocity;

public class FreecamLegacyController
extends FreecamController<Freecam> {
    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull() || Minecraft.thePlayer().w$src$F$15l9epb() <= 0.0f) {
            ((Freecam)this.n).s(false, false);
        }
        if (((Freecam)this.n).C) {
            ((Freecam)this.n).M$src$V$nre1v6();
            return;
        }
        ((Freecam)this.n).c.d(this.n);
        if (((Freecam)this.n).S) {
            ((Freecam)this.n).k$src$V$o7vvo0();
            ((Freecam)this.n).S = false;
        }
        ((Freecam)this.n).X$src$V$nxfse5();
    }


    private CPacketPlayerPosition buildPositionPacket() {
        if (ForgeVersion.MC_1_7_10.L()) {
            return CPacketPlayerPosition.newInstance(((Freecam)this.n).Z.z(), ((Freecam)this.n).Z.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY(), ((Freecam)this.n).Z.N(), ((Freecam)this.n).Z.h(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
        }
        return CPacketPlayerPosition.newInstance(((Freecam)this.n).Z.z(), ((Freecam)this.n).Z.N(), ((Freecam)this.n).Z.h(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (((Freecam)this.n).Z == null) {
            return;
        }
        if (((Freecam)this.n).P != null && eventPacketSend.getPacketInstance().equals(((Freecam)this.n).P)) {
            ((Freecam)this.n).P = null;
            return;
        }
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        if (((Freecam)this.n).c(eventPacketSend.getPacket())) {
            eventPacketSend.setCancelled(true);
            return;
        }
        if (!eventPacketSend.getPacket().isInstance(MappedClasses.qD)) {
            return;
        }
        eventPacketSend.setPacket(this.buildMovementPacket());
        Minecraft.thePlayer().E(((Freecam)this.n).I);
    }

    private C06PacketPlayerPositionLook buildPositionLookPacket() {
        if (ForgeVersion.MC_1_7_10.L()) {
            return C06PacketPlayerPositionLook.create(((Freecam)this.n).Z.z(), ((Freecam)this.n).Z.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY(), ((Freecam)this.n).Z.N(), ((Freecam)this.n).Z.h(), ((Freecam)this.n).Z.J(), ((Freecam)this.n).Z.V(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
        }
        return C06PacketPlayerPositionLook.create(((Freecam)this.n).Z.z(), ((Freecam)this.n).Z.N(), ((Freecam)this.n).Z.h(), ((Freecam)this.n).Z.J(), ((Freecam)this.n).Z.V(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
    }

    private void applyGhostPositionLookLater(PlayerPositionLookPacketModern playerPositionLookPacketModern) {
        this.applyGhostPositionLook(playerPositionLookPacketModern);
    }

    private C06PacketPlayerPositionLook buildLookOnlyPacket() {
        if (ForgeVersion.MC_1_7_10.L()) {
            return C06PacketPlayerPositionLook.create(((Freecam)this.n).Z.t(), -999.0, -999.0, ((Freecam)this.n).Z.T(), ((Freecam)this.n).Z.J(), ((Freecam)this.n).Z.V(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
        }
        return C06PacketPlayerPositionLook.create(((Freecam)this.n).Z.t(), -999.0, ((Freecam)this.n).Z.T(), ((Freecam)this.n).Z.J(), ((Freecam)this.n).Z.V(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
    }

    private void applyGhostPositionLook(PlayerPositionLookPacketModern playerPositionLookPacketModern) {
        if (playerPositionLookPacketModern == null || playerPositionLookPacketModern.isNull()) {
            return;
        }
        EntityOtherPlayerMP entityOtherPlayerMP = ((Freecam)this.n).Z;
        double d = playerPositionLookPacketModern.S();
        double d2 = playerPositionLookPacketModern.H();
        double d3 = playerPositionLookPacketModern.e();
        float f = playerPositionLookPacketModern.f();
        float f2 = playerPositionLookPacketModern.M();
        if (ForgeVersion.MC_1_7_10.Y()) {
            if (playerPositionLookPacketModern.W().contains(PlayerInteractEventAction.z().getObject())) {
                d += entityOtherPlayerMP.z();
            } else {
                entityOtherPlayerMP.r(0.0);
            }
            if (playerPositionLookPacketModern.W().contains(PlayerInteractEventAction.t$src$Lgg_vape_wrapper_impl_PlayerInteractEventAction_$1n8jtc5().getObject())) {
                d2 += entityOtherPlayerMP.N();
            } else {
                entityOtherPlayerMP.k(0.0);
            }
            if (playerPositionLookPacketModern.W().contains(PlayerInteractEventAction.a().getObject())) {
                d3 += entityOtherPlayerMP.h();
            } else {
                entityOtherPlayerMP.i(0.0);
            }
            if (playerPositionLookPacketModern.W().contains(PlayerInteractEventAction.b().getObject())) {
                f2 += entityOtherPlayerMP.V();
            }
            if (playerPositionLookPacketModern.W().contains(PlayerInteractEventAction.m$src$Lgg_vape_wrapper_impl_PlayerInteractEventAction_$1581zn0().getObject())) {
                f += entityOtherPlayerMP.J();
            }
            entityOtherPlayerMP.t(d, d2, d3, f, f2);
            entityOtherPlayerMP.H(f);
            C06PacketPlayerPositionLook c06PacketPlayerPositionLook = C06PacketPlayerPositionLook.create(entityOtherPlayerMP.z(), entityOtherPlayerMP.N(), entityOtherPlayerMP.h(), entityOtherPlayerMP.J(), entityOtherPlayerMP.V(), false);
            ((Freecam)this.n).P = c06PacketPlayerPositionLook.getObject();
            ((Freecam)this.n).K.G(c06PacketPlayerPositionLook);
        }
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (((Freecam)this.n).Z == null) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        if (packet.isInstance(MappedClasses.zw)) {
            ((Freecam)this.n).K = eventPacketReceive.getNetworkManager();
            PlayerPositionLookPacketModern playerPositionLookPacketModern = new PlayerPositionLookPacketModern(packet.getObject());
            eventPacketReceive.setCancelled(true);
            if (ForgeVersion.MC_1_8_9.d()) {
                Minecraft.v(() -> this.applyGhostPositionLookLater(playerPositionLookPacketModern));
            } else {
                this.applyGhostPositionLook(playerPositionLookPacketModern);
            }
            return;
        }
        if (packet.isInstance(MappedClasses.YX)) {
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet.getObject());
            if (sPacketEntityVelocity.getEntityId() == Minecraft.thePlayer().S()) {
                ((Freecam)this.n).Z.E((double)sPacketEntityVelocity.getMotionX() / 8000.0, (double)sPacketEntityVelocity.getMotionY() / 8000.0, (double)sPacketEntityVelocity.getMotionZ() / 8000.0);
            }
        } else if (packet.isInstance(MappedClasses.qe)) {
            PacketVelocityBridge packetVelocityBridge = new PacketVelocityBridge(packet.getObject());
            ((Freecam)this.n).Z.r(((Freecam)this.n).Z.t() + (double)packetVelocityBridge.getMotionX());
            ((Freecam)this.n).Z.k(((Freecam)this.n).Z.q() + (double)packetVelocityBridge.getMotionY());
            ((Freecam)this.n).Z.i(((Freecam)this.n).Z.T() + (double)packetVelocityBridge.getMotionZ());
        }
    }

    @EventHandler
    public void h(EventPreLivingTravel eventPreLivingTravel) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = (Double)((Freecam)this.n).Y.K() / 5.0 * (ClientSettings.B(Minecraft.gameSettings().r()) ? 2.0 : 1.0);
        double d2 = (Double)((Freecam)this.n).v.K() / 5.0 * (ClientSettings.B(Minecraft.gameSettings().r()) ? 2.0 : 1.0);
        entityPlayerSP.z(true);
        float f = entityPlayerSP.movementInput().G() ? 1.0f : (entityPlayerSP.movementInput().D$src$Z$v5d6e8() ? -1.0f : 0.0f);
        double d3 = (double)(f * 0.42f) * d2;
        entityPlayerSP.k(d3);
        Minecraft.thePlayer().movementInput().setCancelled(false);
        entityPlayerSP.R(false);
        double d4 = entityPlayerSP.movementInput().D();
        double d5 = entityPlayerSP.movementInput().T();
        float f2 = entityPlayerSP.J();
        if (d4 == 0.0 && d5 == 0.0) {
            entityPlayerSP.r(0.0);
            entityPlayerSP.i(0.0);
        } else {
            if (d4 != 0.0) {
                if (d5 > 0.0) {
                    f2 += (float)(d4 > 0.0 ? -45 : 45);
                } else if (d5 < 0.0) {
                    f2 += (float)(d4 > 0.0 ? 45 : -45);
                }
                d5 = 0.0;
                d4 = d4 > 0.0 ? 1.0 : -1.0;
            }
            entityPlayerSP.r(d4 * d * Math.cos(Math.toRadians(f2 + 90.0f)) + d5 * d * Math.sin(Math.toRadians(f2 + 90.0f)));
            entityPlayerSP.i(d4 * d * Math.sin(Math.toRadians(f2 + 90.0f)) - d5 * d * Math.cos(Math.toRadians(f2 + 90.0f)));
        }
    }

    public FreecamLegacyController(Freecam freecam) {
        super(freecam);
    }

    private C03PacketPlayer buildMovementPacket() {
        boolean bl;
        double d = ((Freecam)this.n).Z.z() - ((Freecam)this.n).J;
        double d2 = (ClientSettings.H ? ((Freecam)this.n).Z.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().getMinY() : ((Freecam)this.n).Z.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY()) - ((Freecam)this.n).s;
        double d3 = ((Freecam)this.n).Z.h() - ((Freecam)this.n).a;
        double d4 = ((Freecam)this.n).Z.J() - ((Freecam)this.n).F;
        double d5 = ((Freecam)this.n).Z.V() - ((Freecam)this.n).O;
        boolean bl2 = d * d + d2 * d2 + d3 * d3 > 9.0E-4 || ((Freecam)this.n).I >= 20;
        boolean bl3 = bl = d4 != 0.0 || d5 != 0.0;
        if (bl) {
            C03PacketPlayer c03PacketPlayer = null;
            if (((Freecam)this.n).Z.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12().isNull()) {
                c03PacketPlayer = bl2 ? this.buildPositionLookPacket() : CPacketPlayer_Rotation.create(((Freecam)this.n).Z.J(), ((Freecam)this.n).Z.V(), ((Freecam)this.n).Z.b$src$Z$fqlxe4());
            } else {
                c03PacketPlayer = this.buildLookOnlyPacket();
                bl2 = false;
            }
            ++((Freecam)this.n).I;
            if (bl2) {
                ((Freecam)this.n).J = ((Freecam)this.n).Z.z();
                ((Freecam)this.n).s = ClientSettings.H ? ((Freecam)this.n).Z.N() : ((Freecam)this.n).Z.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
                ((Freecam)this.n).a = ((Freecam)this.n).Z.h();
                ((Freecam)this.n).I = 0;
            }
            ((Freecam)this.n).F = ((Freecam)this.n).Z.J();
            ((Freecam)this.n).O = ((Freecam)this.n).Z.V();
            return c03PacketPlayer;
        }
        C03PacketPlayer c03PacketPlayer = null;
        if (((Freecam)this.n).Z.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12().isNull()) {
            if (bl2) {
                // empty if block
            }
            c03PacketPlayer = bl2 ? this.buildPositionPacket() : C03PacketPlayer.newInstance(((Freecam)this.n).Z.b$src$Z$fqlxe4());
        } else {
            c03PacketPlayer = this.buildLookOnlyPacket();
            bl2 = false;
        }
        ++((Freecam)this.n).I;
        if (bl2) {
            ((Freecam)this.n).J = ((Freecam)this.n).Z.z();
            ((Freecam)this.n).s = ClientSettings.H ? ((Freecam)this.n).Z.N() : ((Freecam)this.n).Z.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
            ((Freecam)this.n).a = ((Freecam)this.n).Z.h();
            ((Freecam)this.n).I = 0;
        }
        return c03PacketPlayer;
    }
}

