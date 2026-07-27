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
    private final ModeValue preference;
    private final ModeOption criticalHitsOption;
    private int velocityTicks = 0;
    private boolean pendingTimestampUpdate;
    private static final long VELOCITY_RESET_TICKS = 988881679777005575L;
    private final ModeOption kbReductionOption = new ModeOption("KB reduction");
    private long lastResetTime;
    private boolean cancelUse = true;

    public WTapSprintResetMode(Mod mod, String string) {
        super(mod, string);
        this.criticalHitsOption = new ModeOption("Critical hits");
        this.preference = ModeValue.create((Object)this, "Preference", this.kbReductionOption, this.kbReductionOption, this.criticalHitsOption);
        this.preference.Z$src$Lgg_vape_value_Value_$16i62fx("KB reduction: Favors knockback reduction\nCritical hits: Favors critical hit frequency");
        this.addValue(this.preference);
    }

    @EventHandler
    public void onPlayerUseItem(EventPlayerUseItem eventPlayerUseItem) {
        if (this.cancelUse) {
            eventPlayerUseItem.setCancelled(true);
        }
    }

    private boolean isMovingTowardTarget(Entity entity) {
        double d = entity.z() - Minecraft.thePlayer().z();
        double d2 = entity.h() - Minecraft.thePlayer().h();
        String[] stringArray = BooleanValue.H();
        return d < 0.0 == Minecraft.thePlayer().t() < 0.0 && d2 < 0.0 == Minecraft.thePlayer().T() < 0.0;
    }

    @EventHandler
    public void onRightClickMouse(EventRightClickMouse eventRightClickMouse) {
        if (this.cancelUse) {
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
            this.velocityTicks = (int)VELOCITY_RESET_TICKS;
        }
    }

    private boolean S$src$Z$s2br6w() {
        return this.preference.K() == this.kbReductionOption;
    }

    @EventHandler
    public void onClickMouse(EventClickMouse eventClickMouse) {
        RayTraceResult rayTraceResult;
        String[] stringArray = BooleanValue.H();
        if (!((WTap)this.getParent()).a$src$Z$1npvv6h()) {
            return;
        }
        boolean bl = false;
        if (this.velocityTicks > 0) {
            --this.velocityTicks;
            if (eventClickMouse.getThePlayer().b$src$Z$fqlxe4()) {
                this.velocityTicks = 0;
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
            if (!wTapSprintResetMode.isMovingTowardTarget(entityLivingBase2)) {
                return;
            }
            if (!bl) {
                AttackPacketTimingTracker attackPacketTimingTracker = AttackPacketTimingTracker.a;
                int n = attackPacketTimingTracker.Z();
                int n2 = attackPacketTimingTracker.Z() + 1;
                if (entityLivingBase.c$src$I$15a9iwo() <= n) {
                    AttackPacketTimingTracker attackPacketTimingTracker2 = attackPacketTimingTracker;
                    if (System.currentTimeMillis() - this.lastResetTime >= attackPacketTimingTracker2.Y() * 2L) {
                        this.pendingTimestampUpdate = true;
                        return;
                    }
                }
                if (entityLivingBase.c$src$I$15a9iwo() > n && entityLivingBase.c$src$I$15a9iwo() <= n2) {
                    return;
                }
            }
            eventClickMouse.setCancelled(true);
            this.cancelUse = true;
            EntityLivingBase entityLivingBase3 = entityLivingBase;
            ClientSettings.D(entityLivingBase3);
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.pendingTimestampUpdate) {
            this.lastResetTime = System.currentTimeMillis();
            this.pendingTimestampUpdate = false;
        }
        this.cancelUse = false;
    }

    @EventHandler
    public void onSendClickBlockToController(EventSendClickBlockToController eventSendClickBlockToController) {
        if (this.cancelUse) {
            eventSendClickBlockToController.setCancelled(true);
        }
    }

}

