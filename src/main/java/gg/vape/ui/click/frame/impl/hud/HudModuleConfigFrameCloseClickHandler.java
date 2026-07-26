package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;

public class HudModuleConfigFrameCloseClickHandler
implements GuiClickListener {
    final HudModuleConfigFrame Q;

    public HudModuleConfigFrameCloseClickHandler(HudModuleConfigFrame hudModuleConfigFrame) {
        this.Q = hudModuleConfigFrame;
    }

    @Override
    public void P() {
        HudModuleConfigFrame.O(this.Q, 3);
    }
}

