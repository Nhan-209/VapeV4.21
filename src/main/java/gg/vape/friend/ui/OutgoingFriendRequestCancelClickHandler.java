package gg.vape.friend.ui;

import gg.vape.friend.ui.OutgoingFriendRequestRow;
import gg.vape.ui.click.component.GuiClickListener;

public class OutgoingFriendRequestCancelClickHandler
implements GuiClickListener {
    final OutgoingFriendRequestRow G;

    public OutgoingFriendRequestCancelClickHandler(OutgoingFriendRequestRow mq_12) {
        this.G = mq_12;
    }

    @Override
    public void onPrimaryClick() {
        OutgoingFriendRequestRow.X(this.G);
    }
}
