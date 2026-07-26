package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class MultilineTextBlockComponent
extends GuiComponent {
    private final String q4;
    private double G;
    private Color qi;
    private static final double a = 3.0;
    @Nullable
    private String qs;
    private double o;
    private final boolean qQ;
    private final Color O;
    private static final double I = 2.0;
    private double qx;
    private boolean i;
    private static final double K = 0.75;
    private final Color b;
    private double qn;
    private static final float qM = 2.5f;
    private static final double qq = 6.0;
    private static final double qF = 8.0;
    @Nullable
    private Runnable q0;
    private static final double R = 7.0;
    private final String q5;
    private static final double qm = 0.75;
    private final Color q9;
    private static final float v = 1.0f;
    private static final double qo = 2.0;
    private final String q1;
    private double q8;
    private final Color qg;
    private Color Q;

    @Override
    public double C() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        double d = this.C(smoothFontRenderer, this.q1, this.A() - 2.0 - 16.0);
        return 15.0 + d + 6.0;
    }

    public void N(String string, Runnable runnable) {
        this.qs = string;
        this.q0 = runnable;
    }

    private double C(SmoothFontRenderer smoothFontRenderer, String string, double d) {
        if (d <= 0.0) {
            d = this.o - 2.0 - 16.0;
        }
        double d2 = smoothFontRenderer.d("A") + 1.0;
        int n = 0;
        String[] stringArray = string.split("\n", -1);
        for (int i = 0; i < stringArray.length; ++i) {
            String string2 = stringArray[i];
            if (string2.isEmpty()) {
                ++n;
                continue;
            }
            String[] stringArray2 = string2.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            int n2 = 1;
            for (String string3 : stringArray2) {
                String string4 = stringBuilder.length() == 0 ? string3 : stringBuilder + " " + string3;
                double d3 = smoothFontRenderer.N(string4);
                if (d3 > d && stringBuilder.length() > 0) {
                    ++n2;
                    stringBuilder = new StringBuilder(string3);
                    continue;
                }
                stringBuilder = new StringBuilder(string4);
            }
            n += n2;
        }
        return (double)n * d2;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void k(double d) {
        this.o = d;
    }

    @Override
    public double x() {
        return this.o;
    }

    @Override
    public void I() {
    }

    public MultilineTextBlockComponent(String string, String string2) {
        this.Q = MultilineTextBlockComponent.J.T;
        this.qi = MultilineTextBlockComponent.J.X;
        this.o = 110.0;
        this.q5 = string;
        this.q1 = string2;
        this.q4 = "info";
        this.qg = null;
        this.qQ = true;
        this.b = MultilineTextBlockComponent.J.m;
        this.O = MultilineTextBlockComponent.J.A;
        this.q9 = MultilineTextBlockComponent.J.Z;
    }

    @Override
    public void u() {
    }

    private Color c$src$Ljava_awt_Color_$1hsw1vp() {
        return this.qQ ? J.z() : this.qg;
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.L();
        if (this.qs != null && this.qn > 0.0) {
            MousePosition mousePosition = RenderUtils.h();
            this.i = (double)mousePosition.O >= this.G && (double)mousePosition.O <= this.G + this.qn && (double)mousePosition.H >= this.q8 && (double)mousePosition.H <= this.q8 + this.qx;
        } else {
            this.i = false;
        }
        GuiRenderPrimitives.e(d, d2, d3, d4, this.b, false, 2.5f, 1.0f);
        double d5 = d + 2.0 + 8.0;
        double d6 = d2 + 6.0;
        double d7 = d3 - 2.0 - 16.0;
        Color color = this.c$src$Ljava_awt_Color_$1hsw1vp();
        double d8 = 7.0;
        RenderUtils.m(d, d2, 2.0, d4);
        GuiRenderPrimitives.p(d, d2, d8, d4, color, false, 2.5f, 1.0f, 8.0f, MultilineTextBlockComponent.J.u, 9);
        RenderUtils.T();
        double d9 = d6 + 0.5;
        ImageRenderer.E(color, (float)d5, (float)d9, this.q4, 7.0f, 7.0f, false);
        double d10 = d5 + 7.0 + 3.0;
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.75);
        double d11 = smoothFontRenderer.d("A");
        double d12 = d9 + 3.5 - d11 / 2.0;
        smoothFontRenderer.d(this.q5, d10, d12, this.O);
        double d13 = d6 + 7.0 + 2.0;
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.75);
        this.U(smoothFontRenderer2, this.q1, d5, d13, d7, this.q9);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.qs == null || this.q0 == null || this.qn <= 0.0) {
            return;
        }
        if (guiMouseEvent.getAction() != MouseButton.LEFT_CLICK) {
            return;
        }
        if ((double)guiMouseEvent.getX() >= this.G && (double)guiMouseEvent.getX() <= this.G + this.qn && (double)guiMouseEvent.getY() >= this.q8 && (double)guiMouseEvent.getY() <= this.q8 + this.qx) {
            this.q0.run();
            guiMouseEvent.setCancelled(true);
        }
    }

    private void U(SmoothFontRenderer smoothFontRenderer, String string, double d, double d2, double d3, Color color) {
        Object object;
        if (d3 <= 0.0) {
            d3 = this.o - 2.0 - 16.0;
        }
        double d4 = smoothFontRenderer.d("A") + 1.0;
        double d5 = smoothFontRenderer.N(" ");
        int n = this.qs != null ? string.indexOf(this.qs) : -1;
        int n2 = n >= 0 ? n + this.qs.length() : -1;
        this.G = 0.0;
        this.q8 = 0.0;
        this.qn = 0.0;
        this.qx = 0.0;
        double d6 = d;
        double d7 = d2;
        int n3 = 0;
        String[] stringArray = string.split("\n", -1);
        for (int i = 0; i < stringArray.length; ++i) {
            String string2 = stringArray[i];
            if (i > 0) {
                d6 = d;
                d7 += d4;
            }
            if (string2.isEmpty()) {
                ++n3;
                continue;
            }
            String[] stringArray2 = string2.split(" ");
            for (int j = 0; j < stringArray2.length; ++j) {
                boolean bl;
                object = stringArray2[j];
                double d8 = smoothFontRenderer.N((String)object);
                if (d6 + d8 > d + d3 && d6 > d) {
                    d6 = d;
                    d7 += d4;
                }
                int n4 = n3 + ((String)object).length();
                boolean bl2 = bl = n >= 0 && n3 < n2 && n4 > n;
                if (bl) {
                    Color color2 = this.i ? this.qi : this.Q;
                    smoothFontRenderer.d((String)object, d6, d7, color2);
                    if (this.qn == 0.0) {
                        this.G = d6;
                        this.q8 = d7;
                        this.qx = d4;
                    }
                    this.qn = d6 + d8 - this.G;
                } else {
                    smoothFontRenderer.d((String)object, d6, d7, color);
                }
                d6 += d8 + d5;
                n3 = n4 + 1;
            }
            if (i >= stringArray.length - 1) continue;
            n3 = n3 - 1 + 1;
        }
        if (this.qs != null && this.qn > 0.0) {
            double d9 = smoothFontRenderer.d("A");
            double d10 = this.q8 + d9;
            object = this.i ? this.qi : this.Q;
            GuiRenderPrimitives.z(this.G, d10, this.G + this.qn, d10, 1.0, 1.5, (Color)object);
        }
    }

    @Override
    public void F() {
    }

    public void j(Color color, Color color2) {
        this.Q = color;
        this.qi = color2;
    }

    public MultilineTextBlockComponent(String string, String string2, String string3, Color color, Color color2, Color color3, Color color4) {
        this.Q = MultilineTextBlockComponent.J.T;
        this.qi = MultilineTextBlockComponent.J.X;
        this.o = 110.0;
        this.q5 = string;
        this.q1 = string2;
        this.q4 = string3;
        this.qg = color;
        this.qQ = false;
        this.b = color2;
        this.O = color3;
        this.q9 = color4;
    }

    public MultilineTextBlockComponent(String string, String string2, Color color) {
        this.Q = MultilineTextBlockComponent.J.T;
        this.qi = MultilineTextBlockComponent.J.X;
        this.o = 110.0;
        this.q5 = string;
        this.q1 = string2;
        this.q4 = "info";
        this.qg = color;
        this.qQ = false;
        this.b = MultilineTextBlockComponent.J.m;
        this.O = MultilineTextBlockComponent.J.A;
        this.q9 = MultilineTextBlockComponent.J.Z;
    }
}

