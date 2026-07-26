package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendActionPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.gui.IconActionButton;
import java.awt.Color;

class OnlineFriendActionIconButton
extends IconActionButton {
    final OnlineFriendActionPanel iF;

    OnlineFriendActionIconButton(OnlineFriendActionPanel onlineFriendActionPanel, String string, double d, double d2, double d3, Color color, double d4) {
        super(string, d, d2, d3, color, d4);
        this.iF = onlineFriendActionPanel;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void n(boolean bl) {
        if (OnlineFriendActionPanel.V(this.iF)) {
            this.q = false;
            this.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$onqyea().O();
            this.l$src$Lgg_vape_ui_click_animation_ColorAnimation_$1s4yq9u().O();
            return;
        }
        super.n(bl);
    }
}
