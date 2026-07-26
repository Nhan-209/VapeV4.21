package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class StringMapEntryComponent
extends GuiComponent {
    private int a = (int)O;
    private boolean b = true;
    private static final String G = "newclose";
    private SquareIconButtonComponent i = new SquareIconButtonComponent(G);
    private String v;
    private boolean K;
    private String I;
    private static final long O = -3947651445599240187L;

    public StringMapEntryComponent N(GuiClickListener guiClickListener) {
        this.i.r(guiClickListener);
        return this;
    }

    @Override
    public double C() {
        return 17.5;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void r(boolean bl) {
        this.b = bl;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.8);
        Color color = this.K ? StringMapEntryComponent.J.A : StringMapEntryComponent.J.Z;
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 2.0, this.n() + 1.0, this.A() - 10.0, this.L() - 2.0, StringMapEntryComponent.J.m);
        if (this.K) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 2.0, this.n() + 1.5, this.A() - 10.0 - 1.0, this.L() - 3.0, StringMapEntryComponent.J.i);
        }
        smoothFontRenderer.d(this.I, this.G$src$D$1b2f02a() + 8.0, this.n() + 3.0, color);
        smoothFontRenderer.d(this.v, this.G$src$D$1b2f02a() + 8.0, this.n() + 9.0, color.darker());
        this.i.K(this.G$src$D$1b2f02a() + this.A() - 22.0);
        this.i.S(this.n());
        this.i.Y(this.L());
    }

    @Override
    public void I() {
    }

    @Override
    public void u() {
        if (this.K && !this.w$src$Z$e457mb()) {
            this.K = false;
        }
    }

    public SquareIconButtonComponent s() {
        return this.i;
    }

    @Override
    public void F() {
        this.K = true;
    }

    public String x$src$Ljava_lang_String_$18ql3qa() {
        return this.I;
    }

    public boolean v() {
        return this.K;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.b = !this.b;
    }

    public StringMapEntryComponent(String string, String string2) {
        this.I = string;
        this.v = string2;
        this.H(this.i);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

