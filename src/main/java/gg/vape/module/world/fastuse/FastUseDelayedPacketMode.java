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
    private final Queue<TimedPacketDispatchTask> C = new ConcurrentLinkedQueue<TimedPacketDispatchTask>();
    private boolean c = false;

    @Override
    public void g() {
        this.c = true;
        this.f(true);
    }

    private void f(boolean bl) {
        TimedPacketDispatchTask timedPacketDispatchTask;
        while (this.C.peek() != null && (timedPacketDispatchTask = this.C.peek()) != null && (timedPacketDispatchTask.j(((Double)((FastUseModule)this.getParent()).j.K()).longValue()) || bl)) {
            timedPacketDispatchTask = this.C.poll();
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
        this.c = false;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            this.f(false);
            return;
        }
        if (eventPacketSend.isCanceled()) {
            this.f(true);
            return;
        }
        if (this.c) {
            while (this.c) {
                SleepUtil.sleep(50L);
            }
            return;
        }
        this.f(false);
        this.C.add(new TimedPacketDispatchTask(new PacketDispatchTask(eventPacketSend.getPacket(), true, eventPacketSend.getNetworkManager()), null));
        eventPacketSend.setCancelled(true);
    }
}

