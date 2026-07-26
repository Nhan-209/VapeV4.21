package gg.vape.ui.click.frame;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudEditorReturnToMainLayerHeaderComponent;

public class FrameHeaderMainLayerClickHandler
implements GuiClickListener {
    final HudEditorReturnToMainLayerHeaderComponent D;

    public FrameHeaderMainLayerClickHandler(HudEditorReturnToMainLayerHeaderComponent hudEditorReturnToMainLayerHeaderComponent) {
        this.D = hudEditorReturnToMainLayerHeaderComponent;
    }

    @Override
    public void P() {
        ClientSettings.fW.I(ClientSettings.a);
    }
}

