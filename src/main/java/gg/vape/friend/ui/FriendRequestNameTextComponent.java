package gg.vape.friend.ui;

import gg.vape.friend.ui.FriendRequestRow;
import gg.vape.ui.click.component.TruncatedTextComponent;
import java.awt.Color;

public class FriendRequestNameTextComponent
extends TruncatedTextComponent {
    final FriendRequestRow i;

    public FriendRequestNameTextComponent(FriendRequestRow friendRequestRow, String string, String string2, double d, double d2, Color color, boolean bl) {
        super(string, string2, d, d2, color, bl);
        this.i = friendRequestRow;
    }

    @Override
    public double C() {
        return 16.0;
    }
}
