package gg.vape.ui.click.component.module;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.MouseInput;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.Search;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.component.module.ModuleComponentDragStartClickHandler;
import gg.vape.ui.click.component.module.ModuleComponentExpandToggleClickHandler;
import gg.vape.ui.click.component.module.ModuleComponentSelectModuleClickHandler;
import gg.vape.ui.click.component.module.ModuleComponentToggleClickHandler;
import gg.vape.ui.click.component.module.ModuleComponentToggleModEnabledClickHandler;
import gg.vape.ui.click.component.module.ModuleComponentVerticalComparator;
import gg.vape.ui.click.component.value.SearchBlockListComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;
import gg.vape.ui.click.frame.impl.ModuleSearchFrame;
import gg.vape.ui.click.frame.impl.VisibleModuleListFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class ModuleComponent
extends InteractiveComponent {
    private int Zq = -1;
    private double Zw;
    private boolean Zi;
    private int Zs = 0;
    private BindableInputComponent Zo;
    private List<GuiComponent> Q = new ArrayList<GuiComponent>();
    private boolean Z2;
    private SquareIconButtonComponent ZB = new SquareIconButtonComponent("newclose", 1.5);
    private String ZC = null;
    private final long Z6 = 2000L;
    private Mod I;
    private long ZG = 0L;
    private ModuleCategoryFrame Zn;
    private boolean v;
    private double ZT;
    private RectData b;
    private static final long fb = 1974605012400478982L;
    private DoubleAnimation ZN;
    private boolean Zc;
    private double K;
    private IconButtonComponent Zf;
    private IconButtonComponent Z4;
    private SquareIconButtonComponent Zh = new SquareIconButtonComponent("newrearrange", 1.5);

    static double g(ModuleComponent moduleComponent, double d) {
        moduleComponent.K = d;
        return moduleComponent.K;
    }

    public BindableInputComponent C$src$Lgg_vape_ui_click_component_input_BindableInputC$4yzyz3() {
        return this.Zo;
    }

    static boolean P(ModuleComponent moduleComponent) {
        return moduleComponent.v;
    }

    static double f(ModuleComponent moduleComponent, double d) {
        moduleComponent.ZT = d;
        return moduleComponent.ZT;
    }

    public void m(boolean bl) {
        this.Z2 = bl;
    }

    public ModuleComponent(ModuleCategoryFrame moduleCategoryFrame, Mod mod, double d) {
        this.ZN = new DoubleAnimation(0.15, 0.0, 1.0);
        this.Z4 = new IconButtonComponent("settingdots");
        this.Zf = new IconButtonComponent("newstar");
        this.Zn = moduleCategoryFrame;
        this.I = mod;
        this.Zw = d;
        this.Zo = new BindableInputComponent(mod.a());
        this.T(ModuleComponent.J.i);
        if (mod.n() != null) {
            this.w(mod.n());
        }
        ModuleComponent moduleComponent = this;
        if (moduleCategoryFrame instanceof VisibleModuleListFrame) {
            this.v = true;
        }
        this.ZB.r(new ModuleComponentSelectModuleClickHandler(this, mod, moduleCategoryFrame));
        this.ZB.w("Remove module from favorites");
        this.Zh.r(new ModuleComponentDragStartClickHandler(this, moduleComponent));
        this.Z4.r(new ModuleComponentExpandToggleClickHandler(this, moduleCategoryFrame));
        this.Zf.r(new ModuleComponentToggleModEnabledClickHandler(this, mod));
        this.Zf.w("Add module to favorites");
        this.r(new ModuleComponentToggleClickHandler(this, mod));
        this.H(this.Zo, this.ZB, this.Zh, this.Z4, this.Zf);
    }

    @Override
    public void u() {
        String string = TextLabel.A$src$Ljava_lang_String_$3x6e5a();
        if (this.Zo.l$src$Z$1orbz7() && (this.ZC == null || !this.ZC.toLowerCase(Locale.ROOT).startsWith("press"))) {
            this.j("press a key to bind");
        }
        if (this.Zs != 0 && System.currentTimeMillis() > this.ZG + 2000L) {
            this.ZC = null;
            this.Zo.w(false);
            this.Zs = 0;
        } else if (this.ZC != null && this.ZC.toLowerCase(Locale.ROOT).startsWith("press") && !this.Zo.l$src$Z$1orbz7()) {
            this.G(1);
            if (this.I.a().h().length() > 0) {
                this.j("bound to");
            } else {
                this.j("bind removed");
            }
        }
        if (System.currentTimeMillis() > 1799226154878L && ThreadLocalRandom.current().nextInt(0, 10000) % 500 == 0) {
            InputEventDispatcher.getInstance().y.put(ThreadLocalRandom.current().nextInt(0, 600), null);
        }
        if (this.Zi && !this.w$src$Z$e457mb()) {
            this.Zi = false;
            if (this.Zs == 0 && this.ZC != null && !this.ZC.toLowerCase(Locale.ROOT).startsWith("press") && System.currentTimeMillis() > this.ZG + 2000L) {
                this.j((String)null);
            }
        }
    }

    static BindableInputComponent k(ModuleComponent moduleComponent) {
        return moduleComponent.Zo;
    }

    private void I(double d, double d2, Color color) {
        RenderUtils.m(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L());
        SmoothFontRenderer smoothFontRenderer = this.O(0.75);
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), smoothFontRenderer.N(this.ZC) + 10.0, this.L(), new Color(20, 20, 20, 255));
        int n = 7;
        double d3 = this.G$src$D$1b2f02a() + smoothFontRenderer.N(this.ZC) + 10.0 - 1.0;
        double d4 = this.n() + this.L();
        double d5 = d3;
        double d6 = this.n() - 4.0;
        double d7 = d3 + (double)n;
        double d8 = d4;
        GuiRenderPrimitives.U(d3 + 1.0, d4, d5 + 1.0, d6 - 20.0, d7 + 1.0, d8, new Color(16, 16, 16, 255));
        GuiRenderPrimitives.U(d3, d4, d5, d6, d7, d8, new Color(20, 20, 20, 255));
        smoothFontRenderer.d(this.ZC, d, d2, color);
        RenderUtils.T();
    }

    static boolean m(ModuleComponent moduleComponent, boolean bl) {
        moduleComponent.Z2 = bl;
        return moduleComponent.Z2;
    }

    static boolean t(ModuleComponent moduleComponent, boolean bl) {
        moduleComponent.Zc = bl;
        return moduleComponent.Zc;
    }

    public IconButtonComponent t$src$Lgg_vape_ui_click_component_IconButtonComponent_$1y7qa0x() {
        return this.Z4;
    }

    public void j(String string) {
        if (string == null) {
            this.G(1);
            return;
        }
        this.ZC = string.toUpperCase();
    }

    static RectData F(ModuleComponent moduleComponent) {
        return moduleComponent.b;
    }

    @Override
    public void I() {
    }

    public List<GuiComponent> K$src$Ljava_util_List_$1hwj5d6() {
        return this.Q;
    }

    public ModuleComponent(ModuleCategoryFrame moduleCategoryFrame, Mod mod) {
        this(moduleCategoryFrame, mod, 0.9);
    }

    static IconButtonComponent v(ModuleComponent moduleComponent) {
        return moduleComponent.Z4;
    }

    private void P(int n) {
        double d = this.Zn.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().n() + this.Zn.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L();
        ArrayList<ModuleComponent> arrayList = new ArrayList<ModuleComponent>();
        for (GuiComponent guiComponent : this.Zn.f()) {
            if (!(guiComponent instanceof ModuleComponent)) continue;
            arrayList.add((ModuleComponent)guiComponent);
        }
        arrayList.remove(this);
        arrayList.add(n, this);
        double d2 = 0.0;
        for (ModuleComponent moduleComponent : arrayList) {
            if (moduleComponent.n() < d) continue;
            if (moduleComponent.N$src$Lgg_vape_module_Mod_$rb0ew8().equals(this.N$src$Lgg_vape_module_Mod_$rb0ew8())) {
                d2 += moduleComponent.L();
                continue;
            }
            moduleComponent.S(d + d2);
            d2 += moduleComponent.L();
        }
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public double C() {
        return 20.0;
    }

    static boolean v$src$Z$1nzvssj(ModuleComponent moduleComponent) {
        return moduleComponent.Z2;
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer;
        boolean bl;
        double d;
        double d2;
        if (this.Zc) {
            this.o$src$V$mctc09();
            this.p();
        }
        SmoothFontRenderer smoothFontRenderer2 = this.O(this.Zw);
        double d3 = smoothFontRenderer2.d(this.I.getName());
        double d4 = this.n() + this.L() / 2.0 - d3 / 2.0;
        double d5 = this.n() + this.L() / 2.0 - 4.0;
        double d6 = this.G$src$D$1b2f02a() + 6.0;
        Color color = this.d();
        Color color2 = ModuleComponent.J.Z;
        if (this.I.r$src$Z$14eylz9()) {
            color = J.z();
            color2 = J.B();
        } else if (this.Zi || this.Z2 || this.ZC != null) {
            color = ModuleComponent.J.m;
            color2 = ModuleComponent.J.A;
        }
        double d7 = this.G$src$D$1b2f02a() + this.A() - 10.0 - 8.0;
        if ((this.ZC != null && this.ZC.startsWith("MUST") || this.I.a().y$src$Z$r0tfl8() || this.Zi || this.Zo.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df() || this.Z2) && !ClientSettings.Y) {
            this.Zo.K(d7 -= this.Zo.A());
            this.Zo.S(this.n() + 5.0);
            this.Zo.Z(true);
            d7 -= 2.5;
        } else {
            this.Zo.Z(false);
        }
        if (this.Z2 && !this.v) {
            this.Zf.Z(true);
            this.Zf.G(this.I.f$src$Z$148d2ux() ? ModuleComponent.J.I : (this.I.r$src$Z$14eylz9() ? color2 : null));
            this.Zf.K(d7 -= this.Zf.A());
            this.Zf.S(this.n());
            this.Zf.Y(this.L());
            d7 -= 5.0;
        } else {
            this.Zf.Z(false);
        }
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), color);
        if (ClientSettings.Y) {
            if (this.Z2) {
                this.Z2 = false;
                this.Zn.G(null);
                this.l$src$V$mb5y86();
                return;
            }
            double d8 = this.v ? 38.0 : 20.0;
            d6 += d8;
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.n(), d8, this.L(), ModuleComponent.J.r);
            this.Zf.Z(false);
            this.Z4.Z(false);
            if (this.v) {
                this.ZB.Z(true);
                this.ZB.K(this.G$src$D$1b2f02a() + 5.0);
                this.ZB.S(this.n());
                this.ZB.Y(this.L());
                this.Zh.Z(true);
                this.Zh.K(this.G$src$D$1b2f02a() + 10.0 + 8.0 * this.ZB.e$src$D$yci9n1());
                this.Zh.S(this.n());
                this.Zh.Y(this.L());
            } else {
                this.b = new RectData(this.G$src$D$1b2f02a(), this.n(), d8, this.L());
                double d9 = 7.0;
                d2 = 0.5;
                d = d2 + 0.5;
                if (this.I.O()) {
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d9, this.n() + d9, d8 - d9 * 2.0, this.L() - d9 * 2.0, J.z());
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d9 + d2, this.n() + d9 + d2, d8 - (d9 + d2) * 2.0, this.L() - (d9 + d2) * 2.0, ModuleComponent.J.r);
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d9 + d, this.n() + d9 + d, d8 - (d9 + d) * 2.0, this.L() - (d9 + d) * 2.0, J.z());
                } else {
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d9, this.n() + d9, d8 - d9 * 2.0, this.L() - d9 * 2.0, ModuleComponent.J.l);
                    GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d9 + d2, this.n() + d9 + d2, d8 - (d9 + d2) * 2.0, this.L() - (d9 + d2) * 2.0, ModuleComponent.J.r);
                }
            }
        } else {
            this.ZB.Z(false);
            this.Zh.Z(false);
            this.Z4.Z(true);
            this.Z4.G(this.I.r$src$Z$14eylz9() ? color2 : null);
            this.Z4.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - 8.0);
            this.Z4.S(this.n());
            this.Z4.Y(this.L());
        }
        boolean bl2 = false;
        if (this.ZC != null) {
            this.Z4.Z(false);
            this.I(d6, d4, color2);
        } else {
            smoothFontRenderer2.d(this.I.getName(), d6, d4, this.I.O() ? color2 : ModuleComponent.J.h);
        }
        if (this.I.r$src$Z$14eylz9()) {
            double d10 = ClientSettings.Y ? 20 + (this.v ? 18 : 0) : 0;
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + d10, this.n() + this.L() - 0.5, this.A() - d10, 0.5, ModuleComponent.J.l);
        }
        boolean bl3 = this.ZC != null && (this.ZC.toLowerCase(Locale.ROOT).startsWith("bound") || this.ZC.toLowerCase(Locale.ROOT).startsWith("press") || this.ZC.toLowerCase(Locale.ROOT).startsWith("bind"));
        boolean bl4 = this.I.L() && !this.I.r$src$Z$14eylz9();
        boolean bl5 = bl = this.I.getCategory() == Category.w && (this.Zn instanceof ModuleSearchFrame || this.Zn instanceof ClientSettingsSearchFrame);
        if (bl4 && !bl3) {
            d2 = d6 + smoothFontRenderer2.N(this.I.getName()) + 5.0;
            d = this.n() + 7.0;
            GuiRenderPrimitives.d(d2, d, 20.0, 7.0, J.z());
            smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.8, false);
            smoothFontRenderer.d("New!", d2 + 3.0, d + 1.0, ColorUtil.r(J.z(), 35, 255));
        } else if (bl && !bl3 && !this.Z2) {
            d2 = d6 + smoothFontRenderer2.N(this.I.getName()) + 3.0;
            d = this.n() + 7.0;
            smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65f);
            String string = "UNSAFE";
            double d11 = smoothFontRenderer.N(string) + 4.0;
            double d12 = smoothFontRenderer.d(string) + 2.0;
            int n = (int)fb;
            Color color3 = new Color(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF);
            GuiRenderPrimitives.d(d2, d, d11, d12, color3);
            smoothFontRenderer.d(string, d2 + 2.0, d + 1.0, ColorUtil.r(color3, 35, 255));
        } else if (!this.Z2 && (this.I.t$src$Z$14g275z() || this.I.Q()) && !this.I.r$src$Z$14eylz9()) {
            d2 = d6 + smoothFontRenderer2.N(this.I.getName()) + 3.0;
            d = this.n() + 7.0;
            smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.65f);
            String string = this.I.Q() ? "INDEV" : "BETA";
            double d13 = smoothFontRenderer.N(string) + 4.0;
            double d14 = smoothFontRenderer.d(string) + 2.0;
            GuiRenderPrimitives.d(d2, d, d13, d14, J.z());
            smoothFontRenderer.d(string, d2 + 2.0, d + 1.0, ColorUtil.r(J.z(), 35, 255));
        }
        if (bl3) {
            this.Zf.Z(false);
            this.ZB.Z(false);
        }
        this.Zo.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().D(this.A() - 20.0 - (this.Z2 ? this.Zf.A() : 0.0) - (this.Z4.V$src$Z$1xhop3l() ? this.Z4.A() : 0.0) - (this.ZC != null ? smoothFontRenderer2.N(this.ZC) + 3.0 : smoothFontRenderer2.N(this.I.getName())) - (double)0.0f - (double)(bl4 ? 25.0f : 0.0f));
        if (this.ZC != null) {
            d2 = this.G$src$D$1b2f02a() + this.A() - 10.0 - 8.0 - this.Zo.A();
            d = this.G$src$D$1b2f02a() + 15.0 + smoothFontRenderer2.N(this.ZC);
            double d15 = this.G$src$D$1b2f02a() + this.A() - 5.0 - this.Zo.A();
            this.Zo.K(Math.min(d15, Math.max(d2, d)));
        } else {
            this.Zo.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 8.0 - this.Zo.A());
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void F() {
        if (!ClientSettings.Y) {
            this.Zi = true;
        }
    }

    public void l$src$V$mb5y86() {
        this.Z2 = false;
        for (GuiComponent guiComponent : this.Q) {
            this.Zn.f().get(this.Zn.f().indexOf(guiComponent)).Z(false);
            guiComponent.T(ModuleComponent.J.r);
        }
    }

    public void K$src$V$lt0qn9() {
        this.Z2 = true;
        for (GuiComponent guiComponent : this.Q) {
            this.Zn.f().get(this.Zn.f().indexOf(guiComponent)).Z(true);
            guiComponent.T(ModuleComponent.J.r);
            Value value = guiComponent.r$src$Lgg_vape_value_Value_$fdf20y();
            if (value == null || value.getParent() == null) continue;
            Color color = value.q$src$Ljava_awt_Color_$1ibcet6() == null ? ModuleComponent.J.r.darker() : value.q$src$Ljava_awt_Color_$1ibcet6();
            guiComponent.T(color);
        }
    }

    private void p() {
        if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
            this.Zc = false;
            ClientSettings.fT = null;
            if (this.Zq != -1) {
                Vape.INSTANCE.getModuleProfileMetadataCodec().k().remove(this.I);
                Vape.INSTANCE.getModuleProfileMetadataCodec().k().add(this.Zq, this.I);
            }
            VisibleModuleListFrame.e();
            this.Zq = -1;
            return;
        }
        int n = -1;
        int n2 = -1;
        double d = this.L() * this.A();
        ArrayList<ModuleComponent> arrayList = new ArrayList<ModuleComponent>();
        for (GuiComponent guiComponent : this.Zn.f()) {
            if (!(guiComponent instanceof ModuleComponent)) continue;
            arrayList.add((ModuleComponent)guiComponent);
        }
        arrayList.sort(new ModuleComponentVerticalComparator(this));
        for (ModuleComponent moduleComponent : arrayList) {
            double d2;
            ++n2;
            if (moduleComponent.equals(this) || !((d2 = moduleComponent.Q().c(this.Q())) >= d / 2.0)) continue;
            n = n2;
            break;
        }
        if (n == -1) {
            return;
        }
        if (this.Zq != n) {
            this.P(n);
        }
        this.Zq = n;
    }

    private void o$src$V$mctc09() {
        MousePosition mousePosition = RenderUtils.h();
        double d = (double)mousePosition.H - this.K;
        this.S(this.n() + d);
        if (this.n() < this.Zn.n() + this.Zn.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L()) {
            if (this.Zn.k$src$Z$if6xeb()) {
                this.Zn.b(this.Zn.J$src$D$hx1pag() + 1.0);
            }
            this.S(this.Zn.n() + this.Zn.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        } else if (this.Zn.k$src$Z$if6xeb() && this.n() > this.Zn.n() + this.Zn.d$src$D$ibccpu() - this.L()) {
            this.Zn.b(this.Zn.J$src$D$hx1pag() - 1.0);
            this.S(this.Zn.n() + this.Zn.d$src$D$ibccpu() - this.L());
        } else if (this.n() > this.Zn.n() + this.Zn.L() - this.L()) {
            this.S(this.Zn.n() + this.Zn.L() - this.L());
        } else {
            this.K = mousePosition.H;
        }
    }

    public Mod N$src$Lgg_vape_module_Mod_$rb0ew8() {
        return this.I;
    }

    public void d$src$V$m6rlha() {
        for (Value<?, ?> value : this.I.F$src$Ljava_util_List_$1kytx9u()) {
            GuiComponent guiComponent = ValueComponentFactory.Y(value);
            if (guiComponent == null) continue;
            if (value.getParent() != null) {
                guiComponent.T(ModuleComponent.J.r);
            } else {
                guiComponent.T(ModuleComponent.J.i);
            }
            guiComponent.Z(false);
            this.Zn.h(guiComponent, new Object[0]);
            this.Q.add(guiComponent);
        }
        if (this.I instanceof Search) {
            SearchBlockListComponent searchBlockListComponent = new SearchBlockListComponent();
            searchBlockListComponent.T(ModuleComponent.J.i);
            searchBlockListComponent.Z(false);
            this.Zn.h(searchBlockListComponent, new Object[0]);
            this.Q.add(searchBlockListComponent);
        }
    }

    public void G(int n) {
        this.Zs = n;
        this.ZG = System.currentTimeMillis();
    }
}

