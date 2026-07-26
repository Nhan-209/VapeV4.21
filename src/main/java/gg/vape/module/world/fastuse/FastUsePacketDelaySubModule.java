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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private final Map<DelayedPacketSendEntry, Long> o = new LinkedHashMap<DelayedPacketSendEntry, Long>();
    private SharedModuleControlClaimPrimary c;
    private final PacketDispatchGuard C = PacketDispatchGuard.b;
    private long t;
    private Entity L;
    private PlayerLocationSnapshot v;
    private long F;
    private long b;
    private final ReadWriteLockHelper O = new ReadWriteLockHelper();

    private void lambda$flushPackets$0(DelayedPacketSendEntry delayedPacketSendEntry) {
        this.C.o(DelayedPacketSendEntry.g(delayedPacketSendEntry));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void w$src$V$11mudj() {
        ReadWriteLockHelper readWriteLockHelper = this.O;
        readWriteLockHelper.lockWrite();
        ReadWriteLockHelper readWriteLockHelper2 = this.O;
        readWriteLockHelper2.lockRead();
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.o.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
            this.C.o(DelayedPacketSendEntry.g(entry.getKey()));
            iterator.remove();
        }
        ReadWriteLockHelper readWriteLockHelper3 = this.O;
        readWriteLockHelper3.unlockWrite();
        ReadWriteLockHelper readWriteLockHelper4 = this.O;
        readWriteLockHelper4.unlockRead();
    }

    private void v(EventPacketSend eventPacketSend) {
        int n = DelayedPacketSendEntry.G();
        ReadWriteLockHelper readWriteLockHelper = this.O;
        readWriteLockHelper.lockWrite();
        int n2 = n;
        ReadWriteLockHelper readWriteLockHelper2 = this.O;
        readWriteLockHelper2.lockRead();
        long l = ((Double)this.U.K()).longValue();
        if (l > 0L) {
            long l2 = 0L;
            for (Map.Entry<DelayedPacketSendEntry, Long> entry : this.o.entrySet()) {
                DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
                l2 += l;
                if (delayedPacketSendEntry.E()) continue;
                entry.setValue(System.currentTimeMillis() + l2);
                delayedPacketSendEntry.d(true);
            }
            if (eventPacketSend != null) {
                this.o.put(new DelayedPacketSendEntry(this, eventPacketSend), System.currentTimeMillis() + l2 + l);
                eventPacketSend.setCancelled(true);
            }
        } else {
            this.o.keySet().forEach(this::lambda$flushPackets$0);
            this.o.clear();
        }
        ReadWriteLockHelper readWriteLockHelper3 = this.O;
        readWriteLockHelper3.unlockRead();
        ReadWriteLockHelper readWriteLockHelper4 = this.O;
        readWriteLockHelper4.unlockWrite();
    }

    private void g(EventPacketSend eventPacketSend) {
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
                        if (System.currentTimeMillis() - this.t > 250L) {
                            FastUsePacketDelaySubModule fastUsePacketDelaySubModule2 = this;
                            EventPacketSend eventPacketSend3 = eventPacketSend;
                            if (fastUsePacketDelaySubModule2.l(eventPacketSend3)) {
                                return;
                            }
                        } else {
                            this.t = System.currentTimeMillis();
                        }
                    } else if (entityLivingBase.c$src$I$15a9iwo() <= ((AttackPacketTimingTracker)object).Z() + 1 && (fastUsePacketDelaySubModule = this).l(eventPacketSend2 = eventPacketSend)) {
                        return;
                    }
                }
            }
        } else {
            if (packet.isInstance(MappedClasses.F9) || packet.isInstance(MappedClasses.u7) || packet.isInstance(MappedClasses.DN) || packet.isInstance(MappedClasses.q6)) {
                EventPacketSend eventPacketSend4 = eventPacketSend;
                FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                fastUsePacketDelaySubModule.v(eventPacketSend4);
                return;
            }
            if (packet.isInstance(MappedClasses.YB)) {
                wrapper = new CPacketPlayerBlockPlacement(packet);
                object2 = ((CPacketPlayerBlockPlacement)wrapper).Q$src$Lgg_vape_wrapper_impl_ItemStack_$16phjq1();
                if (((Wrapper)object2).isNotNull()) {
                    if (!ItemStackScoreUtil.h(((ItemStack)object2).getItem())) {
                        EventPacketSend eventPacketSend5 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.v(eventPacketSend5);
                        this.b = System.currentTimeMillis() + 500L;
                        return;
                    }
                    if (System.currentTimeMillis() - this.F >= 500L) {
                        EventPacketSend eventPacketSend6 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.v(eventPacketSend6);
                        this.F = System.currentTimeMillis();
                        return;
                    }
                }
            } else if (this.b > System.currentTimeMillis() || Minecraft.thePlayer().l$src$Z$1io4duf() && !Minecraft.thePlayer().o$src$Z$1iprrmi() || !Minecraft.thePlayer().b$src$Z$fqlxe4() && Minecraft.thePlayer().M$src$F$ff28gb() > 3.0f) {
                EventPacketSend eventPacketSend7 = eventPacketSend;
                FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                fastUsePacketDelaySubModule.v(eventPacketSend7);
                return;
            }
        }
        if (this.L != null && this.L.isNotNull() && this.L.isInstance(MappedClasses.zm)) {
            if (packet.isInstance(MappedClasses.qD)) {
                wrapper = new C03PacketPlayer(packet);
                if (this.v != null && ((C03PacketPlayer)wrapper).isOnGround()) {
                    object2 = new PlayerLocationSnapshot(this.L.z(), this.L.N(), this.L.h());
                    object = new PlayerLocationSnapshot(((C03PacketPlayer)wrapper).getX(), ((C03PacketPlayer)wrapper).getY(), ((C03PacketPlayer)wrapper).getZ());
                    if (PlayerLocationSnapshot.rayIntersectionDistance(this.v, (PlayerLocationSnapshot)object2) < PlayerLocationSnapshot.rayIntersectionDistance((PlayerLocationSnapshot)object, (PlayerLocationSnapshot)object2) - 0.03) {
                        EventPacketSend eventPacketSend8 = eventPacketSend;
                        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
                        fastUsePacketDelaySubModule.v(eventPacketSend8);
                        return;
                    }
                }
            }
        } else {
            EventPacketSend eventPacketSend9 = eventPacketSend;
            FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
            fastUsePacketDelaySubModule.v(eventPacketSend9);
            return;
        }
        long l = ((Double)((FastUseModule)this.getParent()).j.K()).longValue();
        long l2 = System.currentTimeMillis() + l;
        long l3 = System.currentTimeMillis();
        ReadWriteLockHelper readWriteLockHelper = this.O;
        readWriteLockHelper.lockRead();
        for (Map.Entry<DelayedPacketSendEntry, Long> entry : this.o.entrySet()) {
            C03PacketPlayer c03PacketPlayer;
            DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
            Packet packet2 = delayedPacketSendEntry.V().getPacket();
            if (!packet2.isInstance(MappedClasses.qD) || !(c03PacketPlayer = new C03PacketPlayer(packet2)).isOnGround()) continue;
            l3 = entry.getValue();
        }
        l2 = Math.min(l3 + 90L, l2);
        ReadWriteLockHelper readWriteLockHelper2 = this.O;
        readWriteLockHelper2.unlockRead();
        ReadWriteLockHelper readWriteLockHelper3 = this.O;
        readWriteLockHelper3.lockWrite();
        this.o.put(new DelayedPacketSendEntry(this, eventPacketSend), l2);
        ReadWriteLockHelper readWriteLockHelper4 = this.O;
        readWriteLockHelper4.unlockWrite();
        eventPacketSend.setCancelled(true);
    }

    @EventHandler(A=EventPriority.NORMAL)
    public void X(EventPacketSend eventPacketSend) {
        if (this.c.v$src$Z$1r7ksy2()) {
            this.w$src$V$11mudj();
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (eventPacketSend.isCanceled()) {
            return;
        }
        if (eventPacketSend.wasModified()) {
            return;
        }
        if (Minecraft.thePlayer().isNull() || this.C.R(packet) || packet.isInstance(MappedClasses.VP)) {
            return;
        }
        EventPacketSend eventPacketSend2 = eventPacketSend;
        FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
        fastUsePacketDelaySubModule.g(eventPacketSend2);
    }

    @EventHandler
    public void i(EventPreRenderTick eventPreRenderTick) {
        this.L = null;
        RayTraceResult rayTraceResult = RayTraceUtil.F(10.0, 0.0f, true);
        if (rayTraceResult != null && rayTraceResult.isNotNull()) {
            this.L = rayTraceResult.getEntity();
        }
        ReadWriteLockHelper readWriteLockHelper = this.O;
        readWriteLockHelper.lockWrite();
        ReadWriteLockHelper readWriteLockHelper2 = this.O;
        readWriteLockHelper2.lockRead();
        if (!this.o.isEmpty()) {
            FastUsePacketDelaySubModule fastUsePacketDelaySubModule = this;
            fastUsePacketDelaySubModule.Q$src$V$gqntt();
        }
        ReadWriteLockHelper readWriteLockHelper3 = this.O;
        readWriteLockHelper3.unlockWrite();
        ReadWriteLockHelper readWriteLockHelper4 = this.O;
        readWriteLockHelper4.unlockRead();
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void m(EventPacketSend eventPacketSend) {
        C03PacketPlayer c03PacketPlayer;
        Packet packet = eventPacketSend.getPacket();
        if (packet.isInstance(MappedClasses.qD) && (c03PacketPlayer = new C03PacketPlayer(packet)).isOnGround()) {
            this.v = new PlayerLocationSnapshot(c03PacketPlayer.getX(), c03PacketPlayer.getY(), c03PacketPlayer.getZ());
        }
    }

    private boolean l(EventPacketSend eventPacketSend) {
        int n = DelayedPacketSendEntry.G();
        ReadWriteLockHelper readWriteLockHelper = this.O;
        readWriteLockHelper.lockWrite();
        int n2 = n;
        if (n2 != 0) {
            Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.o.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
                DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
                this.C.o(delayedPacketSendEntry.V());
                iterator.remove();
                Packet packet = delayedPacketSendEntry.V().getPacket();
            }
            ReadWriteLockHelper readWriteLockHelper2 = this.O;
            readWriteLockHelper2.unlockWrite();
            return true;
        }
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.o.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<DelayedPacketSendEntry, Long> entry = iterator.next();
            DelayedPacketSendEntry delayedPacketSendEntry = entry.getKey();
            this.C.o(delayedPacketSendEntry.V());
            iterator.remove();
            Packet packet = delayedPacketSendEntry.V().getPacket();
        } else {
            ReadWriteLockHelper readWriteLockHelper3 = this.O;
            readWriteLockHelper3.unlockWrite();
        }
        return true;
    }

    public FastUsePacketDelaySubModule(Mod mod, String string) {
        super(mod, string);
        this.c = SharedModuleControlClaims.L;
        this.addValue(this.U);
    }

    @Override
    public void g() {
        this.w$src$V$11mudj();
    }

    @Override
    public String r() {
        String string = "Dynamic " + ((FastUseModule)this.getParent()).j.c() + "ms";
        return string;
    }

    private void Q$src$V$gqntt() {
        Iterator<Map.Entry<DelayedPacketSendEntry, Long>> iterator = this.o.entrySet().iterator();
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
            this.C.o(DelayedPacketSendEntry.g(entry.getKey()));
            iterator.remove();
        }
    }
}
