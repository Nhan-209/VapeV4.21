package gg.vape.module.render.animations;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventTickBase;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.KeyBindingInputState;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.control.SharedModuleControlClaimPrimary;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Animations;
import gg.vape.module.render.animations.AnimationsMode;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.CPacketPlayerDigging;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.NetworkManager;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PlayerDiggingAction;
import java.util.LinkedList;
import java.util.Queue;

public class LegacyBlockingPacketBufferedAnimationsMode
extends AnimationsMode {
    private final Queue<EventPacketSend> O;
    private int S;
    private SharedModuleControlClaimPrimary o;
    private boolean c;
    private final TimerUtil A;
    private boolean V;
    private final RandomValue Z;
    private boolean C;
    private final PacketDispatchGuard F = PacketDispatchGuard.b;

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int[] nArray = ClientSettings.A();
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return;
        }
        if (this.n$src$Lgg_vape_wrapper_impl_EntityLivingBase_$rbz152() != null) {
            boolean bl;
            boolean bl2 = bl = this.p();
            if (((Animations)this.getParent()).n$src$Z$uk21qf() && !gg.vape.config.ClientSettings.V()) {
                bl = false;
            }
            if (eventPreTick.getThePlayer().c$src$I$15a9iwo() > AttackPacketTimingTracker.a.Z() + 1) {
                boolean bl3 = false;
                LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                legacyBlockingPacketBufferedAnimationsMode.V(bl3);
                this.V = false;
                this.c = false;
                return;
            }
            if (bl) {
                if (this.C && this.A.hasTimeElapsed(this.S - 50)) {
                    boolean bl4 = true;
                    LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                    legacyBlockingPacketBufferedAnimationsMode.V(bl4);
                    return;
                }
                if (!this.c) {
                    if (!this.V) {
                        boolean bl5 = true;
                        LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                        legacyBlockingPacketBufferedAnimationsMode.V(bl5);
                    } else {
                        boolean bl6 = false;
                        LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                        legacyBlockingPacketBufferedAnimationsMode.V(bl6);
                        this.c = true;
                    }
                } else if (!this.V && !eventPreTick.getThePlayer().o$src$Z$1iprrmi()) {
                    this.c = false;
                }
            } else if (this.V) {
                boolean bl7 = false;
                LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                legacyBlockingPacketBufferedAnimationsMode.V(bl7);
                this.c = false;
            }
            return;
        }
        boolean bl = false;
        if (this.p() && ((Animations)this.getParent()).n$src$Z$uk21qf() && ((Animations)this.getParent()).Z.L().booleanValue() && !this.C && gg.vape.config.ClientSettings.V() && Minecraft.thePlayer().o$src$Z$1iprrmi()) {
            KeyBindingInputState.f();
            this.V = false;
            this.c = false;
            return;
        }
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !gg.vape.config.ClientSettings.V()) {
            bl = false;
        }
        if (eventPreTick.getThePlayer().c$src$I$15a9iwo() > AttackPacketTimingTracker.a.Z() + 1) {
            boolean bl8 = false;
            LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
            legacyBlockingPacketBufferedAnimationsMode.V(bl8);
            this.V = false;
            this.c = false;
            return;
        }
        if (this.V) {
            boolean bl9 = false;
            LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
            legacyBlockingPacketBufferedAnimationsMode.V(bl9);
            this.c = false;
        }
    }

    @Override
    public boolean M() {
        return this.V;
    }

    public void V(boolean bl) {
        int[] nArray = ClientSettings.A();
        if (this.V != bl) {
            this.V = bl;
            if (bl) {
                KeyBindingInputState.L();
            } else {
                KeyBindingInputState.f();
            }
        }
    }

    private boolean e(Packet packet) {
        int[] nArray = ClientSettings.A();
        if (packet.isInstance(MappedClasses.DN)) {
            CPacketPlayerDigging cPacketPlayerDigging = new CPacketPlayerDigging(packet);
            boolean bl = ForgeVersion.MC_1_8_9.d() ? cPacketPlayerDigging.Z().equals(PlayerDiggingAction.s()) : cPacketPlayerDigging.I() == 5;
            return bl;
        }
        return false;
    }

    private void d() {
        if (!Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
            return;
        }
        NetHandlerPlayClientImpl netHandlerPlayClientImpl = Minecraft.thePlayer().sendQueue();
        NetworkManager networkManager = netHandlerPlayClientImpl.a();
        PacketDispatchGuard.b(networkManager, this::lambda$flushPackets$0);
    }

    @Override
    public String r() {
        String string = "Lag " + this.Z.c() + "ms";
        if (this.C) {
            string = "\u00a7c" + string;
        }
        return string;
    }


    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketSend(EventPacketSend eventPacketSend) {
        int[] nArray = ClientSettings.A();
        if (eventPacketSend.isCanceled()) {
            return;
        }
        if (eventPacketSend.wasModified()) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (this.F.R(packet)) {
            return;
        }
        if (Minecraft.thePlayer().isNull() || packet.isInstance(MappedClasses.VP)) {
            return;
        }
        if (!Thread.currentThread().equals(EventTickBase.S.getOwnerThread())) {
            return;
        }
        if (this.C) {
            boolean bl;
            boolean bl2 = bl = this.n$src$Lgg_vape_wrapper_impl_EntityLivingBase_$rbz152() == null || !this.p();
            if (bl || this.A.hasTimeElapsed(this.S)) {
                LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
                legacyBlockingPacketBufferedAnimationsMode.d();
                this.C = false;
                this.c = false;
            } else {
                this.O.add(eventPacketSend);
                eventPacketSend.setCancelled(true);
            }
            return;
        }
        LegacyBlockingPacketBufferedAnimationsMode legacyBlockingPacketBufferedAnimationsMode = this;
        Packet packet2 = packet;
        if (legacyBlockingPacketBufferedAnimationsMode.e(packet2)) {
            this.o.c();
            this.O.add(eventPacketSend);
            eventPacketSend.setCancelled(true);
            this.C = true;
            this.S = (int)this.Z.B();
            this.A.reset();
        }
        if (!this.C && this.O.isEmpty()) {
            this.o.Q();
            this.F.J(packet);
        }
    }

    public EntityLivingBase n$src$Lgg_vape_wrapper_impl_EntityLivingBase_$rbz152() {
        return ((Animations)this.getParent()).C((Double)((Animations)this.getParent()).v.K(), (Double)((Animations)this.getParent()).I.K());
    }

    private boolean p() {
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return Minecraft.thePlayer().getHeldItemHand().isNotNull() && ItemStackScoreUtil.h(Minecraft.thePlayer().getHeldItemHand().getItem());
    }

    private void lambda$flushPackets$0() {
        for (EventPacketSend eventPacketSend : this.O) {
            boolean bl = this.F.o(eventPacketSend);
        }
        this.O.clear();
    }

    public LegacyBlockingPacketBufferedAnimationsMode(Mod mod, String string) {
        super(mod, string);
        this.O = new LinkedList<EventPacketSend>();
        this.Z = RandomValue.create(this, "Delay", "#", "", 0.0, 50.0, 100.0, 500.0);
        this.A = new TimerUtil();
        this.o = SharedModuleControlClaims.L;
        this.addValue(this.Z);
        this.o.l(this, 5);
    }
}

