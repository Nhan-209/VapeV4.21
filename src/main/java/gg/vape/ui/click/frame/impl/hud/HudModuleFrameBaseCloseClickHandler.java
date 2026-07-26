package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;

public class HudModuleFrameBaseCloseClickHandler
implements GuiClickListener {
    final HudModuleFrameBase H;

    @Override
    public void P() {
        this.H.k();
    }

    public HudModuleFrameBaseCloseClickHandler(HudModuleFrameBase hudModuleFrameBase) {
        this.H = hudModuleFrameBase;
    }
}

