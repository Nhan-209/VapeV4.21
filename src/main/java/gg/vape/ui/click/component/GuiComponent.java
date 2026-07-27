package gg.vape.ui.click.component;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.MouseInput;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.GuiActivationListener;
import gg.vape.ui.click.component.GuiComponentContract;
import gg.vape.ui.click.component.GuiKeyTypedListener;
import gg.vape.ui.click.component.ToolTips;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.FrameOutsideChildClickFilterMouseListener;
import gg.vape.ui.font.FontOption;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.Value;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jetbrains.annotations.Nullable;

public abstract class GuiComponent
implements GuiComponentContract {
    private double H;
    private boolean Y;
    private double h;
    private boolean S;
    private double y = 5.0;
    private Value Z;
    private List<GuiKeyTypedListener> e;
    private long g;
    private List<GuiMouseListener> m;
    public final float V = 8.0f;
    private boolean T = true;
    private boolean x = false;
    private final ArrayList<GuiComponent> W;
    private GuiMouseEvent P;
    private boolean F;
    @Nullable
    private FontOption U;
    private long D;
    private double k;
    private boolean j = true;
    public final float M = 5.0f;
    private double d = -1.0;
    private boolean s = true;
    private static GuiComponent[] c;
    protected boolean q = false;
    private FrameHeaderComponent w;
    public final double N = 0.15;
    private long C;
    private double l = -1.0;
    private double r;
    private Color L;
    private List<GuiMouseListener> u = new CopyOnWriteArrayList<GuiMouseListener>();
    private boolean n = true;
    private String z = "unnamed";
    private boolean f;
    private final List<GuiActivationListener> E;
    private boolean A;
    private ToolTips B;
    private FrameComponent X;

    public GuiComponent M(ToolTips toolTips) {
        this.B = toolTips;
        return this;
    }

    @Override
    public void o(double d) {
        this.h = d;
        if (this.l == -1.0) {
            this.l = d;
        }
    }

    public boolean K$src$Z$1wnfv4l() {
        return this.S;
    }

    public void w(FrameHeaderComponent frameHeaderComponent) {
        this.w = frameHeaderComponent;
    }

    public void B$src$V$1wihpow() {
    }

    @Override
    public double L() {
        return this.Y ? this.r : Math.max(this.r, this.C());
    }

    @Override
    public boolean V$src$Z$1xhop3l() {
        if (this.r$src$Lgg_vape_value_Value_$fdf20y() != null && this.r$src$Lgg_vape_value_Value_$fdf20y().C$src$Z$1a17d8q()) {
            return false;
        }
        boolean bl = true;
        if (this.Z != null) {
            bl = this.Z.K$src$Z$1a5lpzm();
        }
        return this.s && bl;
    }

    public void Y(@Nullable FontOption fontOption) {
        this.U = fontOption;
    }

    public void A(boolean bl) {
        this.f = bl;
    }

    public List<GuiActivationListener> j$src$Ljava_util_List_$1ys7oc7() {
        return this.E;
    }

    public void d(boolean bl) {
        this.j = bl;
    }

    public static void D(GuiComponent[] guiComponentArray) {
        c = guiComponentArray;
    }

    public void o(boolean bl) {
        this.x = bl;
    }

    public Color d() {
        if (this.L == null) {
            this.L = GuiComponent.J.i;
        }
        return this.L;
    }

    public SmoothFontRenderer U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(double d) {
        return this.U != null ? this.U.k((float)d, true) : Vape.INSTANCE.getFontManager().W(d, false);
    }

    public String a$src$Ljava_lang_String_$f13x47() {
        return this.z;
    }

    public void s(FrameComponent frameComponent) {
        this.X = frameComponent;
    }

    public void P(boolean bl) {
        this.F = bl;
    }

    public boolean Z$src$Z$16e8vsp() {
        return this.j;
    }

    public FrameComponent B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() {
        return this.X;
    }

    public void X$src$V$1wul6qu() {
        GuiRenderPrimitives.y((float)this.G$src$D$1b2f02a(), (float)this.n(), 1.0f, (float)this.L(), Color.MAGENTA);
        GuiRenderPrimitives.y((float)this.G$src$D$1b2f02a(), (float)this.n(), (float)this.A(), 1.0f, Color.MAGENTA);
        GuiRenderPrimitives.y((float)this.G$src$D$1b2f02a() + (float)this.A(), (float)this.n(), 1.0f, (float)this.L(), Color.MAGENTA);
        GuiRenderPrimitives.y((float)this.G$src$D$1b2f02a(), (float)this.n() + (float)this.L(), (float)this.A(), 1.0f, Color.MAGENTA);
    }

    public void o(GuiKeyTypedListener guiKeyTypedListener) {
        this.e.add(guiKeyTypedListener);
    }

    public double H$src$D$1wlsgtk() {
        return this.d;
    }

    public void b(GuiKeyTypedListener guiKeyTypedListener) {
        this.e.remove(guiKeyTypedListener);
    }

    @Override
    public void Y(double d) {
        this.r = d;
        if (this.d == -1.0) {
            this.d = d;
        }
    }

    public void J() {
        if (ClientSettings.P(this)) {
            this.F();
            if (!this.A) {
                this.A = true;
                for (GuiActivationListener object : this.E) {
                    object.A(true);
                }
            }
        }
        this.n(true);
        boolean bl = ClientSettings.fW.fe.L();
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l() || guiComponent.K$src$Z$1wnfv4l() || !guiComponent.t() || !ClientSettings.P(guiComponent) || this instanceof FrameComponent && ((FrameComponent)this).j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null && ((FrameComponent)this).j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().t() && !(guiComponent instanceof FrameHeaderComponent)) continue;
            guiComponent.J();
            if (guiComponent.J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf() == null || !bl) continue;
            bl = false;
        }
        if (bl && this.B != null) {
            this.y$src$V$1xcqebr();
        }
    }

    public double U$src$D$muzvq3() {
        return this.L();
    }

    public double l$src$D$1x5l26k() {
        return this.l;
    }

    public void I(GuiComponent guiComponent) {
        this.f().remove(guiComponent);
    }

    public double Z$src$D$1wvori2() {
        return this.y;
    }

    public void C(Value value) {
        this.Z = value;
        value.L(this);
        if (value.w$src$Ljava_lang_String_$ikqblg() != null) {
            this.w(value.w$src$Ljava_lang_String_$ikqblg());
        }
    }

    public List<GuiMouseListener> O$src$Ljava_util_List_$148rlrm() {
        return this.u;
    }

    @Nullable
    public FontOption n$src$Lgg_vape_ui_font_FontOption_$1sav7ha() {
        return this.U;
    }

    public void S() {
        CopyOnWriteArrayList<GuiComponent> copyOnWriteArrayList = new CopyOnWriteArrayList<GuiComponent>(this.f());
        for (GuiComponent guiComponent : copyOnWriteArrayList) {
            if (!guiComponent.j$src$Z$dapde9()) continue;
            this.I(guiComponent);
        }
    }

    public void y$src$V$1xcqebr() {
        if (this.C == 0L) {
            this.C = System.currentTimeMillis();
        }
        this.g += System.currentTimeMillis() - this.C;
        if (this.g >= 2000L) {
            MousePosition mousePosition = RenderUtils.h();
            this.B.K(mousePosition.O);
            this.B.S(mousePosition.H);
            this.B.Z(true);
            ClientSettings.V = this.B;
        }
    }

    public SmoothFontRenderer A$src$Lgg_vape_ui_font_SmoothFontRenderer_$jrhwp3() {
        return this.U != null ? this.U.k(1.0f, false) : Vape.INSTANCE.getFontManager().Y();
    }

    public void S(boolean bl) {
        this.T = bl;
    }

    public static GuiComponent[] D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() {
        return c;
    }

    public boolean O$src$Z$1wpn1i1() {
        return this.j;
    }

    public RectData Q() {
        return new RectData(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L());
    }

    public void n(boolean bl) {
        this.q = bl;
    }

    public void D(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.equals(this.P)) {
            return;
        }
        this.P = guiMouseEvent;
        if (guiMouseEvent.isCancelled()) {
            return;
        }
        for (GuiMouseListener object2 : this.O$src$Ljava_util_List_$148rlrm()) {
            if (!object2.Q(new Point(guiMouseEvent.getX(), guiMouseEvent.getY()))) continue;
            return;
        }
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>(this.f());
        Collections.reverse(arrayList);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            GuiComponent guiComponent = (GuiComponent)iterator.next();
            if (!guiComponent.V$src$Z$1xhop3l() || guiComponent.K$src$Z$1wnfv4l() || !guiComponent.w$src$Z$e457mb() || this instanceof FrameComponent && ((FrameComponent)this).j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null && ((FrameComponent)this).j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().w$src$Z$e457mb() && !(guiComponent instanceof FrameHeaderComponent) || !guiComponent.b$src$Z$1x034rw()) continue;
            guiComponent.D(guiMouseEvent);
            if (guiComponent instanceof FrameHeaderComponent || this.x) continue;
            return;
        }
        this.g(guiMouseEvent);
        for (GuiMouseListener guiMouseListener : this.O$src$Ljava_util_List_$148rlrm()) {
            guiMouseListener.g(new Point(MouseInput.N(), MouseInput.u()), guiMouseEvent.getAction() == MouseButton.LEFT_CLICK ? MouseClickButton.LEFT_CLICK : (guiMouseEvent.getAction() == MouseButton.RIGHT_CLICK ? MouseClickButton.RIGHT_CLICK : (guiMouseEvent.getAction() == MouseButton.MIDDLE_CLICK ? MouseClickButton.MIDDLE_CLICK : null)));
        }
    }

    public void setText(String string) {
        this.z = string;
    }

    public void g$src$V$1x2u3n9() {
        this.n(false);
        if (this.A) {
            this.A = false;
            this.onEnable();
            for (GuiActivationListener guiActivationListener : this.E) {
                guiActivationListener.A(false);
            }
        }
    }

    public void m$src$V$1x64v7f() {
        this.g = 0L;
        this.C = 0L;
        this.B.Z(false);
        ClientSettings.V = null;
    }

    public void j(GuiMouseListener guiMouseListener) {
        this.u.add(guiMouseListener);
    }

    public void d(GuiActivationListener guiActivationListener) {
        this.E.add(guiActivationListener);
    }

    @Override
    public double n() {
        return this.k;
    }

    @Override
    public void S(double d) {
        this.k = d;
    }

    public void H(GuiComponent ... guiComponentArray) {
        Collections.addAll(this.f(), guiComponentArray);
    }

    public Value r$src$Lgg_vape_value_Value_$fdf20y() {
        return this.Z;
    }

    public ToolTips J$src$Lgg_vape_ui_click_component_ToolTips_$bb9snf() {
        return this.B;
    }

    public void i$src$V$c9opdk() {
    }

    public SmoothFontRenderer O(double d) {
        return this.U != null ? this.U.k((float)d, false) : Vape.INSTANCE.getFontManager().Y(d);
    }

    public void W(boolean bl) {
        this.Y = bl;
    }

    public boolean w$src$Z$e457mb() {
        return this.q;
    }

    public void q(double d) {
        this.l = d;
        this.h = d;
    }

    public double t$src$D$1x9zexg() {
        double d;
        double d2;
        double d3 = this.L();
        if (this instanceof FrameComponent && (d2 = Math.max(d3, d = ((FrameComponent)this).d$src$D$ibccpu())) > d) {
            d3 = d;
        }
        return d3;
    }

    @Override
    public double G$src$D$1b2f02a() {
        return this.H;
    }

    public void onDisable() {
        if (!this.Z$src$Z$16e8vsp()) {
            return;
        }
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.U$src$D$muzvq3(), this.d());
    }

    protected void x$src$V$1xc6lqe() {
        if (this.w$src$Z$e457mb() && !this.t()) {
            this.g$src$V$1x2u3n9();
            for (GuiComponent guiComponent : this.f()) {
                guiComponent.g$src$V$1x2u3n9();
            }
        }
    }

    public boolean o$src$Z$1x78ghl() {
        return this.f;
    }

    public boolean i(int n, int n2) {
        return this.Q().J(n, n2);
    }

    public GuiComponent T(Color color) {
        this.L = color;
        return this;
    }

    public boolean Y$src$Z$1wv4zfn() {
        return this.x;
    }

    public List<GuiComponent> f() {
        return this.W;
    }

    static {
        GuiComponent.D(new GuiComponent[4]);
    }

    public void B(boolean bl) {
        this.S = bl;
    }

    public boolean b$src$Z$1x034rw() {
        return this.T;
    }

    public List<GuiMouseListener> o$src$Ljava_util_List_$10z72du() {
        return this.m;
    }

    public boolean j$src$Z$dapde9() {
        return this.n;
    }

    public void Q(boolean bl) {
        this.n = bl;
    }

    public Frame L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa() {
        if (this.w != null) {
            return this.w.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
        }
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (frameComponent != null) {
            if (frameComponent instanceof Frame) {
                return (Frame)frameComponent;
            }
            return frameComponent.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
        }
        if (frameComponent instanceof Frame) {
            return (Frame)frameComponent;
        }
        if (this instanceof Frame) {
            return (Frame)this;
        }
        return null;
    }

    public void u(double d) {
        this.d = d;
        this.r = d;
    }

    public void z(GuiActivationListener guiActivationListener) {
        this.E.remove(guiActivationListener);
    }

    public void e(GuiMouseEvent guiMouseEvent) {
        this.U(guiMouseEvent);
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            guiComponent.e(guiMouseEvent);
        }
    }

    public void C(double d) {
        this.y = d;
    }

    public void Z(boolean bl) {
        this.s = bl;
    }

    public void q$src$V$1x8c1kv() {
        this.I();
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            guiComponent.q$src$V$1x8c1kv();
        }
    }

    public GuiComponent w(@Nullable String string) {
        if (string == null) {
            this.B = null;
            return this;
        }
        if (string.isEmpty()) {
            return this;
        }
        this.B = new ToolTips(this, string);
        return this;
    }

    public void T$src$V$1wse0de() {
        this.u();
        List<GuiComponent> list = this.f();
        int n = list.size();
        for (int i = 0; i < n; ++i) {
            list.get(i).T$src$V$1wse0de();
        }
    }

    public void K(Color color) {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), color);
        this.O(0.75).d(String.format("x %f, y %f, w %f, h %f", this.G$src$D$1b2f02a(), this.n(), this.A(), this.L()), this.G$src$D$1b2f02a(), this.n(), Color.WHITE);
    }

    private static ObfuscatedRuntimeException e(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void E(GuiMouseListener guiMouseListener) {
        this.u.remove(guiMouseListener);
    }

    @Override
    public double A() {
        return this.F ? this.h : Math.max(this.h, this.x());
    }

    public GuiComponent() {
        this.m = new CopyOnWriteArrayList<GuiMouseListener>();
        this.e = new ArrayList<GuiKeyTypedListener>();
        this.E = new ArrayList<GuiActivationListener>();
        this.W = new ArrayList();
    }

    public void q(Frame frame, Frame frame2) {
        this.u.add(new FrameOutsideChildClickFilterMouseListener(this, frame, frame2));
    }

    public boolean t() {
        MousePosition mousePosition = RenderUtils.h();
        return this.Q().Z(mousePosition);
    }

    public void r$src$V$1x8vu68() {
        this.u.clear();
    }

    @Override
    public void K(double d) {
        this.H = d;
    }

    public List<GuiKeyTypedListener> b$src$Ljava_util_List_$1hubsov() {
        return this.e;
    }

    public void Z(GuiMouseListener guiMouseListener) {
        this.m.add(guiMouseListener);
    }

    public void c() {
        this.x$src$V$1xc6lqe();
        if (!(this.g == 0L || this.D != this.g || this.w$src$Z$e457mb() && this.V$src$Z$1xhop3l())) {
            this.m$src$V$1x64v7f();
        }
        if (this.B != null) {
            this.B.s(true);
        }
        this.D = this.g;
        this.H();
        if (this instanceof FrameComponent) {
            ((FrameComponent)this).z$src$V$infu7a();
        }
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l() || this.K$src$Z$1wnfv4l()) continue;
            try {
                guiComponent.c();
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
    }

    public /* synthetic */ double double_G() {
        return this.G$src$D$1b2f02a();
    }

    public /* synthetic */ double double_n() {
        return this.n();
    }

    public /* synthetic */ double double_A() {
        return this.A();
    }

    public /* synthetic */ double double_L() {
        return this.L();
    }
}
