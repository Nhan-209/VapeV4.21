package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendRequestComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestStatusComponent;
import gg.vape.utils.render.ImageRenderer;

final class ClickGuiFriendsRequestRemoveComponent
extends InteractiveComponent {
    final ClickGuiFriendsFriendRequestComponent K;
    private final ColorAnimation I;
    private static final String v = "newsettings";

    private ClickGuiFriendsRequestRemoveComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent) {
        this.K = clickGuiFriendsFriendRequestComponent;
        this.getClass();
        this.I = new ColorAnimation(0.15, ClickGuiFriendsFriendRequestComponent.g$src$Ljava_awt_Color_$1956ipg(), ClickGuiFriendsFriendRequestComponent.I$src$Ljava_awt_Color_$yvy0ia());
        this.o(10.0);
        this.Y(10.0);
        this.d(false);
    }

    ClickGuiFriendsRequestRemoveComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent, ClickGuiFriendsRequestStatusComponent clickGuiFriendsRequestStatusComponent) {
        this(clickGuiFriendsFriendRequestComponent);
    }

    @Override
    public double C() {
        return 10.0;
    }

    @Override
    public double x() {
        return 10.0;
    }

    @Override
    public void H() {
        this.I.u(this.w$src$Z$e457mb());
        double d = this.G$src$D$1b2f02a() + (this.A() - 5.0) / 2.0;
        double d2 = this.n() + (this.L() - 5.0) / 2.0;
        ImageRenderer.E(this.I.getInterpolatedColor(), (float)d, (float)d2, v, 5.0f, 5.0f, false);
    }
}

