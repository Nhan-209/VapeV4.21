package gg.vape.ui.click.frame.impl.main;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCardActionState;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.StringUtils;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ClickGuiProfileCardComponent
extends InteractiveComponent {
    private final ColorAnimation iE;
    private static final double i4 = 22.0;
    private double iG;
    private static final double ik = 10.0;
    static final boolean im;
    private final ColorAnimation i3;
    private final DoubleAnimation is;
    private boolean io;
    private final ShapeIconComponent i0;
    private String ia;
    private static final int it;
    private static final double iT = 6.0;
    private static final double iU = 10.0;
    private boolean iJ;
    private static final double iQ = 0.75;
    private static final double iM = 6.0;
    private static final Color i7;
    private static final double iz = 2.0;
    private static final double ig = 8.0;
    private static final double iq = 10.0;
    private final RectData iS;
    private static final double ic = 6.0;
    private static final float id = 2.0f;
    private static final double il = 8.0;
    private static final float iw = 2.0f;
    private static final double iZ = 4.0;
    private static final double iP = 4.0;
    private static final double iL = 0.75;
    private String Q;
    private static final double ii = 0.65;
    private final IconGlyphComponent I;
    private String iu;
    private boolean iA;
    @Nullable
    private Runnable iy;
    private final RectData iv = new RectData(0.0, 0.0, 0.0, 0.0);
    private static final float iK = 3.0f;
    private final BindableInputComponent ip;
    private Profile iO;
    private final ColorAnimation v;
    private static final Color iF;
    private static final double ix = 8.0;
    private static final Color b;
    private static final double K = 6.0;
    private static final double iV = 18.0;
    private static final double iY = 2.0;
    private static final double in = 3.0;
    private static final double iX = 12.0;
    private boolean iD;
    private boolean ir;

    public void D(String string) {
        this.ia = string == null ? "" : StringUtils.l(string).trim();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction() == MouseButton.RIGHT_CLICK && !this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().t() && this.iy != null) {
            this.iy.run();
            this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().j(true);
            return;
        }
        if (guiMouseEvent.getAction() == MouseButton.LEFT_CLICK && !this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().t()) {
            if (this.iA && this.iy != null) {
                this.iy.run();
                this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().j(true);
                return;
            }
            if (this.ip.V$src$Z$1xhop3l() && this.ip.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                return;
            }
            if (this.iO != null) {
                Vape.INSTANCE.getProfilesManager().U(this.iO);
                this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().j(true);
            }
        }
        super.g(guiMouseEvent);
    }

    private Color R(Color color) {
        return ProfileCardActionState.t(color, this.is, this.ir);
    }

    public boolean g$src$Z$1ktdwl9() {
        return this.iJ;
    }

    public void g(boolean bl) {
        this.iJ = bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String O$src$Ljava_lang_String_$1yvr8j1() {
        return this.Q;
    }

    public void b(Profile profile) {
        this.iO = profile;
    }

    public boolean D$src$Z$1ka53tm() {
        return this.ir;
    }

    private void m(SmoothFontRenderer smoothFontRenderer, double d) {
        String string;
        String string2 = string = this.Q == null ? "" : StringUtils.l(this.Q).trim();
        if (string.isEmpty() || d <= 0.0) {
            this.iu = "";
            this.iG = 0.0;
            return;
        }
        double d2 = smoothFontRenderer.N(string);
        if (d2 <= d) {
            this.iu = string;
            this.iG = d2;
            return;
        }
        String string3 = "...";
        double d3 = smoothFontRenderer.N(string3);
        if (d3 > d) {
            this.iu = "";
            this.iG = 0.0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n = string.length();
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(string.charAt(i));
            double d4 = smoothFontRenderer.N(stringBuilder.toString()) + d3;
            if (!(d4 > d)) continue;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            break;
        }
        this.iu = stringBuilder.append(string3).toString();
        this.iG = smoothFontRenderer.N(this.iu);
    }

    public boolean F$src$Z$1kb8p0c() {
        return this.io;
    }

    public Profile h$src$Lgg_vape_config_Profile_$18amnwr() {
        return this.iO;
    }

    public void e(boolean bl) {
        this.ir = bl;
    }

    public void v(boolean bl) {
        this.io = bl;
    }

    public ClickGuiProfileCardComponent(Profile profile) {
        this.iS = new RectData(0.0, 0.0, 0.0, 0.0);
        this.i0 = new ShapeIconComponent(IconShape.ROUNDED_RECT, "ACTIVE", 10.0, 12.0, 3.0, 2.0f, Color.BLACK, Color.WHITE, 0.65);
        this.getClass();
        this.v = new ColorAnimation(0.15, ClickGuiProfileCardComponent.J.t, ClickGuiProfileCardComponent.J.z);
        this.getClass();
        this.i3 = new ColorAnimation(0.15, ClickGuiProfileCardComponent.J.t, ClickGuiProfileCardComponent.J.E);
        this.getClass();
        this.iE = new ColorAnimation(0.15, iF, ClickGuiProfileCardComponent.J.o);
        this.getClass();
        this.is = new DoubleAnimation(0.15, 0.0, 1.0);
        this.iu = "";
        this.iD = true;
        this.ia = "ACTIVE";
        this.iO = profile;
        this.Q = profile.n$src$Ljava_lang_String_$xqhelw();
        this.o(true);
        this.I = new IconGlyphComponent("settingdots", 6.0f, 6.0f, ClickGuiProfileCardComponent.J.W);
        this.I.r(true);
        this.ip = new BindableInputComponent(profile, ClickGuiProfileCardComponent.J.A);
        this.ip.Z(false);
        this.ip.Y(10.0);
        this.Y(22.0);
        this.d(false);
        this.H(this.I, this.i0);
        this.H(this.ip);
    }

    @Override
    public void H() {
        double d;
        boolean bl;
        Color color;
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        double d4 = this.A();
        double d5 = this.L();
        double d6 = d3 + d5 / 2.0;
        MousePosition mousePosition = RenderUtils.h();
        this.is.u(this.ir && !this.io);
        Color color2 = this.iJ ? this.R(J.z()) : null;
        Color color3 = ClickGuiProfileCardComponent.J.m;
        if (this.io) {
            color3 = ClickGuiProfileCardComponent.J.H;
        } else if (this.iJ) {
            color3 = ClickGuiProfileCardComponent.J.R;
        }
        GuiRenderPrimitives.B(d2, d3, d4, d5, this.R(color3), 3.0f);
        if (this.iJ) {
            if (!im && color2 == null) {
                throw new AssertionError();
            }
            color = ColorUtil.W(color2, 51);
            GuiRenderPrimitives.P(d2, d3, d4, d5 - 0.5, this.R(color), 3.0f, 0.75f, 1.0f);
        }
        if ((color = this.v.getInterpolatedColor()).getAlpha() > 0) {
            GuiRenderPrimitives.B(d2, d3, d4, d5, this.R(color), 3.0f);
        }
        double d7 = d2 + 8.0;
        double d8 = d6 - 3.0 - 0.5;
        GuiRenderPrimitives.m((float)d7, (float)d8, 6.0f, 1.0f, 0.5f, this.R(this.iJ ? color2 : b));
        if (this.iJ) {
            this.i0.W(ColorUtil.W(color2, 30));
            Color color4 = J.z();
            double d9 = Math.min(1.0, Math.max(0.0, this.is.getInterpolatedValue()));
            Color color5 = color4;
            if (this.ir || d9 > 0.0) {
                float f = (float)(1.0 - 0.4 * d9);
                int n = Math.max(0, (int)((float)color4.getRed() * f));
                int n2 = Math.max(0, (int)((float)color4.getGreen() * f));
                int n3 = Math.max(0, (int)((float)color4.getBlue() * f));
                color5 = new Color(n, n2, n3, color4.getAlpha());
            }
            this.i0.O(color5);
            double d10 = 2.4;
            double d11 = d7 + (6.0 - d10) / 2.0;
            double d12 = d6 - d10 / 2.0 - 0.5;
            GuiRenderPrimitives.V((float)d11, (float)d12, (float)d10, 0.5, this.R(color2));
        }
        double d13 = d2 + d4 - 6.0 - 6.0;
        double d14 = d6 - 3.0;
        this.iv.M(d13 - 6.0);
        this.iv.O(d14 - 8.0);
        this.iv.A(16.0);
        this.iv.U(22.0);
        this.iA = this.iv.Z(mousePosition);
        boolean bl2 = this.w$src$Z$e457mb() && !this.iA;
        this.v.u(bl2 && !this.io);
        this.i3.u(this.iA);
        Color color6 = this.i3.getInterpolatedColor();
        if (color6.getAlpha() > 0) {
            GuiRenderPrimitives.p(this.iv.o(), this.iv.W(), this.iv.e(), this.iv.R(), this.R(color6), false, 2.0f, 1.0f, 0.0f, ClickGuiProfileCardComponent.J.u, 6);
        }
        Color color7 = ClickGuiProfileCardComponent.J.W;
        if (this.iJ || this.io) {
            color7 = Color.WHITE;
        } else if (this.iA) {
            color7 = ClickGuiProfileCardComponent.J.f;
        }
        this.I.S(this.R(color7));
        this.I.K(d13);
        this.I.S(d14);
        this.I.c();
        double d15 = 0.0;
        double d16 = 0.0;
        boolean bl3 = this.iO.y$src$Z$r0tfl8();
        boolean bl4 = this.ip.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df();
        boolean bl5 = bl = bl3 || bl4 || this.w$src$Z$e457mb();
        if (bl) {
            d16 = d13 - 2.0 - this.ip.A() - 8.0;
            d = d6 - 5.0;
            this.ip.K(d16);
            this.ip.S(d);
            this.ip.Y(10.0);
            this.ip.Z(true);
            if (this.ir) {
                this.ip.f(false);
            } else {
                this.ip.f(null);
            }
            this.iS.M(d16);
            this.iS.O(d);
            this.iS.A(d15);
            this.iS.U(10.0);
        } else {
            this.ip.Z(false);
            this.iS.A(0.0);
        }
        d = d7 + 6.0 + 6.0;
        double d17 = (this.ip.V$src$Z$1xhop3l() ? d16 : d13) - 8.0;
        double d18 = Math.max(0.0, d17 - d);
        boolean bl6 = this.S$src$Z$1kie0q1();
        if (bl6) {
            double d19 = 0.0;
            this.i0.O(this.ia);
            this.i0.Z(true);
            d19 = this.i0.O$src$D$h1g6kw();
            d18 = Math.max(0.0, d18 - d19 - 4.0);
            SmoothFontRenderer smoothFontRenderer = this.O(0.75);
            this.m(smoothFontRenderer, d18);
            double d20 = d6 - smoothFontRenderer.d("A") / 2.0;
            Color color8 = ClickGuiProfileCardComponent.J.A;
            if (this.iJ || this.io) {
                color8 = Color.WHITE;
            }
            double d21 = Math.min(1.0, Math.max(0.0, this.is.getInterpolatedValue()));
            if (this.ir || d21 > 0.0) {
                float f = (float)(1.0 - 0.6 * d21);
                int n = Math.max(0, (int)((float)color8.getRed() * f));
                int n4 = Math.max(0, (int)((float)color8.getGreen() * f));
                int n5 = Math.max(0, (int)((float)color8.getBlue() * f));
                color8 = new Color(n, n4, n5, color8.getAlpha());
            }
            smoothFontRenderer.d(this.iu, d, d20 - 0.5, color8);
            double d22 = d + this.iG + 4.0 + 1.0;
            double d23 = d6 - 5.0;
            this.i0.K(d22);
            this.i0.S(d23);
            this.i0.o(20.0);
            this.i0.Y(10.0);
            this.i0.d$src$Lgg_vape_ui_click_component_TruncatedTextCompone$1ti9i2w().M(0.5);
            this.i0.c();
            return;
        }
        double d24 = 0.0;
        this.i0.Z(false);
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        this.m(smoothFontRenderer, d18);
        double d25 = d6 - smoothFontRenderer.d("A") / 2.0;
        Color color9 = ClickGuiProfileCardComponent.J.A;
        if (this.iJ || this.io) {
            color9 = Color.WHITE;
        }
        double d26 = Math.min(1.0, Math.max(0.0, this.is.getInterpolatedValue()));
        if (this.ir || d26 > 0.0) {
            float f = (float)(1.0 - 0.6 * d26);
            int n = Math.max(0, (int)((float)color9.getRed() * f));
            int n6 = Math.max(0, (int)((float)color9.getGreen() * f));
            int n7 = Math.max(0, (int)((float)color9.getBlue() * f));
            color9 = new Color(n, n6, n7, color9.getAlpha());
        }
        smoothFontRenderer.d(this.iu, d, d25 - 0.5, color9);
    }

    public boolean S$src$Z$1kie0q1() {
        return this.iD && this.iJ && !this.ia.isEmpty();
    }

    public void f(@Nullable Runnable runnable) {
        this.iy = runnable;
    }

    public void X(String string) {
        this.Q = string;
    }

    static {
        long l = 2390347671878500382L;
        it = (int)l;
        im = !ClickGuiProfileCardComponent.class.desiredAssertionStatus();
        b = new Color(62, 61, 62);
        i7 = new Color(115, 113, 115);
        iF = new Color(37, 36, 37);
    }

    public void j(boolean bl) {
        this.iD = bl;
    }
}

