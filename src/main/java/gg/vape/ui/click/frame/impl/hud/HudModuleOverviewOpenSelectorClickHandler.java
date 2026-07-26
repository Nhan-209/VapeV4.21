package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleListPanel;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;

class HudModuleOverviewOpenSelectorClickHandler
implements GuiClickListener {
    final HudModuleOverviewHeaderComponent v;

    HudModuleOverviewOpenSelectorClickHandler(HudModuleOverviewHeaderComponent hudModuleOverviewHeaderComponent) {
        this.v = hudModuleOverviewHeaderComponent;
    }

    @Override
    public void P() {
        ClientSettings.g(HudModuleListPanel.class).N$src$V$wrn2a4();
        ClientSettings.T(HudModuleSelectorFrame.class);
        HudModuleSelectorFrame.W_ = false;
        ClientSettings.g(HudModuleOverviewFrame.class).Z(false);
    }
}

