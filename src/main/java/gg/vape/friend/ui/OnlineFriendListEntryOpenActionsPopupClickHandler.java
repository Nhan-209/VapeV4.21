package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendListEntry;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineFriendListEntryOpenActionsPopupClickHandler
implements GuiClickListener {
    final OnlineFriendListEntry T;

    public OnlineFriendListEntryOpenActionsPopupClickHandler(OnlineFriendListEntry onlineFriendListEntry) {
        this.T = onlineFriendListEntry;
    }

    @Override
    public void onPrimaryClick() {
        OnlineFriendListEntry.G(this.T);
    }
}
