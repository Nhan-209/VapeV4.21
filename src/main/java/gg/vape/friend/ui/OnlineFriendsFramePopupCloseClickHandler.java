package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineFriendsFramePopupCloseClickHandler
implements GuiClickListener {
    final OnlineFriendsFrame E;

    @Override
    public void onPrimaryClick() {
        OnlineFriendsFrame.c$src$V$11veyie(this.E);
    }

    public OnlineFriendsFramePopupCloseClickHandler(OnlineFriendsFrame onlineFriendsFrame) {
        this.E = onlineFriendsFrame;
    }
}
