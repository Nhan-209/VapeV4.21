package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorChannelSliderComponent;
import gg.vape.ui.click.component.value.ColorChannelType;
import gg.vape.ui.click.component.value.SearchBlockEditorEnabledSyncMouseListener;
import gg.vape.ui.click.component.value.SearchBlockEditorMouseListener;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.ui.unmap.SearchBlock;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import java.awt.Color;

public class SearchBlockEditorComponent
extends GuiComponent {
    private boolean i;
    private final BooleanToggleComponent o;
    private boolean K;
    private final BooleanValue Q = BooleanValue.create(null, "", false);
    private final SquareIconButtonComponent G;
    private final ColorChannelSliderComponent I;
    private final ColorValue rE;
    private final ColorChannelSliderComponent a;
    private final SearchBlock R;
    private final BooleanValue b;
    private final ColorChannelSliderComponent O;
    private final BooleanToggleComponent v = new BooleanToggleComponent(this.Q);

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if ((double)guiMouseEvent.getY() > this.n() + 20.0 - 2.0) {
            return;
        }
        this.K = !this.K;
        this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
    }

    public boolean k() {
        return this.v.i$src$Z$1d37ezg();
    }

    @Override
    public void F() {
        this.i = true;
    }

    @Override
    public double C() {
        return (this.K ? 110.0 : 18.0) + 3.0;
    }

    public SearchBlockEditorComponent g(GuiClickListener guiClickListener) {
        this.G.r(guiClickListener);
        return this;
    }

    @Override
    public void u() {
        if (this.i && !this.w$src$Z$e457mb()) {
            this.i = false;
        }
    }

    public SearchBlockEditorComponent(SearchBlock searchBlock) {
        this.b = BooleanValue.create(null, "Tracers", false);
        this.o = new BooleanToggleComponent(this.b);
        this.G = new SquareIconButtonComponent("newclose");
        this.R = searchBlock;
        this.rE = ColorValue.L(null, searchBlock.d(), searchBlock.B());
        this.a = new ColorChannelSliderComponent(ColorChannelType.RAINBOW, this.rE, "Color", 1.0);
        this.a.H((GuiComponent)this);
        this.O = new ColorChannelSliderComponent(ColorChannelType.SATURATION, this.rE, "", 1.0);
        this.I = new ColorChannelSliderComponent(ColorChannelType.VIBRANCE, this.rE, "", 1.0);
        this.a.P(true);
        this.a.o(98.0);
        this.O.P(true);
        this.O.o(98.0);
        this.I.P(true);
        this.I.o(98.0);
        this.a.T(SearchBlockEditorComponent.J.m);
        this.O.T(SearchBlockEditorComponent.J.m);
        this.I.T(SearchBlockEditorComponent.J.m);
        this.Q.o(searchBlock.T());
        this.v.Q$src$V$11xzx98();
        this.v.j(new SearchBlockEditorEnabledSyncMouseListener(this, searchBlock));
        this.b.o(searchBlock.W());
        this.o.Q$src$V$11xzx98();
        this.o.j(new SearchBlockEditorMouseListener(this, searchBlock));
        searchBlock.c(this.rE.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().getRGB());
        this.H(this.v, this.G, this.o, this.a, this.O, this.I);
    }

    public static BooleanValue q(SearchBlockEditorComponent searchBlockEditorComponent) {
        return searchBlockEditorComponent.b;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        String string = this.R.d();
        double d = smoothFontRenderer.d(string);
        double d2 = 18.0;
        double d3 = this.L() - 3.0;
        Color color = SearchBlockEditorComponent.J.Z;
        double d4 = this.G$src$D$1b2f02a();
        this.getClass();
        double d5 = d4 + 5.0 - 0.5;
        double d6 = this.n() + 1.0 - 0.5;
        double d7 = this.A();
        this.getClass();
        GuiRenderPrimitives.d(d5, d6, d7 - (double)(5.0f * 2.0f) + 1.0, d3 - 2.0 + 1.0, this.R.B());
        double d8 = this.G$src$D$1b2f02a();
        this.getClass();
        double d9 = d8 + 5.0;
        double d10 = this.n() + 1.0;
        double d11 = this.A();
        this.getClass();
        GuiRenderPrimitives.d(d9, d10, d11 - (double)(5.0f * 2.0f), d3 - 2.0, SearchBlockEditorComponent.J.m);
        float f = (float)this.G$src$D$1b2f02a();
        this.getClass();
        float f2 = f + 5.0f * 2.0f;
        double d12 = this.n();
        this.getClass();
        ImageRenderer.E(Color.WHITE, f2, (float)(d12 + (double)(5.0f * 2.0f)) - 2.0f, this.K ? "upcollapse" : "downexpand", 2.0f, 2.0f, false);
        double d13 = this.G$src$D$1b2f02a() + this.A() - 30.0;
        double d14 = this.n() + d2 / 2.0 - d / 2.0;
        RenderUtils.m(this.G$src$D$1b2f02a(), this.n(), d13 - this.G$src$D$1b2f02a(), d2);
        double d15 = this.G$src$D$1b2f02a();
        this.getClass();
        double d16 = d15 + (double)(5.0f * 3.0f);
        this.getClass();
        smoothFontRenderer.d(string, d16 + 8.0, d14, color);
        RenderUtils.T();
        double d17 = smoothFontRenderer.d(string);
        double d18 = d13;
        double d19 = d18 - 10.0;
        double d20 = d14 - 2.0;
        double d21 = d20 + d17 + 2.0;
        RenderUtils.X(new Color(SearchBlockEditorComponent.J.m.getRed(), SearchBlockEditorComponent.J.m.getGreen(), SearchBlockEditorComponent.J.m.getBlue(), 0), SearchBlockEditorComponent.J.m, d18, d21, d18, d20, d19, d20, d19, d21);
        double d22 = this.G$src$D$1b2f02a() + this.A();
        this.getClass();
        double d23 = d22 - 5.0 * 1.5;
        this.getClass();
        this.G.K(d23 - 8.0);
        this.G.S(this.n());
        this.G.Y(d2);
        this.v.d(false);
        this.v.K(d13);
        this.v.S(this.n());
        this.v.o(12.0);
        this.v.Y(d2);
        this.v.P(true);
        this.a.Z(this.K);
        this.O.Z(this.K);
        this.I.Z(this.K);
        this.o.Z(this.K);
        this.a.K(this.G$src$D$1b2f02a() + 6.0);
        this.O.K(this.G$src$D$1b2f02a() + 6.0);
        this.I.K(this.G$src$D$1b2f02a() + 6.0);
        this.a.S(this.n() + 16.0);
        this.O.S(this.n() + 40.0);
        this.I.S(this.n() + 65.0);
        this.o.d(false);
        this.o.K(this.G$src$D$1b2f02a() + 8.0);
        this.o.S(90.0 + this.n());
        this.o.o(this.A() - 16.0);
        this.o.P(true);
        this.o.Y(12.0);
        this.R.c(this.rE.HSBtoRGB());
    }


    public static BooleanValue S(SearchBlockEditorComponent searchBlockEditorComponent) {
        return searchBlockEditorComponent.Q;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void a(boolean bl) {
        this.v.h(bl);
    }

    @Override
    public void I() {
    }
}

