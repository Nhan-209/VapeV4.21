package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendListEntry;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineFriendListEntryClosePopupClickHandler
implements GuiClickListener {
    final OnlineFriendListEntry B;

    @Override
    public void P() {
        OnlineFriendListEntry.d(this.B);
    }

    public OnlineFriendListEntryClosePopupClickHandler(OnlineFriendListEntry mz_22) {
        this.B = mz_22;
    }
}

