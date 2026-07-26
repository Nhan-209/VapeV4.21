package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendActionPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.frame.PopupFrame;
import java.awt.Point;

public class OnlineFriendActionPanelPopupOutsideClickFilter
implements GuiMouseListener {
    final OnlineFriendActionPanel w;
    final PopupFrame G;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean Q(Point point) {
        if (this.w.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().Q().R(point) && !this.G.Q().R(point)) {
            return true;
        }
        return GuiMouseListener.super.Q(point);
    }

    public OnlineFriendActionPanelPopupOutsideClickFilter(OnlineFriendActionPanel onlineFriendActionPanel, PopupFrame popupFrame) {
        this.w = onlineFriendActionPanel;
        this.G = popupFrame;
    }
}

