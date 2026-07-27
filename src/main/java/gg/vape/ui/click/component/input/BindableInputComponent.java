package gg.vape.ui.click.component.input;

import gg.vape.input.BindActivationMode;
import gg.vape.input.BindCaptureTask;
import gg.vape.input.KeyboardInput;
import gg.vape.ui.click.component.ToolTips;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.input.BindableInputComponentCaptureToggleClickHandler;
import gg.vape.ui.click.component.input.BindableInputComponentSavingBindCaptureTask;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class BindableInputComponent
extends InteractiveComponent {
    private final TruncatedTextComponent b;
    private int k3;
    private boolean Q;
    private Color K;
    private Bendable k6;
    private final BindCaptureTask kR;
    @Nullable
    private Boolean v = null;
    private static final long fb = -6023760571160068045L;
    private final Color kt = new Color(255, 255, 255, 100);
    private float I = 1.0f;

    public boolean L$src$Z$175wzn() {
        return this.Q;
    }

    static BindCaptureTask J(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.kR;
    }

    public void R(float f) {
        this.I = Math.max(0.0f, Math.min(1.0f, f));
    }

    public void r(Bendable bendable) {
        this.k6 = bendable;
        this.kR.B(bendable);
        this.b.O(bendable.h());
    }

    public BindCaptureTask u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o() {
        return this.kR;
    }

    public BindableInputComponent(Bendable bendable) {
        this(bendable, null);
    }

    @Override
    public void H() {
        Object object;
        float f;
        Color color;
        this.b.Z(!this.w$src$Z$e457mb() && !this.kR.V$src$Z$xc25df());
        if (this.k6 == null) {
            return;
        }
        this.b.O(this.k6.h());
        double d = this.b.f$src$D$ldt7xy();
        double d2 = this.b.u$src$D$ivbecn();
        Color color2 = color = this.K == null ? ColorUtil.j() : this.K;
        Color color3 = this.Q ? BindableInputComponent.J.Y : (this.H$src$Z$14yqm7() ? color : BindableInputComponent.J.h);
        this.b.R(this.h(color3));
        float f2 = 5.0f;
        float f3 = f = this.A() != 10.0 ? (float)this.A() - 6.0f : f2;
        if (this.kR.V$src$Z$xc25df()) {
            this.q(10.0);
            this.o(this.l$src$D$1x5l26k());
            this.w("Click to remove bind");
        } else {
            if (this.b.S$src$Ljava_lang_String_$1bp7ddx() == null || this.b.S$src$Ljava_lang_String_$1bp7ddx().isEmpty()) {
                this.q(10.0);
                this.o(this.l$src$D$1x5l26k());
            }
            object = this.G$src$Ljava_lang_String_$8s3q7o();
            ToolTips toolTips = this.b.J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf() != null ? new ToolTips(this, (String)object, 0.7, BindableInputComponent.J.h, false, this.b.J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf().c$src$Ljava_lang_String_$1q00otb(), 0.8, BindableInputComponent.J.Z, true) : new ToolTips(this, (String)object, 0.7, BindableInputComponent.J.h, false);
            this.M(toolTips);
        }
        if (this.w$src$Z$e457mb() || this.kR.V$src$Z$xc25df()) {
            object = new Color(255, 255, 255, this.H$src$Z$14yqm7() ? this.k3 : 17);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.h((Color)object));
            ImageRenderer.E(this.h(this.H$src$Z$14yqm7() ? color : BindableInputComponent.J.Z), (float)this.G$src$D$1b2f02a() + f / 2.0f, (float)this.n() + f2 / 2.0f - 0.5f, this.kR.V$src$Z$xc25df() ? "newclose" : "newedit", f2, f2, false);
            return;
        }
        if (this.b.S$src$Ljava_lang_String_$1bp7ddx() == null || this.b.S$src$Ljava_lang_String_$1bp7ddx().isEmpty()) {
            object = new Color(255, 255, 255, this.H$src$Z$14yqm7() ? this.k3 : 17);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.h((Color)(this.Q ? BindableInputComponent.J.N : object)));
            ImageRenderer.E(this.h(this.Q ? BindableInputComponent.J.I : (this.H$src$Z$14yqm7() ? color : BindableInputComponent.J.h)), (float)this.G$src$D$1b2f02a() + f / 2.0f, (float)this.n() + f2 / 2.0f - 0.5f, "newbind", f2, f2, false);
            return;
        }
        Object object2 = this.Q ? BindableInputComponent.J.N : (object = new Color(255, 255, 255, this.H$src$Z$14yqm7() ? this.k3 : 17));
        if (this.b.S$src$Ljava_lang_String_$1bp7ddx().length() == 1) {
            this.q(10.0);
        } else {
            this.getClass();
            this.q(d2 + 5.0);
        }
        this.o(this.l$src$D$1x5l26k());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.h((Color)object2));
        if (this.k6.G().equals((Object)BindActivationMode.HELD)) {
            GuiRenderPrimitives.a(this.G$src$D$1b2f02a() + 2.0, this.n() + this.L() - 1.0, this.A() - 4.0, 1.0f, J.z());
        }
        this.b.K(this.G$src$D$1b2f02a() + this.A() / 2.0 - d2 / 2.0);
        this.b.S(this.n() + this.L() / 2.0 - d / 2.0 - 0.5);
    }

    public Bendable D$src$Lgg_vape_unmap_Bendable_$wx85t() {
        return this.k6;
    }


    public void f(@Nullable Boolean bl) {
        this.v = bl;
    }

    @Override
    public double x() {
        return 10.0;
    }

    static String h(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.G$src$Ljava_lang_String_$8s3q7o();
    }

    private boolean H$src$Z$14yqm7() {
        if (this.k6 == null) {
            return false;
        }
        if (this.v != null) {
            return this.v;
        }
        return this.k6.m();
    }

    public BindableInputComponent(Bendable bendable, Color color) {
        this.k3 = (int)fb;
        this.K = color;
        this.k6 = bendable;
        this.r(new BindableInputComponentCaptureToggleClickHandler(this));
        this.kR = new BindableInputComponentSavingBindCaptureTask(this, this.D$src$Lgg_vape_unmap_Bendable_$wx85t());
        this.b = new TruncatedTextComponent(bendable == null ? "" : bendable.h(), "...", "", 50.0, 0.8, color == null ? ColorUtil.j() : color, false, false);
        this.H(this.b);
    }

    static boolean J$src$Z$1bckkjz(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.s$src$Z$1slw4q();
    }

    private boolean s$src$Z$1slw4q() {
        return KeyboardInput.isKeyDown(160) || KeyboardInput.isKeyDown(161);
    }

    private boolean n$src$Z$1pux5x() {
        return this.k6 != null && this.k6.A$src$Z$jg36ch();
    }

    static TruncatedTextComponent P(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.b;
    }

    public void w(boolean bl) {
        this.Q = bl;
    }

    public void A(Color color) {
        this.K = color;
    }

    static boolean C(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.n$src$Z$1pux5x();
    }

    private String G$src$Ljava_lang_String_$8s3q7o() {
        String string = "Click to bind\nShift click to modify bind functionality";
        string = string + "\n";
        if (this.s$src$Z$1slw4q()) {
            string = string + "\u00a7c";
        }
        string = string + "Bind functionality = " + this.k6.G().b();
        return string;
    }

    @Override
    public double C() {
        return 10.0;
    }

    static Bendable Z(BindableInputComponent bindableInputComponent) {
        return bindableInputComponent.k6;
    }

    public TruncatedTextComponent Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl() {
        return this.b;
    }

    public boolean l$src$Z$1orbz7() {
        return this.kR.V$src$Z$xc25df();
    }

    private Color h(Color color) {
        if (color == null) {
            return null;
        }
        int n = Math.max(0, Math.min(255, Math.round((float)color.getAlpha() * this.I)));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public void g(int n) {
        this.k3 = n;
    }
}
