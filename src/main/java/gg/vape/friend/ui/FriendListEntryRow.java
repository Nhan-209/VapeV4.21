package gg.vape.friend.ui;

import gg.vape.friend.ExternalFriend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.ui.FriendListEntryRemoveClickHandler;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class FriendListEntryRow
extends SelectableTextRowComponent {
    private final FriendEntry friendEntry;

    @Override
    public String getText() {
        return super.getText();
    }

    @Override
    public void H() {
        super.H();
        String text = this.friendEntry.s();
        if (!this.isHovered()) {
            if (!this.friendEntry.o().equals(this.friendEntry.s())) {
                text = "*" + this.friendEntry.E();
            }
        } else if (this.friendEntry instanceof ExternalFriend) {
            ExternalFriend externalFriend = (ExternalFriend)this.friendEntry;
            text = "*" + externalFriend.d().C();
        }
        this.setText(text);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        super.g(guiMouseEvent);
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            this.friendEntry.k(!this.friendEntry.c());
        }
    }

    public FriendListEntryRow(FriendEntry friendEntry) {
        super(FriendListEntryRow.J.B, friendEntry.s());
        this.friendEntry = friendEntry;
        if (friendEntry instanceof ExternalFriend) {
            this.setIndicatorIcon("synced@2x");
            this.setSelectedIndicatorColor(FriendListEntryRow.J.T);
        }
        this.setHorizontalInset(0.0f);
        this.o(99.0);
        this.setUseExplicitWidth(true);
        this.setDeleteActionListener(new FriendListEntryRemoveClickHandler(this, friendEntry));
        this.w("Toggle friend between Active and Inactive");
        this.getDeleteButton().w("Remove friend from list");
    }


    @Override
    public boolean isSelected() {
        return this.friendEntry.c();
    }
}

