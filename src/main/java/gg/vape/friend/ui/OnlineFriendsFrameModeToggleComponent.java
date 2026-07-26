package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.OnlineModeToggleComponent;

public class OnlineFriendsFrameModeToggleComponent
extends OnlineModeToggleComponent {
    final OnlineFriendsFrame _7;

    public OnlineFriendsFrameModeToggleComponent(OnlineFriendsFrame onlineFriendsFrame, String string, String string2, boolean bl) {
        super(string, string2, bl);
        this._7 = onlineFriendsFrame;
    }

    @Override
    public void u(Boolean bl) {
        super.u(bl);
        this._7.p(bl);
    }
}
