package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.HudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.animation.ThemeColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleListEntryToggleClickListener;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class HudModuleListEntry
extends GuiComponent {
    private float CH;
    private HudModule o;
    private float Q;
    private ColorAnimation I;
    protected Color Co;
    private String R;
    private float CJ;
    private float C1;
    private ThemeColorAnimation G;
    private Frame a;
    protected Color i;
    private IconButtonComponent b;
    private Color v;
    private boolean K;
    private String CN;
    private boolean CS;
    private float Cx;
    private static int[] Cz;
    private DoubleAnimation O;

    public static int[] a$src$AI$dby6px() {
        return Cz;
    }

    @Override
    public void F() {
        this.K = true;
    }

    public boolean r$src$Z$1suh9lm() {
        return this.O.I$src$Z$c48gtw();
    }

    @Override
    public void I() {
    }

    protected void Y$src$V$1sgqeo5() {
        if (this.o.r$src$Z$14eylz9() != this.CS || this.CS != this.r$src$Z$1suh9lm() && !this.y$src$Z$1sybtr5()) {
            this.h();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void h() {
        if (this.CS == this.r$src$Z$1suh9lm()) {
            this.CS = !this.CS;
            this.G.J();
            this.O.J();
            this.O$src$V$1sb8gqj();
            if (this.o.r$src$Z$14eylz9() != this.CS) {
                this.o.Y(this.CS);
            }
        } else if (this.CS) {
            this.G.C();
            this.O.C();
        } else {
            this.G.O();
            this.O.O();
        }
    }

    public boolean y$src$Z$1sybtr5() {
        return !this.O.getInterpolatedValue().equals(this.O.getStartValue()) && !this.O.getInterpolatedValue().equals(this.O.getEndValue());
    }

    public HudModuleListEntry(HudModule hudModule) {
        this(hudModule, 0.9f);
    }

    @Override
    public double x() {
        return 84.5;
    }

    public HudModule l$src$Lgg_vape_module_render_hud_HudModule_$jeyvjw() {
        return this.o;
    }

    private void a$src$V$1sl4rf1() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d = smoothFontRenderer.d(this.R);
        double d2 = this.n() + (this.L() - 15.0) - d / 2.0;
        Color color = this.CS ? Color.WHITE : HudModuleListEntry.J.Z;
        smoothFontRenderer.d(this.R, this.G$src$D$1b2f02a() + 10.0, d2, color);
    }

    public void O$src$V$1sb8gqj() {
        if (this.a == null) {
            return;
        }
        this.a.Z(this.CS);
        this.a.c(true);
        if (HudModuleConfigFrameBase.h$src$Z$1tlh1co()) {
            this.a.U();
        }
        double d = Minecraft.J();
        double d2 = Minecraft.h();
        if (this.a.n() > d2 || this.a.n() < 0.0) {
            this.a.S(d2 / 2.0);
        }
        if (this.a.G$src$D$1b2f02a() > d || this.a.G$src$D$1b2f02a() < 0.0) {
            this.a.K(d / 2.0);
        }
        if (this.a instanceof HudModuleConfigFrameBase) {
            HudModuleConfigFrameBase hudModuleConfigFrameBase = (HudModuleConfigFrameBase)this.a;
            hudModuleConfigFrameBase.w$src$V$1ttpy5n();
            hudModuleConfigFrameBase.Z$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$1jkbe02().Z(false);
        }
    }

    static {
        HudModuleListEntry.X(null);
    }

    public static void X(int[] nArray) {
        Cz = nArray;
    }

    private void D$src$V$1s56q7k() {
        Color color;
        double d = this.G$src$D$1b2f02a() + this.A() - (double)(this.C1 * 2.0f) - 17.5;
        double d2 = this.n() + 10.0 - 3.0;
        Color color2 = color = this.G.q() > 0.0 ? this.G.getInterpolatedColor() : this.I.getInterpolatedColor();
        if (this.K && this.G.q() > 0.0) {
            color = ColorUtil.N(color, 30.0);
        }
        ImageRenderer.E(color, (float)d, (float)d2, "togglebg", this.C1, this.Cx, false);
        ImageRenderer.E(this.v, (float)d + this.CJ + (float)this.O.getInterpolatedValue().doubleValue(), (float)d2 + this.CJ, "toggledot", this.Q, this.Q, false);
    }

    @Override
    public void u() {
        if (this.K && !this.w$src$Z$e457mb()) {
            this.K = false;
        }
        this.Y$src$V$1sgqeo5();
    }

    public HudModuleListEntry U(Frame frame) {
        this.a = frame;
        return this;
    }

    @Override
    public double C() {
        return 60.0;
    }

    public HudModuleListEntry(HudModule hudModule, float f) {
        this.i = HudModuleListEntry.J.Z;
        this.Co = HudModuleListEntry.J.h;
        this.b = new IconButtonComponent("settingdots", 0.8);
        this.C1 = 6.0f;
        this.Cx = 6.0f;
        this.Q = 4.0f;
        this.CJ = 1.0f;
        this.I = new ColorAnimation(0.15, HudModuleListEntry.J.K, HudModuleListEntry.J.W);
        this.G = new ThemeColorAnimation(0.15, HudModuleListEntry.J.W);
        this.O = new DoubleAnimation(0.15, 0.0, this.C1 - this.CJ);
        this.v = HudModuleListEntry.J.r;
        this.a = null;
        this.o = hudModule;
        this.R = hudModule.getName();
        this.CN = hudModule.s$src$Ljava_lang_String_$pdppcm();
        this.CS = hudModule.r$src$Z$14eylz9();
        this.CH = f;
        if (hudModule.n() != null) {
            this.w(hudModule.n());
        }
        this.b.r(new HudModuleListEntryToggleClickListener(this, hudModule));
        this.H(this.b);
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 2.0, this.n(), this.A() - 3.0, this.L() - 3.0, this.K || this.CS ? HudModuleListEntry.J.l : HudModuleListEntry.J.m);
        GuiRenderPrimitives.F(this.CN, this.G$src$D$1b2f02a() + 10.0 + 3.0, this.n() + 10.0, (double)(8.0f * this.CH), 8.0f * this.CH, this.CS ? J.z() : (this.K ? HudModuleListEntry.J.f : HudModuleListEntry.J.W));
        this.b.K(this.G$src$D$1b2f02a() + this.A() - 15.0);
        this.b.S(this.n() + 10.0 - 4.5);
        this.b.Y(9.0);
        if (this.o.j$src$Ljava_lang_Class_$wxgaiy() != null) {
            // empty if block
        }
        this.D$src$V$1s56q7k();
        this.a$src$V$1sl4rf1();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            this.h();
        }
    }

    @Override
    public void c() {
        super.c();
    }

    public static IconButtonComponent x(HudModuleListEntry hudModuleListEntry) {
        return hudModuleListEntry.b;
    }
}

