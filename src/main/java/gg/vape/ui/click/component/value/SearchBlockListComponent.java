package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.manager.SearchManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.value.AbstractListValueComponent;
import gg.vape.ui.click.component.value.SearchBlockEditorComponent;
import gg.vape.ui.click.component.value.SearchBlockListAddInputComponent;
import gg.vape.ui.click.component.value.SearchBlockListDropdownLayer;
import gg.vape.ui.click.component.value.SearchBlockListOpenClickListener;
import gg.vape.ui.click.component.value.SearchBlockRemoveClickListener;
import gg.vape.ui.click.component.value.ValueComponentMode;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.ui.unmap.SearchBlock;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class SearchBlockListComponent
extends AbstractListValueComponent {
    private ValueComponentMode I;
    private String K;
    SearchManager Lf = Vape.INSTANCE.getSearch();
    private SearchBlockListDropdownLayer Lt;

    @Override
    public double C() {
        if (this.I == ValueComponentMode.STANDALONE) {
            return 23.0;
        }
        return super.C();
    }

    private void Z$src$V$7bx6v2() {
        Frame frame;
        if (this.I == ValueComponentMode.STANDALONE && this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null && (frame = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa()) instanceof ClickGuiMainFrame) {
            ClickGuiMainFrame clickGuiMainFrame = (ClickGuiMainFrame)frame;
            ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel = new ClickGuiModulesSidecarPanel(null);
            clickGuiModulesSidecarPanel.k(false);
            clickGuiModulesSidecarPanel.f(false);
            ClickGuiOverlaySpec clickGuiOverlaySpec = ClickGuiOverlaySpec.q().e(this.K).C("newallowed").v(clickGuiModulesSidecarPanel).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).r(ClickGuiOverlayTransitionMode.PUSH).D(SearchBlockListComponent::lambda$onButtonClick$0).N(this::lambda$onButtonClick$1).w();
            clickGuiMainFrame.Z(clickGuiOverlaySpec);
            return;
        }
        this.a(!this.P$src$Z$og01j6());
        if (this.P$src$Z$og01j6()) {
            this.Lt.e();
        }
    }

    private String Q(SmoothFontRenderer smoothFontRenderer, String string, double d) {
        if (string.isEmpty() || smoothFontRenderer.N(string) <= d) {
            return string;
        }
        int n = 0;
        int n2 = string.length();
        int n3 = 0;
        while (n <= n2) {
            int n4 = (n + n2) / 2;
            String string2 = string.substring(0, n4);
            if (smoothFontRenderer.N(string2) <= d) {
                n3 = n4;
                n = n4 + 1;
                continue;
            }
            n2 = n4 - 1;
        }
        return string.substring(0, n3);
    }

    private static void lambda$onButtonClick$0(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
    }

    public String t$src$Ljava_lang_String_$1kbjhi6() {
        return this.K;
    }

    private void T(PanelComponent panelComponent) {
        panelComponent.S();
        Runnable runnable = () -> this.lambda$renderStandaloneContent$2(panelComponent);
        SearchBlockListAddInputComponent searchBlockListAddInputComponent = new SearchBlockListAddInputComponent("Block name / ID", runnable);
        searchBlockListAddInputComponent.q(panelComponent.A() - 1.0);
        panelComponent.h(searchBlockListAddInputComponent, new Object[0]);
        for (SearchBlock searchBlock : this.Lf.O()) {
            SearchBlockEditorComponent searchBlockEditorComponent = new SearchBlockEditorComponent(searchBlock);
            searchBlockEditorComponent.q(panelComponent.A() - 1.0);
            searchBlockEditorComponent.g(new SearchBlockRemoveClickListener(this, searchBlock, runnable));
            panelComponent.h(searchBlockEditorComponent, new Object[0]);
        }
    }

    private void Y$src$V$7bde9p() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.85);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.7);
        SmoothFontRenderer smoothFontRenderer3 = this.O(0.68);
        double d = this.G$src$D$1b2f02a() + 5.0;
        double d2 = this.n() + 0.5;
        double d3 = this.A() - 10.0;
        double d4 = this.L() - 1.0;
        Color color = this.d$src$Z$oqzxee() ? SearchBlockListComponent.J.a : SearchBlockListComponent.J.S;
        Color color2 = this.d$src$Z$oqzxee() ? SearchBlockListComponent.J.A : SearchBlockListComponent.J.Z;
        Color color3 = SearchBlockListComponent.J.h;
        double d5 = d2 + 3.0;
        double d6 = d5 + smoothFontRenderer.d(this.K) + 1.0;
        double d7 = smoothFontRenderer3.N("" + this.T$src$I$78mezp());
        double d8 = Math.max(11.0, d7 + 6.0);
        double d9 = 10.0;
        double d10 = d + d3 - 4.0 - d8;
        double d11 = d2 + 3.0;
        float f = 6.0f;
        float f2 = (float)(d + 6.0);
        float f3 = (float)(d2 + (d4 - (double)f) / 2.0);
        double d12 = d + 17.0;
        double d13 = Math.max(0.0, d10 - d12 - 4.0);
        GuiRenderPrimitives.B(d, d2, d3, d4, color, 3.0f);
        GuiRenderPrimitives.B(d10, d11, d8, d9, this.d$src$Z$oqzxee() ? SearchBlockListComponent.J.F : SearchBlockListComponent.J.a, 2.4f);
        smoothFontRenderer.d(this.K, d12, d5, color2);
        smoothFontRenderer2.d(this.g(smoothFontRenderer2, d13), d12, d6, color3);
        smoothFontRenderer3.d("" + this.T$src$I$78mezp(), d10 + (d8 - d7) / 2.0, d11 + 1.5, color2);
        ImageRenderer.E(color2, f2 + 0.5f, f3, "newallowedlist", f, f, false);
        ImageRenderer.E(SearchBlockListComponent.J.B, f2 + 0.5f, f3, "newallowed", f, f, false);
    }

    @Override
    public void H() {
        if (this.I == ValueComponentMode.STANDALONE) {
            this.Y$src$V$7bde9p();
        } else {
            this.e$src$V$7hyxe1();
        }
    }

    public SearchBlockListComponent() {
        this(ValueComponentMode.MAIN);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void e(SearchBlockListComponent searchBlockListComponent) {
        searchBlockListComponent.Z$src$V$7bx6v2();
    }

    private void e$src$V$7hyxe1() {
        this.onDisable();
        this.Lt.h();
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.75);
        Color color = SearchBlockListComponent.J.i;
        Color color2 = this.d$src$Z$oqzxee() ? SearchBlockListComponent.J.A : (this.P$src$Z$og01j6() ? SearchBlockListComponent.J.A : SearchBlockListComponent.J.Z);
        Color color3 = SearchBlockListComponent.J.h;
        float f = (float)(this.n() + this.L() / 2.0) - 3.0f;
        double d = smoothFontRenderer.d(this.K);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0 - 2.5;
        double d3 = d2 + 7.5;
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 2.5, this.A() - 10.0, this.L() - 5.0, this.P$src$Z$og01j6() ? J.z() : this.K$src$Lgg_vape_ui_click_animation_ColorAnimation_$la4la().getInterpolatedColor());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0 + 0.5, this.n() + 2.5 + 0.5, this.A() - 10.0 - 1.0, this.L() - 5.0 - 1.0, color);
        smoothFontRenderer.d(this.K, this.G$src$D$1b2f02a() + 15.0 + 8.0, d2, color2);
        smoothFontRenderer.d("" + this.T$src$I$78mezp(), this.G$src$D$1b2f02a() + this.A() - 10.0 - smoothFontRenderer.N("10"), d2, color2);
        smoothFontRenderer2.d(this.g(smoothFontRenderer2, this.A() - 35.0), this.G$src$D$1b2f02a() + 15.0 + 8.0, d3, color3);
        ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 10.0f + 0.5f, f, "newallowedlist", 6.0f, 6.0f, false);
        ImageRenderer.E(SearchBlockListComponent.J.B, (float)this.G$src$D$1b2f02a() + 10.0f + 0.5f, f, "newallowed", 6.0f, 6.0f, false);
    }

    private void lambda$renderStandaloneContent$2(PanelComponent panelComponent) {
        this.T(panelComponent);
    }

    private int T$src$I$78mezp() {
        int n = 0;
        for (SearchBlock searchBlock : this.Lf.O()) {
            if (!searchBlock.T()) continue;
            ++n;
        }
        return n;
    }

    public void a(ValueComponentMode valueComponentMode) {
        this.I = valueComponentMode;
    }

    private String g(SmoothFontRenderer smoothFontRenderer, double d) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SearchBlock searchBlock : this.Lf.O()) {
            if (!searchBlock.T()) continue;
            String string = searchBlock.d();
            String string2 = stringBuilder.length() == 0 ? string : ", " + string;
            StringBuilder stringBuilder2 = new StringBuilder();
            if (smoothFontRenderer.N(stringBuilder2.append((Object)stringBuilder).append(string2).toString()) <= d) {
                stringBuilder.append(string2);
                continue;
            }
            stringBuilder.append(string2);
            double d2 = Math.max(0.0, d - smoothFontRenderer.N("..."));
            String string3 = this.Q(smoothFontRenderer, stringBuilder.toString(), d2);
            return string3 + "...";
        }
        if (stringBuilder.length() == 0) {
            return "None";
        }
        return stringBuilder.toString();
    }

    public SearchBlockListComponent(ValueComponentMode valueComponentMode) {
        this.I = ValueComponentMode.MAIN;
        this.I = valueComponentMode;
        this.K = "Search blocks";
        this.r(new SearchBlockListOpenClickListener(this));
        if (valueComponentMode != ValueComponentMode.STANDALONE) {
            this.Lt = new SearchBlockListDropdownLayer(this);
            ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v().q(this.Lt);
        }
    }

    public ValueComponentMode o$src$Lgg_vape_ui_click_component_value_ValueComponent$rfgo77() {
        return this.I;
    }

    private void lambda$onButtonClick$1(PanelComponent panelComponent) {
        this.T(panelComponent);
    }
}

