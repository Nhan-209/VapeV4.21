package gg.vape.friend.ui;

import gg.vape.friend.ui.IncomingFriendRequestRow;
import gg.vape.ui.click.component.GuiClickListener;

public class IncomingFriendRequestDeclineClickHandler
implements GuiClickListener {
    final IncomingFriendRequestRow A;

    public IncomingFriendRequestDeclineClickHandler(IncomingFriendRequestRow mo_12) {
        this.A = mo_12;
    }

    @Override
    public void onPrimaryClick() {
        IncomingFriendRequestRow.v(this.A);
    }
}
