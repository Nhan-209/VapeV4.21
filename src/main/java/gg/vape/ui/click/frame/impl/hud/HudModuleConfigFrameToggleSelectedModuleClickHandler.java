package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;

class HudModuleConfigFrameToggleSelectedModuleClickHandler
implements GuiClickListener {
    final HudModuleConfigFrameHeaderComponent I;
    final HudModuleConfigFrame m;

    @Override
    public void P() {
        HudModule hudModule = this.m.F$src$Lgg_vape_module_render_hud_HudModule_$vjtm3x();
        hudModule.M(!hudModule.f$src$Z$148d2ux());
        if (ClientSettings.g(HudModuleSelectorFrame.class).S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() == HudModuleGroup.r) {
            ClientSettings.g(HudModuleSelectorFrame.class).a$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleListPa$qfwoz4().N$src$V$wrn2a4();
        }
    }

    HudModuleConfigFrameToggleSelectedModuleClickHandler(HudModuleConfigFrameHeaderComponent hudModuleConfigFrameHeaderComponent, HudModuleConfigFrame hudModuleConfigFrame) {
        this.I = hudModuleConfigFrameHeaderComponent;
        this.m = hudModuleConfigFrame;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
