package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class LabeledTextInputComponent
extends TextInputComponentBase {
    private final TextLabel E4;
    private double Ee = 20.0;
    private boolean EW;
    private boolean Ea;
    private final SquareIconButtonComponent Eo;
    private String Eu;
    private final IconButtonComponent EV;
    protected IconButtonComponent E6 = new IconButtonComponent("newsearch", 0.75);
    private boolean E0;
    private boolean Ej;
    private double Eq = 110.0;

    @Override
    public void o(double d) {
        super.o(d);
        this.Eq = d;
    }

    private void lambda$new$0() {
        this.k("");
    }

    public boolean C$src$Z$1mh64l2() {
        return this.E0;
    }

    @Override
    public double C() {
        return this.Ee;
    }

    public void y(boolean bl) {
        this.Ej = bl;
    }

    public IconButtonComponent e() {
        return this.EV;
    }

    public IconButtonComponent v$src$Lgg_vape_ui_click_component_IconButtonComponent_$9khxxe() {
        return this.E6;
    }

    public LabeledTextInputComponent(String string, boolean bl, boolean bl2) {
        super(string);
        this.EV = new IconButtonComponent("newedit", 0.65f);
        this.E4 = new TextLabel("Done", 0.65, false);
        this.Eo = new SquareIconButtonComponent("newclose", 1.0);
        this.b.Z(false);
        this.EV.Z(false);
        this.E4.Z(false);
        this.E4.l(LabeledTextInputComponent.J.Z);
        this.Eo.Z(false);
        this.Eo.o(10.0);
        this.Eo.Y(10.0);
        this.Eo.r(this::lambda$new$0);
        this.H(this.E6, this.EV, this.E4, this.Eo);
        this.E0 = bl;
        this.Ej = bl2;
        this.Eu = string;
        if (bl2) {
            this.e(false);
        }
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SquareIconButtonComponent V$src$Lgg_vape_ui_click_component_SquareIconButtonComp$5pfk79() {
        return this.Eo;
    }

    public boolean i$src$Z$1n22b4s() {
        return this.Ea;
    }

    public TextLabel s() {
        return this.E4;
    }

    public boolean U$src$Z$1mr2f9k() {
        return this.Ej;
    }

    public LabeledTextInputComponent(String string) {
        this(string, true, false);
    }

    public void a(boolean bl) {
        this.E0 = bl;
    }

    @Override
    public double x() {
        return this.Eq;
    }

    @Override
    public void H() {
        double d;
        if (this.EW) {
            this.EV.Z(false);
            this.E6.Z(false);
            this.E4.Z(true);
            this.E4.K(this.G$src$D$1b2f02a() + this.p$src$D$187zcry() - (double)this.y() - this.E4.W() - 4.0);
            this.E4.S(this.n());
            this.E4.Y(this.L());
            this.E4.o(this.E4.W());
            this.E4.l(LabeledTextInputComponent.J.A);
            this.E4.c(true);
            this.E4.y(0.8);
            this.F(true);
            this.b("Editing favorites");
            this.A(LabeledTextInputComponent.J.I);
        } else {
            boolean bl;
            this.A(LabeledTextInputComponent.J.A);
            this.E4.Z(false);
            this.E6.Z(true);
            this.F(false);
            this.b(this.Eu);
            boolean bl2 = bl = this.i$src$Ljava_lang_String_$1n2xf3k() != null && !this.i$src$Ljava_lang_String_$1n2xf3k().isEmpty();
            if (bl) {
                this.EV.Z(false);
                this.Eo.Z(true);
                double d2 = this.G$src$D$1b2f02a() + this.p$src$D$187zcry() - (double)this.y() - this.Eo.A() - 4.0;
                this.Eo.K(d2);
                this.Eo.S(this.n() + (this.L() - this.Eo.L()) / 2.0);
            } else {
                this.Eo.Z(false);
                this.EV.Z(this.Ea);
                if (this.Ea) {
                    this.EV.K(this.G$src$D$1b2f02a() + this.p$src$D$187zcry() - (double)this.y() - 8.0 - 6.0);
                    this.EV.S(this.n());
                    this.EV.Y(this.L());
                    this.EV.o(8.0);
                    Color color = this.xR.getInterpolatedColor();
                    this.EV.o(new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(color.getAlpha() - 6, 0)));
                }
            }
        }
        double d3 = this.Ea && !this.EW ? 12.0 : 0.0;
        double d4 = d = this.EW ? this.E4.W() + 4.0 : 0.0;
        if (!this.EW) {
            if (this.E0) {
                this.E6.K(this.G$src$D$1b2f02a() + this.p$src$D$187zcry() - (double)this.y() - 8.0 - 6.0 - d3);
            } else {
                this.E6.K(this.G$src$D$1b2f02a() + (double)this.y() + 5.0);
            }
            this.E6.S(this.n());
            this.E6.Y(this.L());
            this.E6.o(8.0);
        }
        if (this.Ej) {
            this.I(LabeledTextInputComponent.J.Z);
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a() + (double)this.y(), this.n() + (double)(this.O() / 2.0f), this.p$src$D$187zcry() - (double)(this.y() * 2.0f), this.L() - (double)this.O(), this.v() ? LabeledTextInputComponent.J.y.darker() : LabeledTextInputComponent.J.y, 2.0f, 0.75f, 1.0f);
        }
    }

    public void g(boolean bl) {
        this.EW = bl;
    }

    @Override
    public float g() {
        return 5.0f + (this.E6.V$src$Z$1xhop3l() ? (this.E0 ? 0.0f : 12.0f) : 0.0f);
    }

    public boolean L$src$Z$1mm49xb() {
        return this.EW;
    }

    @Override
    public double r() {
        boolean bl;
        boolean bl2 = bl = this.i$src$Ljava_lang_String_$1n2xf3k() != null && !this.i$src$Ljava_lang_String_$1n2xf3k().isEmpty();
        double d = this.EW ? this.E4.W() + 8.0 : (bl ? this.Eo.A() + 8.0 : (this.Ea ? 12.0 : 0.0));
        return this.p$src$D$187zcry() - (double)(this.E6.V$src$Z$1xhop3l() ? (this.E0 ? 16.0f : 4.0f) : 0.0f) - (double)this.g() - (double)(this.y() * 2.0f) - d;
    }

    @Override
    public void p() {
    }

    @Override
    public void Y(double d) {
        super.Y(d);
        this.Ee = d;
    }

    public void G(boolean bl) {
        this.Ea = bl;
    }
}

