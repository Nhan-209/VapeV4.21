package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.ExternalFriend;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendRequestComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestStatusComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

final class ClickGuiFriendsRequestTextComponent
extends GuiComponent {
    final ClickGuiFriendsFriendRequestComponent a;
    private String I;
    private Color i;
    private static final double o = 5.0;
    private static final String v = "synced@2x";

    private ClickGuiFriendsRequestTextComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent) {
        this.a = clickGuiFriendsFriendRequestComponent;
        this.i = ClickGuiFriendsRequestTextComponent.J.B;
        this.o(6.0);
        this.Y(6.0);
        this.d(false);
        this.S(false);
    }

    private void A$src$V$l00tpt() {
        this.i = ClickGuiFriendsRequestTextComponent.J.B;
        this.I = null;
        if (ClickGuiFriendsFriendRequestComponent.w(this.a) instanceof ExternalFriend) {
            ExternalFriend externalFriend = (ExternalFriend)ClickGuiFriendsFriendRequestComponent.w(this.a);
            this.I = v;
            this.i = ClickGuiFriendsRequestTextComponent.J.T;
        }
    }

    @Override
    public double x() {
        return 6.0;
    }


    @Override
    public double C() {
        return 6.0;
    }

    @Override
    public void H() {
        if (this.a.isBlatantMod()) {
            if (this.I != null) {
                GuiRenderPrimitives.F(this.I, this.G$src$D$1b2f02a() + 2.5, this.n() + 2.5, 5.0, 5.0, this.i);
            } else {
                GuiRenderPrimitives.V((float)this.G$src$D$1b2f02a(), (float)this.n(), 5.0, 0.5, this.i);
            }
        } else if (this.I != null) {
            GuiRenderPrimitives.F(this.I, this.G$src$D$1b2f02a() + 2.5, this.n() + 2.5, 5.0, 5.0, ClickGuiFriendsRequestTextComponent.J.W);
        } else {
            GuiRenderPrimitives.m((float)this.G$src$D$1b2f02a(), (float)this.n(), 5.0f, 1.0f, 0.5f, ClickGuiFriendsRequestTextComponent.J.W);
        }
    }

    ClickGuiFriendsRequestTextComponent(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent, ClickGuiFriendsRequestStatusComponent clickGuiFriendsRequestStatusComponent) {
        this(clickGuiFriendsFriendRequestComponent);
    }

    static void q(ClickGuiFriendsRequestTextComponent clickGuiFriendsRequestTextComponent) {
        clickGuiFriendsRequestTextComponent.A$src$V$l00tpt();
    }
}

