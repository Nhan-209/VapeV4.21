package gg.vape.module.world.fastuse;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.world.FastUseModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.SleepUtil;
import gg.vape.utils.network.PacketDispatchTask;
import gg.vape.utils.network.TimedPacketDispatchTask;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FastUseDelayedPacketMode
extends SubModule<FastUseModule> {
    private final Queue<TimedPacketDispatchTask> pendingPackets = new ConcurrentLinkedQueue<TimedPacketDispatchTask>();
    private boolean flushing = false;

    @Override
    public void g() {
        this.flushing = true;
        this.flush(true);
    }

    private void flush(boolean force) {
        TimedPacketDispatchTask timedPacketDispatchTask;
        while (this.pendingPackets.peek() != null && (timedPacketDispatchTask = this.pendingPackets.peek()) != null && (timedPacketDispatchTask.j(((Double)((FastUseModule)this.getParent()).j.K()).longValue()) || force)) {
            timedPacketDispatchTask = this.pendingPackets.poll();
            timedPacketDispatchTask.z().t();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public FastUseDelayedPacketMode(Mod mod, String string) {
        super(mod, string);
    }

    @Override
    public String r() {
        String string = "Latency " + ((FastUseModule)this.getParent()).j.c() + "ms";
        return string;
    }

    @Override
    public void onDisable() {
        this.flushing = false;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.flush(false);
            return;
        }
        if (eventPacketSend.isCanceled()) {
            this.flush(true);
            return;
        }
        if (this.flushing) {
            while (this.flushing) {
                SleepUtil.sleep(50L);
            }
            return;
        }
        this.flush(false);
        this.pendingPackets.add(new TimedPacketDispatchTask(new PacketDispatchTask(eventPacketSend.getPacket(), true, eventPacketSend.getNetworkManager()), null));
        eventPacketSend.setCancelled(true);
    }
}

