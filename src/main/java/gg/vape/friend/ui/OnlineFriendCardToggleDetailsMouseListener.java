package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendCard;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

class OnlineFriendCardToggleDetailsMouseListener
implements GuiMouseListener {
    final OnlineFriendCard c;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        OnlineFriendCard.a(this.c, mouseClickButton);
    }

    OnlineFriendCardToggleDetailsMouseListener(OnlineFriendCard onlineFriendCard) {
        this.c = onlineFriendCard;
    }
}

