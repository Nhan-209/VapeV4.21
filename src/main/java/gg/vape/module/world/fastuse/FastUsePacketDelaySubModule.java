package gg.vape.module.world.fastuse;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.DelayedPacketSendEntry;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.control.SharedModuleControlClaimPrimary;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.world.FastUseModule;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.concurrent.ReadWriteLockHelper;
import gg.vape.utils.datas.PlayerLocationSnapshot;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.CPacketPlayerBlockPlacement;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FastUsePacketDelaySubModule
extends SubModule<FastUseModule> {
    public final NumberValue U = NumberValue.create((Object)this, "Transmission offset", "#", "", 0.0, 5.0, 50.0, 1.0);
    private final Map<DelayedPacketSendEntry, Long> delayedPackets = new LinkedHashMap<DelayedPacketSendEntry, Long>();
    private SharedModuleControlClaimPrimary controlClaim;
    private final PacketDispatchGuard dispatchGuard = PacketDispatchGuard.b;
    private long lastAttackTime;
    private Entity targetEntity;
    private PlayerLocationSnapshot lastGroundSnapshot;
    private long lastBlockPlaceTime;
    private long blockPlaceCooldownUntil;
    private final ReadWriteLockHelper lock = new ReadWriteLockHelper();

    private void flushPacket(DelayedPacketSendEntry delayedPacketSendEntry) {
        this.dispatchGuard.o(DelayedPacketSendEntry.g(delayedPacketSendEntry));
    }


    private void flushAll() {
        ReadWriteLockHelper writeLock = this.lock;
        writeLock.lockWrite();
        ReadWriteLockHelper readLock = this.lock;
        readLock.lockRead();
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.delayedPackets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
            this.dispatchGuard.o(DelayedPacketSendEntry.g(entry.getKey()));
            iterator.remove();
        }
        ReadWriteLockHelper writeUnlock = this.lock;
        writeUnlock.unlockWrite();
        ReadWriteLockHelper readUnlock = this.lock;
        readUnlock.unlockRead();
    }

    private void delaySend(EventPacketSend eventPacketSend) {
        int n = DelayedPacketSendEntry.G();
        ReadWriteLockHelper writeLock = this.lock;
        writeLock.lockWrite();
        int n2 = n;
        ReadWriteLockHelper readLock = this.lock;
        readLock.lockRead();
        long offset = ((Double)this.U.K()).longValue();
        if (offset > 0L) {
            long accumulated = 0L;
            for (Map.Entry<DelayedPacketSendEntry, Long> entry : this.delayedPackets.entrySet()) {
                DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
                accumulated += offset;
                if (delayedPacketSendEntry.E()) continue;
                entry.setValue(System.currentTimeMillis() + accumulated);
                delayedPacketSendEntry.d(true);
            }
            if (eventPacketSend != null) {
                this.delayedPackets.put(new DelayedPacketSendEntry(this, eventPacketSend), System.currentTimeMillis() + accumulated + offset);
                eventPacketSend.setCancelled(true);
            }
        } else {
            this.delayedPackets.keySet().forEach(this::flushPacket);
            this.delayedPackets.clear();
        }
        ReadWriteLockHelper readUnlock = this.lock;
        readUnlock.unlockRead();
        ReadWriteLockHelper writeUnlock = this.lock;
        writeUnlock.unlockWrite();
    }

    private void processPacket(EventPacketSend eventPacketSend) {
        Object object;
        Object object2;
        Wrapper wrapper;
        Packet packet = eventPacketSend.getPacket();
        int n = DelayedPacketSendEntry.I();
        if (UseEntityPacketBridge.h(packet)) {
            wrapper = new UseEntityPacketBridge(packet);
            if (((UseEntityPacketBridge)wrapper).S()) {
                object2 = Minecraft.theWorld().V(((UseEntityPacketBridge)wrapper).w());
                object = AttackPacketTimingTracker.a;
                if (((Wrapper)object2).isInstance(MappedClasses.zm)) {
                    EventPacketSend eventPacketSend2;
                    FastUsePacketDelaySubModule fastUsePacketDelaySubModule;
                    EntityLivingBase entityLivingBase = new EntityLivingBase(object2);
                    if (entityLivingBase.c$src$I$15a9iwo() <= ((AttackPacketTimingTracker)object).Z()) {
                        if (System.currentTimeMillis() - this.lastAttackTime > 250L) {
                            FastUsePacketDelaySubModule fastUsePacketDelaySubModule2 = this;
                            EventPacketSend eventPacketSend3 = eventPacketSend;
                            if (fastUsePacketDelaySubModule2.releasePending(eventPacketSend3)) {
                                return;
                            }
                        } else {
                            this.lastAttackTime = System.currentTimeMillis();
                        }
                    } else if (entityLivingBase.c$src$I$15a9iwo() <= ((AttackPacketTimingTracker)object).Z() + 1 && (fastUsePacketDelaySubModule = this).releasePending(eventPacketSend2 = eventPacketSend)) {
                        return;
                    }
                }
            }
        } else {
            if (packet.isInstance(MappedClasses.F9) || packet.isInstance(MappedClasses.u7) || packet.isInstance(MappedClasses.DN) || packet.isInstance(MappedClasses.q6)) {
                EventPacketSend eventPacketSend4 = eventPacketSend;
                FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                fastUsePacketDelaySubModule.delaySend(eventPacketSend4);
                return;
            }
            if (packet.isInstance(MappedClasses.YB)) {
                wrapper = new CPacketPlayerBlockPlacement(packet);
                object2 = ((CPacketPlayerBlockPlacement)wrapper).Q$src$Lgg_vape_wrapper_impl_ItemStack_$16phjq1();
                if (((Wrapper)object2).isNotNull()) {
                    if (!ItemStackScoreUtil.h(((ItemStack)object2).getItem())) {
                        EventPacketSend eventPacketSend5 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.delaySend(eventPacketSend5);
                        this.blockPlaceCooldownUntil = System.currentTimeMillis() + 500L;
                        return;
                    }
                    if (System.currentTimeMillis() - this.lastBlockPlaceTime >= 500L) {
                        EventPacketSend eventPacketSend6 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.delaySend(eventPacketSend6);
                        this.lastBlockPlaceTime = System.currentTimeMillis();
                        return;
                    }
                }
            } else if (this.blockPlaceCooldownUntil > System.currentTimeMillis() || Minecraft.thePlayer().l$src$Z$1io4duf() && !Minecraft.thePlayer().o$src$Z$1iprrmi() || !Minecraft.thePlayer().b$src$Z$fqlxe4() && Minecraft.thePlayer().M$src$F$ff28gb() > 3.0f) {
                EventPacketSend eventPacketSend7 = eventPacketSend;
                FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                fastUsePacketDelaySubModule.delaySend(eventPacketSend7);
                return;
            }
        }
        if (this.targetEntity != null && this.targetEntity.isNotNull() && this.targetEntity.isInstance(MappedClasses.zm)) {
            if (packet.isInstance(MappedClasses.qD)) {
                wrapper = new C03PacketPlayer(packet);
                if (this.lastGroundSnapshot != null && ((C03PacketPlayer)wrapper).isOnGround()) {
                    object2 = new PlayerLocationSnapshot(this.targetEntity.z(), this.targetEntity.N(), this.targetEntity.h());
                    object = new PlayerLocationSnapshot(((C03PacketPlayer)wrapper).getX(), ((C03PacketPlayer)wrapper).getY(), ((C03PacketPlayer)wrapper).getZ());
                    if (PlayerLocationSnapshot.rayIntersectionDistance(this.lastGroundSnapshot, (PlayerLocationSnapshot)object2) < PlayerLocationSnapshot.rayIntersectionDistance((PlayerLocationSnapshot)object, (PlayerLocationSnapshot)object2) - 0.03) {
                        EventPacketSend eventPacketSend8 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.delaySend(eventPacketSend8);
                        return;
                    }
                }
            }
        } else {
            EventPacketSend eventPacketSend9 = eventPacketSend;
            FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
            fastUsePacketDelaySubModule.delaySend(eventPacketSend9);
            return;
        }
        long baseDelay = ((Double)((FastUseModule)this.getParent()).j.K()).longValue();
        long sendTime = System.currentTimeMillis() + baseDelay;
        long lastGroundTime = System.currentTimeMillis();
        ReadWriteLockHelper readLock = this.lock;
        readLock.lockRead();
        for (Map.Entry<DelayedPacketSendEntry, Long> entry : this.delayedPackets.entrySet()) {
            C03PacketPlayer c03PacketPlayer;
            DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
            Packet packet2 = delayedPacketSendEntry.V().getPacket();
            if (!packet2.isInstance(MappedClasses.qD) || !(c03PacketPlayer = new C03PacketPlayer(packet2)).isOnGround()) continue;
            lastGroundTime = entry.getValue();
        }
        sendTime = Math.min(lastGroundTime + 90L, sendTime);
        ReadWriteLockHelper readUnlock = this.lock;
        readUnlock.unlockRead();
        ReadWriteLockHelper writeLock = this.lock;
        writeLock.lockWrite();
        this.delayedPackets.put(new DelayedPacketSendEntry(this, eventPacketSend), sendTime);
        ReadWriteLockHelper writeUnlock = this.lock;
        writeUnlock.unlockWrite();
        eventPacketSend.setCancelled(true);
    }

    @EventHandler(A=EventPriority.NORMAL)
    public void X(EventPacketSend eventPacketSend) {
        if (this.controlClaim.v$src$Z$1r7ksy2()) {
            this.flushAll();
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (eventPacketSend.isCanceled()) {
            return;
        }
        if (eventPacketSend.wasModified()) {
            return;
        }
        if (Minecraft.thePlayer().isNull() || this.dispatchGuard.R(packet) || packet.isInstance(MappedClasses.VP)) {
            return;
        }
        EventPacketSend eventPacketSend2 = eventPacketSend;
        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
        fastUsePacketDelaySubModule.processPacket(eventPacketSend2);
    }

    @EventHandler
    public void i(EventPreRenderTick eventPreRenderTick) {
        this.targetEntity = null;
        RayTraceResult rayTraceResult = RayTraceUtil.F(10.0, 0.0f, true);
        if (rayTraceResult != null && rayTraceResult.isNotNull()) {
            this.targetEntity = rayTraceResult.getEntity();
        }
        ReadWriteLockHelper writeLock = this.lock;
        writeLock.lockWrite();
        ReadWriteLockHelper readLock = this.lock;
        readLock.lockRead();
        if (!this.delayedPackets.isEmpty()) {
            FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
            fastUsePacketDelaySubModule.flushExpired();
        }
        ReadWriteLockHelper writeUnlock = this.lock;
        writeUnlock.unlockWrite();
        ReadWriteLockHelper readUnlock = this.lock;
        readUnlock.unlockRead();
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void m(EventPacketSend eventPacketSend) {
        C03PacketPlayer c03PacketPlayer;
        Packet packet = eventPacketSend.getPacket();
        if (packet.isInstance(MappedClasses.qD) && (c03PacketPlayer = new C03PacketPlayer(packet)).isOnGround()) {
            this.lastGroundSnapshot = new PlayerLocationSnapshot(c03PacketPlayer.getX(), c03PacketPlayer.getY(), c03PacketPlayer.getZ());
        }
    }

    private boolean releasePending(EventPacketSend eventPacketSend) {
        int n = DelayedPacketSendEntry.G();
        ReadWriteLockHelper writeLock = this.lock;
        writeLock.lockWrite();
        int n2 = n;
        if (n2 != 0) {
            Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.delayedPackets.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
                DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
                this.dispatchGuard.o(delayedPacketSendEntry.V());
                iterator.remove();
                Packet packet = delayedPacketSendEntry.V().getPacket();
            }
            ReadWriteLockHelper writeUnlock = this.lock;
            writeUnlock.unlockWrite();
            return true;
        }
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.delayedPackets.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
            DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
            this.dispatchGuard.o(delayedPacketSendEntry.V());
            iterator.remove();
            Packet packet = delayedPacketSendEntry.V().getPacket();
        } else {
            ReadWriteLockHelper writeUnlock = this.lock;
            writeUnlock.unlockWrite();
        }
        return true;
    }

    public FastUsePacketDelaySubModule(Mod mod, String string) {
        super(mod, string);
        this.controlClaim = SharedModuleControlClaims.L;
        this.addValue(this.U);
    }

    @Override
    public void g() {
        this.flushAll();
    }

    @Override
    public String r() {
        String string = "Dynamic " + ((FastUseModule)this.getParent()).j.c() + "ms";
        return string;
    }

    private void flushExpired() {
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.delayedPackets.entrySet().iterator();
        int n = DelayedPacketSendEntry.I();
        if (n != 0) {
            if (iterator.hasNext()) {
                Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
                long l = System.currentTimeMillis() - entry.getValue();
                iterator.remove();
            }
            return;
        }
        while (iterator.hasNext()) {
            Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
            if (System.currentTimeMillis() < entry.getValue()) break;
            this.dispatchGuard.o(DelayedPacketSendEntry.g(entry.getKey()));
            iterator.remove();
        }
    }
}
