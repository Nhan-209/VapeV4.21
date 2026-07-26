package gg.vape.ui.click.component.layout;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.layout.ComponentLayout;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class PaddedComponent
extends FrameComponent {
    private final double Y5;
    private static final String eb = "wrap";
    private final double Yh;
    private double Yc;
    private final SpacerComponent Ya;
    private final FrameComponent YB = new PanelComponent(0.0, 0.0);
    private final double YO;
    private final SpacerComponent Yn;
    private final GuiComponent YL;

    public FrameComponent z$src$Lgg_vape_ui_click_frame_FrameComponent_$s47o9d() {
        return this.YB;
    }

    @Override
    public double x() {
        return this.YB.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() + this.YO;
    }

    @Override
    public void C$src$V$nadrmg() {
        super.C$src$V$nadrmg();
        this.YB.C$src$V$nadrmg();
    }

    public GuiComponent H$src$Lgg_vape_ui_click_component_GuiComponent_$kfnvup() {
        return this.YL;
    }

    @Override
    public double C() {
        this.YB.Y(this.YB.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y());
        this.YB.o(this.YB.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C());
        return this.YB.L() + this.Yc + this.Yh;
    }

    @Override
    public void v() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public PaddedComponent(double d, double d2, double d3, double d4, GuiComponent guiComponent) {
        this.Yc = d;
        this.Yh = d2;
        this.Y5 = d3;
        this.YO = d4;
        this.YL = guiComponent;
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
        componentLayout.M(eb);
        this.Yn = new SpacerComponent(0.0, d);
        this.Ya = new SpacerComponent(d3, 0.0);
        this.YB.H(this.Ya, guiComponent);
        this.H(this.Yn, this.YB);
        this.YB.d(false);
        this.d(false);
    }

    public double o$src$D$1nnrfcl() {
        return this.YO;
    }

    public double h() {
        return this.Yh;
    }

    @Override
    public void Y() {
    }

    public void N(double d) {
        this.Yc = d;
        this.Yn.Y(d);
        this.Yn.d(true);
        this.Yn.T(Color.magenta);
    }

    public double c$src$D$1nh5w89() {
        return this.Y5;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public PaddedComponent(double d, GuiComponent guiComponent) {
        this(d, d, guiComponent);
    }

    @Nullable
    public <T extends GuiComponent> T t(Class<T> clazz) {
        return (T)(clazz.isInstance(this.YL) ? (GuiComponent)clazz.cast(this.YL) : null);
    }

    @Override
    public void V() {
    }

    public PaddedComponent(double d, double d2, GuiComponent guiComponent) {
        this(d2, d2, d, d, guiComponent);
    }
}

