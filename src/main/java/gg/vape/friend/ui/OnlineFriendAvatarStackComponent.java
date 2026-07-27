package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RemoteImageTextureManager;
import java.awt.Color;
import java.util.List;

public class OnlineFriendAvatarStackComponent
extends GuiComponent {
    private final List<OnlineFriend> Q;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void u() {
    }

    @Override
    public void H() {
        double d = this.Q.size();
        double d2 = 8.0;
        double d3 = d2 + 1.0;
        double d4 = d * d3;
        double d5 = this.A() - d4;
        double d6 = d3;
        double d7 = d5 / d - d2 / 2.0 / d;
        if (d5 < 0.0) {
            d6 += d7;
        }
        float f = 0.0f;
        int n = 0;
        while ((double)n < d) {
            OnlineFriend onlineFriend = this.Q.get(n);
            GlImageTexture glImageTexture = RemoteImageTextureManager.e().r(onlineFriend.I(), 32);
            if (glImageTexture != null) {
                GuiRenderPrimitives.V((float)this.G$src$D$1b2f02a() + f - 1.0f, (float)this.n() - 1.0f, (float)d2 + 2.0f, 1.0, OnlineFriendAvatarStackComponent.J.m);
                GuiRenderPrimitives.u((float)this.G$src$D$1b2f02a() + f, (float)this.n(), (float)d2, 1.0f, Color.WHITE, glImageTexture);
            }
            f = (float)((double)f + d6);
            ++n;
        }
    }

    @Override
    public double x() {
        return 32.0;
    }

    @Override
    public double C() {
        return 8.0;
    }

    @Override
    public void I() {
    }


    @Override
    public void F() {
    }

    public OnlineFriendAvatarStackComponent(List<OnlineFriend> list) {
        this.Q = list;
    }
}

