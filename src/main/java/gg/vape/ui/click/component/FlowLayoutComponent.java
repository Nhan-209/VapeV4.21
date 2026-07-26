package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.layout.WrappingFlowLayout;

public class FlowLayoutComponent
extends FrameComponent {
    private final WrappingFlowLayout qD;
    private static final String fb;
    private static String qp;

    @Override
    public void Y() {
    }

    @Override
    public void v() {
    }

    @Override
    public void V() {
    }

    static {
        FlowLayoutComponent.j("cAcODb");
        fb = "wrap, widthwrap";
    }

    public static String z$src$Ljava_lang_String_$1izdxzn() {
        return qp;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void h() {
    }

    public FlowLayoutComponent(double d) {
        this(d, 0.0);
    }

    public FlowLayoutComponent(double d, double d2) {
        this.Y(d2);
        this.qD = new WrappingFlowLayout(this);
        this.qD.o(d);
        this.qD.M(fb);
        this.N(this.qD);
    }

    @Override
    public double x() {
        return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C();
    }

    public void B(double d) {
        this.qD.o(d);
    }

    public double i() {
        double d = 0.0;
        if (!this.V$src$Z$1xhop3l()) {
            return d;
        }
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            d += guiComponent.A();
        }
        return d;
    }

    @Override
    public double C() {
        return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
    }

    public static void j(String string) {
        qp = string;
    }
}

