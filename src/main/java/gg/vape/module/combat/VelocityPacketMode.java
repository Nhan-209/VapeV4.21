package gg.vape.module.combat;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.control.SharedModuleControlClaimSecondary;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.Packet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class VelocityPacketMode
extends Mod {
    private static final long MODULE_ID = 6243151782707490976L;
    private long delayMillis;
    private long releaseTime;
    private final RandomValue airDelay;
    private final Queue<EventPacketReceive> heldPackets;
    private final NumberValue chance;
    private final RandomValue groundDelay;
    private long lastHitTime;
    private double distanceToTarget;
    private Entity target;
    private final PacketDispatchGuard dispatchGuard = PacketDispatchGuard.b;
    private final BooleanValue waterCheck;
    private int hitCount = 0;
    private SharedModuleControlClaimSecondary controlClaim;

    private boolean rollChance() {
        int roll = MathUtil.randomExclusiveUpper(new Random(), 0, 100);
        return (double)roll >= 100.0 - (Double)this.chance.K();
    }

    public VelocityPacketMode() {
        super("KnockbackDelay", (int)MODULE_ID, Category.Y, "Delays incoming knockback packets");
        this.heldPackets = new LinkedList<EventPacketReceive>();
        this.chance = NumberValue.E(this, "Chance", "#", "%", 0.0, 40.0, 100.0, "Chance of delaying knockback");
        this.airDelay = RandomValue.create(this, "Air delay", "#", "", 0.0, 50.0, 100.0, 500.0);
        this.groundDelay = RandomValue.create(this, "Ground delay", "#", "", 0.0, 200.0, 250.0, 500.0);
        this.waterCheck = BooleanValue.create(this, "Water check", false, "Won't delay knockback if in water");
        this.controlClaim = SharedModuleControlClaims.d;
        this.addValue(this.chance, this.airDelay, this.groundDelay, this.waterCheck);
        this.chance.C(0);
        this.controlClaim.l(this, 5);
    }

    @Override
    public String r() {
        if (!this.heldPackets.isEmpty()) {
            return "\u00a7cHolding";
        }
        return this.groundDelay.c() + "ms";
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean isInWater() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        return this.waterCheck.L() != false && entityPlayerSP.h$src$Z$ftwoya();
    }

    private boolean shouldDelay() {
        return !this.isInWater() && this.rollChance();
    }

    public EntityLivingBase findTargetInRange(double fov, double range) {
        EntityLivingBase entityLivingBase = RotationUtil.u(range, fov / 2.0);
        if (entityLivingBase == null) {
            return null;
        }
        if (RotationUtil.o(Minecraft.thePlayer(), entityLivingBase, range, 90.0, true)) {
            return entityLivingBase;
        }
        return null;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        Packet packet;
        int n = ClientSettings.C();
        if (eventPacketReceive.getWorld().isNotNull() && eventPacketReceive.getThePlayer().isNotNull() && !this.dispatchGuard.R(packet = eventPacketReceive.getPacket())) {
            boolean isVelocity = this.containsPlayerVelocity(packet, eventPacketReceive.getThePlayer());
            if (isVelocity) {
                if (this.target == null || !this.shouldDelay()) {
                    isVelocity = false;
                } else {
                    this.lastHitTime = System.currentTimeMillis();
                }
            }
            if (isVelocity && this.heldPackets.isEmpty()) {
                this.delayMillis = this.hitCount < 3 ? (long)this.airDelay.B() : (long)this.groundDelay.B();
                if (this.delayMillis > 0L) {
                    this.distanceToTarget = eventPacketReceive.getThePlayer().getDistanceToEntity(this.target);
                    this.releaseTime = System.currentTimeMillis() + this.delayMillis;
                    this.heldPackets.add(eventPacketReceive);
                    eventPacketReceive.setCancelled(true);
                    this.controlClaim.c();
                }
            } else if (!this.heldPackets.isEmpty()) {
                this.heldPackets.add(eventPacketReceive);
                eventPacketReceive.setCancelled(true);
                this.controlClaim.c();
            }
            if (this.heldPackets.isEmpty()) {
                long l = System.currentTimeMillis() - this.releaseTime;
                this.dispatchGuard.J(packet);
                this.controlClaim.Q();
            }
        }
    }

    private void flushHeldPackets() {
        if (this.heldPackets.isEmpty()) {
            return;
        }
        if (System.currentTimeMillis() >= this.releaseTime) {
            NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.thePlayer().sendQueue();
            for (EventPacketReceive eventPacketReceive : this.heldPackets) {
                this.dispatchGuard.l(eventPacketReceive.getPacket(), netHandlerPlayClientImpl);
            }
            this.heldPackets.clear();
        }
    }

    private boolean containsPlayerVelocity(Packet packet, EntityPlayerSP entityPlayerSP) {
        boolean[] found = new boolean[]{false};
        Packet.n(packet, arg_0 -> this.checkVelocityPacket(found, entityPlayerSP, arg_0));
        return found[0];
    }

    private void flushOnTick() {
        this.flushHeldPackets();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int n = ClientSettings.J();
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        PacketDispatchGuard.B(this::flushOnTick);
        if (eventPreTick.getWorld().isNotNull() && eventPreTick.getThePlayer().isNotNull()) {
            this.target = this.findTargetInRange(90.0, 5.0);
            this.hitCount = eventPreTick.getThePlayer().b$src$Z$fqlxe4() ? ++this.hitCount : 0;
        }
    }

    private void checkVelocityPacket(boolean[] found, EntityPlayerSP entityPlayerSP, Packet packet) {
        Entity entity;
        if (!found[0] && (entity = AttackPacketTimingTracker.F(packet)) != null && entity.equals(entityPlayerSP) && System.currentTimeMillis() - this.lastHitTime > 475L) {
            found[0] = true;
        }
    }
}

