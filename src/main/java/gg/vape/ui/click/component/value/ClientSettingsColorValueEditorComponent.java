package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.value.ColorChannelSliderComponent;
import gg.vape.ui.click.component.value.ColorChannelType;
import gg.vape.ui.click.component.value.ColorPaletteSliderComponent;
import gg.vape.ui.click.component.value.ColorPreviewSwatchComponent;
import gg.vape.ui.click.component.value.ColorValueEditorExpandToggleClickHandler;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.ColorValue;
import java.awt.Color;

public class ClientSettingsColorValueEditorComponent
extends GuiComponent {
    private ColorPaletteSliderComponent R;
    private ColorChannelSliderComponent a;
    private static String[] i;
    private IconButtonComponent v = new IconButtonComponent("upcollapse", 0.3);
    private boolean I = true;
    private ColorChannelSliderComponent G;
    private final ColorPreviewSwatchComponent K;
    private ColorValue o;
    private ColorChannelSliderComponent Q;

    @Override
    public void F() {
    }

    static {
        ClientSettingsColorValueEditorComponent.k(null);
    }

    static boolean p(ClientSettingsColorValueEditorComponent clientSettingsColorValueEditorComponent, boolean bl) {
        clientSettingsColorValueEditorComponent.I = bl;
        return clientSettingsColorValueEditorComponent.I;
    }

    @Override
    public void H() {
        this.onDisable();
        this.R.o(this.A());
        this.Q.o(this.A());
        this.a.o(this.A());
        this.G.o(this.A());
        this.R.K(this.G$src$D$1b2f02a());
        this.R.S(this.n());
        this.v.K(this.G$src$D$1b2f02a() + this.R.F$src$D$1e98w1u() + 5.0);
        this.v.S(this.n() + 2.5);
        this.v.Y(this.R.L() / 2.0);
        this.v.H(this.I ? "downexpand" : "upcollapse");
        ImageRenderer.E(this.o.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), (float)(this.G$src$D$1b2f02a() + this.A() - 5.0 - 6.0), (float)this.n() + 5.0f, "colorpreview", 6.0f, 6.0f, false);
        this.K.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 5.0 - 6.0);
        this.K.S(this.n() + 5.0);
        if (this.I) {
            this.Q.Z(false);
            this.a.Z(false);
            this.G.Z(false);
        } else {
            float f = 7.0f;
            double d = this.R.A$src$Lfunc_skidline_RectData_$x2qn2p().o() - (double)(f / 2.0f);
            double d2 = this.n() + this.R.L() + 2.5 + 3.0;
            double d3 = d + (double)f;
            double d4 = d2 - 3.0;
            double d5 = d + (double)(f * 2.0f);
            double d6 = d2;
            GuiRenderPrimitives.U(d, d2, d3, d4, d5, d6, ClientSettingsColorValueEditorComponent.J.r);
            this.Q.Z(true);
            this.Q.K(this.G$src$D$1b2f02a());
            this.Q.S(this.n() + 20.0 + 5.0);
            this.a.Z(true);
            this.a.K(this.G$src$D$1b2f02a());
            this.a.S(this.n() + 40.0 + 10.0);
            this.G.Z(true);
            this.G.K(this.G$src$D$1b2f02a());
            this.G.S(this.n() + 60.0 + 15.0);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void I() {
    }

    public void j$src$V$104url0() {
        this.R.k();
    }

    public ClientSettingsColorValueEditorComponent(ColorValue colorValue) {
        this.o = colorValue;
        this.C(colorValue);
        this.R = new ColorPaletteSliderComponent("GUI Theme", colorValue, new Color[]{new Color(250, 50, 56), new Color(242, 99, 33), new Color(252, 179, 22), ClientSettingsColorValueEditorComponent.J.B, new Color(47, 122, 229), new Color(126, 84, 217), new Color(232, 96, 152)});
        this.Q = new ColorChannelSliderComponent(this.R);
        this.a = new ColorChannelSliderComponent(ColorChannelType.SATURATION, colorValue);
        this.G = new ColorChannelSliderComponent(ColorChannelType.VIBRANCE, colorValue);
        this.K = new ColorPreviewSwatchComponent(colorValue);
        this.R.T(this.d());
        this.Q.T(ClientSettingsColorValueEditorComponent.J.r);
        this.a.T(ClientSettingsColorValueEditorComponent.J.r);
        this.G.T(ClientSettingsColorValueEditorComponent.J.r);
        this.R.M(null);
        this.a.M(null);
        this.G.M(null);
        this.v.r(new ColorValueEditorExpandToggleClickHandler(this));
        this.H(this.R, this.v, this.K, this.Q, this.a, this.G);
    }

    public static void k(String[] stringArray) {
        i = stringArray;
    }

    public static String[] v() {
        return i;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double C() {
        return this.I ? 25.0 : 100.0;
    }

    @Override
    public void u() {
    }

    static boolean Q(ClientSettingsColorValueEditorComponent clientSettingsColorValueEditorComponent) {
        return clientSettingsColorValueEditorComponent.I;
    }

    @Override
    public void Z(boolean bl) {
        super.Z(bl);
        this.R.s(false);
        this.R.l$src$V$1eu5312();
    }

    @Override
    public double x() {
        return 110.0;
    }
}

