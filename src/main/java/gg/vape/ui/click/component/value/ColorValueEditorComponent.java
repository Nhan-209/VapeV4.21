package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.value.ColorChannelSliderComponent;
import gg.vape.ui.click.component.value.ColorChannelType;
import gg.vape.ui.click.component.value.ColorPreviewSwatchComponent;
import gg.vape.ui.click.component.value.ColorValueEditorToggleExpandedClickHandler;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ColorValue;
import java.awt.Color;

public class ColorValueEditorComponent
extends GuiComponent {
    private ColorChannelSliderComponent G;
    private boolean i = true;
    private boolean O;
    private ColorChannelSliderComponent v;
    private ColorChannelSliderComponent o;
    private IconButtonComponent I = new IconButtonComponent("upcollapse", 0.3);
    private final ColorPreviewSwatchComponent b;
    private ColorChannelSliderComponent R;
    private static String a;
    private ColorValue K;

    @Override
    public void I() {
    }

    @Override
    public void H() {
        this.onDisable();
        this.v.K(this.G$src$D$1b2f02a());
        this.v.S(this.n());
        this.I.K(this.G$src$D$1b2f02a() + this.v.c$src$D$35vrdj() + 5.0);
        this.I.S(this.n() + 2.5 - 2.0);
        this.I.Y(this.v.L() / 2.0);
        this.I.o(10.0);
        this.I.H(this.i ? "downexpand" : "upcollapse");
        ImageRenderer.E(this.K.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), (float)(this.G$src$D$1b2f02a() + this.A() - 5.0 - 6.0), (float)this.n() + 5.0f, "colorpreview", 6.0f, 6.0f, false);
        this.b.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 5.0 - 6.0);
        this.b.S(this.n() + 5.0);
        if (this.i) {
            this.R.Z(false);
            this.G.Z(false);
            this.o.Z(false);
        } else {
            this.R.Z(true);
            this.R.K(this.G$src$D$1b2f02a());
            this.R.S(this.n() + 20.0 + 5.0);
            this.G.Z(true);
            this.G.K(this.G$src$D$1b2f02a());
            this.G.S(this.n() + 40.0 + 10.0);
            this.o.Z(true);
            this.o.K(this.G$src$D$1b2f02a());
            this.o.S(this.n() + 60.0 + 15.0);
        }
    }

    @Override
    public void P(boolean bl) {
        super.P(bl);
        this.v.P(bl);
        this.R.P(bl);
        this.G.P(bl);
        this.o.P(bl);
    }

    @Override
    public void F() {
        MousePosition mousePosition = RenderUtils.h();
    }

    public static String P() {
        return a;
    }

    public static void x(String string) {
        a = string;
    }

    public ColorValueEditorComponent(ColorValue colorValue) {
        this.K = colorValue;
        this.C(colorValue);
        this.v = new ColorChannelSliderComponent(ColorChannelType.RAINBOW, colorValue);
        this.v.H((GuiComponent)this);
        this.R = new ColorChannelSliderComponent(ColorChannelType.SATURATION, colorValue);
        this.G = new ColorChannelSliderComponent(ColorChannelType.VIBRANCE, colorValue);
        this.o = new ColorChannelSliderComponent(ColorChannelType.OPACITY, colorValue);
        this.b = new ColorPreviewSwatchComponent(colorValue);
        this.v.T(Color.WHITE);
        this.R.T(ColorValueEditorComponent.J.r);
        this.G.T(ColorValueEditorComponent.J.r);
        this.o.T(ColorValueEditorComponent.J.r);
        this.R.M(null);
        this.G.M(null);
        this.o.M(null);
        this.I.r(new ColorValueEditorToggleExpandedClickHandler(this));
        this.H(this.I, this.b, this.v, this.R, this.G, this.o);
    }

    static {
        ColorValueEditorComponent.x(null);
    }

    @Override
    public void u() {
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (this.O) {
            return;
        }
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l() || !guiComponent.w$src$Z$e457mb()) continue;
            guiComponent.D(guiMouseEvent);
            if (guiComponent instanceof FrameHeaderComponent) continue;
            return;
        }
    }

    @Override
    public void q(double d) {
        super.q(d);
        this.v.q(d);
        this.R.q(d);
        this.G.q(d);
        this.o.q(d);
    }

    @Override
    public void o(double d) {
        super.o(d);
        this.v.o(d);
        this.R.o(d);
        this.G.o(d);
        this.o.o(d);
    }

    @Override
    public double x() {
        return 110.0;
    }

    public static boolean h(ColorValueEditorComponent colorValueEditorComponent, boolean bl) {
        colorValueEditorComponent.i = bl;
        return colorValueEditorComponent.i;
    }

    public static boolean M(ColorValueEditorComponent colorValueEditorComponent) {
        return colorValueEditorComponent.i;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double C() {
        return this.i ? 25.0 : 104.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

