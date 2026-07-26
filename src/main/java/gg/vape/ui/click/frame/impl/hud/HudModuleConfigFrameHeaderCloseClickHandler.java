package gg.vape.ui.click.frame.impl.hud;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameHeaderComponent;

class HudModuleConfigFrameHeaderCloseClickHandler
implements GuiClickListener {
    final HudModuleConfigFrame p;
    final HudModuleConfigFrameHeaderComponent t;

    @Override
    public void P() {
        if (this.p.L$src$I$jm6ud8() == 3 || this.p.L$src$I$jm6ud8() == 4) {
            return;
        }
        if (this.p.V$src$Z$1xhop3l()) {
            this.p.R(3);
            this.p.U();
        }
    }

    HudModuleConfigFrameHeaderCloseClickHandler(HudModuleConfigFrameHeaderComponent hudModuleConfigFrameHeaderComponent, HudModuleConfigFrame hudModuleConfigFrame) {
        this.t = hudModuleConfigFrameHeaderComponent;
        this.p = hudModuleConfigFrame;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

