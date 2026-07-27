package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorHeaderComponent;

class HudModuleSelectorOpenConfigFrameClickHandler
implements GuiClickListener {
    final HudModuleSelectorHeaderComponent A;

    HudModuleSelectorOpenConfigFrameClickHandler(HudModuleSelectorHeaderComponent hudModuleSelectorHeaderComponent) {
        this.A = hudModuleSelectorHeaderComponent;
    }

    @Override
    public void P() {
        HudModuleConfigFrame hudModuleConfigFrame = ClientSettings.g(HudModuleConfigFrame.class);
        if (hudModuleConfigFrame == null) {
            return;
        }
        hudModuleConfigFrame.T("Settings");
        hudModuleConfigFrame.S();
        hudModuleConfigFrame.h(new SimpleTextLabelComponent("No settings yet"), new Object[0]);
        HudModuleSelectorHeaderComponent.a(this.A).Z(true);
        hudModuleConfigFrame.Z(true);
        hudModuleConfigFrame.U();
        hudModuleConfigFrame.t(hudModuleConfigFrame.L());
        hudModuleConfigFrame.R(1);
        hudModuleConfigFrame.l$src$V$1mibm4x();
    }

}

