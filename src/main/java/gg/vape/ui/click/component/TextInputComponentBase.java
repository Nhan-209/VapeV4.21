package gg.vape.ui.click.component;

import gg.vape.Vape;
import gg.vape.input.KeyboardInput;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.FocusableComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.GuiRefreshListener;
import gg.vape.ui.click.component.value.SliderInputHandle;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.ClipboardUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ListValueSuggestionProvider;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public abstract class TextInputComponentBase
extends GuiComponent
implements FocusableComponent {
    private Color x3;
    private boolean xs;
    private float x5;
    private int xd;
    private float xL;
    private float xm;
    protected boolean x4;
    @Nullable
    private ListValueSuggestionProvider x6;
    private final TimerUtil x_;
    protected int x2 = 0;
    protected double xk;
    private boolean i = true;
    private final TimerUtil x8;
    private float xz;
    @Nullable
    private List<String> I;
    @Nullable
    private Color xc;
    private String xF = "";
    @Nullable
    private Consumer<String> xb;
    private static String[] G;
    protected boolean R;
    protected GlyphIconComponent b;
    protected boolean xS;
    private boolean v;
    private float Q;
    private final List<GuiRefreshListener> o;
    protected ColorAnimation xR;
    private Color xD;
    private boolean O;
    private boolean xf;
    int K;
    private float xe;
    private double xZ;
    protected Color a;
    @Nullable
    private SmoothFontRenderer xQ;
    private String xX;

    public float K$src$F$w63uk8() {
        return this.Q;
    }

    public void D(float f) {
        this.xL = f;
    }

    public double p$src$D$187zcry() {
        return this.A();
    }

    public void s(ColorAnimation colorAnimation) {
        this.xR = colorAnimation;
    }

    public float p$src$F$1qfoyd() {
        return 0.0f;
    }

    public void S(SmoothFontRenderer smoothFontRenderer, double d, double d2) {
        if (!this.G$src$Lgg_vape_utils_TimerUtil_$3050nw().hasTimeElapsed(500L)) {
            smoothFontRenderer.d("|", d, d2, TextInputComponentBase.J.Z);
        }
        if (this.G$src$Lgg_vape_utils_TimerUtil_$3050nw().hasTimeElapsed(1000L)) {
            this.G$src$Lgg_vape_utils_TimerUtil_$3050nw().reset();
        }
    }

    public void K$src$V$w63uy0() {
        this.k("");
    }

    private void lambda$new$0() {
        this.p();
    }

    private void lambda$new$1(char c, int n) {
        String string;
        this.G$src$Lgg_vape_utils_TimerUtil_$3050nw().reset();
        if (n == 27) {
            ClientSettings.fT = null;
            return;
        }
        if (n == 13) {
            this.p();
            return;
        }
        ListValueSuggestionProvider listValueSuggestionProvider = this.K$src$Lgg_vape_value_ListValueSuggestionProvider_$yndqzl();
        if (listValueSuggestionProvider != null && n == 9) {
            Consumer<String> consumer;
            List<String> list = this.I;
            if (list == null || list.isEmpty()) {
                return;
            }
            if (this.xd > list.size()) {
                this.xd = 0;
            }
            String string2 = list.get(this.xd);
            this.M(string2, false);
            ++this.xd;
            if (this.xd > list.size() - 1) {
                this.xd = 0;
            }
            if ((consumer = this.xb) != null) {
                consumer.accept(string2);
            }
            return;
        }
        if (c == '\u0016' && KeyboardInput.isKeyDown(162)) {
            this.k(ClipboardUtil.getText());
            this.x2 = this.i$src$Ljava_lang_String_$1n2xf3k().length();
            return;
        }
        if (c == '\u0003' && KeyboardInput.isKeyDown(162)) {
            ClipboardUtil.setText(this.i$src$Ljava_lang_String_$1n2xf3k());
            return;
        }
        if (n == 37 && this.x2 > 0) {
            --this.x2;
            this.n$src$V$wpcnpn();
            return;
        }
        if (n == 39 && this.x2 < this.i$src$Ljava_lang_String_$1n2xf3k().length()) {
            ++this.x2;
            this.n$src$V$wpcnpn();
            return;
        }
        if (n == 36 && this.x2 > 0) {
            this.x2 = 0;
            this.n$src$V$wpcnpn();
            return;
        }
        if (n == 35 && this.x2 < this.i$src$Ljava_lang_String_$1n2xf3k().length()) {
            this.x2 = this.i$src$Ljava_lang_String_$1n2xf3k().length();
            this.n$src$V$wpcnpn();
            return;
        }
        if (n == 8) {
            this.l$src$V$1mkxjop();
            if (this.i$src$Ljava_lang_String_$1n2xf3k().length() > 0 && this.x2 > 0) {
                --this.x2;
                int n2 = this.x2;
                String string3 = this.i$src$Ljava_lang_String_$1n2xf3k();
                String string4 = this.i$src$Ljava_lang_String_$1n2xf3k().substring(0, this.x2) + this.i$src$Ljava_lang_String_$1n2xf3k().substring(Math.min(this.x2 + 1, this.i$src$Ljava_lang_String_$1n2xf3k().length()));
                this.k(string4);
                if (!this.i$src$Ljava_lang_String_$1n2xf3k().equals(string3)) {
                    this.x2 = n2;
                }
            }
            return;
        }
        if (this.K == -1 ? this.r$src$Lgg_vape_ui_font_SmoothFontRenderer_$ce911j().N(this.i$src$Ljava_lang_String_$1n2xf3k()) > this.r() * 2.0 : this.i$src$Ljava_lang_String_$1n2xf3k().length() >= this.K) {
            return;
        }
        if (c != '\u0000' && ((string = this.i$src$Ljava_lang_String_$1n2xf3k()).length() > 0 || this.x2 == 0)) {
            try {
                String string5 = string.substring(0, this.x2) + c + string.substring(this.x2);
                this.k(string5);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
    }

    protected double Y$src$D$wdsytk() {
        return this.G$src$D$1b2f02a() + (double)this.y();
    }

    public Color q$src$Ljava_awt_Color_$1w22ppk() {
        return this.x3;
    }

    private void n$src$V$wpcnpn() {
        if (!this.xS) {
            return;
        }
        double d = this.O(1.2).N("|");
        double d2 = this.r$src$Lgg_vape_ui_font_SmoothFontRenderer_$ce911j().N(this.i$src$Ljava_lang_String_$1n2xf3k().substring(0, this.x2));
        this.x4 = d2 + this.xk >= this.r();
        boolean bl = this.R = d2 + this.xk <= 0.0;
        if (this.R) {
            this.xk = -d2 + d;
        } else if (this.x4) {
            this.xk = this.r() - d2 - d;
        }
    }

    public void L$src$V$w6nnjd() {
        if (this.n$src$Z$1rnxqrn()) {
            ClientSettings.fT = null;
        }
    }

    public TextInputComponentBase s(GuiRefreshListener guiRefreshListener) {
        this.o.add(guiRefreshListener);
        return this;
    }

    public TimerUtil e$src$Lgg_vape_utils_TimerUtil_$1qrc1iy() {
        return this.x8;
    }

    public float y() {
        return this.xm;
    }

    public void i(boolean bl) {
        this.v = bl;
        this.xQ = null;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        boolean bl = this.Q().J(guiMouseEvent.getX(), guiMouseEvent.getY());
        if (bl) {
            if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK)) {
                this.K$src$V$w63uy0();
            }
            if (ClientSettings.fT != null) {
                // empty if block
            }
            ClientSettings.fT = this;
            return;
        }
        if (ClientSettings.fT != null) {
            this.L$src$V$w6nnjd();
        } else {
            ClientSettings.fT = this;
        }
    }

    public void H(float f) {
        this.xm = f;
    }

    static {
        TextInputComponentBase.w(new String[3]);
    }

    public void e$src$V$wkeide() {
        Color color;
        double d;
        double d2;
        String string;
        SmoothFontRenderer smoothFontRenderer;
        boolean bl;
        SmoothFontRenderer smoothFontRenderer2 = this.r$src$Lgg_vape_ui_font_SmoothFontRenderer_$ce911j();
        double d3 = smoothFontRenderer2.d("A");
        double d4 = this.n() + this.L() / 2.0 - d3 / 2.0;
        String string2 = this.i$src$Ljava_lang_String_$1n2xf3k();
        boolean bl2 = bl = this.n$src$Z$1rnxqrn() && this.i;
        if (bl) {
            Color color2;
            double d5;
            double d6;
            String string3;
            SmoothFontRenderer smoothFontRenderer3;
            boolean bl3 = false;
            if (this.i) {
                if (this.i$src$Ljava_lang_String_$1n2xf3k() != null && this.i$src$Ljava_lang_String_$1n2xf3k().length() < 1) {
                    // empty if block
                }
            } else {
                bl3 = true;
                string2 = this.C$src$Ljava_lang_String_$1pcbyty();
            }
            if (string2 == null) {
                string2 = "";
            }
            double d7 = smoothFontRenderer2.N(string2.substring(0, this.x2));
            SmoothFontRenderer smoothFontRenderer4 = this.O(1.2);
            this.R();
            if (this.xS) {
                RenderUtils.m(this.Y$src$D$wdsytk() + (double)this.g() - this.O(1.2).N("|"), this.n() + 2.5, this.r() + this.O(1.2).N("|") + 2.0, this.L() - 5.0);
            }
            double d8 = this.Y$src$D$wdsytk() + (double)this.g() + this.xk;
            double d9 = d4 + (double)this.p$src$F$1qfoyd();
            double d10 = d8;
            String string4 = string2;
            SmoothFontRenderer smoothFontRenderer5 = smoothFontRenderer2;
            if (bl3) {
                smoothFontRenderer3 = smoothFontRenderer5;
                string3 = string4;
                d6 = d10;
                d5 = d9;
                color2 = this.xD;
            } else {
                smoothFontRenderer3 = smoothFontRenderer5;
                string3 = string4;
                d6 = d10;
                d5 = d9;
                color2 = this.x3;
            }
            smoothFontRenderer3.d(string3, d6, d5, color2);
            this.x(string2, bl3, smoothFontRenderer2, d8, d4, d3);
            if (this.xS) {
                RenderUtils.T();
            }
            this.S(smoothFontRenderer4, this.Y$src$D$wdsytk() + (double)this.g() + d7 + this.xk, this.n() + this.L() / 2.0 - smoothFontRenderer4.d("|") / 2.0 + (double)this.p$src$F$1qfoyd());
            if (KeyboardInput.isKeyDown(8) && this.e$src$Lgg_vape_utils_TimerUtil_$1qrc1iy().hasTimeElapsed(100L)) {
                this.e$src$Lgg_vape_utils_TimerUtil_$1qrc1iy().reset();
            }
            return;
        }
        boolean bl4 = false;
        if (!this.i || this.i$src$Ljava_lang_String_$1n2xf3k() == null || this.i$src$Ljava_lang_String_$1n2xf3k().length() < 1) {
            bl4 = true;
            string2 = this.C$src$Ljava_lang_String_$1pcbyty();
        }
        if (string2 == null) {
            string2 = "";
        }
        double d11 = smoothFontRenderer2.N(string2.substring(0, this.x2));
        SmoothFontRenderer smoothFontRenderer6 = this.O(1.2);
        this.R();
        if (this.xS) {
            RenderUtils.m(this.Y$src$D$wdsytk() + (double)this.g() - this.O(1.2).N("|"), this.n() + 2.5, this.r() + this.O(1.2).N("|") + 2.0, this.L() - 5.0);
        }
        double d12 = this.Y$src$D$wdsytk() + (double)this.g() + this.xk;
        double d13 = d4 + (double)this.p$src$F$1qfoyd();
        double d14 = d12;
        String string5 = string2;
        SmoothFontRenderer smoothFontRenderer7 = smoothFontRenderer2;
        if (bl4) {
            smoothFontRenderer = smoothFontRenderer7;
            string = string5;
            d2 = d14;
            d = d13;
            color = this.xD;
        } else {
            smoothFontRenderer = smoothFontRenderer7;
            string = string5;
            d2 = d14;
            d = d13;
            color = this.x3;
        }
        smoothFontRenderer.d(string, d2, d, color);
        this.x(string2, bl4, smoothFontRenderer2, d12, d4, d3);
        if (this.xS) {
            RenderUtils.T();
        }
    }

    @Nullable
    public Consumer<String> z() {
        return this.xb;
    }

    public void b$src$V$17wa4kz() {
        if (!this.n$src$Z$1rnxqrn()) {
            ClientSettings.fT = this;
        }
    }

    @Override
    public void F(boolean bl) {
        this.i = !bl;
    }

    public float g() {
        return this.xe;
    }

    public void v(Color color) {
        this.xc = color;
    }

    public void I(float f) {
        this.xz = f;
    }

    public Color O$src$Ljava_awt_Color_$dtc8p6() {
        return this.xD;
    }

    public void W(@Nullable Color color) {
        this.xc = color;
    }

    public static String[] i$src$ALjava_lang_String_$r4oxjr() {
        return G;
    }

    public String C$src$Ljava_lang_String_$1pcbyty() {
        return this.xX;
    }

    public abstract void p();

    public void r(boolean bl) {
        this.b.Z(bl);
    }

    public void t(Color color) {
        this.a = color;
    }

    public void P() {
        for (GuiRefreshListener guiRefreshListener : this.o) {
            guiRefreshListener.G();
        }
    }

    private void x(String string, boolean bl, SmoothFontRenderer smoothFontRenderer, double d, double d2, double d3) {
        boolean bl2;
        if (bl) {
            return;
        }
        if (!this.n$src$Z$1rnxqrn()) {
            return;
        }
        List<String> list = this.I;
        if (list == null || list.isEmpty()) {
            return;
        }
        String string2 = list.get(this.xd);
        String string3 = string2.length() >= string.length() ? string2.substring(string.length()) : "";
        boolean bl3 = bl2 = !string3.isEmpty() && string2.toLowerCase().startsWith(string.toLowerCase());
        if (bl2) {
            double d4 = d + smoothFontRenderer.N(string);
            smoothFontRenderer.d(string3, d4, d2 + (double)this.p$src$F$1qfoyd(), this.x3.darker().darker());
        }
        SmoothFontRenderer smoothFontRenderer2 = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.85);
        int n = (int)smoothFontRenderer2.N("tab");
        int n2 = (int)d + (int)smoothFontRenderer.N(string + string3) + 3;
        boolean bl4 = list.size() > 1 && !bl2;
        GuiRenderPrimitives.B(n2, d2 + (double)this.p$src$F$1qfoyd() - 1.0, n + 6 + (bl4 ? 6 : 0), d3 + 3.0, TextInputComponentBase.J.a, 2.5f);
        smoothFontRenderer2.d("tab", n2 + 3, d2 + (double)this.p$src$F$1qfoyd() + 0.5, TextInputComponentBase.J.Z);
        if (bl4) {
            ImageRenderer.E(TextInputComponentBase.J.Z, n2 + 15, (float)d2 + 0.5f, "icons8_downloading_updates", 4.0f, 5.5f, false);
        }
    }

    public TimerUtil G$src$Lgg_vape_utils_TimerUtil_$3050nw() {
        return this.x_;
    }

    @Override
    public boolean v() {
        return !this.i;
    }

    public String i$src$Ljava_lang_String_$1n2xf3k() {
        return this.xF;
    }

    public void I(Color color) {
        this.x3 = color;
    }

    public ColorAnimation Z$src$Lgg_vape_ui_click_animation_ColorAnimation_$lc08l0() {
        return this.xR;
    }

    public void A(Color color) {
        this.xD = color;
    }

    public void Q(@Nullable Consumer<String> consumer) {
        this.xb = consumer;
    }

    @Nullable
    public Color k() {
        return this.xc;
    }

    public void E(@Nullable ListValueSuggestionProvider listValueSuggestionProvider) {
        this.x6 = listValueSuggestionProvider;
    }

    public void b(String string) {
        this.xX = string;
    }

    public TextInputComponentBase(String string) {
        this.xR = new ColorAnimation(0.15, TextInputComponentBase.J.m, TextInputComponentBase.J.l);
        this.xc = TextInputComponentBase.J.r;
        this.b = new GlyphIconComponent("newadd", 8.0, 8.0, 12.0, 12.0, J.z(), J.z().brighter(), null);
        this.a = null;
        this.x_ = new TimerUtil();
        this.x8 = new TimerUtil();
        this.xs = true;
        this.x3 = TextInputComponentBase.J.A;
        this.xD = TextInputComponentBase.J.A;
        this.K = -1;
        this.xk = 0.0;
        this.x4 = false;
        this.R = false;
        this.xS = false;
        this.x5 = 5.0f;
        this.xm = 5.0f;
        this.xL = 1.0f;
        this.xz = 2.0f;
        this.xe = 5.0f;
        this.xd = 0;
        this.O = false;
        this.Q = 0.85f;
        this.o = new ArrayList<GuiRefreshListener>();
        this.xX = string;
        this.b.r(this::lambda$new$0);
        this.o(this::lambda$new$1);
        this.H(this.b);
    }

    public void V(float f) {
        this.xe = f;
    }

    public boolean n$src$Z$1rnxqrn() {
        return this.i && ClientSettings.fT != null && ClientSettings.fT.equals(this);
    }

    private static Exception b(Exception exception) {
        return exception;
    }

    public void e(boolean bl) {
        this.xs = bl;
    }

    protected void R() {
    }

    public void b(boolean bl) {
        this.O = bl;
    }

    public boolean Q$src$Z$w9emlm() {
        return this.O;
    }

    public float O() {
        return this.x5;
    }

    @Override
    public void u() {
        if (this.xf && !this.w$src$Z$e457mb() && !this.n$src$Z$1rnxqrn()) {
            this.xR.J();
            this.xf = false;
        }
    }

    public void l$src$V$1mkxjop() {
    }

    public GlyphIconComponent t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o() {
        return this.b;
    }

    public static void w(String[] stringArray) {
        G = stringArray;
    }

    @Nullable
    public ListValueSuggestionProvider K$src$Lgg_vape_value_ListValueSuggestionProvider_$yndqzl() {
        return this.x6;
    }

    public void o(float f) {
        this.Q = f;
        this.xQ = null;
    }

    @Override
    public void H() {
    }

    public boolean u$src$Z$wt77ym() {
        return this.i$src$Ljava_lang_String_$1n2xf3k().replaceAll(" ", "").length() > 0;
    }

    public double r() {
        return this.p$src$D$187zcry() - (double)this.y() - (double)this.g() - 8.0 - (this.b.V$src$Z$1xhop3l() ? this.b.A() : 0.0);
    }

    @Override
    public void F() {
        if (!this.xf) {
            this.xR.J();
        }
        this.xf = true;
    }

    public void O(float f) {
        this.x5 = f;
    }

    @Override
    public void I() {
    }

    private SmoothFontRenderer r$src$Lgg_vape_ui_font_SmoothFontRenderer_$ce911j() {
        double d = Vape.INSTANCE.getClientSettings().s();
        if (this.xQ == null || this.xZ != d) {
            this.xQ = this.v ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.Q) : this.O((double)this.Q);
            this.xZ = d;
        }
        return this.xQ;
    }

    public void k(String string) {
        this.M(string, true);
        if (this.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.P();
        }
    }

    public void M(String string, boolean bl) {
        ListValueSuggestionProvider listValueSuggestionProvider;
        if (this.O && !string.isEmpty() && !string.matches("^\\d+(\\.\\d*)?$|^\\.\\d+$")) {
            return;
        }
        int n = string.length() - this.xF.length();
        this.x2 += n;
        this.x2 = MathUtil.clamp(this.x2, 0, string.length());
        boolean bl2 = this.xS = this.O(0.9).N(string) > this.r();
        if (!this.xS) {
            this.x4 = false;
            this.R = false;
            this.xk = 0.0;
        }
        this.xF = string;
        this.n$src$V$wpcnpn();
        if (bl && (listValueSuggestionProvider = this.K$src$Lgg_vape_value_ListValueSuggestionProvider_$yndqzl()) != null) {
            listValueSuggestionProvider.updateFilter(this.xF);
            List<String> list = this.I;
            List<String> list2 = listValueSuggestionProvider.getSuggestions();
            if (list != null && !list.equals(list2)) {
                this.xd = 0;
            }
            this.I = list2;
        }
    }

    public float j$src$F$wn5gyf() {
        return this.xz;
    }

    public float D$src$F$w29aep() {
        return this.xL;
    }

    public void n(int n) {
        this.K = n;
    }

    public boolean D$src$Z$w29avx() {
        return this.v;
    }

    public void j(boolean bl) {
        this.i = bl;
    }

    @Override
    public void c() {
        this.b.o(this.a != null ? this.a : J.z());
        this.b.P(this.b.e$src$Ljava_awt_Color_$1yl68fq().brighter());
        double d = this.n() + (double)(this.O() / 2.0f) + (double)this.p$src$F$1qfoyd();
        double d2 = this.L() - (double)this.O();
        double d3 = d + d2 / 2.0 - this.b.L() / 2.0;
        this.b.K(this.G$src$D$1b2f02a() + this.p$src$D$187zcry() - (double)this.g() - (double)this.y() - 8.0 - 2.0);
        this.b.S(d3);
        this.b.A(2.0);
        this.b.w(2.0);
        if (!(this instanceof SliderInputHandle)) {
            if (this.xs) {
                double d4 = this.Y$src$D$wdsytk();
                double d5 = d;
                double d6 = this.p$src$D$187zcry() - (double)(this.y() * 2.0f);
                double d7 = d2;
                GuiRenderPrimitives.P(d4, d5, d6, d7, this.xR.getInterpolatedColor(), this.xz, this.xL, 1.0f);
                if (this.xc != null) {
                    GuiRenderPrimitives.d(d4 + 0.5, d5 + 0.5, d6 - 1.0, d7 - 0.5, this.xc);
                }
            }
            this.e$src$V$wkeide();
        }
        super.c();
    }
}

