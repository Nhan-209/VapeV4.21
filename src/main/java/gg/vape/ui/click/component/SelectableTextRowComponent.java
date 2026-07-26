package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.ToggleableListEntry;
import java.awt.Color;

public class SelectableTextRowComponent
extends GuiComponent {
    private float i = 5.0f;
    private float Q = 0.0f;
    private boolean v = true;
    private TruncatedTextComponent O;
    private static final long mb = 5225434907995537413L;
    private ToggleableListEntry I;
    private String a;
    private String o;
    private int G;
    private SquareIconButtonComponent Ai;
    private Color R;
    private int b = (int)mb;
    private Color K;

    public void i(boolean bl) {
        this.v = bl;
    }

    public SelectableTextRowComponent B(float f) {
        this.Q = f;
        return this;
    }

    public SelectableTextRowComponent I(GuiClickListener guiClickListener) {
        this.Ai.r(guiClickListener);
        return this;
    }

    public SelectableTextRowComponent d(int n) {
        this.b = n;
        return this;
    }

    public boolean l() {
        return this.I != null ? this.I.q() : this.v;
    }

    public SquareIconButtonComponent x$src$Lgg_vape_ui_click_component_SquareIconButtonComp$122v8iw() {
        return this.Ai;
    }

    public SelectableTextRowComponent(Color color, String string) {
        this(color, string, -1);
    }

    public SelectableTextRowComponent s(ToggleableListEntry toggleableListEntry) {
        this.I = toggleableListEntry;
        return this;
    }

    public String g$src$Ljava_lang_String_$n6442u() {
        return this.o;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SelectableTextRowComponent(Color color, String string, String string2) {
        this(color, string, -1);
        this.a = string2;
    }

    public boolean u$src$Z$1dafklf() {
        return this.w$src$Z$e457mb();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        boolean bl = this.v = !this.v;
        if (this.I != null) {
            this.I.z();
        }
    }

    public SelectableTextRowComponent(Color color, String string, int n) {
        this.Ai = new SquareIconButtonComponent("newclose");
        this.K = SelectableTextRowComponent.J.m;
        this.R = color;
        this.o = string;
        this.G = n;
        this.O = new TruncatedTextComponent(this.g$src$Ljava_lang_String_$n6442u(), "...", this.A() - 30.0, 0.9, SelectableTextRowComponent.J.Z, false);
        this.O.S(false);
        this.H(this.O, this.Ai);
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d = smoothFontRenderer.d(this.g$src$Ljava_lang_String_$n6442u());
        Color color = this.l() ? (this.w$src$Z$e457mb() ? SelectableTextRowComponent.J.A : SelectableTextRowComponent.J.Z) : (this.w$src$Z$e457mb() ? SelectableTextRowComponent.J.Z : SelectableTextRowComponent.J.h);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + this.k(), this.n() + 1.0, this.A() - this.k() * 2.0, this.L() - 2.0, this.K);
        if (this.w$src$Z$e457mb()) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + this.k() + 0.5, this.n() + 1.5, this.A() - this.k() * 2.0 - 1.0, this.L() - 3.0, SelectableTextRowComponent.J.i);
        }
        if (this.l()) {
            if (this.a != null) {
                GuiRenderPrimitives.F(this.a, this.G$src$D$1b2f02a() + this.k() + 5.0 + (double)(this.b / 2), this.n() + this.L() / 2.0 - (double)(this.b / 2) + (double)(this.b / 2) + (double)this.Q, (double)this.b, this.b, this.R);
            } else {
                GuiRenderPrimitives.V((float)(this.G$src$D$1b2f02a() + this.k() + 5.0), (float)(this.n() + this.L() / 2.0 - (double)(this.b / 2) + (double)this.Q), this.b, 0.5, this.R);
            }
        } else if (this.a != null) {
            GuiRenderPrimitives.F(this.a, this.G$src$D$1b2f02a() + this.k() + 5.0 + (double)(this.b / 2), this.n() + this.L() / 2.0 - (double)(this.b / 2) + (double)(this.b / 2) + (double)this.Q, (double)this.b, this.b, SelectableTextRowComponent.J.W);
        } else {
            GuiRenderPrimitives.m((float)(this.G$src$D$1b2f02a() + this.k() + 5.0), (float)(this.n() + this.L() / 2.0 - (double)(this.b / 2) + (double)this.Q), this.b, 1.0f, 0.5f, SelectableTextRowComponent.J.W);
        }
        this.O.R(color);
        this.O.D(this.A() - 30.0);
        this.O.K(this.G$src$D$1b2f02a() + this.k() + 15.0);
        this.O.S(this.n() + this.L() / 2.0 - d / 2.0);
        this.Ai.K(this.G$src$D$1b2f02a() + this.A() - this.k() - 5.0 - 8.0);
        this.Ai.S(this.n());
        this.Ai.Y(this.L());
    }

    public SelectableTextRowComponent R(Color color) {
        this.K = color;
        return this;
    }

    @Override
    public double C() {
        return 17.5;
    }

    public void i(float f) {
        this.i = f;
    }

    public void n(String string) {
        this.o = string;
        this.O.O(string);
    }

    public void g(String string) {
        this.a = string;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void W(Color color) {
        this.R = color;
    }

    private double k() {
        return this.i;
    }
}

