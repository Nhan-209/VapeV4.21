package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineModeToggleClickHandler;
import gg.vape.friend.ui.OnlineModeToggleInactiveClickHandler;
import gg.vape.friend.ui.OnlineModeToggleLeftTextButton;
import gg.vape.friend.ui.OnlineModeToggleRightTextButton;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class OnlineModeToggleComponent
extends PanelComponent {
    private TextButton qd;
    private DoubleAnimation q1;
    private boolean qj;
    private static int qs;
    private boolean qW;
    private TextButton q_ = new OnlineModeToggleLeftTextButton(this, "", 0.7, new Color(255, 255, 255, 102), new Color(255, 255, 255));

    public static void n(int n) {
        qs = n;
    }

    public OnlineModeToggleComponent(String string, String string2, boolean bl) {
        super(100.0, 12.0);
        this.qd = new OnlineModeToggleRightTextButton(this, "", 0.7, new Color(255, 255, 255, 102), new Color(255, 255, 255));
        this.q1 = new DoubleAnimation(0.1, 0.0, 49.0);
        this.q_.d(string);
        this.qd.d(string2);
        this.qW = bl;
        this.qj = bl;
        this.d(false);
        if (!bl) {
            this.q1.Z();
        }
        this.q_.q(this.A() / 2.0);
        this.q_.Y(12.0);
        this.qd.q(this.A() / 2.0);
        this.qd.Y(12.0);
        this.q_.r(new OnlineModeToggleInactiveClickHandler(this));
        this.qd.r(new OnlineModeToggleClickHandler(this));
        this.H(this.q_, this.qd);
    }

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
    }

    public static int e$src$I$1rjbws3() {
        return qs;
    }

    @Override
    public void F() {
    }

    @Override
    public void V() {
    }

    private void k$src$V$1rmmong() {
        this.u(this.r$src$Ljava_lang_Boolean_$180i77a() == false);
    }

    @Override
    public void o(double d) {
        super.o(d);
        try {
            this.q_.q(d / 2.0);
            this.qd.q(d / 2.0);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void u(Boolean bl) {
        boolean bl2 = this.qW != bl;
        this.qW = bl;
        if (bl2) {
            boolean bl3 = false;
            if (this.qj) {
                if (bl.booleanValue()) {
                    if (this.q1.getInterpolatedValue().doubleValue() == this.q1.getEndValue()) {
                        bl3 = true;
                    }
                } else if (this.q1.getInterpolatedValue() == 0.0) {
                    bl3 = true;
                }
            } else if (bl.booleanValue()) {
                if (this.q1.getInterpolatedValue() == 0.0) {
                    bl3 = true;
                }
            } else if (this.q1.getInterpolatedValue().doubleValue() == this.q1.getEndValue()) {
                bl3 = true;
            }
            if (bl3) {
                this.q1.J();
            }
        }
    }

    @Override
    public void Y() {
    }

    public Boolean r$src$Ljava_lang_Boolean_$180i77a() {
        return this.qW;
    }

    public static int P$src$I$1r7s8bi() {
        int n = OnlineModeToggleComponent.e$src$I$1rjbws3();
        if (n == 0) {
            return 115;
        }
        return 0;
    }

    public static void w(OnlineModeToggleComponent onlineModeToggleComponent) {
        onlineModeToggleComponent.k$src$V$1rmmong();
    }

    static {
        if (OnlineModeToggleComponent.P$src$I$1r7s8bi() != 0) {
            OnlineModeToggleComponent.n(57);
        }
    }

    @Override
    public double x() {
        return 100.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void v() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void u(String string, String string2) {
        this.q_.w(string);
        this.qd.w(string2);
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new Color(54, 53, 54, 128));
        GuiRenderPrimitives.I(this.G$src$D$1b2f02a() + this.q1.getInterpolatedValue(), this.n() + 0.5, this.A() / 2.0 + 1.0, this.L() - 1.0, OnlineModeToggleComponent.J.y, true, 1.0f, 1.0f, 8.0f, new Color(0, 0, 0, 70));
        boolean bl = this.r$src$Ljava_lang_Boolean_$180i77a();
        if (bl) {
            SmoothFontRenderer smoothFontRenderer = this.O(0.6);
            double d = (this.q_.L() - smoothFontRenderer.d(this.q_.L$src$Ljava_lang_String_$1ncdwqb())) / 2.0;
            double d2 = this.q_.n() + d;
            double d3 = this.G$src$D$1b2f02a() + this.A() * 0.25;
            String string = this.q_.L$src$Ljava_lang_String_$1ncdwqb();
            SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer;
            smoothFontRenderer2.W(string, d3, d2, OnlineModeToggleComponent.J.A);
            double d4 = this.qd.n() + d;
            double d5 = this.G$src$D$1b2f02a() + this.A() * 0.75;
            String string2 = this.qd.L$src$Ljava_lang_String_$1ncdwqb();
            SmoothFontRenderer smoothFontRenderer3 = smoothFontRenderer;
            smoothFontRenderer3.W(string2, d5, d4, this.qd.L$src$Lgg_vape_ui_click_animation_ColorAnimation_$1j6vdwo().getInterpolatedColor());
            return;
        }
        SmoothFontRenderer smoothFontRenderer = this.O(0.6);
        double d = (this.q_.L() - smoothFontRenderer.d(this.q_.L$src$Ljava_lang_String_$1ncdwqb())) / 2.0;
        double d6 = this.q_.n() + d;
        double d7 = this.G$src$D$1b2f02a() + this.A() * 0.25;
        String string = this.q_.L$src$Ljava_lang_String_$1ncdwqb();
        SmoothFontRenderer smoothFontRenderer4 = smoothFontRenderer;
        smoothFontRenderer4.W(string, d7, d6, this.q_.L$src$Lgg_vape_ui_click_animation_ColorAnimation_$1j6vdwo().getInterpolatedColor());
        double d8 = this.qd.n() + d;
        double d9 = this.G$src$D$1b2f02a() + this.A() * 0.75;
        String string3 = this.qd.L$src$Ljava_lang_String_$1ncdwqb();
        SmoothFontRenderer smoothFontRenderer5 = smoothFontRenderer;
        smoothFontRenderer5.W(string3, d9, d8, OnlineModeToggleComponent.J.A);
    }
}

