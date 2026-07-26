package gg.vape.friend.ui;

import gg.vape.friend.ui.IncomingFriendRequestRow;
import gg.vape.ui.click.component.GuiClickListener;

public class IncomingFriendRequestAcceptClickHandler
implements GuiClickListener {
    final IncomingFriendRequestRow G;

    @Override
    public void P() {
        IncomingFriendRequestRow.k(this.G);
    }

    public IncomingFriendRequestAcceptClickHandler(IncomingFriendRequestRow mo_12) {
        this.G = mo_12;
    }
}

