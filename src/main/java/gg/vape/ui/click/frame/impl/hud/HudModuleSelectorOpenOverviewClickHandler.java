package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorHeaderComponent;

class HudModuleSelectorOpenOverviewClickHandler
implements GuiClickListener {
    final HudModuleSelectorHeaderComponent I;

    @Override
    public void P() {
        HudModuleOverviewFrame hudModuleOverviewFrame = ClientSettings.g(HudModuleOverviewFrame.class);
        HudModuleSelectorFrame hudModuleSelectorFrame = ClientSettings.g(HudModuleSelectorFrame.class);
        if (hudModuleOverviewFrame == null || hudModuleSelectorFrame == null) {
            return;
        }
        hudModuleOverviewFrame.Z(true);
        hudModuleSelectorFrame.Z(false);
        hudModuleOverviewFrame.l$src$Z$193vdc5();
        HudModuleSelectorFrame.W_ = true;
        ClientSettings.g(HudModuleOverviewFrame.class).s$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleOvervi$1xo3dwo().w$src$V$1pyk8v9();
    }

    HudModuleSelectorOpenOverviewClickHandler(HudModuleSelectorHeaderComponent hudModuleSelectorHeaderComponent) {
        this.I = hudModuleSelectorHeaderComponent;
    }

}

