package gg.vape.friend.ui;

import gg.vape.friend.FriendEntry;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class FriendSettingsEntryRowComponent
extends SelectableTextRowComponent {
    private final FriendEntry friendEntry;

    @Override
    public boolean isSelected() {
        return this.friendEntry.c();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.friendEntry.k(!this.friendEntry.c());
    }

    @Override
    public void H() {
        String text = this.friendEntry.s();
        if (!this.friendEntry.o().equals(this.friendEntry.s()) && !this.isHovered()) {
            text = "*" + this.friendEntry.E();
        }
        this.setText(text);
        super.H();
    }


    public FriendSettingsEntryRowComponent(FriendEntry friendEntry) {
        super(FriendSettingsEntryRowComponent.J.B, friendEntry.s());
        this.friendEntry = friendEntry;
    }

    public FriendEntry getFriendEntry() {
        return this.friendEntry;
    }
}

