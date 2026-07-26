package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsFriendListComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRowActions;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

final class ClickGuiFriendsPrimaryActionButton
extends InteractiveComponent {
    private final String v;
    final ClickGuiFriendsFriendListComponent K;
    private final ColorAnimation I;

    @Override
    public void H() {
        this.I.u(this.w$src$Z$e457mb());
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.L();
        GuiRenderPrimitives.B(d, d2, d3, d4, this.I.getInterpolatedColor(), 2.0f);
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.5);
        double d5 = smoothFontRenderer.N(this.v);
        double d6 = smoothFontRenderer.d(this.v);
        double d7 = d + (d3 - d5) / 2.0;
        double d8 = d2 + (d4 - d6) / 2.0;
        smoothFontRenderer.d(this.v, d7, d8, ClickGuiFriendsFriendListComponent.G$src$Ljava_awt_Color_$1oq4c31());
    }

    private void K$src$V$1ls7n6e() {
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.5);
        double d = smoothFontRenderer.N(this.v) + 12.0;
        this.o(Math.max(d, 14.0));
    }

    ClickGuiFriendsPrimaryActionButton(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent, String string, ClickGuiFriendsRowActions clickGuiFriendsRowActions) {
        this(clickGuiFriendsFriendListComponent, string);
    }

    static void T(ClickGuiFriendsPrimaryActionButton clickGuiFriendsPrimaryActionButton) {
        clickGuiFriendsPrimaryActionButton.K$src$V$1ls7n6e();
    }

    @Override
    public double x() {
        return this.A();
    }

    private ClickGuiFriendsPrimaryActionButton(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent, String string) {
        this.K = clickGuiFriendsFriendListComponent;
        this.I = new ColorAnimation(0.15, ClickGuiFriendsFriendListComponent.W(), ClickGuiFriendsFriendListComponent.R());
        this.v = string.toUpperCase();
        this.d(false);
        this.Z(false);
        this.K$src$V$1ls7n6e();
    }

    @Override
    public double C() {
        return 10.0;
    }
}

