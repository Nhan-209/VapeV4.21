package gg.vape.friend.ui;

import gg.vape.friend.ui.FriendRequestsPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;

class FriendRequestUsernameInputComponent
extends TextInputComponentBase {
    final FriendRequestsPanel qM;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    FriendRequestUsernameInputComponent(FriendRequestsPanel friendRequestsPanel, String string) {
        super(string);
        this.qM = friendRequestsPanel;
    }

    @Override
    public double C() {
        return 22.0;
    }

    @Override
    public double x() {
        return 104.0;
    }

    @Override
    public void p() {
        if (this.i$src$Ljava_lang_String_$1n2xf3k().equals("")) {
            return;
        }
        FriendRequestsPanel.I(this.qM, this.i$src$Ljava_lang_String_$1n2xf3k());
        this.k("");
    }

    @Override
    public float y() {
        return 2.0f;
    }
}
