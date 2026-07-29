package gg.vape.friend.ui;

import gg.vape.friend.ui.AddFriendInputPanel;
import gg.vape.ui.click.component.input.CompactTextInputComponent;

class AddFriendInputComponent
extends CompactTextInputComponent {
    final AddFriendInputPanel to;

    @Override
    public void submit() {
        super.submit();
        AddFriendInputPanel.h(this.to);
    }

    AddFriendInputComponent(AddFriendInputPanel addFriendInputPanel, String string) {
        super(string);
        this.to = addFriendInputPanel;
    }
}
