package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.KeystrokesHudFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

public class KeystrokesCpsCounterComponent
extends GuiComponent {
    private final Queue<Long> I = new LinkedList<Long>();
    private double v;
    private boolean R = false;
    private final Queue<Long> K = new LinkedList<Long>();
    private static boolean Q;
    private KeystrokesHudFrame o;
    private static final String b;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public KeystrokesCpsCounterComponent(KeystrokesHudFrame keystrokesHudFrame) {
        this.o = keystrokesHudFrame;
    }

    public static boolean U$src$Z$1qvfe5t() {
        return Q;
    }

    @Override
    public double C() {
        return 0.0;
    }

    public static void b(boolean bl) {
        Q = bl;
    }

    public static boolean J$src$Z$1qpdnmu() {
        boolean bl = KeystrokesCpsCounterComponent.U$src$Z$1qvfe5t();
        return false;
    }

    public int t(Queue<Long> queue) {
        long l = System.currentTimeMillis();
        while (!queue.isEmpty() && queue.peek() < l) {
            queue.remove();
        }
        return queue.size();
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void F() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public void O(int n) {
        switch (n) {
            case 0: {
                this.I.add(System.currentTimeMillis() + 1000L);
                break;
            }
            case 1: {
                this.K.add(System.currentTimeMillis() + 1000L);
            }
        }
    }

    @Override
    public void H() {
        if (this.o != null) {
            this.z();
        } else {
            this.t$src$V$1rch0gk();
        }
    }

    public KeystrokesCpsCounterComponent() {
    }

    public double s() {
        return this.O(0.65).d(this.t(this.I) + "");
    }

    static {
        KeystrokesCpsCounterComponent.b(true);
        b = "CPS";
    }

    @Override
    public void I() {
        this.H();
    }

    public void t$src$V$1rch0gk() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().Y(0.65);
        String string = this.t(this.I) + "";
        if (this.R) {
            smoothFontRenderer.v(string, d - smoothFontRenderer.N(string) / 2.0, d2 - smoothFontRenderer.d(string) / 2.0, KeystrokesCpsCounterComponent.J.A);
        } else {
            String string2 = this.t(this.K) + "";
            smoothFontRenderer.v("|", d - smoothFontRenderer.N("|") / 2.0, d2 + smoothFontRenderer.d("|") / 2.0, KeystrokesCpsCounterComponent.J.A);
            smoothFontRenderer.v(string, d - smoothFontRenderer.N("|") / 2.0 - smoothFontRenderer.N(string) - 2.0, d2 + smoothFontRenderer.d(string) / 2.0 + (smoothFontRenderer.d("|") - smoothFontRenderer.d(string)) / 2.0, KeystrokesCpsCounterComponent.J.A);
            smoothFontRenderer.v(string2, d + smoothFontRenderer.N("|") / 2.0 + 2.0, d2 + smoothFontRenderer.d(string2) / 2.0 + (smoothFontRenderer.d("|") - smoothFontRenderer.d(string2)) / 2.0, KeystrokesCpsCounterComponent.J.A);
        }
    }

    private void z() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(1.0, false);
        String string = String.valueOf(this.t(this.I));
        String string2 = String.valueOf(this.t(this.K));
        if (this.R) {
            String string3 = b;
            double d3 = smoothFontRenderer.N(string);
            double d4 = d + 11.0;
            double d5 = 10.0 + smoothFontRenderer.N(string3) + 1.5 + 8.0;
            if (this.o.U$src$Z$brwr1a()) {
                GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), d5, 12.0, this.o.l(new Color(0, 0, 0, 100)));
                smoothFontRenderer.d(string, d4 - d3, d2 + 2.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
                smoothFontRenderer.d(string3, d4 + 1.5, d2 + 2.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
            } else {
                smoothFontRenderer.v(string, d4 - d3, d2 + 2.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
                smoothFontRenderer.v(string3, d4 + 1.5, d2 + 2.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
            }
        } else {
            if (!this.o.m()) {
                GuiRenderPrimitives.I(this.G$src$D$1b2f02a(), this.n() + 2.0, this.A(), 12.0, this.o.l(new Color(20, 20, 20, 180)), false, 1.0f, 1.0f, 3.0f, KeystrokesCpsCounterComponent.J.u);
            }
            GuiRenderPrimitives.d(d + this.A() / 2.0, d2 + 4.0, 1.0, 9.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
            smoothFontRenderer.d(string, d + 5.0, d2 + 3.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
            smoothFontRenderer.d(string2, d + this.A() - smoothFontRenderer.N(string2) - 5.0, d2 + 3.0, this.o.l(KeystrokesCpsCounterComponent.J.A));
        }
    }

    @Override
    public void u() {
    }

    public double f$src$D$1r4rvq0() {
        return this.O(0.65).N(this.t(this.I) + "");
    }

    public int Q(int n) {
        if (n == 0) {
            return this.t(this.I);
        }
        if (n == 1) {
            return this.t(this.K);
        }
        return 0;
    }

    public void g(boolean bl) {
        this.R = bl;
    }
}

