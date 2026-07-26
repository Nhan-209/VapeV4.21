package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendListComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRowActions;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;

final class ClickGuiFriendsSecondaryActionButton
extends InteractiveComponent {
    private final ColorAnimation b;
    private final String Q;
    private boolean K;
    final ClickGuiFriendsFriendListComponent v;

    @Override
    public void H() {
        this.b.u(this.w$src$Z$e457mb());
        double d = this.G$src$D$1b2f02a() + (this.A() - 6.0) / 2.0;
        double d2 = this.n() + (this.L() - 6.0) / 2.0;
        ImageRenderer.E(this.b.getInterpolatedColor(), (float)d, (float)d2, this.Q, 6.0f, 6.0f, false);
        if (this.K) {
            double d3 = 3.0;
            double d4 = this.G$src$D$1b2f02a() + this.A() - 3.0;
            double d5 = this.n() + 1.0;
            GuiRenderPrimitives.V((float)d4, (float)d5, 3.0, 0.8f, ClickGuiFriendsSecondaryActionButton.J.d);
        }
    }

    ClickGuiFriendsSecondaryActionButton(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent, String string, ClickGuiFriendsRowActions clickGuiFriendsRowActions) {
        this(clickGuiFriendsFriendListComponent, string);
    }

    private void h(boolean bl) {
        this.K = bl;
    }

    @Override
    public double C() {
        return 10.0;
    }

    static void y(ClickGuiFriendsSecondaryActionButton clickGuiFriendsSecondaryActionButton, boolean bl) {
        clickGuiFriendsSecondaryActionButton.h(bl);
    }

    private ClickGuiFriendsSecondaryActionButton(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent, String string) {
        this.v = clickGuiFriendsFriendListComponent;
        this.b = new ColorAnimation(0.15, ClickGuiFriendsSecondaryActionButton.J.W, ClickGuiFriendsSecondaryActionButton.J.f);
        this.Q = string;
        this.d(false);
    }

    @Override
    public double x() {
        return 10.0;
    }
}

