package gg.vape.ui.click.component;

import gg.vape.Vape;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class DropdownSelectOptionComponent
extends InteractiveComponent {
    private Color b;
    private boolean Q;
    protected double OA;
    private boolean v;
    protected String K;
    private boolean I;

    public double T$src$D$fk51np() {
        SmoothFontRenderer smoothFontRenderer = this.I ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.OA) : this.O(this.OA);
        double d = smoothFontRenderer.d(this.K);
        return this.n() + this.L() / 2.0 - d / 2.0;
    }

    public void K(boolean bl) {
        this.v = bl;
    }

    public boolean P$src$Z$fhxvt7() {
        return this.Q;
    }

    public DropdownSelectOptionComponent(String string) {
        this(string, 0.9);
    }


    public void a(boolean bl) {
        this.Q = bl;
    }

    @Override
    public void u() {
        if (this.A() < 0.0) {
            this.o(this.r$src$D$g0mvgj());
        }
    }

    public DropdownSelectOptionComponent Z$src$Lgg_vape_ui_click_component_DropdownSelectOption$8kn55b(boolean bl) {
        this.I = bl;
        return this;
    }

    public boolean F$src$Z$fcfxvl() {
        return this.I;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public DropdownSelectOptionComponent(String string, double d, double d2, double d3) {
        this.b = DropdownSelectOptionComponent.J.Z;
        this.K = string;
        this.OA = d;
        this.o(d2);
        this.Y(d3);
    }

    public DropdownSelectOptionComponent(String string, double d) {
        this.b = DropdownSelectOptionComponent.J.Z;
        this.K = string;
        this.OA = d;
    }

    public Color q$src$Ljava_awt_Color_$1xdob5y() {
        return this.b;
    }

    public double k() {
        SmoothFontRenderer smoothFontRenderer = this.I ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.OA) : this.O(this.OA);
        return smoothFontRenderer.d(this.K.toUpperCase());
    }

    public DropdownSelectOptionComponent b(Color color) {
        this.b = color;
        return this;
    }

    public void W(double d) {
        this.OA = d;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer;
        double d;
        double d2;
        String string = this.K;
        SmoothFontRenderer smoothFontRenderer2 = this.I ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.OA) : this.O(this.OA);
        double d3 = this.T$src$D$fk51np();
        if (this.w$src$Z$e457mb()) {
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + 0.5, this.n(), this.A() - 0.5, this.L(), DropdownSelectOptionComponent.J.m);
        }
        Color color = this.b != null ? (this.w$src$Z$e457mb() ? this.b.brighter() : this.b) : (this.w$src$Z$e457mb() ? DropdownSelectOptionComponent.J.A : DropdownSelectOptionComponent.J.Z);
        double d4 = this.G$src$D$1b2f02a();
        this.getClass();
        smoothFontRenderer2.d(string, d4 + 5.0, d3, color);
        if (this.Q) {
            double d5 = this.G$src$D$1b2f02a() + smoothFontRenderer2.N(string);
            this.getClass();
            d2 = d5 + (double)(5.0f * 2.0f);
            d = d3 + 0.5;
            GuiRenderPrimitives.d(d2, d, 20.0, 7.0, J.z());
            smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.8, false);
            smoothFontRenderer.d("New!", d2 + 3.0, d + 0.5, ColorUtil.r(J.z(), 35, 255));
        }
        if (this.v) {
            double d6 = this.G$src$D$1b2f02a() + smoothFontRenderer2.N(string);
            this.getClass();
            d2 = d6 + (double)(5.0f * 2.0f);
            if (this.Q) {
                d2 += 24.0;
            }
            d = d3 + 0.5;
            smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.8, false);
            double d7 = smoothFontRenderer.N("Beta") + 6.0;
            GuiRenderPrimitives.d(d2, d, d7, 7.0, J.z());
            smoothFontRenderer.d("Beta", d2 + 3.0, d + 0.5, ColorUtil.r(J.z(), 35, 255));
        }
    }

    public String P() {
        return this.K;
    }

    public boolean u$src$Z$g2a9rk() {
        return this.v;
    }

    @Override
    public double C() {
        return 0.0;
    }

    public DropdownSelectOptionComponent E(String string) {
        this.K = string;
        this.o(this.r$src$D$g0mvgj());
        return this;
    }

    public double r$src$D$g0mvgj() {
        SmoothFontRenderer smoothFontRenderer = this.I ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.OA) : this.O(this.OA);
        return smoothFontRenderer.N(this.K.toUpperCase());
    }

    @Override
    public void I() {
    }
}

