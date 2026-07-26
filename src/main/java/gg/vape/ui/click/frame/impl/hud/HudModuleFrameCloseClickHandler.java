package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;

public class HudModuleFrameCloseClickHandler
implements GuiClickListener {
    final HudModuleFrameBase Z;

    @Override
    public void P() {
        this.Z.h();
    }

    public HudModuleFrameCloseClickHandler(HudModuleFrameBase hudModuleFrameBase) {
        this.Z = hudModuleFrameBase;
    }
}

