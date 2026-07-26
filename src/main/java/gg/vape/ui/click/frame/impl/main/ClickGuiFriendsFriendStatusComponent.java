package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ClickGuiFriendsFriendStatusComponent
extends InteractiveComponent {
    private static final double yD = 4.0;
    private static final double I = 10.0;
    private static final Color yu;
    private static final double yG = 5.0;
    private Color yF;
    private static final double ys = 1.0;
    private static final double yq = 8.0;
    private Color yM;
    private static final Color yV;
    private static final Color yH;
    private boolean yv;
    private static final double yh = 6.0;
    private static final double yw = 7.0;
    private double v;
    private static final double yA = 6.0;
    private final TruncatedTextComponent y7;
    private static final double yr = 1.5;
    private static final Color yt;
    private static final Color yB;
    private static final double yT = 2.0;
    private double yY;
    private static final Color K;
    private static final double y_ = 4.0;
    private static final double yI = 20.0;
    private static final Color yz;
    private double yb;
    private static final double ya = 2.5;
    private final ColorAnimation yZ;
    private static final float b = 3.0f;
    private final SimpleTextLabelComponent yk;
    private static final double y2 = 114.0;
    private static final double yo = 0.75;
    private double yy;
    private final IconGlyphComponent Q;
    private static final double yj = 2.0;
    private static final double yC = 6.0;

    private void w$src$V$zveipx() {
        double d;
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        double d4 = this.A();
        double d5 = this.L();
        this.yY = d = d2 + 4.0;
        this.yy = d3 + (d5 - 10.0) / 2.0;
        double d6 = 6.0;
        if (this.yv) {
            d6 += 12.0;
        }
        double d7 = d4 - 4.0 - d6 + d2;
        double d8 = this.yY + 10.0 + 4.0;
        double d9 = Math.max(0.0, d7 - d8);
        double d10 = 16.0;
        double d11 = d3 + (d5 - 16.0) / 2.0;
        this.y7.K(d8);
        this.y7.S(d11);
        this.y7.o(d9);
        this.y7.Y(8.0);
        this.y7.D(d9);
        this.yk.K(d8);
        this.yk.S(d11 + 8.0 + 1.0);
        this.yk.o(d9);
        this.yk.Y(7.0);
        this.v = d7 + (this.yv ? 12.0 : 0.0);
        this.yb = d3 + (d5 - 6.0) / 2.0;
        if (this.yv) {
            double d12 = d7;
            double d13 = d3 + (d5 - 6.0) / 2.0;
            this.Q.K(d12);
            this.Q.S(d13);
            this.Q.o(6.0);
            this.Q.Y(6.0);
        }
    }

    public void G(Color color) {
        if (color != null) {
            this.yF = color;
        }
    }

    public void r(Color color) {
        if (color != null) {
            this.yM = color;
            this.Q.S(color);
        }
    }

    public void G(boolean bl) {
        this.yv = bl;
        this.Q.Z(bl);
        this.Q.S(this.yM);
    }

    @Override
    public void H() {
        this.yZ.u(this.w$src$Z$e457mb());
        this.w$src$V$zveipx();
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.yZ.getInterpolatedColor(), 3.0f);
        this.o$src$V$zr05z1();
        this.W();
        this.O$src$V$z9eqzh();
        this.l$src$V$zpcs6y();
    }

    private void o$src$V$zr05z1() {
        GuiRenderPrimitives.V(this.yY, this.yy, 10.0, 0.75, yt);
        double d = 4.2;
        double d2 = this.yY + 2.5;
        double d3 = this.yy + 1.5;
        GuiRenderPrimitives.V(d2, d3, 4.2, 0.75, K);
        double d4 = 6.0;
        double d5 = this.yY + 2.0;
        double d6 = this.yy + 4.5;
        GuiRenderPrimitives.V(d5, d6, 6.0, 0.75, yu);
    }

    public static double m$src$D$zpwkct() {
        return 114.0;
    }

    private void W() {
        double d = 5.0;
        double d2 = this.yY + 10.0 - 5.0 + 2.5;
        double d3 = this.yy + 10.0 - 5.0 + 2.5;
        GuiRenderPrimitives.V(d2, d3, 5.0, 0.75, yV);
        GuiRenderPrimitives.V(d2 + 0.5, d3 + 0.5, 4.0, 0.75, this.yF);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void l$src$V$zpcs6y() {
        double d = this.v + 3.0 - 0.75;
        double d2 = this.yb + 3.0 - 1.5 - 1.0;
        for (int i = 0; i < 3; ++i) {
            GuiRenderPrimitives.V(d, d2, 1.5, 0.75, yz);
            d2 += 2.0;
        }
    }

    public ClickGuiFriendsFriendStatusComponent(String string, String string2, boolean bl) {
        this.getClass();
        this.yZ = new ColorAnimation(0.15, ClickGuiFriendsFriendStatusComponent.J.i, ClickGuiFriendsFriendStatusComponent.J.a);
        this.y7 = new TruncatedTextComponent("Player", "...", 0.0, 0.75, ClickGuiFriendsFriendStatusComponent.J.A, false);
        this.yk = new SimpleTextLabelComponent("Offline", 0.625, ClickGuiFriendsFriendStatusComponent.J.C);
        this.Q = new IconGlyphComponent("party@2x", 6.0f, 6.0f, yH);
        this.yF = yB;
        this.yM = yH;
        this.o(114.0);
        this.Y(20.0);
        this.d(false);
        this.o(true);
        this.yZ.O();
        this.y7.O(string);
        this.y7.R(ClickGuiFriendsFriendStatusComponent.J.A);
        this.y7.D(0.0);
        this.yk.G(string2);
        this.yk.T$src$V$1orl066(ClickGuiFriendsFriendStatusComponent.J.C);
        this.yk.g(0.0f);
        this.yk.z(0.0f);
        this.yk.c(0);
        this.Q.Z(false);
        this.Q.r(true);
        this.H(this.y7, this.yk, this.Q);
        this.G(bl);
    }

    public void j(String string) {
        this.yk.G(string);
    }

    private void O$src$V$z9eqzh() {
        this.Q.Z(this.yv);
    }

    static {
        yt = new Color(54, 53, 54);
        K = new Color(124, 123, 124);
        yu = new Color(89, 88, 89);
        yV = new Color(31, 30, 31);
        yB = new Color(98, 197, 84);
        yz = new Color(163, 163, 163);
        yH = new Color(98, 197, 84);
    }

    public void f(String string) {
        this.y7.O(string);
    }
}

