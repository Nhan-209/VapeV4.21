package gg.vape.ui.click.frame.impl.hud;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;

class HudModuleFrameOpenConfigClickHandler
implements GuiClickListener {
    final AnchoredHudModuleConfigFrame B;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        this.B.Z(false);
        for (GuiComponent guiComponent : this.B.f()) {
            if (guiComponent == this.B.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc()) continue;
            guiComponent.Z(false);
        }
    }

    HudModuleFrameOpenConfigClickHandler(AnchoredHudModuleConfigFrame anchoredHudModuleConfigFrame) {
        this.B = anchoredHudModuleConfigFrame;
    }
}

