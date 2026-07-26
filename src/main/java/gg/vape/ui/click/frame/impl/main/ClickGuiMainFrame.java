package gg.vape.ui.click.frame.impl.main;

import func.skidline.RectData;
import gg.vape.module.Category;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.OutlinedFrameBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsPage;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrameHeader;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrameTransitionComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesPage;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayController;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayLayer;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayNavigationPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiPageBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiPanelBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiProfilesPage;
import gg.vape.ui.click.frame.impl.main.ClickGuiSection;
import gg.vape.ui.click.frame.impl.main.ClickGuiSectionTabComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.click.layout.ComponentLayout;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class ClickGuiMainFrame
extends OutlinedFrameBase {
    private static final double _j = 240.0;
    private static final double _Z = 120.0;
    private final List<Runnable> _a;
    private static final double _7 = 10.0;
    private static final double _L = 136.0;
    private static final double _5 = 0.0;
    private final List<ClickGuiSectionTabComponent> __ = new ArrayList<ClickGuiSectionTabComponent>();
    public static final float _J = 1.0f;
    private static final double _r = 40.0;
    private final ClickGuiMainFrameTransitionComponent _3;
    private double _w = -1.0;
    private final PanelComponent _b;
    private final EnumMap<ClickGuiSection, ClickGuiPanelBase> _B;
    private ClickGuiPanelBase _0;
    public static final float _H = 2.0f;
    private final ClickGuiOverlayController _X;
    private static int[] _N;
    public static final double _E = 20.0;
    private final ClickGuiMainFrameHeader _Y;
    private ClickGuiSection _U;
    private static final double _O = 0.001;
    private static final double _x = 104.0;
    private final ClickGuiOverlayController _c;
    private static final double _Q = 104.0;
    private final List<Runnable> _h;
    private static final double _e = 400.0;

    public ClickGuiOverlaySpec R$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$190smu6() {
        if (this._X.x()) {
            return this._X.u();
        }
        if (this._c.x()) {
            return this._c.u();
        }
        return null;
    }

    @Override
    public void J() {
        boolean bl = this._X.F();
        boolean bl2 = this._c.F();
        if (!bl && !bl2) {
            super.J();
            return;
        }
        this.F();
        this.n(true);
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.V$src$Z$1xhop3l() || !guiComponent.t()) continue;
            if (bl) {
                if (guiComponent != this._X.o() && guiComponent != this._X.f()) continue;
                guiComponent.J();
                continue;
            }
            if (bl2) {
                if (guiComponent != this._Y && guiComponent != this._c.o() && guiComponent != this._c.f() && guiComponent != this._X.o() && guiComponent != this._X.f()) continue;
                guiComponent.J();
                continue;
            }
            guiComponent.J();
        }
    }

    public void W(Runnable runnable) {
        if (runnable != null) {
            this._h.remove(runnable);
        }
    }

    public ClickGuiOverlayNavigationPanel j$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$195c90l() {
        return this.K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$14yv086().o();
    }

    public void k(Runnable runnable) {
        if (runnable != null) {
            this._a.add(runnable);
        }
    }

    public boolean u$src$Z$1fj0nz() {
        if (this._3.y$src$Z$1yxqxvj()) {
            return false;
        }
        if (this._U == ClickGuiSection.MODULES && this._0 instanceof ClickGuiModulesPage) {
            return ((ClickGuiModulesPage)this._0).i$src$Z$yw4pzp();
        }
        return false;
    }

    private void a(ClickGuiSection clickGuiSection) {
        ClickGuiPanelBase clickGuiPanelBase = this._B.computeIfAbsent(clickGuiSection, this::q);
        this.K$src$V$sfnnd();
        if (clickGuiPanelBase == null) {
            return;
        }
        if (this._0 == clickGuiPanelBase) {
            clickGuiPanelBase.Z$src$V$15w0jcm();
            clickGuiPanelBase.H(true);
            return;
        }
        if (this._0 != null) {
            this._0.K();
            this._b.I(this._0);
        }
        this._0 = clickGuiPanelBase;
        this._b.h(clickGuiPanelBase, new Object[0]);
        clickGuiPanelBase.Z$src$V$15w0jcm();
    }

    private void lambda$initializeTabs$0(ClickGuiSection clickGuiSection) {
        this.o(clickGuiSection);
    }

    public ClickGuiMainFrameTransitionComponent K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$2ph4q() {
        return this._3;
    }

    private double t(ClickGuiOverlayPlacement clickGuiOverlayPlacement) {
        if (clickGuiOverlayPlacement == ClickGuiOverlayPlacement.OVERLAY) {
            return 136.0;
        }
        return 120.0;
    }

    private void o(ClickGuiSection clickGuiSection) {
        this._U = clickGuiSection;
        this.Z$src$V$10okjs();
        this.a(clickGuiSection);
    }

    @Override
    public double A() {
        return 400.0;
    }

    public boolean m$src$Z$1b4nx3() {
        return this._X.z() || this._c.z();
    }

    @Override
    public void H() {
        this.d$src$V$166ihe();
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), 400.0, 240.0, this.d(), false, 2.0f, 1.0f);
        super.H();
    }

    public double f$src$D$17a38m() {
        return 40.0;
    }

    @Override
    public void u() {
        this.h(new RectData(this.G$src$D$1b2f02a(), this.n(), 400.0, 240.0));
        this._c.l();
        this._X.l();
        super.u();
        this._c.K();
        this._X.K();
    }

    @Override
    public String getName() {
        return "Standalone GUI";
    }

    public boolean h() {
        return false;
    }

    static void V(ClickGuiMainFrame clickGuiMainFrame) {
        clickGuiMainFrame.d$src$V$166ihe();
    }

    public void K$src$V$sfnnd() {
        if (this._X.L()) {
            this._X.q();
        } else {
            this._c.q();
        }
        this.d$src$V$166ihe();
    }

    private void Z$src$V$10okjs() {
        for (ClickGuiSectionTabComponent clickGuiSectionTabComponent : this.__) {
            clickGuiSectionTabComponent.E(clickGuiSectionTabComponent.B$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiSectio$14jx3m7() == this._U);
        }
    }

    private void e() {
        ClickGuiMainFrameHeader clickGuiMainFrameHeader = this._Y;
        for (ClickGuiSection clickGuiSection : ClickGuiSection.values()) {
            ClickGuiSectionTabComponent clickGuiSectionTabComponent = new ClickGuiSectionTabComponent(clickGuiSection);
            clickGuiSectionTabComponent.Y(20.0);
            clickGuiSectionTabComponent.r(() -> this.lambda$initializeTabs$0(clickGuiSection));
            this.__.add(clickGuiSectionTabComponent);
            clickGuiMainFrameHeader.Q(clickGuiSectionTabComponent);
        }
    }

    public ClickGuiMainFrame() {
        this._a = new ArrayList<Runnable>();
        this._h = new ArrayList<Runnable>();
        this._B = new EnumMap(ClickGuiSection.class);
        this._U = ClickGuiSection.MODULES;
        this.o(400.0);
        this.Y(240.0);
        this.T(ClickGuiMainFrame.J.i);
        this.d(false);
        this.g(true);
        this.L(false, true);
        this.Y(false);
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
        this._Y = new ClickGuiMainFrameHeader(this);
        this.h(this._Y, "wrap");
        this._b = new PanelComponent(400.0, 200.0);
        this._b.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this._b.d(false);
        this._b.t(false);
        this.h(this._b, new Object[0]);
        this._X = new ClickGuiOverlayController(this, ClickGuiOverlayLayer.FRAME_OVERLAY, 136.0, null);
        this._c = new ClickGuiOverlayController(this, ClickGuiOverlayLayer.CONTENT_OVERLAY, 120.0, null);
        this._3 = new ClickGuiMainFrameTransitionComponent(this);
        this.H(this._c.f(), this._c.o(), this._X.f(), this._X.o(), this._3);
        this.e();
        this.o(this._U);
    }

    static List b(ClickGuiMainFrame clickGuiMainFrame) {
        return clickGuiMainFrame._a;
    }

    public Category s$src$Lgg_vape_module_Category_$154whg1() {
        return Category.b;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private ClickGuiOverlayController K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$14yv086() {
        if (this._X.x()) {
            return this._X;
        }
        return this._c;
    }

    static double G(ClickGuiMainFrame clickGuiMainFrame, ClickGuiOverlayPlacement clickGuiOverlayPlacement) {
        return clickGuiMainFrame.t(clickGuiOverlayPlacement);
    }

    public void F(Runnable runnable) {
        if (runnable != null) {
            this._a.remove(runnable);
        }
    }

    @Override
    public boolean d$src$Z$1lx9d06() {
        return false;
    }

    public static int[] s$src$AI$17facz5() {
        return _N;
    }

    static {
        ClickGuiMainFrame.B(new int[4]);
    }

    public ClickGuiOverlayPlacement n$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$lsweym() {
        ClickGuiOverlaySpec clickGuiOverlaySpec = this.R$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$190smu6();
        if (clickGuiOverlaySpec != null) {
            return clickGuiOverlaySpec.O();
        }
        return ClickGuiOverlayPlacement.OVERLAY;
    }

    private void d$src$V$166ihe() {
        double d = this._c.n();
        boolean bl = this._c.d(d);
        if (bl) {
            double d2;
            double d3;
            double d4 = Math.min(1.0, d * 1.35);
            double d5 = 0.0;
            d5 = (this._c.y() - 6.0) * d4;
            if (this._0 instanceof ClickGuiModulesPage) {
                d3 = Math.max(0.0, this.G$src$D$1b2f02a() - this._b.G$src$D$1b2f02a());
                d2 = Math.max(0.0, ((ClickGuiModulesPage)this._0).B$src$D$yaoqbo() - 0.0 + d3);
                d5 = Math.min(d5, d2);
            }
            d3 = this.G$src$D$1b2f02a() - d5;
            this._b.K(d3);
            this._b.o(400.0);
            this._b.h(null);
            this._b.H(true);
            d2 = 10.0 * d4;
            if (this._0 != null && d2 != this._w) {
                this._w = d2;
                this._0.K(400.0, 240.0, d2);
            }
            return;
        }
        double d6 = d;
        double d7 = 0.0;
        double d8 = this.G$src$D$1b2f02a() - d7;
        this._b.K(d8);
        this._b.o(400.0);
        this._b.h(null);
        this._b.H(true);
        double d9 = 0.0;
        if (this._0 != null && d9 != this._w) {
            this._w = d9;
            this._0.K(400.0, 240.0, d9);
        }
    }

    static List W(ClickGuiMainFrame clickGuiMainFrame) {
        return clickGuiMainFrame._h;
    }

    public static void B(int[] nArray) {
        _N = nArray;
    }

    @Override
    public double L() {
        return 240.0;
    }

    public ClickGuiMainFrameHeader l$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$5y2poa() {
        return this._Y;
    }

    public void B(Runnable runnable) {
        if (runnable != null) {
            this._h.add(runnable);
        }
    }

    private ClickGuiOverlayController N(ClickGuiOverlayPlacement clickGuiOverlayPlacement) {
        if (clickGuiOverlayPlacement == ClickGuiOverlayPlacement.OVERLAY) {
            return this._X;
        }
        return this._c;
    }

    public void Z(ClickGuiOverlaySpec clickGuiOverlaySpec) {
        Objects.requireNonNull(clickGuiOverlaySpec, "config");
        ClickGuiOverlayController clickGuiOverlayController = this.N(clickGuiOverlaySpec.O());
        clickGuiOverlayController.L(clickGuiOverlaySpec);
        this.d$src$V$166ihe();
    }

    public ClickGuiSidecarPanelBase w$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiSideca$hy8cve() {
        return this.K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$14yv086().o().P$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiSideca$1kad6p7();
    }

    private ClickGuiPanelBase q(ClickGuiSection clickGuiSection) {
        ClickGuiPageBase clickGuiPageBase;
        switch (clickGuiSection) {
            case MODULES: {
                clickGuiPageBase = new ClickGuiModulesPage(this, this._b.A(), this._b.L(), 104.0);
                break;
            }
            case FRIENDS: {
                clickGuiPageBase = new ClickGuiFriendsPage(this, this._b.A(), this._b.L(), 104.0);
                break;
            }
            case PROFILES: {
                clickGuiPageBase = new ClickGuiProfilesPage(this, this._b.A(), this._b.L(), 186.0);
                break;
            }
            default: {
                return null;
            }
        }
        return clickGuiPageBase;
    }
}

