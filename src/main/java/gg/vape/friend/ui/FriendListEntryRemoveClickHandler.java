package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ExternalFriend;
import gg.vape.friend.Friend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.ui.FriendListEntryRow;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.ui.click.component.GuiClickListener;

public class FriendListEntryRemoveClickHandler
implements GuiClickListener {
    final FriendEntry n;
    final FriendListEntryRow a;

    public FriendListEntryRemoveClickHandler(FriendListEntryRow friendListEntryRow, FriendEntry friendEntry) {
        this.a = friendListEntryRow;
        this.n = friendEntry;
    }


    @Override
    public void P() {
        if (this.n instanceof Friend) {
            Vape.INSTANCE.getFriendManager().E((Friend)this.n);
        } else if (this.n instanceof ExternalFriend) {
            ExternalFriend externalFriend = (ExternalFriend)this.n;
            externalFriend.d().O(false);
        }
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }
}

