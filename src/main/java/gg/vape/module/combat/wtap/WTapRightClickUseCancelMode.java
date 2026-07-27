package gg.vape.module.combat.wtap;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.HitSelect;
import gg.vape.rotation.RotationManager;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;

public class WTapRightClickUseCancelMode
extends SubModule<HitSelect> {
    private int cooldownTicks = -1;
    private boolean cancelActive = false;
    private static final long c;

    static {
        c = -5882419382201614328L;
    }

    public WTapRightClickUseCancelMode(Mod mod, String string) {
        super(mod, string);
    }

    @EventHandler
    public void onClickMouse(EventClickMouse eventClickMouse) {
        if (this.l()) {
            eventClickMouse.setCancelled(true);
        }
    }


    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int n;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.cancelActive = false;
            return;
        }
        if (this.cooldownTicks > 0) {
            --this.cooldownTicks;
            this.cancelActive = false;
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        EntityLivingBase entityLivingBase = rayTraceResult.isNotNull() && rayTraceResult.getEntity().isInstance(MappedClasses.zm) ? new EntityLivingBase(rayTraceResult.getEntity()) : new EntityLivingBase(null);
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (entityLivingBase.isNull() && guiScreen.isNull()) {
            this.cancelActive = false;
            return;
        }
        if (entityLivingBase.isNotNull() && entityPlayerSP.b$src$Z$fqlxe4() && (n = entityLivingBase.V$src$I$fk0dv5()) > 12) {
            if (!((HitSelect)this.getParent()).a$src$Z$1npvv6h()) {
                this.cooldownTicks = (int)c;
            }
            this.cancelActive = true;
            return;
        }
        this.cancelActive = false;
    }

    public boolean l() {
        return this.cancelActive;
    }
}

