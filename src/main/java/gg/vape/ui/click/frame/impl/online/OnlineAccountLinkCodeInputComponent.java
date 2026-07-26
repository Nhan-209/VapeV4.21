package gg.vape.ui.click.frame.impl.online;

import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountLinkCodePageComponent;
import gg.vape.ui.notification.NotificationType;

class OnlineAccountLinkCodeInputComponent
extends DebouncedTextInputComponent {
    final OnlineAccountLinkCodePageComponent we;
    private static final String lb = "You are on cooldown!";

    @Override
    public void j() {
        OnlineFriendUiHelper.w(NotificationType.WARNING, lb);
    }

    OnlineAccountLinkCodeInputComponent(OnlineAccountLinkCodePageComponent onlineAccountLinkCodePageComponent, String string, long l) {
        super(string, l);
        this.we = onlineAccountLinkCodePageComponent;
    }

    @Override
    public double C() {
        return 24.0;
    }

    @Override
    public double x() {
        return 104.0;
    }

    @Override
    public void U$src$V$1pxrzte() {
        OnlineAccountLinkCodePageComponent.r(this.we);
    }
}
