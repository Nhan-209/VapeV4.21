package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.component.GuiKeyTypedListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleSearchBox;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;

public class HudModuleSearchKeyHandler
implements GuiKeyTypedListener {
    final HudModuleSelectorFrame y;
    final HudModuleSearchBox J;

    @Override
    public void v(char c, int n) {
        this.y.j(HudModuleSearchBox.N(this.J).i$src$Z$1n22b4s());
    }

    public HudModuleSearchKeyHandler(HudModuleSearchBox hudModuleSearchBox, HudModuleSelectorFrame hudModuleSelectorFrame) {
        this.J = hudModuleSearchBox;
        this.y = hudModuleSelectorFrame;
    }
}

