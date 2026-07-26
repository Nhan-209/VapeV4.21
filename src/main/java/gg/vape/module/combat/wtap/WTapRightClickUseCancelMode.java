package gg.vape.module.combat.wtap;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.WTap;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import java.lang.invoke.MethodHandles;

public class WTapRightClickUseCancelMode
extends SubModule<WTap> {
    private int V = -1;
    private boolean D = false;
    private static final long c;
    private static final long b;

    static {
        b = ZkmLongKeyState.a(-502233687818883432L, 3668763834773085192L, MethodHandles.lookup().lookupClass()).a(129915197180799L);
        long l = b ^ 0x62B359CBE5C6L;
        c = -5882419382201614328L;
    }

    public WTapRightClickUseCancelMode(Mod mod, String string) {
        super(mod, string);
    }

    @EventHandler
    public void l(EventClickMouse eventClickMouse) {
        if (this.l()) {
            eventClickMouse.setCancelled(true);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int n;
        long l = b ^ 0x6DB7702F0208L;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.D = false;
            return;
        }
        if (this.V > 0) {
            --this.V;
            this.D = false;
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        EntityLivingBase entityLivingBase = rayTraceResult.isNotNull() && rayTraceResult.getEntity().isInstance(MappedClasses.zm) ? new EntityLivingBase(rayTraceResult.getEntity()) : new EntityLivingBase(null);
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (entityLivingBase.isNull() && guiScreen.isNull()) {
            this.D = false;
            return;
        }
        if (entityLivingBase.isNotNull() && entityPlayerSP.b$src$Z$fqlxe4() && (n = entityLivingBase.V$src$I$fk0dv5()) > 12) {
            if (!((WTap)this.getParent()).a$src$Z$1npvv6h()) {
                this.V = (int)c;
            }
            this.D = true;
            return;
        }
        this.D = false;
    }

    public boolean l() {
        return this.D;
    }
}

