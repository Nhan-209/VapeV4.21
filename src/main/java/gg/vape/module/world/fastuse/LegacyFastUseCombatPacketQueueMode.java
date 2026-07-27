package gg.vape.module.world.fastuse;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.DelayedPacketSendEntry;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventTickBase;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.control.SharedModuleControlClaimPrimary;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.world.FastUseModule;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.SleepUtil;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class LegacyFastUseCombatPacketQueueMode
extends SubModule<FastUseModule> {
    private final Queue<EventPacketSend> r;
    private long O;
    private final PacketDispatchGuard o = PacketDispatchGuard.b;
    private AtomicBoolean Z;
    private EntityLivingBase I;
    private SharedModuleControlClaimPrimary S;

    public LegacyFastUseCombatPacketQueueMode(Mod mod, String string) {
        super(mod, string);
        this.r = new LinkedList<EventPacketSend>();
        this.Z = new AtomicBoolean(false);
        this.S = SharedModuleControlClaims.L;
    }

    @Override
    public String r() {
        String string = "Repel " + ((FastUseModule)this.getParent()).j.c() + "ms";
        if (!this.r.isEmpty()) {
            string = "\u00a7c" + string;
        }
        return string;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.N();
        int n = DelayedPacketSendEntry.G();
        if (n != 0) {
            boolean bl;
            int n2;
            int n3;
            EntityLivingBase entityLivingBase = this.I;
            if (entityLivingBase != null && (n3 = this.I.c$src$I$15a9iwo()) > 0 && (n2 = this.I.c$src$I$15a9iwo()) <= AttackPacketTimingTracker.a.Z() && !(bl = this.r.isEmpty()) && netHandlerPlayClientImpl.isNotNull()) {
                LegacyFastUseCombatPacketQueueMode legacyFastUseCombatPacketQueueMode = this;
                legacyFastUseCombatPacketQueueMode.D();
            }
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                DelayedPacketSendEntry.A(++n);
            }
            return;
        }
        EntityLivingBase entityLivingBase = this.I;
        int n4 = entityLivingBase.c$src$I$15a9iwo();
        int n5 = n4;
        int n6 = n5;
        if (n6 != 0) {
            LegacyFastUseCombatPacketQueueMode legacyFastUseCombatPacketQueueMode = this;
            legacyFastUseCombatPacketQueueMode.D();
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            DelayedPacketSendEntry.A(++n);
        }
    }


    private void D() {
        int n = DelayedPacketSendEntry.I();
        boolean bl = this.r.isEmpty();
        if (n == 0) {
            if (bl) {
                return;
            }
            bl = Thread.currentThread().equals(EventTickBase.S.getOwnerThread());
        }
        if (!bl) {
            return;
        }
        this.Z.set(true);
        this.r.forEach(this::lambda$flushPackets$0);
        this.r.clear();
        this.Z.set(false);
    }

    private void lambda$flushPackets$0(EventPacketSend eventPacketSend) {
        this.o.o(eventPacketSend);
    }

    @EventHandler(A=EventPriority.LOW)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        Entity entity;
        UseEntityPacketBridge useEntityPacketBridge;
        int n = DelayedPacketSendEntry.I();
        if (this.S.v$src$Z$1r7ksy2()) {
            LegacyFastUseCombatPacketQueueMode legacyFastUseCombatPacketQueueMode = this;
            legacyFastUseCombatPacketQueueMode.D();
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (this.o.R(packet)) {
            return;
        }
        while (this.Z.get()) {
            SleepUtil.sleep(10L);
        }
        if (!this.r.isEmpty()) {
            UseEntityPacketBridge useEntityPacketBridge2;
            boolean bl = false;
            if (System.currentTimeMillis() >= this.O) {
                bl = true;
            }
            if (UseEntityPacketBridge.h(packet) && (useEntityPacketBridge2 = new UseEntityPacketBridge(packet)).S()) {
                bl = true;
            }
            if (packet.isInstance(MappedClasses.u7)) {
                bl = true;
            }
            if (!Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
                bl = false;
            }
            if (bl) {
                this.r.add(eventPacketSend);
                eventPacketSend.setCancelled(true);
                LegacyFastUseCombatPacketQueueMode legacyFastUseCombatPacketQueueMode = this;
                legacyFastUseCombatPacketQueueMode.D();
                return;
            }
            this.r.add(eventPacketSend);
            eventPacketSend.setCancelled(true);
            return;
        }
        if (UseEntityPacketBridge.h(packet) && (useEntityPacketBridge = new UseEntityPacketBridge(packet)).S() && (entity = Minecraft.theWorld().V(useEntityPacketBridge.w())).isInstance(MappedClasses.zm)) {
            this.I = new EntityLivingBase(entity);
            long l = ((Double)((FastUseModule)this.getParent()).j.K()).longValue() + (long)ThreadLocalRandom.current().nextInt(100);
            if (this.I.c$src$I$15a9iwo() > 0 && this.I.c$src$I$15a9iwo() <= (int)Math.ceil((double)l / 50.0)) {
                this.r.add(eventPacketSend);
                this.O = System.currentTimeMillis() + l;
                eventPacketSend.setCancelled(true);
            }
        }
        if (this.r.isEmpty()) {
            this.o.J(packet);
        }
    }
}

