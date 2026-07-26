package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;
import java.util.function.Supplier;

public class ProfileListEntryBadgeComponent
extends InteractiveComponent {
    private static final double KC = 7.0;
    private static final double K7 = 4.0;
    private static final double KK = 3.0;
    private final String KQ;
    private static final double K = 6.0;
    private final ColorAnimation b;
    private Supplier<Integer> KY;
    private final ShapeIconComponent KW;
    private static final double I = 0.625;
    private static final Color Kg;
    private static final Color Kj;
    private static final double Q = 0.5;
    private final IconGlyphComponent v = new IconGlyphComponent("newpublicprofiles", 6.0f, 6.0f, K5);
    private final ColorAnimation Kt;
    private static final Color K5;

    private static Integer lambda$setBadgeCount$2(int n) {
        return n;
    }

    @Override
    public void I() {
    }

    static {
        K5 = new Color(173, 173, 173);
        Kj = new Color(209, 209, 209);
        Kg = new Color(209, 209, 209);
    }

    private void C$src$V$12bmzv5() {
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.625);
        double d = smoothFontRenderer.N(this.KQ.toUpperCase());
        double d2 = 9.0 + d;
        int n = this.KY.get();
        if (n > 0) {
            d2 += 11.0;
        }
        this.o(d2);
    }

    private static Integer lambda$new$0() {
        return 0;
    }

    @Override
    public void u() {
        this.C$src$V$12bmzv5();
    }

    private static Integer lambda$setBadgeCountSupplier$1() {
        return 0;
    }

    public void p(int n) {
        this.KY = () -> ProfileListEntryBadgeComponent.lambda$setBadgeCount$2(n);
        this.KW.x(this.KY);
        this.C$src$V$12bmzv5();
    }

    public void I(Supplier<Integer> supplier) {
        this.KY = supplier != null ? supplier : ProfileListEntryBadgeComponent::lambda$setBadgeCountSupplier$1;
        this.KW.x(this.KY);
        this.C$src$V$12bmzv5();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int O$src$I$12i8ioa() {
        return this.KY.get();
    }

    public ProfileListEntryBadgeComponent() {
        this.KQ = "VIEW PUBLIC PROFILES";
        this.KW = new ShapeIconComponent(IconShape.CIRCLE, null, 7.0, 7.0, 0.0, 3.5f, ProfileListEntryBadgeComponent.J.d, Color.WHITE, 0.5);
        this.KY = ProfileListEntryBadgeComponent::lambda$new$0;
        this.KW.x(this.KY);
        this.b = new ColorAnimation(0.15, K5, Kj);
        this.Kt = new ColorAnimation(0.15, Kg, Kg.brighter());
        this.Y(12.0);
        this.C$src$V$12bmzv5();
        this.d(false);
        this.H(this.KW);
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.L();
        double d4 = d2 + d3 / 2.0;
        this.b.u(this.w$src$Z$e457mb());
        this.Kt.u(this.w$src$Z$e457mb());
        double d5 = d;
        double d6 = d4 - 3.0;
        this.v.K(d5);
        this.v.S(d6);
        this.v.S(this.b.getInterpolatedColor());
        this.v.c();
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.625);
        String string = this.KQ.toUpperCase();
        double d7 = d5 + 6.0 + 3.0;
        double d8 = smoothFontRenderer.d(string);
        double d9 = d4 - d8 / 2.0;
        smoothFontRenderer.d(string, d7, d9, this.Kt.getInterpolatedColor());
        int n = this.KY.get();
        if (n > 0) {
            double d10 = smoothFontRenderer.N(string);
            double d11 = d7 + d10 + 4.0;
            double d12 = d4 - 3.5;
            this.KW.K(d11);
            this.KW.S(d12);
            this.KW.o(7.0);
            this.KW.Y(7.0);
            this.KW.Z(true);
            this.KW.c();
        } else {
            this.KW.Z(false);
        }
    }
}

