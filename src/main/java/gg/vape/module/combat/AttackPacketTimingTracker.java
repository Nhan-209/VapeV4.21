package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.mapping.MappedClasses;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketAnimation;
import gg.vape.wrapper.impl.SPacketEntityStatus;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import java.util.ArrayList;
import java.util.List;

public class AttackPacketTimingTracker
implements EventListener {
    private final List<Long> hitDelays = new ArrayList<Long>();
    public static final AttackPacketTimingTracker a = new AttackPacketTimingTracker();
    private long lastHitTime;
    private static boolean initialized;
    private int targetId;
    private long lastAttackTime;

    public static Entity U(SPacketEntityStatus sPacketEntityStatus) {
        if (sPacketEntityStatus.o() == 2) {
            return Minecraft.theWorld().V(sPacketEntityStatus.X());
        }
        return null;
    }

    @EventHandler
    public void onPreAttack(EventPreAttack eventPreAttack) {
        this.targetId = eventPreAttack.getTarget().S();
    }

    private void recordHitDelay() {
        block5: {
            long l;
            block4: {
                long delay = System.currentTimeMillis() - this.lastHitTime;
                boolean bl = AttackPacketTimingTracker.v();
                long diff = delay - 500L;
                l = diff == 0L ? 0 : (diff < 0L ? -1 : 1);
                if (!bl) break block4;
                if (l >= 0) break block5;
                this.hitDelays.add(delay);
                l = this.hitDelays.size();
            }
            if (l == 20) {
                this.hitDelays.remove(0);
            }
        }
    }

    public static boolean v() {
        boolean bl = AttackPacketTimingTracker.d();
        return true;
    }

    public List<Long> n() {
        return this.hitDelays;
    }

    public static Entity F(Packet packet) {
        if (packet.isInstance(MappedClasses.YX)) {
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet);
            return Minecraft.theWorld().V(sPacketEntityVelocity.getEntityId());
        }
        return null;
    }

    public static Entity V(Packet packet) {
        if (packet.isInstance(MappedClasses.lU)) {
            SPacketEntityStatus sPacketEntityStatus = new SPacketEntityStatus(packet);
            return AttackPacketTimingTracker.U(sPacketEntityStatus);
        }
        if (packet.isInstance(MappedClasses.ZQ)) {
            SPacketAnimation sPacketAnimation = new SPacketAnimation(packet);
            return AttackPacketTimingTracker.f(sPacketAnimation);
        }
        return null;
    }

    public static boolean d() {
        return initialized;
    }

    public long h() {
        return this.lastHitTime;
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        Packet packet = eventPacketSend.getPacket();
        if (UseEntityPacketBridge.h(packet)) {
            UseEntityPacketBridge useEntityPacketBridge;
            UseEntityPacketBridge useEntityPacketBridge2 = useEntityPacketBridge = new UseEntityPacketBridge(packet);
            AttackPacketTimingTracker attackPacketTimingTracker = this;
            attackPacketTimingTracker.recordAttack(useEntityPacketBridge2);
        }
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        Entity entity = AttackPacketTimingTracker.V(eventPacketReceive.getPacket());
        if (entity != null && entity.isNotNull() && entity.S() == this.targetId) {
            AttackPacketTimingTracker attackPacketTimingTracker = this;
            attackPacketTimingTracker.recordHitDelay();
        }
    }

    public int Z() {
        AttackPacketTimingTracker attackPacketTimingTracker = this;
        return (int)Math.floor((double)attackPacketTimingTracker.Y() / 50.0);
    }

    public long a() {
        return this.lastAttackTime;
    }

    public static void L(boolean bl) {
        initialized = bl;
    }

    public static Entity f(SPacketAnimation sPacketAnimation) {
        if (sPacketAnimation.x() == 1) {
            return Minecraft.theWorld().V(sPacketAnimation.K());
        }
        return null;
    }

    public long Y() {
        long l = 0L;
        boolean bl = AttackPacketTimingTracker.d();
        if (!this.hitDelays.isEmpty()) {
            for (long l2 : this.hitDelays) {
                l += l2;
            }
            l /= (long)this.hitDelays.size();
        }
        return l;
    }

    private void recordAttack(UseEntityPacketBridge useEntityPacketBridge) {
        Entity entity;
        boolean bl = AttackPacketTimingTracker.d();
        if (useEntityPacketBridge.S() && (entity = Minecraft.theWorld().V(useEntityPacketBridge.w())).isInstance(MappedClasses.zm)) {
            EntityLivingBase entityLivingBase = new EntityLivingBase(entity);
            if (entityLivingBase.c$src$I$15a9iwo() == 0 && System.currentTimeMillis() - this.lastHitTime > 400L) {
                AttackPacketTimingTracker attackPacketTimingTracker = this;
                if (System.currentTimeMillis() - this.lastAttackTime > attackPacketTimingTracker.Y() * 2L) {
                    this.lastHitTime = System.currentTimeMillis();
                }
            }
            this.lastAttackTime = System.currentTimeMillis();
        }
    }


    public int r() {
        return this.targetId;
    }

    static {
        AttackPacketTimingTracker.L(false);
    }
}

