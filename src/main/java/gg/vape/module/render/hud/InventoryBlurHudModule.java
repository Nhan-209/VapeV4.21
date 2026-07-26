package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventGuiOpen;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.render.ShaderGroupRenderStateManager;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class InventoryBlurHudModule
extends HudModule {
    public InventoryBlurHudModule() {
        super("Inventory Blur", HudModuleGroup.T, "inventory_blur");
        this.setSuffix("Blurs the background while in an inventory");
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void d(EventGuiOpen eventGuiOpen) {
        if (eventGuiOpen.getGuiScreen().isNull() || eventGuiOpen.getGuiScreen().isInstance(MappedClasses.qo) || eventGuiOpen.getGuiScreen().isInstance(MappedClasses.Fl) || MappedClasses.zL != null && eventGuiOpen.getGuiScreen().isInstance(MappedClasses.zL)) {
            ShaderGroupRenderStateManager.Q().f();
            return;
        }
        ShaderGroupRenderStateManager.Q().K();
    }
}

