package gg.vape.ui.click.component.publicprofiles;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PublicProfileIdBadgeComponent
extends GuiComponent {
    private long v;
    private static final String b = "99";
    private double G;

    @Override
    public double C() {
        return 7.0;
    }

    public long h() {
        return this.v;
    }

    public void D(double d) {
        this.G = d;
    }

    public void g(long l) {
        this.v = l;
    }

    @Override
    public double x() {
        return 8.0;
    }

    public PublicProfileIdBadgeComponent(long l, double d) {
        this.v = l;
        this.G = d;
    }

    @Override
    public void H() {
        if (this.v > 0L) {
            double d = 7.0;
            GuiRenderPrimitives.V(this.G$src$D$1b2f02a(), this.n(), d, 1.0, PublicProfileIdBadgeComponent.J.d);
            SmoothFontRenderer smoothFontRenderer = this.O(this.G);
            String string = PublicProfileIdBadgeComponent.i(this.v);
            double d2 = smoothFontRenderer.N(string);
            smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + d / 2.0 - d2 / 2.0, this.n() + 1.5, PublicProfileIdBadgeComponent.J.A);
        }
    }

    public static String i(long l) {
        if (l > 99L) {
            return b;
        }
        return String.valueOf(l);
    }


    public PublicProfileIdBadgeComponent(long l) {
        this(l, 0.6);
    }
}

