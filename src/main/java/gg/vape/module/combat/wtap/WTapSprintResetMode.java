package gg.vape.module.combat.wtap;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.event.impl.EventSendClickBlockToController;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.combat.WTap;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.SPacketEntityVelocity;

public class WTapSprintResetMode
extends SubModule<WTap> {
    private final ModeValue t;
    private final ModeOption S;
    private int L = 0;
    private boolean s;
    private static final long r = 988881679777005575L;
    private final ModeOption O = new ModeOption("KB reduction");
    private long D;
    private boolean V = true;

    public WTapSprintResetMode(Mod mod, String string) {
        super(mod, string);
        this.S = new ModeOption("Critical hits");
        this.t = ModeValue.create((Object)this, "Preference", this.O, this.O, this.S);
        this.t.Z$src$Lgg_vape_value_Value_$16i62fx("KB reduction: Favors knockback reduction\nCritical hits: Favors critical hit frequency");
        this.addValue(this.t);
    }

    @EventHandler
    public void U(EventPlayerUseItem eventPlayerUseItem) {
        if (this.V) {
            eventPlayerUseItem.setCancelled(true);
        }
    }

    private boolean g(Entity entity) {
        double d = entity.z() - Minecraft.thePlayer().z();
        double d2 = entity.h() - Minecraft.thePlayer().h();
        String[] stringArray = BooleanValue.H();
        return d < 0.0 == Minecraft.thePlayer().t() < 0.0 && d2 < 0.0 == Minecraft.thePlayer().T() < 0.0;
    }

    @EventHandler
    public void T(EventRightClickMouse eventRightClickMouse) {
        if (this.V) {
            eventRightClickMouse.setCancelled(true);
        }
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        SPacketEntityVelocity sPacketEntityVelocity;
        if (eventPacketReceive.getPacketInstance() == null) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        boolean bl = false;
        if (packet.isInstance(MappedClasses.qe)) {
            bl = true;
        } else if (packet.isInstance(MappedClasses.YX) && (sPacketEntityVelocity = new SPacketEntityVelocity(packet)).getEntityId() == Minecraft.thePlayer().S()) {
            bl = true;
        }
        if (bl) {
            this.L = (int)r;
        }
    }

    private boolean S$src$Z$s2br6w() {
        return this.t.K() == this.O;
    }

    @EventHandler
    public void d(EventClickMouse eventClickMouse) {
        RayTraceResult rayTraceResult;
        String[] stringArray = BooleanValue.H();
        if (!((WTap)this.getParent()).a$src$Z$1npvv6h()) {
            return;
        }
        boolean bl = false;
        if (this.L > 0) {
            --this.L;
            if (eventClickMouse.getThePlayer().b$src$Z$fqlxe4()) {
                this.L = 0;
            }
            if (this.S$src$Z$s2br6w()) {
                return;
            }
            if (eventClickMouse.getThePlayer().q() > 0.0) {
                bl = true;
            } else if (!eventClickMouse.getThePlayer().b$src$Z$fqlxe4()) {
                return;
            }
        }
        if ((rayTraceResult = RotationManager.b.n()).isNotNull() && rayTraceResult.getEntity().isInstance(MappedClasses.zm)) {
            WTapSprintResetMode wTapSprintResetMode = this;
            EntityLivingBase entityLivingBase = new EntityLivingBase(rayTraceResult.getEntity());
            EntityLivingBase entityLivingBase2 = entityLivingBase;
            if (!wTapSprintResetMode.g(entityLivingBase2)) {
                return;
            }
            if (!bl) {
                AttackPacketTimingTracker attackPacketTimingTracker = AttackPacketTimingTracker.a;
                int n = attackPacketTimingTracker.Z();
                int n2 = attackPacketTimingTracker.Z() + 1;
                if (entityLivingBase.c$src$I$15a9iwo() <= n) {
                    AttackPacketTimingTracker attackPacketTimingTracker2 = attackPacketTimingTracker;
                    if (System.currentTimeMillis() - this.D >= attackPacketTimingTracker2.Y() * 2L) {
                        this.s = true;
                        return;
                    }
                }
                if (entityLivingBase.c$src$I$15a9iwo() > n && entityLivingBase.c$src$I$15a9iwo() <= n2) {
                    return;
                }
            }
            eventClickMouse.setCancelled(true);
            this.V = true;
            EntityLivingBase entityLivingBase3 = entityLivingBase;
            ClientSettings.D(entityLivingBase3);
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.s) {
            this.D = System.currentTimeMillis();
            this.s = false;
        }
        this.V = false;
    }

    @EventHandler
    public void T(EventSendClickBlockToController eventSendClickBlockToController) {
        if (this.V) {
            eventSendClickBlockToController.setCancelled(true);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

