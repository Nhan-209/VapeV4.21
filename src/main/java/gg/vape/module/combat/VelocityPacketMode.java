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
    private static final long A = 6243151782707490976L;
    private long p;
    private long S;
    private final RandomValue j;
    private final Queue<EventPacketReceive> v;
    private final NumberValue F;
    private final RandomValue C;
    private long k;
    private double s;
    private Entity r;
    private final PacketDispatchGuard L = PacketDispatchGuard.b;
    private final BooleanValue D;
    private int a = 0;
    private SharedModuleControlClaimSecondary V;

    private boolean C$src$Z$14l94gn() {
        int n = MathUtil.randomExclusiveUpper(new Random(), 0, 100);
        return (double)n >= 100.0 - (Double)this.F.K();
    }

    public VelocityPacketMode() {
        super("KnockbackDelay", (int)A, Category.Y, "Delays incoming knockback packets");
        this.v = new LinkedList<EventPacketReceive>();
        this.F = NumberValue.E(this, "Chance", "#", "%", 0.0, 40.0, 100.0, "Chance of delaying knockback");
        this.j = RandomValue.create(this, "Air delay", "#", "", 0.0, 50.0, 100.0, 500.0);
        this.C = RandomValue.create(this, "Ground delay", "#", "", 0.0, 200.0, 250.0, 500.0);
        this.D = BooleanValue.create(this, "Water check", false, "Won't delay knockback if in water");
        this.V = SharedModuleControlClaims.d;
        this.addValue(this.F, this.j, this.C, this.D);
        this.F.C(0);
        this.V.l(this, 5);
    }

    @Override
    public String r() {
        if (!this.v.isEmpty()) {
            return "\u00a7cHolding";
        }
        return this.C.c() + "ms";
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean e() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        return this.D.L() != false && entityPlayerSP.h$src$Z$ftwoya();
    }

    private boolean M$src$Z$14qr2e9() {
        return !this.e() && this.C$src$Z$14l94gn();
    }

    public EntityLivingBase A(double d, double d2) {
        EntityLivingBase entityLivingBase = RotationUtil.u(d2, d / 2.0);
        if (entityLivingBase == null) {
            return null;
        }
        if (RotationUtil.o(Minecraft.thePlayer(), entityLivingBase, d2, 90.0, true)) {
            return entityLivingBase;
        }
        return null;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        Packet packet;
        int n = ClientSettings.C();
        if (eventPacketReceive.getWorld().isNotNull() && eventPacketReceive.getThePlayer().isNotNull() && !this.L.R(packet = eventPacketReceive.getPacket())) {
            boolean bl = this.c(packet, eventPacketReceive.getThePlayer());
            if (bl) {
                if (this.r == null || !this.M$src$Z$14qr2e9()) {
                    bl = false;
                } else {
                    this.k = System.currentTimeMillis();
                }
            }
            if (bl && this.v.isEmpty()) {
                this.p = this.a < 3 ? (long)this.j.B() : (long)this.C.B();
                if (this.p > 0L) {
                    this.s = eventPacketReceive.getThePlayer().getDistanceToEntity(this.r);
                    this.S = System.currentTimeMillis() + this.p;
                    this.v.add(eventPacketReceive);
                    eventPacketReceive.setCancelled(true);
                    this.V.c();
                }
            } else if (!this.v.isEmpty()) {
                this.v.add(eventPacketReceive);
                eventPacketReceive.setCancelled(true);
                this.V.c();
            }
            if (this.v.isEmpty()) {
                long l = System.currentTimeMillis() - this.S;
                this.L.J(packet);
                this.V.Q();
            }
        }
    }

    private void U() {
        if (this.v.isEmpty()) {
            return;
        }
        if (System.currentTimeMillis() >= this.S) {
            NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.thePlayer().sendQueue();
            for (EventPacketReceive eventPacketReceive : this.v) {
                this.L.l(eventPacketReceive.getPacket(), netHandlerPlayClientImpl);
            }
            this.v.clear();
        }
    }

    private boolean c(Packet packet, EntityPlayerSP entityPlayerSP) {
        boolean[] blArray = new boolean[]{false};
        Packet.n(packet, arg_0 -> this.lambda$containsPlayerVelocity$1(blArray, entityPlayerSP, arg_0));
        return blArray[0];
    }

    private void lambda$onClientTick$0() {
        this.U();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int n = ClientSettings.J();
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        PacketDispatchGuard.B(this::lambda$onClientTick$0);
        if (eventPreTick.getWorld().isNotNull() && eventPreTick.getThePlayer().isNotNull()) {
            this.r = this.A(90.0, 5.0);
            this.a = eventPreTick.getThePlayer().b$src$Z$fqlxe4() ? ++this.a : 0;
        }
    }

    private void lambda$containsPlayerVelocity$1(boolean[] blArray, EntityPlayerSP entityPlayerSP, Packet packet) {
        Entity entity;
        if (!blArray[0] && (entity = AttackPacketTimingTracker.F(packet)) != null && entity.equals(entityPlayerSP) && System.currentTimeMillis() - this.k > 475L) {
            blArray[0] = true;
        }
    }
}

