package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModuleDisplayScope;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.Search;
import gg.vape.module.render.hud.HudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.FriendModuleInteractiveComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.value.SearchBlockListComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.ui.click.component.value.ValueComponentMode;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiContentPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiLegitModuleCardComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosController;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleCardComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleViewMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiModuleViewModeSwitchMap;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesFilterInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSearchInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiPageBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.utils.StringUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ListValue;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ClickGuiModulesPage
extends ClickGuiPageBase {
    final ClickGuiMainFrame wN;
    private ClickGuiModuleViewMode wR;
    private final Runnable wH;
    private ClickGuiContentPanel wB;
    private Category wG = Category.L;
    private ClickGuiContentPanel wn;
    private String wj = "";
    private LabeledTextInputComponent ws;
    private static final double w2 = 0.0;
    private LabeledTextInputComponent wc;
    private String wq = "";
    private final ClickGuiMacrosController wC;
    private boolean wF;

    private void lambda$filterModuleButtons$16(ClickGuiModuleCardComponent clickGuiModuleCardComponent) {
        this.W(clickGuiModuleCardComponent);
    }

    private void W(@Nullable ClickGuiModuleCardComponent clickGuiModuleCardComponent) {
        if (this.wB == null) {
            return;
        }
        if (clickGuiModuleCardComponent != null) {
            if (clickGuiModuleCardComponent.i$src$Z$feafs0()) {
                this.wN.K$src$V$sfnnd();
                clickGuiModuleCardComponent.G(false);
                clickGuiModuleCardComponent = null;
            } else {
                this.n(clickGuiModuleCardComponent.j$src$Lgg_vape_module_Mod_$ozzvpn(), clickGuiModuleCardComponent);
            }
        }
        for (GuiComponent guiComponent : this.wB.f()) {
            ClickGuiModuleCardComponent clickGuiModuleCardComponent2;
            if (!(guiComponent instanceof PaddedComponent) || (clickGuiModuleCardComponent2 = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null) continue;
            boolean bl = clickGuiModuleCardComponent2 == clickGuiModuleCardComponent;
            clickGuiModuleCardComponent2.G(bl);
            clickGuiModuleCardComponent2.V(!bl && clickGuiModuleCardComponent != null);
        }
    }

    @Override
    public void K() {
        super.K();
        if (this.wH != null) {
            this.wN.F(this.wH);
        }
    }

    private void Q$src$V$yixnnl() {
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().S();
        switch (ClickGuiModuleViewModeSwitchMap.C[this.wR.ordinal()]) {
            case 1: {
                this.p();
                break;
            }
            case 2: {
                this.u(true);
                break;
            }
            default: {
                this.u(false);
            }
        }
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().H(true);
    }

    private void lambda$openModuleSettings$22() {
        this.wN.K$src$V$sfnnd();
        this.W(null);
    }

    private void n(Mod mod, ClickGuiModuleCardComponent clickGuiModuleCardComponent) {
        ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel = new ClickGuiModulesSidecarPanel(null);
        clickGuiModulesSidecarPanel.B("new" + mod.getCategory().getName().toLowerCase());
        clickGuiModulesSidecarPanel.c(false);
        clickGuiModulesSidecarPanel.N(this::lambda$openModuleSettings$22);
        clickGuiModulesSidecarPanel.k(true);
        clickGuiModulesSidecarPanel.z(mod.f$src$Z$148d2ux());
        clickGuiModulesSidecarPanel.q(() -> ClickGuiModulesPage.lambda$openModuleSettings$23(mod, clickGuiModulesSidecarPanel, clickGuiModuleCardComponent));
        clickGuiModulesSidecarPanel.f(true);
        clickGuiModulesSidecarPanel.T((Runnable)null);
        clickGuiModulesSidecarPanel.E(() -> ClickGuiModulesPage.lambda$openModuleSettings$24(mod));
        this.wN.Z(ClickGuiOverlaySpec.q().e(mod.getName()).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).v(clickGuiModulesSidecarPanel).N(arg_0 -> this.lambda$openModuleSettings$25(mod, arg_0)).x(false).w());
    }

    private static int lambda$filterModuleButtons$14(Mod mod, Mod mod2) {
        return Boolean.compare(mod2.f$src$Z$148d2ux(), mod.f$src$Z$148d2ux());
    }

    private static String lambda$openLegitModuleSettings$19(HudModule hudModule) {
        return hudModule.r$src$Z$14eylz9() ? "ON" : "OFF";
    }

    public boolean i$src$Z$yw4pzp() {
        switch (ClickGuiModuleViewModeSwitchMap.C[this.wR.ordinal()]) {
            case 2: {
                if (this.wc == null) break;
                this.wc.k("");
                this.wc.b$src$V$17wa4kz();
                return true;
            }
            case 3: {
                if (this.ws == null) break;
                this.ws.k("");
                this.ws.b$src$V$17wa4kz();
                return true;
            }
        }
        return false;
    }

    private static String lambda$openModuleSettings$24(Mod mod) {
        return mod.r$src$Z$14eylz9() ? "ON" : "OFF";
    }

    public static String k(ClickGuiModulesPage clickGuiModulesPage, String string) {
        clickGuiModulesPage.wq = string;
        return clickGuiModulesPage.wq;
    }

    private static void lambda$openModuleSettings$23(Mod mod, ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel, ClickGuiModuleCardComponent clickGuiModuleCardComponent) {
        mod.K(!mod.f$src$Z$148d2ux());
        clickGuiModulesSidecarPanel.z(mod.f$src$Z$148d2ux());
        clickGuiModuleCardComponent.u(!mod.f$src$Z$148d2ux());
    }

    private void lambda$filterModuleButtons$15(ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent) {
        this.F(clickGuiLegitModuleCardComponent);
    }

    private void lambda$new$0() {
        this.wC.D(null);
        this.wC.S();
        this.W(null);
        if (this.wB != null) {
            this.S(this.wB, false);
        }
        this.F((ClickGuiLegitModuleCardComponent)null);
        if (this.wn != null) {
            this.S(this.wn, true);
        }
    }

    double Q$src$D$yixn83() {
        return Math.max(0.0, this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A() - 0.0);
    }

    private void S(ClickGuiContentPanel clickGuiContentPanel, boolean bl) {
        boolean bl2;
        Object object2;
        if (clickGuiContentPanel == null) {
            return;
        }
        String string = null;
        if (bl) {
            for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent) || !((ClickGuiLegitModuleCardComponent)(object2 = (ClickGuiLegitModuleCardComponent)guiComponent)).R()) continue;
                string = ((ClickGuiLegitModuleCardComponent)object2).F$src$Lgg_vape_module_render_hud_HudModule_$mt0j3c().getName();
                break;
            }
        } else {
            for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                if (!(guiComponent instanceof PaddedComponent) || (object2 = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null || !((ClickGuiModuleCardComponent)object2).i$src$Z$feafs0()) continue;
                string = ((ClickGuiModuleCardComponent)object2).j$src$Lgg_vape_module_Mod_$ozzvpn().getName();
                break;
            }
        }
        double d = clickGuiContentPanel.J$src$D$hx1pag();
        clickGuiContentPanel.S();
        object2 = bl ? (this.wc != null ? this.wc.i$src$Ljava_lang_String_$1n2xf3k() : "") : (this.ws != null ? this.ws.i$src$Ljava_lang_String_$1n2xf3k() : "");
        String string2 = StringUtils.y(object2 == null ? "" : ((String)object2).trim());
        boolean bl3 = bl2 = !string2.isEmpty();
        if (bl2) {
            ArrayList<Mod> arrayList = new ArrayList<>();
            if (bl) {
                ArrayList<Mod> arrayList2 = Vape.INSTANCE.getModManager().l();
                for (Mod object4 : arrayList2) {
                    HudModule hudModule;
                    if (!(object4 instanceof HudModule) || !StringUtils.y((hudModule = (HudModule)object4).getName()).contains(string2)) continue;
                    arrayList.add(hudModule);
                }
            } else {
                arrayList = new ArrayList<Mod>(Vape.INSTANCE.getModManager().collectMods());
                arrayList.removeIf(ClickGuiModulesPage::lambda$filterModuleButtons$11);
                arrayList.removeIf(ClickGuiModulesPage::lambda$filterModuleButtons$12);
                arrayList.removeIf(arg_0 -> ClickGuiModulesPage.lambda$filterModuleButtons$13(string2, arg_0));
                arrayList.sort(ClickGuiModulesPage::lambda$filterModuleButtons$14);
            }
            int n = 0;
            for (Mod mod : arrayList) {
                if (bl) {
                    ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent = new ClickGuiLegitModuleCardComponent((HudModule)mod);
                    clickGuiLegitModuleCardComponent.o(clickGuiContentPanel.A() / 3.0);
                    clickGuiLegitModuleCardComponent.n(() -> this.lambda$filterModuleButtons$15(clickGuiLegitModuleCardComponent));
                    clickGuiContentPanel.h(clickGuiLegitModuleCardComponent, n % 3 == 2 ? "wrap" : "widthwrap");
                    ++n;
                    continue;
                }
                ClickGuiModuleCardComponent clickGuiModuleCardComponent = new ClickGuiModuleCardComponent(mod);
                clickGuiModuleCardComponent.o(clickGuiContentPanel.A());
                clickGuiModuleCardComponent.b(() -> this.lambda$filterModuleButtons$16(clickGuiModuleCardComponent));
                clickGuiModuleCardComponent.l(bl2);
                clickGuiModuleCardComponent.U(bl2);
                if (this.wF && this.wG == Category.L) {
                    clickGuiModuleCardComponent.K(true);
                    clickGuiModuleCardComponent.G(this::lambda$filterModuleButtons$17);
                }
                clickGuiContentPanel.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiModuleCardComponent), new Object[0]);
            }
            clickGuiContentPanel.H(true);
            boolean bl4 = false;
            if (string != null) {
                if (bl) {
                    ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent;
                    for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                        if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent) || !(clickGuiLegitModuleCardComponent = (ClickGuiLegitModuleCardComponent)guiComponent).F$src$Lgg_vape_module_render_hud_HudModule_$mt0j3c().getName().equals(string)) continue;
                        clickGuiLegitModuleCardComponent.J(true);
                        bl4 = true;
                        break;
                    }
                    if (bl4) {
                        for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                            if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent)) continue;
                            clickGuiLegitModuleCardComponent = (ClickGuiLegitModuleCardComponent)guiComponent;
                            clickGuiLegitModuleCardComponent.N(!clickGuiLegitModuleCardComponent.R());
                        }
                    } else {
                        this.wN.K$src$V$sfnnd();
                    }
                } else {
                    ClickGuiModuleCardComponent clickGuiModuleCardComponent;
                    for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                        if (!(guiComponent instanceof PaddedComponent) || (clickGuiModuleCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null || !clickGuiModuleCardComponent.j$src$Lgg_vape_module_Mod_$ozzvpn().getName().equals(string)) continue;
                        clickGuiModuleCardComponent.G(true);
                        bl4 = true;
                        break;
                    }
                    if (bl4) {
                        for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                            if (!(guiComponent instanceof PaddedComponent) || (clickGuiModuleCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null) continue;
                            clickGuiModuleCardComponent.V(!clickGuiModuleCardComponent.i$src$Z$feafs0());
                        }
                    } else {
                        this.wN.K$src$V$sfnnd();
                    }
                }
            }
            clickGuiContentPanel.b(d);
            return;
        }
        ArrayList<Mod> arrayList = new ArrayList<>();
        if (bl) {
            ArrayList<Mod> arrayList3 = Vape.INSTANCE.getModManager().l();
            for (Mod mod : arrayList3) {
                if (!(mod instanceof HudModule)) continue;
                HudModule hudModule = (HudModule)mod;
                arrayList.add(hudModule);
            }
        } else if (this.wG == Category.L) {
            arrayList = new ArrayList<Mod>(Vape.INSTANCE.getModuleProfileMetadataCodec().k());
        } else {
            arrayList = new ArrayList<Mod>(Vape.INSTANCE.getModManager().collectMods());
            arrayList.removeIf(ClickGuiModulesPage::lambda$filterModuleButtons$11);
            arrayList.removeIf(ClickGuiModulesPage::lambda$filterModuleButtons$12);
        }
        int n = 0;
        for (Mod mod : arrayList) {
            if (!bl && (this.wG != Category.L ? mod.getCategory() != this.wG : !mod.f$src$Z$148d2ux())) continue;
            if (bl) {
                ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent = new ClickGuiLegitModuleCardComponent((HudModule)mod);
                clickGuiLegitModuleCardComponent.o(clickGuiContentPanel.A() / 3.0);
                clickGuiLegitModuleCardComponent.n(() -> this.lambda$filterModuleButtons$15(clickGuiLegitModuleCardComponent));
                clickGuiContentPanel.h(clickGuiLegitModuleCardComponent, n % 3 == 2 ? "wrap" : "widthwrap");
                ++n;
                continue;
            }
            ClickGuiModuleCardComponent clickGuiModuleCardComponent = new ClickGuiModuleCardComponent(mod);
            clickGuiModuleCardComponent.o(clickGuiContentPanel.A());
            clickGuiModuleCardComponent.b(() -> this.lambda$filterModuleButtons$16(clickGuiModuleCardComponent));
            clickGuiModuleCardComponent.l(bl2);
            clickGuiModuleCardComponent.U(bl2);
            if (this.wF && this.wG == Category.L) {
                clickGuiModuleCardComponent.K(true);
                clickGuiModuleCardComponent.G(this::lambda$filterModuleButtons$17);
            }
            clickGuiContentPanel.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiModuleCardComponent), new Object[0]);
        }
        clickGuiContentPanel.H(true);
        boolean bl5 = false;
        if (string != null) {
            if (bl) {
                ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent;
                for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                    if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent) || !(clickGuiLegitModuleCardComponent = (ClickGuiLegitModuleCardComponent)guiComponent).F$src$Lgg_vape_module_render_hud_HudModule_$mt0j3c().getName().equals(string)) continue;
                    clickGuiLegitModuleCardComponent.J(true);
                    bl5 = true;
                    break;
                }
                if (bl5) {
                    for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                        if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent)) continue;
                        clickGuiLegitModuleCardComponent = (ClickGuiLegitModuleCardComponent)guiComponent;
                        clickGuiLegitModuleCardComponent.N(!clickGuiLegitModuleCardComponent.R());
                    }
                } else {
                    this.wN.K$src$V$sfnnd();
                }
            } else {
                ClickGuiModuleCardComponent clickGuiModuleCardComponent;
                for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                    if (!(guiComponent instanceof PaddedComponent) || (clickGuiModuleCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null || !clickGuiModuleCardComponent.j$src$Lgg_vape_module_Mod_$ozzvpn().getName().equals(string)) continue;
                    clickGuiModuleCardComponent.G(true);
                    bl5 = true;
                    break;
                }
                if (bl5) {
                    for (GuiComponent guiComponent : clickGuiContentPanel.f()) {
                        if (!(guiComponent instanceof PaddedComponent) || (clickGuiModuleCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiModuleCardComponent.class)) == null) continue;
                        clickGuiModuleCardComponent.V(!clickGuiModuleCardComponent.i$src$Z$feafs0());
                    }
                } else {
                    this.wN.K$src$V$sfnnd();
                }
            }
        }
        clickGuiContentPanel.b(d);
    }

    private static boolean lambda$filterModuleButtons$12(Mod mod) {
        return mod.J$src$Lgg_vape_module_ModuleDisplayScope_$1w905sh() == ModuleDisplayScope.FRAMES_ONLY;
    }

    private static void lambda$openLegitModuleSettings$20(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
    }

    private void lambda$openLegitModuleSettings$21(HudModule hudModule, PanelComponent panelComponent) {
        this.z(hudModule, panelComponent);
    }

    @Override
    public void Z$src$V$15w0jcm() {
        super.Z$src$V$15w0jcm();
        if (this.wH != null) {
            this.wN.F(this.wH);
            this.wN.k(this.wH);
        }
    }

    public double B$src$D$yaoqbo() {
        GuiComponent guiComponent = this.wB;
        if (guiComponent == null || !guiComponent.V$src$Z$1xhop3l()) {
            guiComponent = this.ws;
        }
        if (guiComponent == null) {
            return Double.MAX_VALUE;
        }
        return Math.max(0.0, guiComponent.G$src$D$1b2f02a() - this.wN.G$src$D$1b2f02a());
    }

    private static Integer lambda$renderCategoryButtons$3(Category category) {
        return Vape.INSTANCE.getModManager().x(category);
    }

    private void lambda$filterModuleButtons$17() {
        this.S(this.wB, false);
    }

    private void lambda$renderModuleContent$10(LabeledTextInputComponent labeledTextInputComponent) {
        this.wF = false;
        labeledTextInputComponent.g(false);
        labeledTextInputComponent.s(ColorAnimation.Y(ClickGuiModulesPage.J.s));
        this.S(this.wB, false);
    }

    public static ClickGuiContentPanel x(ClickGuiModulesPage clickGuiModulesPage) {
        return clickGuiModulesPage.wB;
    }

    private static boolean lambda$filterModuleButtons$13(String string, Mod mod) {
        String string2 = StringUtils.y(mod.getName());
        if (mod.getCategory() == Category.w) {
            return !string2.equals(string);
        }
        return !string2.contains(string);
    }

    private void p() {
        this.wC.i();
    }

    private void lambda$openModuleSettings$25(Mod mod, PanelComponent panelComponent) {
        this.z(mod, panelComponent);
    }

    private Boolean lambda$renderCategoryButtons$5() {
        return this.wR == ClickGuiModuleViewMode.MACROS;
    }

    private Boolean lambda$renderCategoryButtons$1(Category category) {
        return this.wR == ClickGuiModuleViewMode.MODULE_CATEGORY && this.wG == category;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void u(boolean bl) {
        ClickGuiContentPanel clickGuiContentPanel;
        LabeledTextInputComponent labeledTextInputComponent = bl ? (this.wc = new ClickGuiModulesFilterInputComponent(this, "Search legit modules...")) : (this.ws = new ClickGuiModulesSearchInputComponent(this, "Search modules..."));
        labeledTextInputComponent.D(0.75f);
        labeledTextInputComponent.I(4.0f);
        labeledTextInputComponent.s(ColorAnimation.Y(ClickGuiModulesPage.J.s));
        labeledTextInputComponent.W(null);
        labeledTextInputComponent.a(false);
        labeledTextInputComponent.o(bl ? this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A() : this.Q$src$D$yixn83());
        labeledTextInputComponent.H(0.0f);
        labeledTextInputComponent.C(0.0);
        labeledTextInputComponent.V(0.0f);
        labeledTextInputComponent.O(0.0f);
        labeledTextInputComponent.W(true);
        labeledTextInputComponent.Y(16.0);
        if (!bl && this.wG == Category.L) {
            labeledTextInputComponent.G(true);
            labeledTextInputComponent.e().r(() -> this.lambda$renderModuleContent$9(labeledTextInputComponent));
            labeledTextInputComponent.s().r(() -> this.lambda$renderModuleContent$10(labeledTextInputComponent));
            if (this.wF) {
                labeledTextInputComponent.g(true);
                labeledTextInputComponent.s(ColorAnimation.Y(ClickGuiModulesPage.J.I));
                labeledTextInputComponent.k("");
            }
        }
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(labeledTextInputComponent, new Object[0]);
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 6.0), new Object[0]);
        if (bl) {
            this.wn = new ClickGuiContentPanel(this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().A(), this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().L() - this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 1.0);
            this.wn.d(false);
            this.wn.t(this.wn.L());
            this.wn.F(FrameScrollbarPlacement.OUTSIDE);
            this.wn.u(true);
            clickGuiContentPanel = this.wn;
        } else {
            this.wB = new ClickGuiContentPanel(this.Q$src$D$yixn83(), this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().L() - this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 1.0);
            this.wB.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
            this.wB.d(false);
            this.wB.t(this.wB.L());
            this.wB.F(FrameScrollbarPlacement.OUTSIDE);
            clickGuiContentPanel = this.wB;
        }
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(clickGuiContentPanel, new Object[0]);
        clickGuiContentPanel.E(true);
        if (bl) {
            if (this.wj != null && !this.wj.isEmpty()) {
                this.wc.k(this.wj);
            } else {
                this.S(clickGuiContentPanel, true);
            }
        } else if (this.wq != null && !this.wq.isEmpty()) {
            this.ws.k(this.wq);
        } else {
            this.S(clickGuiContentPanel, false);
        }
    }

    private static boolean lambda$filterModuleButtons$11(Mod mod) {
        return mod.getCategory() == Category.b;
    }

    private void lambda$renderCategoryButtons$4(Category category) {
        if (this.wR != ClickGuiModuleViewMode.MODULE_CATEGORY || this.wG != category) {
            this.wR = ClickGuiModuleViewMode.MODULE_CATEGORY;
            this.wG = category;
            this.wq = "";
            this.wF = false;
            this.Q$src$V$yixnnl();
        }
    }

    public static ClickGuiContentPanel d(ClickGuiModulesPage clickGuiModulesPage) {
        return clickGuiModulesPage.wn;
    }

    private static Integer lambda$renderCategoryButtons$2() {
        return Vape.INSTANCE.getModuleProfileMetadataCodec().S();
    }

    private void e$src$V$ytxjit() {
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().S();
        ArrayList<FriendModuleInteractiveComponent> arrayList = new ArrayList<>();
        for (Category object2 : Category.values()) {
            if (object2 == Category.b || object2 == Category.w) continue;
            FriendModuleInteractiveComponent friendModuleInteractiveComponent = new FriendModuleInteractiveComponent(object2.getName(), object2.N(), () -> this.lambda$renderCategoryButtons$1(object2), ClientSettings.fW.fc.L().booleanValue() ? (object2 == Category.L ? ClickGuiModulesPage::lambda$renderCategoryButtons$2 : () -> ClickGuiModulesPage.lambda$renderCategoryButtons$3(object2)) : null, "expandarrow");
            friendModuleInteractiveComponent.X(Color.WHITE);
            friendModuleInteractiveComponent.z(ClickGuiModulesPage.J.h);
            if (object2 == Category.L) {
                friendModuleInteractiveComponent.Y$src$Lgg_vape_ui_click_component_ShapeIconComponent_$1mbomq8().W(new Color(255, 255, 255, 7));
                friendModuleInteractiveComponent.X(Color.WHITE);
                friendModuleInteractiveComponent.z(ClickGuiModulesPage.J.h);
                friendModuleInteractiveComponent.B$src$Lgg_vape_ui_click_animation_ColorAnimation_$15kpmz4().setStartColor(ClickGuiModulesPage.J.t);
                friendModuleInteractiveComponent.B$src$Lgg_vape_ui_click_animation_ColorAnimation_$15kpmz4().setEndColor(ClickGuiModulesPage.J.t);
                friendModuleInteractiveComponent.r(ClickGuiModulesPage.J.I);
                friendModuleInteractiveComponent.M(ClickGuiModulesPage.J.I);
                friendModuleInteractiveComponent.C(ClickGuiModulesPage.J.Y);
            } else {
                arrayList.add(friendModuleInteractiveComponent);
            }
            friendModuleInteractiveComponent.r(() -> this.lambda$renderCategoryButtons$4(object2));
            this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(0.0, object2 == Category.L ? 3.0 : 2.0, 0.0, 0.0, friendModuleInteractiveComponent), new Object[0]);
        }
        FriendModuleInteractiveComponent friendModuleInteractiveComponent = new FriendModuleInteractiveComponent("Macros", "newmacros", this::lambda$renderCategoryButtons$5, null, "expandarrow");
        arrayList.add(friendModuleInteractiveComponent);
        friendModuleInteractiveComponent.r(this::lambda$renderCategoryButtons$6);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(0.0, 2.0, 0.0, 0.0, friendModuleInteractiveComponent), new Object[0]);
        FriendModuleInteractiveComponent friendModuleInteractiveComponent2 = new FriendModuleInteractiveComponent("Legit", "legit_primary", this::lambda$renderCategoryButtons$7, null, "expandarrow");
        arrayList.add(friendModuleInteractiveComponent2);
        friendModuleInteractiveComponent2.r(this::lambda$renderCategoryButtons$8);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(0.0, 2.0, 0.0, 0.0, friendModuleInteractiveComponent2), new Object[0]);
        for (FriendModuleInteractiveComponent friendModuleInteractiveComponent3 : arrayList) {
            friendModuleInteractiveComponent3.Y$src$Lgg_vape_ui_click_component_ShapeIconComponent_$1mbomq8().W(new Color(255, 255, 255, 7));
            friendModuleInteractiveComponent3.X(Color.WHITE);
            friendModuleInteractiveComponent3.z(ClickGuiModulesPage.J.h);
            friendModuleInteractiveComponent3.B$src$Lgg_vape_ui_click_animation_ColorAnimation_$15kpmz4().setStartColor(ClickGuiModulesPage.J.t);
            friendModuleInteractiveComponent3.B$src$Lgg_vape_ui_click_animation_ColorAnimation_$15kpmz4().setEndColor(ClickGuiModulesPage.J.t);
        }
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().H(true);
    }

    public static String m(ClickGuiModulesPage clickGuiModulesPage, String string) {
        clickGuiModulesPage.wj = string;
        return clickGuiModulesPage.wj;
    }

    private void lambda$renderModuleContent$9(LabeledTextInputComponent labeledTextInputComponent) {
        this.wF = true;
        labeledTextInputComponent.g(true);
        labeledTextInputComponent.s(ColorAnimation.Y(ClickGuiModulesPage.J.I));
        labeledTextInputComponent.k("");
        this.S(this.wB, false);
    }

    private void J(HudModule hudModule, ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent) {
        ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel = new ClickGuiModulesSidecarPanel(null);
        clickGuiModulesSidecarPanel.c(false);
        clickGuiModulesSidecarPanel.N(this::lambda$openLegitModuleSettings$18);
        clickGuiModulesSidecarPanel.k(false);
        clickGuiModulesSidecarPanel.f(true);
        clickGuiModulesSidecarPanel.T((Runnable)null);
        clickGuiModulesSidecarPanel.E(() -> ClickGuiModulesPage.lambda$openLegitModuleSettings$19(hudModule));
        this.wN.Z(ClickGuiOverlaySpec.q().e(hudModule.getName()).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).v(clickGuiModulesSidecarPanel).D(ClickGuiModulesPage::lambda$openLegitModuleSettings$20).N(arg_0 -> this.lambda$openLegitModuleSettings$21(hudModule, arg_0)).x(false).w());
    }

    public ClickGuiModulesPage(ClickGuiMainFrame clickGuiMainFrame, double d, double d2, double d3) {
        super(d, d2, d3, 0.0, "Modules");
        this.wR = ClickGuiModuleViewMode.MODULE_CATEGORY;
        this.wN = clickGuiMainFrame;
        this.wC = new ClickGuiMacrosController(this);
        this.wH = this::lambda$new$0;
        this.wN.k(this.wH);
        this.e$src$V$ytxjit();
        this.Q$src$V$yixnnl();
    }

    private void z(Mod mod, PanelComponent panelComponent) {
        panelComponent.S();
        boolean bl = false;
        for (Value<?, ?> value : mod.F$src$Ljava_util_List_$1kytx9u()) {
            GuiComponent guiComponent;
            if (value == null || (guiComponent = ValueComponentFactory.K(value, false, ValueComponentMode.STANDALONE)) == null) continue;
            if (value.getParent() != null) {
                BooleanValue booleanValue = value.getParent() instanceof BooleanValue ? (BooleanValue)value.getParent() : null;
                boolean bl2 = value instanceof ListValue && booleanValue != null && booleanValue.q$src$Ljava_util_List_$fyau59().size() == 1;
                guiComponent.d(!bl2);
                if (!bl2) {
                    guiComponent.T(ClickGuiModulesPage.J.r);
                }
            } else {
                guiComponent.d(false);
            }
            guiComponent.q(panelComponent.A() - 1.0);
            panelComponent.h(guiComponent, "wrap");
            bl = true;
        }
        if (mod instanceof Search) {
            SearchBlockListComponent searchBlockList = new SearchBlockListComponent(ValueComponentMode.STANDALONE);
            searchBlockList.d(false);
            searchBlockList.q(panelComponent.A() - 1.0);
            panelComponent.h(searchBlockList, "wrap");
            bl = true;
        }
        if (mod instanceof HudModule && ((HudModule)mod).W()) {
            BindValueRowComponent bindValueRow = new BindValueRowComponent("Keybind", mod.a());
            bindValueRow.d(false);
            bindValueRow.C(0.0);
            bindValueRow.q(panelComponent.A() - 1.0);
            panelComponent.h(bindValueRow, "wrap");
            bl = true;
        }
        if (!bl) {
            double d;
            SimpleTextLabelComponent noSettingsLabel = new SimpleTextLabelComponent("No settings");
            noSettingsLabel.T$src$V$1orl066(ClickGuiModulesPage.J.h);
            noSettingsLabel.g(0.0f);
            double d2 = panelComponent.A();
            if (d2 <= 0.0 && (d = panelComponent.l$src$D$1x5l26k()) > 0.0) {
                d2 = d;
            }
            if (d2 > 0.0) {
                noSettingsLabel.o(d2);
            }
            panelComponent.h(new PaddedComponent(0.0, 4.0, 0.0, 0.0, noSettingsLabel), new Object[0]);
        }
        panelComponent.H(true);
    }

    public static void h(ClickGuiModulesPage clickGuiModulesPage, ClickGuiContentPanel clickGuiContentPanel, boolean bl) {
        clickGuiModulesPage.S(clickGuiContentPanel, bl);
    }

    private void lambda$openLegitModuleSettings$18() {
        this.wN.K$src$V$sfnnd();
        this.F((ClickGuiLegitModuleCardComponent)null);
    }

    private void F(@Nullable ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent) {
        if (this.wn == null) {
            return;
        }
        if (clickGuiLegitModuleCardComponent != null) {
            if (clickGuiLegitModuleCardComponent.R()) {
                this.wN.K$src$V$sfnnd();
                clickGuiLegitModuleCardComponent.J(false);
                clickGuiLegitModuleCardComponent = null;
            } else {
                this.J(clickGuiLegitModuleCardComponent.F$src$Lgg_vape_module_render_hud_HudModule_$mt0j3c(), clickGuiLegitModuleCardComponent);
            }
        }
        for (GuiComponent guiComponent : this.wn.f()) {
            if (!(guiComponent instanceof ClickGuiLegitModuleCardComponent)) continue;
            ClickGuiLegitModuleCardComponent clickGuiLegitModuleCardComponent2 = (ClickGuiLegitModuleCardComponent)guiComponent;
            boolean bl = clickGuiLegitModuleCardComponent2 == clickGuiLegitModuleCardComponent;
            clickGuiLegitModuleCardComponent2.J(bl);
            clickGuiLegitModuleCardComponent2.N(!bl && clickGuiLegitModuleCardComponent != null);
        }
    }

    private Boolean lambda$renderCategoryButtons$7() {
        return this.wR == ClickGuiModuleViewMode.LEGIT;
    }

    private void lambda$renderCategoryButtons$8() {
        if (this.wR != ClickGuiModuleViewMode.LEGIT) {
            this.wR = ClickGuiModuleViewMode.LEGIT;
            this.Q$src$V$yixnnl();
        }
    }

    private void lambda$renderCategoryButtons$6() {
        if (this.wR != ClickGuiModuleViewMode.MACROS) {
            this.wR = ClickGuiModuleViewMode.MACROS;
            this.Q$src$V$yixnnl();
        }
    }
}
