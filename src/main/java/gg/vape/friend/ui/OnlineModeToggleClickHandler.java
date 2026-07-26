package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineModeToggleComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineModeToggleClickHandler
implements GuiClickListener {
    final OnlineModeToggleComponent y;

    @Override
    public void P() {
        if (this.y.r$src$Ljava_lang_Boolean_$180i77a().booleanValue()) {
            OnlineModeToggleComponent.w(this.y);
        }
    }

    public OnlineModeToggleClickHandler(OnlineModeToggleComponent onlineModeToggleComponent) {
        this.y = onlineModeToggleComponent;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

