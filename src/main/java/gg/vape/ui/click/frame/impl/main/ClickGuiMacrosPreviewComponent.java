package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsPanel;
import gg.vape.utils.render.GuiRenderPrimitives;

class ClickGuiMacrosPreviewComponent
extends GuiComponent {
    final ClickGuiMacrosSettingsPanel i;

    @Override
    public void H() {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), 1.0, ClickGuiMacrosSettingsPanel.N$src$Ljava_awt_Color_$1qg5s8k());
    }

    ClickGuiMacrosPreviewComponent(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        this.i = clickGuiMacrosSettingsPanel;
    }
}

