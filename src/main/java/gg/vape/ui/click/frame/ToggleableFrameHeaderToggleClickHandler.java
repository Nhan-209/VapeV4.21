package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.ToggleableFrameHeaderComponent;

class ToggleableFrameHeaderToggleClickHandler
implements GuiClickListener {
    final ToggleableFrameHeaderComponent w;

    @Override
    public void P() {
        ToggleableFrameHeaderComponent toggleableFrameHeaderComponent = this.w;
        boolean bl = !ToggleableFrameHeaderComponent.i(this.w);
        ToggleableFrameHeaderComponent.a(toggleableFrameHeaderComponent, bl);
        this.w.R();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    ToggleableFrameHeaderToggleClickHandler(ToggleableFrameHeaderComponent toggleableFrameHeaderComponent) {
        this.w = toggleableFrameHeaderComponent;
    }
}

