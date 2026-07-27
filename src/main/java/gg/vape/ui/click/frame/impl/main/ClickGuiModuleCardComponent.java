package gg.vape.ui.click.frame.impl.main;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.MouseInput;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.Animation;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleCardDetailComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleCardRenderState;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ClickGuiModuleCardComponent
extends GuiComponent {
    private final SquareIconButtonComponent Rt;
    private static final double R4 = 84.0;
    private final ClickGuiModuleCardDetailComponent RN;
    private final ColorAnimation RG;
    private final IconGlyphComponent R2;
    private final ColorAnimation Rd;
    private long RW;
    private final ColorAnimation Rf;
    private static final double o = 12.5;
    private static final long fb = -4353476493014852858L;
    private final RectData Rk;
    private double RC;
    private static final double Rg = 20.0;
    @Nullable
    private Runnable R1;
    private boolean Ry;
    private final DoubleAnimation G;
    private static final double Rl = 2.0;
    private static final Color Ra;
    private final ColorAnimation Rx;
    private static final double Rh = 10.0;
    private static final double RF = 3.0;
    private final DoubleAnimation Rp;
    private static final Color RL;
    private static final Color Re;
    private static final Color RI;
    private boolean K;
    private final ColorAnimation R0;
    private final Mod Rw;
    private int RV;
    private static final double a = 10.0;
    private final TruncatedTextComponent Rc;
    private boolean R7;
    private final BindableInputComponent RA;
    private static final double b = 2.0;
    private static final double R8 = 7.0;
    private static final double RS = 4.0;
    private static final double R9 = 22.0;
    private static final double RB = 45.0;
    private static final Color RJ;
    private static final Color RP;
    private boolean RU;
    private boolean R_;
    private static final double I = 1.5;
    private final IconGlyphComponent Rv;
    private double RO;
    private static final double RD = 4.0;
    private final RectData O;
    private final IconGlyphComponent i;
    private boolean Rz;
    private Runnable RM;
    private boolean Ru;
    private final DoubleAnimation R;
    private static final double RR = 10.0;
    private static final long v = 2000L;
    private static final double Rs = 3.0;
    private static final double Q = 6.0;
    private final ColorAnimation R6;

    private void A$src$V$esany4() {
        int n;
        if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
            List<ClickGuiModuleCardComponent> list = this.P();
            this.K(list);
            if (this.RV != -1) {
                Vape.INSTANCE.getModuleProfileMetadataCodec().k().remove(this.Rw);
                Vape.INSTANCE.getModuleProfileMetadataCodec().k().add(this.RV, this.Rw);
            }
            this.RV = -1;
            if (this.RM != null) {
                this.RM.run();
            }
            return;
        }
        List<ClickGuiModuleCardComponent> list = this.P();
        double d = this.L();
        double d2 = this.RO;
        double d3 = this.RO + d;
        int n2 = n = list.indexOf(this);
        for (int i = 0; i < list.size(); ++i) {
            ClickGuiModuleCardComponent clickGuiModuleCardComponent = list.get(i);
            if (clickGuiModuleCardComponent == this) continue;
            double d4 = clickGuiModuleCardComponent.Y$src$D$f5hpra();
            double d5 = d4 + clickGuiModuleCardComponent.L() / 2.0;
            if (i < n && d2 <= d5) {
                n2 = i;
                break;
            }
            if (i <= n || !(d3 >= d5)) continue;
            n2 = i;
        }
        this.RV = n2;
        if (n2 != n) {
            this.W(n2, list);
        } else {
            for (ClickGuiModuleCardComponent clickGuiModuleCardComponent : list) {
                if (clickGuiModuleCardComponent == this) continue;
                clickGuiModuleCardComponent.RO = Double.NaN;
            }
        }
    }

    static {
        Re = new Color(34, 33, 34);
        RL = new Color(173, 173, 173);
        RP = new Color(31, 30, 31);
        RI = new Color(46, 45, 47);
        Ra = new Color(0, 0, 0, 152);
        RJ = new Color(236, 129, 44, 30);
    }

    public boolean i$src$Z$feafs0() {
        return this.Rz;
    }

    public void G(boolean bl) {
        this.Rz = bl;
    }

    private void f(Animation<?> animation, boolean bl) {
        if (bl) {
            animation.C();
        } else {
            animation.O();
        }
    }

    public boolean E() {
        return this.RU;
    }

    public ClickGuiModuleCardComponent(Mod mod, double d) {
        this.getClass();
        this.RG = new ColorAnimation(0.15, ClickGuiModuleCardComponent.J.t, ClickGuiModuleCardComponent.J.E);
        this.getClass();
        this.Rd = new ColorAnimation(0.15, ClickGuiModuleCardComponent.J.t, ClickGuiModuleCardComponent.J.E);
        this.getClass();
        this.Rx = new ColorAnimation(0.15 * 1.5, ClickGuiModuleCardComponent.J.m, ClickGuiModuleCardComponent.J.H);
        this.getClass();
        this.Rf = new ColorAnimation(0.15, ClickGuiModuleCardComponent.J.H, RI);
        this.getClass();
        this.R0 = new ColorAnimation(0.15 * 1.5, ClickGuiModuleCardComponent.J.m, ClickGuiModuleCardComponent.J.H);
        this.getClass();
        this.Rp = new DoubleAnimation(0.15, 0.0, 2.0);
        this.getClass();
        this.G = new DoubleAnimation(0.15, 0.0, 1.0);
        this.getClass();
        this.R = new DoubleAnimation(0.15, 0.0, 1.0);
        this.getClass();
        this.R6 = new ColorAnimation(0.15, ClickGuiModuleCardComponent.J.t, RJ);
        this.Rk = new RectData(0.0, 0.0, 0.0, 0.0);
        this.O = new RectData(0.0, 0.0, 0.0, 0.0);
        this.RO = Double.NaN;
        this.RV = -1;
        this.Rw = mod;
        this.Y(d);
        this.o(true);
        this.Rc = new TruncatedTextComponent(mod.getName(), 50.0, 0.75);
        this.Rc.C(0.0);
        String string = mod.n();
        this.w(string);
        this.RN = new ClickGuiModuleCardDetailComponent();
        this.RN.Y(this.Rw.S());
        this.RN.O$src$V$wbyi9r(0.7);
        this.RN.P(true);
        this.R2 = new IconGlyphComponent("settingdots", 6.0f, 6.0f);
        this.R2.S(ClickGuiModuleCardComponent.J.W);
        this.RA = new BindableInputComponent(this.Rw.a(), ClickGuiModuleCardComponent.J.A);
        this.RA.Z(false);
        this.RA.Y(10.0);
        this.i = new IconGlyphComponent("newrearrange", 9.0f, 9.0f, ClickGuiModuleCardComponent.J.W);
        this.i.Z(false);
        this.Rt = new SquareIconButtonComponent("newclose", 1.0);
        this.Rt.Z(false);
        this.Rt.o(10.0);
        this.Rt.Y(10.0);
        this.H(this.i, this.Rt, this.Rc, this.R2);
        this.Rv = new IconGlyphComponent("newstar", 6.0f, 6.0f, ClickGuiModuleCardComponent.J.I);
        this.H(this.Rv);
        this.H(this.RN);
        this.H(this.RA);
        this.W();
    }

    public void U(boolean bl) {
        this.RU = bl;
    }

    private void p() {
        MousePosition mousePosition = RenderUtils.h();
        double d = (double)mousePosition.H - this.RC;
        GuiComponent guiComponent = this.C$src$Lgg_vape_ui_click_component_GuiComponent_$1la08n7();
        if (guiComponent instanceof FrameComponent) {
            FrameComponent frameComponent = (FrameComponent)guiComponent;
            double d2 = frameComponent.n();
            double d3 = d2 + frameComponent.d$src$D$ibccpu();
            if (frameComponent.k$src$Z$if6xeb()) {
                if ((double)mousePosition.H < d2 + this.L()) {
                    frameComponent.b(frameComponent.J$src$D$hx1pag() + 3.0);
                } else if ((double)mousePosition.H > d3 - this.L()) {
                    frameComponent.b(frameComponent.J$src$D$hx1pag() - 3.0);
                }
                d = Math.max(d2, Math.min(d, d3 - this.L()));
            } else {
                double d4 = d2 + frameComponent.L() - this.L();
                d = Math.max(d2, Math.min(d, d4));
            }
        }
        this.RO = d;
    }

    private void K(List<ClickGuiModuleCardComponent> list) {
        this.Ru = false;
        this.RO = Double.NaN;
        this.A(false);
        PaddedComponent paddedComponent = this.t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je();
        if (paddedComponent != null) {
            paddedComponent.A(false);
        }
        if (ClientSettings.fT == this) {
            ClientSettings.fT = null;
        }
        for (ClickGuiModuleCardComponent clickGuiModuleCardComponent : list) {
            clickGuiModuleCardComponent.RO = Double.NaN;
        }
    }

    public boolean c$src$Z$fazo7u() {
        return this.R7;
    }

    public void K(boolean bl) {
        this.Ry = bl;
    }

    private void W(int n, List<ClickGuiModuleCardComponent> list) {
        ArrayList<ClickGuiModuleCardComponent> arrayList = new ArrayList<ClickGuiModuleCardComponent>(list);
        arrayList.remove(this);
        arrayList.add(n, this);
        for (int i = 0; i < arrayList.size(); ++i) {
            ClickGuiModuleCardComponent clickGuiModuleCardComponent = (ClickGuiModuleCardComponent)arrayList.get(i);
            if (clickGuiModuleCardComponent == this) continue;
            clickGuiModuleCardComponent.RO = list.get(i).Y$src$D$f5hpra();
        }
    }

    public void l(boolean bl) {
        this.R_ = bl;
    }

    private float U$src$F$f3ajfk() {
        if (!this.R7 || this.Rz) {
            return 1.0f;
        }
        double d = Math.min(1.0, Math.max(0.0, this.R.getInterpolatedValue()));
        return (float)(1.0 - 0.8 * d);
    }

    @Nullable
    private GuiComponent C$src$Lgg_vape_ui_click_component_GuiComponent_$1la08n7() {
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent == null) {
            return null;
        }
        FrameComponent frameComponent2 = frameComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent2 == null) {
            return null;
        }
        return frameComponent2.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
    }

    @Override
    public void H() {
        Color color;
        int n;
        double d;
        double d2;
        double d3;
        double d4;
        SmoothFontRenderer smoothFontRenderer;
        Color color2;
        boolean bl;
        Color color3;
        if (this.Ru) {
            this.p();
            this.A$src$V$esany4();
        }
        if (!Double.isNaN(this.RO)) {
            this.S(this.RO);
        }
        boolean bl2 = System.currentTimeMillis() - this.RW < 2000L;
        boolean bl3 = this.Rw.X() && !this.Rw.a().y$src$Z$r0tfl8();
        double d5 = this.G$src$D$1b2f02a();
        double d6 = this.n();
        double d7 = this.A();
        double d8 = this.L();
        double d9 = d6 + d8 / 2.0;
        double d10 = this.Rp.getInterpolatedValue();
        Color color4 = this.Rx.getInterpolatedColor();
        GuiRenderPrimitives.B(d5 += d10, d6, d7, d8, this.r(color4), 3.0f);
        Color color5 = this.RG.getInterpolatedColor();
        if (color5.getAlpha() > 0) {
            GuiRenderPrimitives.B(d5, d6, d7, d8, this.r(color5), 3.0f);
        }
        Color color6 = ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1();
        Color color7 = color3 = this.Rw.r$src$Z$14eylz9() ? color6 : ClickGuiModuleCardComponent.J.y;
        if (bl2 && bl3) {
            color3 = ClickGuiModuleCardComponent.J.I;
        } else if (this.K) {
            color3 = ClickGuiModuleCardComponent.J.I;
        }
        RenderUtils.m(d5, d6 - 1.0, 2.0, d8 + 0.5);
        GuiRenderPrimitives.p(d5, d6, 10.0, d8 + 0.5, this.r(color3), false, 3.0f, 1.0f, 2.0f, this.r(Ra), 9);
        RenderUtils.T();
        MousePosition mousePosition = RenderUtils.h();
        double d11 = this.R2.A();
        double d12 = this.R2.L();
        double d13 = d5 + d7 - 4.0 - d11;
        double d14 = d9 - d12 / 2.0;
        this.R2.Z(!this.Ry);
        if (this.Ry) {
            bl = false;
        } else {
            this.Rk.M(d13 - 6.0);
            this.Rk.O(d14 - 8.0);
            this.Rk.A(d11 + 10.0);
            this.Rk.U(d12 + 16.0);
            bl = this.Rk.Z(mousePosition);
        }
        boolean bl4 = this.w$src$Z$e457mb() && !bl;
        this.RG.u(bl4);
        this.Rd.u(bl);
        this.Rx.u(this.Rw.r$src$Z$14eylz9());
        this.Rf.u(this.Rw.r$src$Z$14eylz9());
        this.R0.u(this.Rz);
        this.Rp.u(this.Rz);
        this.G.u(this.Rw.r$src$Z$14eylz9());
        this.R.u(this.R7 && !this.Rz);
        this.R6.u(this.K);
        this.R2.K(d13);
        this.R2.S(d14);
        if (!this.Ry && (color2 = this.Rd.getInterpolatedColor()).getAlpha() > 0) {
            GuiRenderPrimitives.p(this.Rk.o(), this.Rk.W(), this.Rk.e(), this.Rk.R(), this.r(color2), false, 2.0f, 1.0f, 0.0f, ClickGuiModuleCardComponent.J.u, 6);
        }
        color2 = ClickGuiModuleCardComponent.J.W;
        this.R2.S(this.r(bl ? ClickGuiModuleCardComponent.J.f : color2));
        double d15 = d13 - 12.5 - 10.0;
        double d16 = d9 - 3.5;
        if (!this.Ry) {
            Color color8 = this.Rw.r$src$Z$14eylz9() ? color6 : ClickGuiModuleCardComponent.J.K;
            GuiRenderPrimitives.j(d15, d16, 12.5, 7.0, this.r(color8));
            double d17 = 5.5;
            double d18 = d15 + 1.5 + 5.5 * this.G.getInterpolatedValue();
            if (this.Rw.X() && this.Rw.a().y$src$Z$r0tfl8()) {
                d18 += 3.125;
            }
            double d19 = d16 + 1.5;
            Color color9 = ClickGuiModuleCardComponent.J.i;
            GuiRenderPrimitives.V((float)d18, (float)d19, 4.0, (float)(0.8 / Vape.INSTANCE.getClientSettings().s()), color9);
        }
        double d20 = d15 - this.RA.A() - 8.0;
        double d21 = d9 - 5.0;
        boolean bl5 = this.Rw.a() != null && this.Rw.a().y$src$Z$r0tfl8();
        boolean bl6 = this.RA.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
        boolean bl7 = !this.Ry && (bl5 || bl6 || bl4);
        this.RA.K(d20);
        this.RA.S(d21);
        this.RA.o(this.RA.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().u$src$D$ivbecn());
        this.RA.Y(10.0);
        this.RA.Z(bl7);
        this.RA.w(bl2);
        this.RA.g(15);
        this.RA.A(ClickGuiModuleCardComponent.J.Z);
        this.RA.R(this.U$src$F$f3ajfk());
        double d22 = d20 + (double)(this.RA.V$src$Z$1xhop3l() ? 0 : 20) - 6.0;
        double d23 = d5 + 84.0;
        double d24 = Math.max(0.0, d22 - d23);
        boolean bl8 = this.RN.V$src$Z$1xhop3l();
        Color color10 = this.Rw.r$src$Z$14eylz9() ? ClickGuiModuleCardComponent.J.A : ClickGuiModuleCardComponent.J.C;
        this.RN.n(bl2 ? ClickGuiModuleCardComponent.J.I : this.r(color10));
        this.RN.K(this.U$src$F$f3ajfk());
        this.RN.S(d6);
        this.RN.Y(d8);
        boolean bl9 = bl2 && this.Rw.X();
        boolean bl10 = this.Rw.L() && !this.Rw.r$src$Z$14eylz9();
        boolean bl11 = this.Rw.getCategory() == Category.w && this.RU;
        boolean bl12 = !this.Rw.r$src$Z$14eylz9() && (this.Rw.t$src$Z$14g275z() || this.Rw.Q());
        double d25 = 0.0;
        if (bl10 && !bl9) {
            smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65);
            String string = "New!";
            d4 = smoothFontRenderer.N(string) + 4.0;
            d3 = smoothFontRenderer.d(string) + 2.0;
            d2 = d23;
            d = d9 - d3 / 2.0;
            GuiRenderPrimitives.d(d2, d, d4, d3, this.r(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
            smoothFontRenderer.d(string, d2 + 2.0, d + 1.0, this.r(ColorUtil.r(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1(), 35, 255)));
            d25 = d4 + 3.0;
        } else if (bl11 && !bl9) {
            smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65);
            String string = "UNSAFE";
            d4 = smoothFontRenderer.N(string) + 4.0;
            d3 = smoothFontRenderer.d(string) + 2.0;
            d2 = d23;
            d = d9 - d3 / 2.0;
            n = (int)fb;
            color = new Color(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF);
            GuiRenderPrimitives.d(d2, d, d4, d3, this.r(color));
            smoothFontRenderer.d(string, d2 + 2.0, d + 1.0, this.r(ColorUtil.r(color, 35, 255)));
            d25 = d4 + 3.0;
        } else if (bl12 && !bl9) {
            smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65);
            String string = this.Rw.Q() ? "INDEV" : "BETA";
            d4 = smoothFontRenderer.N(string) + 4.0;
            d3 = smoothFontRenderer.d(string) + 2.0;
            d2 = d23;
            d = d9 - d3 / 2.0;
            GuiRenderPrimitives.d(d2, d, d4, d3, this.r(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()));
            smoothFontRenderer.d(string, d2 + 2.0, d + 1.0, this.r(ColorUtil.r(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1(), 35, 255)));
            d25 = d4 + 3.0;
        }
        double d26 = d23 + d25;
        d4 = Math.max(0.0, d24 - d25);
        this.RN.K(d26);
        this.RN.o(d4);
        this.RN.J(d4);
        d3 = d5 + 10.0;
        d2 = this.i.A();
        this.i.Z(this.Ry);
        this.Rt.Z(this.Ry);
        if (this.Ry) {
            d = d2 - 1.0;
            double d27 = d3 + d2 / 2.0 - d / 2.0;
            double d28 = d9 - d / 2.0;
            GuiRenderPrimitives.V((float)d27, (float)d28, (float)d, 1.0, this.r(ClickGuiModuleCardComponent.J.Q));
            this.i.S(this.r(ClickGuiModuleCardComponent.J.W));
            this.i.K(d3);
            this.i.S(d9 - d2 / 2.0);
            d3 += d + 6.0;
            double d29 = 6.0;
            this.Rt.K(d5 + d7 - 6.0 - this.Rt.A());
            this.Rt.S(d9 - this.Rt.L() / 2.0);
        }
        d = this.Rv.A();
        n = this.R_ || this.Rw.f$src$Z$148d2ux() && this.R_ ? 1 : 0;
        this.Rv.Z(n != 0);
        if (n != 0) {
            color = this.Rw.f$src$Z$148d2ux() ? ClickGuiModuleCardComponent.J.I : ClickGuiModuleCardComponent.J.K;
            this.Rv.S(this.r(color));
            this.Rv.K(d3);
            this.Rv.S(d9 - d / 2.0);
            this.O.M(d3 - 2.0);
            this.O.O(d9 - d / 2.0 - 4.0);
            this.O.A(d + 4.0);
            this.O.U(d + 8.0);
            d3 += d + 6.0;
        }
        double d30 = bl8 ? Math.max(d3, d23 - 6.0) : d22;
        double d31 = Math.max(0.0, d30 - d3);
        Color color11 = bl2 && bl3 ? ClickGuiModuleCardComponent.J.I : (this.Rw.r$src$Z$14eylz9() ? Color.WHITE : ClickGuiModuleCardComponent.J.A);
        this.Rc.R(this.r(color11));
        this.Rc.K(d3);
        this.Rc.S(d6);
        this.Rc.o(d31);
        this.Rc.D(this.Rc.A());
        this.Rc.Y(d8);
    }

    private List<ClickGuiModuleCardComponent> P() {
        GuiComponent guiComponent = this.C$src$Lgg_vape_ui_click_component_GuiComponent_$1la08n7();
        if (guiComponent == null) {
            return new ArrayList<ClickGuiModuleCardComponent>();
        }
        ArrayList<ClickGuiModuleCardComponent> arrayList = new ArrayList<ClickGuiModuleCardComponent>();
        for (GuiComponent guiComponent2 : guiComponent.f()) {
            ClickGuiModuleCardComponent clickGuiModuleCardComponent;
            if (!(guiComponent2 instanceof PaddedComponent) || (clickGuiModuleCardComponent = ((PaddedComponent)guiComponent2).t(ClickGuiModuleCardComponent.class)) == null) continue;
            arrayList.add(clickGuiModuleCardComponent);
        }
        arrayList.sort(ClickGuiModuleCardComponent::lambda$collectButtons$0);
        return arrayList;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction() == MouseButton.LEFT_CLICK) {
            if (this.Ry && this.i.V$src$Z$1xhop3l()) {
                if (this.Rt.V$src$Z$1xhop3l() && this.Rt.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                    this.Rw.K(false);
                    if (this.RM != null) {
                        this.RM.run();
                    }
                    return;
                }
                MousePosition mousePosition = RenderUtils.h();
                this.RC = (double)mousePosition.H - this.n();
                this.Ru = true;
                this.RO = Double.NaN;
                this.A(true);
                PaddedComponent paddedComponent = this.t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je();
                if (paddedComponent != null) {
                    paddedComponent.A(true);
                }
                ClientSettings.fT = this;
                return;
            }
            if (this.Rv.V$src$Z$1xhop3l() && this.R_ && this.O.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                this.Rw.K(!this.Rw.f$src$Z$148d2ux());
                return;
            }
            if (this.Rk.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                if (this.R1 != null) {
                    this.R1.run();
                }
                return;
            }
            if (this.RA.V$src$Z$1xhop3l() && this.RA.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                return;
            }
            if (this.Rw.X()) {
                this.RW = System.currentTimeMillis();
                return;
            }
            this.Rw.s(!this.Rw.r$src$Z$14eylz9(), true);
        } else if (guiMouseEvent.getAction() == MouseButton.RIGHT_CLICK && this.R1 != null) {
            this.R1.run();
        }
    }

    private void W() {
        this.f(this.Rx, this.Rw.r$src$Z$14eylz9());
        this.f(this.Rf, this.Rw.r$src$Z$14eylz9());
        this.f(this.G, this.Rw.r$src$Z$14eylz9());
        this.f(this.R0, this.Rz);
        this.f(this.Rp, this.Rz);
        this.f(this.R, this.R7 && !this.Rz);
        this.f(this.R6, this.K);
    }

    public ClickGuiModuleCardComponent(Mod mod) {
        this(mod, 22.0);
    }

    public Mod j$src$Lgg_vape_module_Mod_$ozzvpn() {
        return this.Rw;
    }

    private String L$src$Ljava_lang_String_$1xzc1sf() {
        String string = this.Rw.a().h();
        if (string != null && !string.isEmpty()) {
            return string;
        }
        return this.Rw.X() ? "Set bind" : "";
    }

    private void j$src$V$feu89x() {
        if (System.currentTimeMillis() - this.RW < 2000L) {
            if (this.Rw.X() && this.Rw.a().y$src$Z$r0tfl8()) {
                this.RN.Y(Collections.singletonList(ClickGuiModuleCardRenderState.j("Use via keybind while in game")));
            } else {
                this.RN.Y(Collections.singletonList(ClickGuiModuleCardRenderState.j("Must be bound to use")));
            }
            return;
        }
        this.RN.Y(this.Rw.S());
    }


    public boolean H$src$Z$ew5873() {
        return this.K;
    }

    private Color r(Color color) {
        if (color == null) {
            return null;
        }
        if (this.R7 && !this.Rz) {
            int n = Math.max(0, Math.round((float)color.getAlpha() * this.U$src$F$f3ajfk()));
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        return color;
    }

    @Override
    public void u() {
        this.j$src$V$feu89x();
    }

    public boolean z() {
        return this.Ry;
    }

    private static int lambda$collectButtons$0(ClickGuiModuleCardComponent clickGuiModuleCardComponent, ClickGuiModuleCardComponent clickGuiModuleCardComponent2) {
        PaddedComponent paddedComponent = clickGuiModuleCardComponent.t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je();
        PaddedComponent paddedComponent2 = clickGuiModuleCardComponent2.t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je();
        double d = paddedComponent != null ? paddedComponent.n() : 0.0;
        double d2 = paddedComponent2 != null ? paddedComponent2.n() : 0.0;
        return Double.compare(d, d2);
    }

    public void V(boolean bl) {
        this.R7 = bl;
    }

    public void b(@Nullable Runnable runnable) {
        this.R1 = runnable;
    }

    public void u(boolean bl) {
        this.K = bl;
    }

    private double Y$src$D$f5hpra() {
        PaddedComponent paddedComponent = this.t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je();
        return paddedComponent != null ? paddedComponent.n() : this.n();
    }

    public boolean m$src$Z$fghm5g() {
        return this.R_;
    }

    @Nullable
    private PaddedComponent t$src$Lgg_vape_ui_click_component_layout_PaddedCompone$1tyz2je() {
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent == null) {
            return null;
        }
        FrameComponent frameComponent2 = frameComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        return frameComponent2 instanceof PaddedComponent ? (PaddedComponent)frameComponent2 : null;
    }

    public void G(Runnable runnable) {
        this.RM = runnable;
    }
}

