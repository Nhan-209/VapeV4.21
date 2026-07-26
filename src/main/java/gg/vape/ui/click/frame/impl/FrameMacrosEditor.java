package gg.vape.ui.click.frame.impl;

import gg.vape.Vape;
import gg.vape.module.Macro;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.component.FadingTruncatedTextComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.RandomRangeSliderComponent;
import gg.vape.ui.click.frame.impl.FrameMacros;
import gg.vape.ui.click.frame.impl.FrameMacrosEditorRefreshFrameMouseListener;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class FrameMacrosEditor
extends InteractiveComponent {
    private RandomRangeSliderComponent Q;
    private Macro q1;
    private final TextButton q5;
    private BooleanToggleComponent qz;
    private final AnimatedIconButtonComponent K;
    private RandomRangeSliderComponent qR;
    private Macro q3;
    private BindableInputComponent qr;
    private final IconButtonComponent v;
    private FrameMacros qQ;
    private FadingTruncatedTextComponent I;
    private final TextButton b;
    private boolean qn;

    @Override
    public void H() {
        double d;
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d2 = smoothFontRenderer.d(this.q1.getName());
        double d3 = this.n() + 9.0 - d2 / 2.0;
        double d4 = this.n() + 9.0;
        double d5 = this.G$src$D$1b2f02a() + 10.0;
        this.I.K(d5);
        this.I.S(d3);
        this.I.D(this.qn ? this.A() - 20.0 - 8.0 : 65.0 - this.qr.A());
        if (this.qn) {
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0 - 0.5, this.n() + 0.5, this.A() - 10.0 + 1.0, this.L() - 1.0, FrameMacrosEditor.J.l);
        }
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 1.0, this.A() - 10.0, this.L() - 2.0, FrameMacrosEditor.J.m);
        if (this.qn) {
            d = this.G$src$D$1b2f02a() + 5.0;
            double d6 = this.n() + 20.0;
            double d7 = this.A() - 10.0;
            this.qR.K(d);
            this.qR.S(d6);
            this.qR.o(d7);
            this.qz.K(d);
            this.qz.S(d6 += this.qR.L());
            this.qz.o(d7 - 6.0);
            d6 += this.qz.L();
            if (this.qz.M$src$Lgg_vape_value_BooleanValue_$1ruml8g().L().booleanValue()) {
                this.Q.Z(true);
                this.Q.K(d);
                this.Q.S(d6);
                this.Q.o(d7);
                d6 += this.Q.L();
            } else {
                this.Q.Z(false);
                d6 += 5.0;
            }
            double d8 = (this.A() - 25.0) / 5.0;
            double d9 = this.G$src$D$1b2f02a() + 10.0;
            if (this.K != null) {
                this.K.K(d9);
                this.K.S(d6);
                this.K.Y(18.0);
                this.K.o(d8);
            }
            this.q5.K(d9 += d8 + 2.5);
            this.q5.S(d6);
            this.q5.Y(18.0);
            this.q5.o(d8 * 2.0);
            this.b.K(d9 += d8 * 2.0 + 2.5);
            this.b.S(d6);
            this.b.Y(18.0);
            this.b.o(d8 * 2.0);
        }
        d = this.G$src$D$1b2f02a() + this.A() - 15.0;
        if (this.v.V$src$Z$1xhop3l()) {
            this.v.G(this.qn ? FrameMacrosEditor.J.f : null);
            this.v.K(d);
            this.v.S(this.n());
            this.v.Y(18.0);
            d -= 5.0;
        }
        if (!this.qn) {
            this.qr.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().D(20.0);
            this.qr.K(d -= this.qr.A());
            this.qr.S(this.n() + 4.0);
            GuiRenderPrimitives.F("newclock", d -= 9.0, d4, 8.0, 8.0, FrameMacrosEditor.J.K);
        }
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.qQ.l$src$Lgg_vape_ui_click_frame_impl_FrameMacrosEditor_$1712c36() != null && this.qQ.l$src$Lgg_vape_ui_click_frame_impl_FrameMacrosEditor_$1712c36().equals(this) && !this.Q().J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            if (!this.v.V$src$Z$1xhop3l()) {
                this.qQ.X(null);
                return;
            }
            this.v.P$src$V$q7uwbv();
            return;
        }
        super.g(guiMouseEvent);
    }

    @Override
    public void I() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Macro z() {
        return this.q1;
    }

    public FrameMacrosEditor(FrameMacros frameMacros, Macro macro) {
        this.b = new TextButton("Update", 0.8, FrameMacrosEditor.J.B).R(FrameMacrosEditor.J.l);
        this.q5 = new TextButton("Cancel", 0.8, FrameMacrosEditor.J.B).R(FrameMacrosEditor.J.l);
        this.K = new AnimatedIconButtonComponent("newtrash", 0.8, FrameMacrosEditor.J.d);
        this.v = new IconButtonComponent("settingdots");
        this.b.F(false);
        this.q5.F(false);
        this.qQ = frameMacros;
        this.q1 = macro;
        this.qr = new BindableInputComponent(macro);
        FrameMacrosEditor frameMacrosEditor = this;
        this.v.r(() -> {
            this.qn = !this.qn;
            if (this.qn) {
                frameMacros.X(frameMacrosEditor);
                this.q3 = Macro.create(macro.getName()).loadJson(macro.toJson());
                this.qz.Z(true);
                this.qR.Z(true);
                this.Q.Z(true);
                this.q5.Z(true);
                this.b.Z(true);
                this.K.Z(true);
                this.qr.Z(false);
            } else {
                frameMacros.X(null);
                this.qz.Z(false);
                this.qR.Z(false);
                this.Q.Z(false);
                this.q5.Z(false);
                this.b.Z(false);
                this.K.Z(false);
                this.qr.Z(true);
            }
            frameMacros.l$src$V$1mibm4x();
        });
        this.qR = new RandomRangeSliderComponent(macro.getDelay());
        this.Q = new RandomRangeSliderComponent(macro.getDoubleClickDelay());
        this.qz = new BooleanToggleComponent(macro.getDoubleClick());
        this.qz.j(new FrameMacrosEditorRefreshFrameMouseListener(this, frameMacros));
        this.K.r(() -> {
            Vape.INSTANCE.getMacrosManager().removeMacro(macro);
            frameMacros.X(null);
        });
        this.q5.r(() -> {
            if (this.q3 != null) {
                macro.loadJson(this.q3.toJson());
            }
            this.v.P$src$V$q7uwbv();
        });
        this.b.r(() -> {
            if (!this.v.V$src$Z$1xhop3l()) {
                frameMacros.X(null);
            } else {
                this.v.P$src$V$q7uwbv();
            }
        });
        this.qz.Z(false);
        this.qz.P(true);
        this.qz.T(FrameMacrosEditor.J.m);
        this.qR.Z(false);
        this.qR.P(true);
        this.qR.T(FrameMacrosEditor.J.m);
        this.Q.Z(false);
        this.Q.P(true);
        this.Q.T(FrameMacrosEditor.J.m);
        this.q5.Z(false);
        this.b.Z(false);
        this.K.Z(false);
        this.K.T(FrameMacrosEditor.J.m);
        this.I = new FadingTruncatedTextComponent(macro.getName(), 40.0, 0.9, FrameMacrosEditor.J.Z, FrameMacrosEditor.J.m, false, false);
        this.H(this.I, this.v, this.qr, this.qz, this.qR, this.Q, this.q5, this.b, this.K);
    }

    @Override
    public double C() {
        return this.qn ? (this.qz.M$src$Lgg_vape_value_BooleanValue_$1ruml8g().L().booleanValue() ? 120.0 : 90.0) : 18.0;
    }

    private void lambda$setInitialMode$4() {
        this.Z(false);
        this.qQ.X(null);
    }

    private void lambda$setInitialMode$5() {
        this.q1 = Macro.create(this.q1.getName()).loadJson(this.q1.toJson());
        Vape.INSTANCE.getMacrosManager().addMacro(this.q1);
        this.Z(false);
        this.qQ.X(null);
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void F() {
    }

    @Override
    public void u() {
    }

    public void N$src$V$13y6z98() {
        this.v.P$src$V$q7uwbv();
        this.v.Z(false);
        this.qr.Z(false);
        this.K.Z(false);
        this.q5.d("Cancel");
        this.q5.G(new Color(0, 0, 0, 0), FrameMacrosEditor.J.d);
        this.b.d("Add");
        this.b.G(new Color(0, 0, 0, 0), FrameMacrosEditor.J.B);
        this.q5.s(this::lambda$setInitialMode$4);
        this.b.s(this::lambda$setInitialMode$5);
    }
}
