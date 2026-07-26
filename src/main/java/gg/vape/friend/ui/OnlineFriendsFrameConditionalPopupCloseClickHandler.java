package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineFriendsFrameConditionalPopupCloseClickHandler
implements GuiClickListener {
    final OnlineFriendsFrame d;

    @Override
    public void P() {
        if (OnlineFriendsFrame.c(this.d) != null) {
            OnlineFriendsFrame.c$src$V$11veyie(this.d);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public OnlineFriendsFrameConditionalPopupCloseClickHandler(OnlineFriendsFrame onlineFriendsFrame) {
        this.d = onlineFriendsFrame;
    }
}

