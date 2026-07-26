package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.OnlineStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendListComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRowActions;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RemoteImageTextureManager;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

final class ClickGuiFriendsStatusIconComponent
extends GuiComponent {
    private boolean O;
    private double i;
    private static final String b = "avatar offline@2x";
    final ClickGuiFriendsFriendListComponent v;

    @Override
    public double x() {
        return 10.0;
    }

    @Nullable
    private GlImageTexture N() {
        if (!ClickGuiFriendsFriendListComponent.H(this.v).u()) {
            return null;
        }
        String string = ClickGuiFriendsFriendListComponent.H(this.v).I();
        if (string == null || string.isEmpty()) {
            return null;
        }
        if (ClickGuiFriendsFriendListComponent.H(this.v).F() == OnlineStatus.OFFLINE) {
            return null;
        }
        return RemoteImageTextureManager.e().r(string, 32);
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        GlImageTexture glImageTexture = this.N();
        GuiRenderPrimitives.B(d, d2, 10.0, 10.0, ClickGuiFriendsStatusIconComponent.J.m, 5.0f);
        if (glImageTexture != null) {
            GuiRenderPrimitives.u((float)d, (float)d2, 10.0f, 0.8f, Color.WHITE, glImageTexture);
        } else {
            ImageRenderer.E(Color.WHITE, (float)d, (float)d2, b, 10.0f, 10.0f, false);
        }
        double d3 = 10.0;
        Color color = this.O ? ClickGuiFriendsStatusIconComponent.J.M : ClickGuiFriendsStatusIconComponent.J.E;
        GuiRenderPrimitives.m((float)d, (float)d2, 10.0f, 1.0f, 0.8f, color);
        OnlineStatus onlineStatus = ClickGuiFriendsFriendListComponent.H(this.v).F();
        if (onlineStatus != null) {
            Color color2 = onlineStatus.P();
            double d4 = d + 10.0 - this.i;
            double d5 = d2 + 10.0 - this.i;
            GuiRenderPrimitives.V((float)(d4 - 1.0), (float)(d5 - 1.0), (float)(this.i + 2.0), 0.8f, ClickGuiFriendsStatusIconComponent.J.m);
            GuiRenderPrimitives.V((float)d4, (float)d5, (float)this.i, 0.8f, color2);
        }
    }

    @Override
    public void n(boolean bl) {
        this.O = bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    ClickGuiFriendsStatusIconComponent(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent, ClickGuiFriendsRowActions clickGuiFriendsRowActions) {
        this(clickGuiFriendsFriendListComponent);
    }

    @Override
    public double C() {
        return 10.0;
    }

    private ClickGuiFriendsStatusIconComponent(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent) {
        this.v = clickGuiFriendsFriendListComponent;
        this.i = 5.0;
        this.S(false);
        this.d(false);
    }

    public void H(double d) {
        this.i = d;
    }
}

