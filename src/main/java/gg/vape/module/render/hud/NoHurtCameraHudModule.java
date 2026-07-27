package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class NoHurtCameraHudModule
extends HudModule {
    private int savedHurtTime;

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void B(EventPreRenderTick eventPreRenderTick) {
        if (eventPreRenderTick.getThePlayer().isNull()) {
            return;
        }
        if (eventPreRenderTick.getThePlayer().c$src$I$15a9iwo() > 0) {
            this.savedHurtTime = eventPreRenderTick.getThePlayer().c$src$I$15a9iwo();
            eventPreRenderTick.getThePlayer().I(0);
        }
    }

    public NoHurtCameraHudModule() {
        super("NoHurtCam", HudModuleGroup.T, "legitmodeicon");
        this.setSuffix("Disables the hurt camera shaking effect");
    }

    @EventHandler
    public void E(EventPostRenderTick eventPostRenderTick) {
        if (eventPostRenderTick.getThePlayer().isNull()) {
            return;
        }
        if (this.savedHurtTime > 0) {
            eventPostRenderTick.getThePlayer().I(this.savedHurtTime);
            this.savedHurtTime = 0;
        }
    }
}

