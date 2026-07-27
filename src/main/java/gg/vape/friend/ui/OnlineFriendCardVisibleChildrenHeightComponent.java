package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendCard;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SpacerComponent;

class OnlineFriendCardVisibleChildrenHeightComponent
extends FlowLayoutComponent {
    final OnlineFriendCard Tw;


    OnlineFriendCardVisibleChildrenHeightComponent(OnlineFriendCard onlineFriendCard, double d) {
        super(d);
        this.Tw = onlineFriendCard;
    }

    @Override
    public double i() {
        double d = 0.0;
        if (!OnlineFriendCard.B(this.Tw).V$src$Z$1xhop3l()) {
            return d;
        }
        for (GuiComponent guiComponent : OnlineFriendCard.B(this.Tw).f()) {
            if (guiComponent instanceof SpacerComponent || !guiComponent.V$src$Z$1xhop3l()) continue;
            d += guiComponent.A();
        }
        return d + 2.0;
    }
}
