package gg.vape.ui.click.component;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class WrappingTextLabelComponent
extends SimpleTextLabelComponent {
    private double b = -1.0;
    private double o = 0.0;

    public double X$src$D$wrvu2w() {
        return this.b;
    }

    public double w$src$D$x8xgh3() {
        return this.o;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void A(double d) {
        this.b = d;
    }

    @Override
    public void H() {
        if (this.O.contains("\n")) {
            String[] stringArray = this.O.split("\n");
            int n = 0;
            for (String string : stringArray) {
                n += this.X(string, n);
            }
            this.o = n;
        } else {
            this.o = this.X(this.O, 0.0);
        }
    }

    @Override
    public double x() {
        return super.l$src$D$1x5l26k();
    }

    public WrappingTextLabelComponent(String string, double d, Color color) {
        super(string, d);
        this.T$src$V$1orl066(color);
    }

    private int X(String string, double d) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(this.G, false);
        if (string.contains(" ")) {
            double d2;
            double d3;
            String[] stringArray = string.split(" ");
            double d4 = 0.0;
            String string2 = "";
            double d5 = 0.0;
            double d6 = smoothFontRenderer.d(string);
            for (String string3 : stringArray) {
                d3 = smoothFontRenderer.N(string3 + " ");
                d4 += d3;
                double d7 = this.X$src$D$wrvu2w() != -1.0 ? this.X$src$D$wrvu2w() : this.A();
                if (d4 > d7) {
                    d2 = smoothFontRenderer.N(string2);
                    double d8 = this.n() + d + this.L() / 2.0 - d6 / 2.0;
                    double d9 = this.G$src$D$1b2f02a() + this.A() / 2.0;
                    double d10 = d9 - d2 / 2.0;
                    smoothFontRenderer.d(string2, d10, d8, this.G$src$Ljava_awt_Color_$11jgid7());
                    string2 = "";
                    d4 = 0.0;
                    d5 += 1.0;
                    d += d6;
                    d4 += d3;
                }
                string2 = string2 + string3 + " ";
            }
            if (d6 == 0.0) {
                d6 = smoothFontRenderer.d("|");
            }
            double d11 = smoothFontRenderer.N(string2);
            double d12 = this.n() + d + this.L() / 2.0 - d6 / 2.0;
            d3 = this.G$src$D$1b2f02a() + this.A() / 2.0;
            d2 = d3 - d11 / 2.0;
            smoothFontRenderer.d(string2, d2, d12, this.G$src$Ljava_awt_Color_$11jgid7());
            return (int)(d6 * (d5 += 1.0));
        }
        double d13 = smoothFontRenderer.d(string);
        double d14 = smoothFontRenderer.N(string);
        double d15 = this.n() + d + this.L() / 2.0 - d13 / 2.0;
        double d16 = this.G$src$D$1b2f02a() + this.A() / 2.0;
        double d17 = d16 - d14 / 2.0;
        smoothFontRenderer.d(string, d17, d15, this.G$src$Ljava_awt_Color_$11jgid7());
        return (int)d13;
    }

    public WrappingTextLabelComponent(String string, double d) {
        super(string, d);
    }
}

