package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import java.awt.Point;

public class OnlineFriendsFramePopupOutsideClickListener
implements GuiMouseListener {
    final OnlineFriendsFrame j;

    public OnlineFriendsFramePopupOutsideClickListener(OnlineFriendsFrame onlineFriendsFrame) {
        this.j = onlineFriendsFrame;
    }

    @Override
    public boolean Q(Point point) {
        if (!this.j.q() && OnlineFriendsFrame.e(this.j).w$src$Z$e457mb()) {
            OnlineFriendsFrame.e(this.j).R();
            return true;
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

