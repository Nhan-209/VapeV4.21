package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendRequestComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestStatusComponent;
import gg.vape.utils.render.ImageRenderer;

final class ClickGuiFriendsRequestActionComponent
extends InteractiveComponent {
    final ClickGuiFriendsFriendRequestComponent I;
    private final ColorAnimation Q;
    private static final String v = "newtrash";

    ClickGuiFriendsRequestActionComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent, ClickGuiFriendsRequestStatusComponent clickGuiFriendsRequestStatusComponent) {
        this(clickGuiFriendsFriendRequestComponent);
    }

    @Override
    public double x() {
        return 9.0;
    }

    private ClickGuiFriendsRequestActionComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent) {
        this.I = clickGuiFriendsFriendRequestComponent;
        this.getClass();
        this.Q = new ColorAnimation(0.15, ClickGuiFriendsFriendRequestComponent.T$src$Ljava_awt_Color_$vjmbs7(), ClickGuiFriendsFriendRequestComponent.s$src$Ljava_awt_Color_$z1gb6w());
        this.o(9.0);
        this.Y(9.0);
        this.d(false);
    }

    @Override
    public double C() {
        return 9.0;
    }

    @Override
    public void H() {
        this.Q.u(this.w$src$Z$e457mb());
        double d = this.G$src$D$1b2f02a() + (this.A() - 4.5) / 2.0;
        double d2 = this.n() + (this.L() - 4.5) / 2.0;
        ImageRenderer.E(this.Q.getInterpolatedColor(), (float)d, (float)d2, v, 4.5f, 4.5f, false);
    }
}

