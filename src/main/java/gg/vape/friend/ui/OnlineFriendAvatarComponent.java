package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.PartyState;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RemoteImageTextureManager;
import java.awt.Color;

public class OnlineFriendAvatarComponent
extends GuiComponent {
    private final OnlineFriend O;
    private static final String b = "leader@2x";
    private final double I;
    private final double Q;

    @Override
    public double C() {
        return this.Q;
    }

    @Override
    public void F() {
    }

    @Override
    public double x() {
        return this.I;
    }

    @Override
    public void H() {
        GlImageTexture glImageTexture = RemoteImageTextureManager.getInstance().getTexture(this.O.I(), 32);
        if (glImageTexture != null) {
            GuiRenderPrimitives.u((float)this.G$src$D$1b2f02a(), (float)this.n(), (float)this.A(), 1.0f, Color.WHITE, glImageTexture);
            PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
            if (partyState != null && partyState.r().equals(this.O)) {
                ImageRenderer.drawImage(Color.WHITE, (float)this.G$src$D$1b2f02a() + 1.5f, (float)this.n() - 4.5f, b, 3.0f, 3.0f, false);
            }
        }
    }

    public OnlineFriendAvatarComponent(OnlineFriend onlineFriend, double d, double d2) {
        this.O = onlineFriend;
        this.I = d;
        this.Q = d2;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void I() {
    }


    @Override
    public void u() {
    }
}

