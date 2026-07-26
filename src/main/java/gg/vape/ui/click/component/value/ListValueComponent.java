package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.value.AbstractListValueComponent;
import gg.vape.ui.click.component.value.ListValueDropdownLayer;
import gg.vape.ui.click.component.value.ListValueOptionsPanel;
import gg.vape.ui.click.component.value.ValueComponentMode;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.BooleanValue;
import gg.vape.value.ListValue;
import gg.vape.value.ToggleableListEntry;
import java.awt.Color;
import java.util.List;

public class ListValueComponent
extends AbstractListValueComponent {
    private boolean Je;
    private FrameStackManager Ji;
    private ListValue K;
    private ListValueDropdownLayer J2;
    protected AnchoredPopupFrame Jo;
    private ValueComponentMode JR = ValueComponentMode.MAIN;
    private static final double JN = 0.08;
    private final ColorAnimation I;
    private boolean J0;
    private String JS;
    private final ColorAnimation Jf;

    private void C$src$V$131te2f() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.75);
        Color color = ListValueComponent.J.i;
        Color color2 = this.d$src$Z$oqzxee() ? ListValueComponent.J.A : (this.P$src$Z$og01j6() ? ListValueComponent.J.A : ListValueComponent.J.Z);
        Color color3 = ListValueComponent.J.h;
        float f = (float)(this.n() + this.L() / 2.0) - 3.0f;
        double d = smoothFontRenderer.d(this.K.getName());
        double d2 = this.n() + this.L() / 2.0 - d / 2.0 - 2.5;
        double d3 = d2 + 7.5;
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 2.5, this.A() - 10.0, this.L() - 5.0, this.P$src$Z$og01j6() ? J.z() : this.K$src$Lgg_vape_ui_click_animation_ColorAnimation_$la4la().getInterpolatedColor());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0 + 0.5, this.n() + 2.5 + 0.5, this.A() - 10.0 - 1.0, this.L() - 5.0 - 1.0, color);
        smoothFontRenderer.d(this.JS, this.G$src$D$1b2f02a() + 15.0 + 8.0, d2, color2);
        smoothFontRenderer.d("" + this.a$src$I$13ib7k2(), this.G$src$D$1b2f02a() + this.A() - 10.0 - smoothFontRenderer.N("10"), d2, color2);
        smoothFontRenderer2.d(this.z(smoothFontRenderer2, this.A() - 35.0), this.G$src$D$1b2f02a() + 15.0 + 8.0, d3, color3);
        if (this.Je) {
            ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 10.0f + 0.5f, f, "newblockedlist", 6.0f, 6.0f, false);
            ImageRenderer.E(ListValueComponent.J.d, (float)this.G$src$D$1b2f02a() + 10.0f - 0.5f, f, "newblocked", 6.0f, 6.0f, false);
        } else {
            ImageRenderer.E(color2, (float)this.G$src$D$1b2f02a() + 10.0f + 0.5f, f, "newallowedlist", 6.0f, 6.0f, false);
            ImageRenderer.E(ListValueComponent.J.B, (float)this.G$src$D$1b2f02a() + 10.0f + 0.5f, f, "newallowed", 6.0f, 6.0f, false);
        }
    }

    private void l$src$V$13ocye8() {
        Frame frame;
        if (this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null && (frame = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa()) instanceof ClickGuiMainFrame) {
            ClickGuiMainFrame clickGuiMainFrame = (ClickGuiMainFrame)frame;
            ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel = new ClickGuiModulesSidecarPanel(null);
            clickGuiModulesSidecarPanel.k(false);
            clickGuiModulesSidecarPanel.f(false);
            ClickGuiOverlaySpec clickGuiOverlaySpec = ClickGuiOverlaySpec.q().e(this.JS).C(this.Je ? "newblocked" : "newallowed").v(clickGuiModulesSidecarPanel).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).r(ClickGuiOverlayTransitionMode.PUSH).D(ListValueComponent::lambda$onClick$1).N(this::lambda$onClick$2).w();
            clickGuiMainFrame.Z(clickGuiOverlaySpec);
            return;
        }
        if (this.J0) {
            if (this.J2 == null) {
                this.J2 = new ListValueDropdownLayer(this);
            }
            this.J2.e();
            this.Jo = ClientSettings.g(this, this.J2.m$src$Lgg_vape_ui_click_component_value_ListValueOptio$g5twj8(), AnchoredPopupFrame.class);
            this.Jo.t(true);
        } else {
            this.a(!this.P$src$Z$og01j6());
            if (this.P$src$Z$og01j6()) {
                if (this.J2 == null) {
                    this.J2 = new ListValueDropdownLayer(this);
                }
                this.Ji = ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
                this.Ji.q(this.J2);
                this.J2.e();
            }
        }
    }

    private void lambda$onClick$2(PanelComponent panelComponent) {
        panelComponent.S();
        ListValueOptionsPanel listValueOptionsPanel = new ListValueOptionsPanel(this.K, this.Je, panelComponent.A(), panelComponent.L() - 0.1, true);
        listValueOptionsPanel.k$src$V$admw0a();
        panelComponent.h(listValueOptionsPanel, new Object[0]);
    }

    public ListValueComponent(ListValue listValue) {
        this.Jf = new ColorAnimation(0.08, ListValueComponent.J.S, ListValueComponent.J.a);
        this.I = new ColorAnimation(0.08, ListValueComponent.J.V, ListValueComponent.J.f);
        this.K = listValue;
        this.C(listValue);
        this.r(this::lambda$new$0);
        String string = listValue.getName().toLowerCase();
        boolean bl = this.Je = string.contains("blacklist") || string.contains("blocked");
        this.JS = string.contains("whitelist") ? "Whitelist" : (string.contains("blacklist") ? "Blacklist" : (string.contains("allowed") ? "Allowed Items" : (string.contains("blocked") ? "Blocked Items" : string.substring(0, 1).toUpperCase() + string.substring(1).replaceAll("-", " "))));
    }

    public ValueComponentMode H$src$Lgg_vape_ui_click_component_value_ValueComponent$1x99xt8() {
        return this.JR;
    }

    @Override
    public void H() {
        if (this.JR == ValueComponentMode.STANDALONE) {
            this.z();
        } else {
            this.C$src$V$131te2f();
        }
    }

    public ListValue i$src$Lgg_vape_value_ListValue_$1aag8wx() {
        return this.K;
    }

    private int a$src$I$13ib7k2() {
        return ((List)this.K.K()).size();
    }

    private String z(SmoothFontRenderer smoothFontRenderer, double d) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object e : (List)this.K.K()) {
            if (e instanceof ToggleableListEntry && !((ToggleableListEntry)e).q()) continue;
            if (stringBuilder.toString().length() < 1) {
                stringBuilder.append(e.toString());
                continue;
            }
            String string = ", " + e.toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            if (smoothFontRenderer.N(stringBuilder2.append(stringBuilder.toString()).append(string).toString()) < d) {
                stringBuilder.append(string);
                continue;
            }
            stringBuilder.append("...");
            break;
        }
        if (stringBuilder.length() < 1) {
            stringBuilder.append("None");
        }
        return stringBuilder.toString();
    }

    private void lambda$new$0() {
        this.l$src$V$13ocye8();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void a(boolean bl) {
        super.a(bl);
        if (!bl && this.J2 != null) {
            this.Ji.m(this.J2);
        }
    }

    private static void lambda$onClick$1(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
    }

    private void z() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.85);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.7);
        SmoothFontRenderer smoothFontRenderer3 = this.O(0.68);
        boolean bl = this.F$src$Z$133grxy();
        if (bl) {
            this.Jf.u(this.d$src$Z$oqzxee());
            this.I.u(this.d$src$Z$oqzxee());
            double d = this.G$src$D$1b2f02a() + 5.0;
            double d2 = this.n() + 0.5;
            double d3 = this.A() - 10.0;
            double d4 = this.L() - 1.0;
            Color color = this.Jf.getInterpolatedColor();
            Color color2 = ListValueComponent.J.A;
            Color color3 = ListValueComponent.J.C;
            Color color4 = ListValueComponent.J.F;
            Color color5 = this.I.getInterpolatedColor();
            double d5 = d2 + 3.0;
            double d6 = d5 + smoothFontRenderer.d(this.JS) + 1.0;
            double d7 = smoothFontRenderer3.N("" + this.a$src$I$13ib7k2());
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
            GuiRenderPrimitives.B(d10, d11, d8, d9, color4, 2.4f);
            smoothFontRenderer.d(this.JS, d12, d5, color2);
            smoothFontRenderer2.d(this.z(smoothFontRenderer2, d13), d12, d6, color3);
            smoothFontRenderer3.d("" + this.a$src$I$13ib7k2(), d10 + (d8 - d7) / 2.0, d11 + 1.5, color2);
            if (this.Je) {
                ImageRenderer.E(color5, f2 + 0.5f, f3, "newblockedlist", f, f, false);
                ImageRenderer.E(ListValueComponent.J.d, f2 - 0.5f, f3, "newblocked", f, f, false);
            } else {
                ImageRenderer.E(color5, f2 + 0.5f, f3, "newallowedlist", f, f, false);
                ImageRenderer.E(ListValueComponent.J.B, f2 + 0.5f, f3, "newallowed", f, f, false);
            }
            return;
        }
        double d = this.G$src$D$1b2f02a() + 5.0;
        double d14 = this.n() + 0.5;
        double d15 = this.A() - 10.0;
        double d16 = this.L() - 1.0;
        Color color = this.d$src$Z$oqzxee() ? ListValueComponent.J.a : ListValueComponent.J.S;
        Color color6 = this.d$src$Z$oqzxee() ? ListValueComponent.J.A : ListValueComponent.J.Z;
        Color color7 = ListValueComponent.J.h;
        Color color8 = this.d$src$Z$oqzxee() ? ListValueComponent.J.F : ListValueComponent.J.a;
        Color color9 = color6;
        double d17 = d14 + 3.0;
        double d18 = d17 + smoothFontRenderer.d(this.JS) + 1.0;
        double d19 = smoothFontRenderer3.N("" + this.a$src$I$13ib7k2());
        double d20 = Math.max(11.0, d19 + 6.0);
        double d21 = 10.0;
        double d22 = d + d15 - 4.0 - d20;
        double d23 = d14 + 3.0;
        float f = 6.0f;
        float f4 = (float)(d + 6.0);
        float f5 = (float)(d14 + (d16 - (double)f) / 2.0);
        double d24 = d + 17.0;
        double d25 = Math.max(0.0, d22 - d24 - 4.0);
        GuiRenderPrimitives.B(d, d14, d15, d16, color, 3.0f);
        GuiRenderPrimitives.B(d22, d23, d20, d21, color8, 2.4f);
        smoothFontRenderer.d(this.JS, d24, d17, color6);
        smoothFontRenderer2.d(this.z(smoothFontRenderer2, d25), d24, d18, color7);
        smoothFontRenderer3.d("" + this.a$src$I$13ib7k2(), d22 + (d20 - d19) / 2.0, d23 + 1.5, color6);
        if (this.Je) {
            ImageRenderer.E(color9, f4 + 0.5f, f5, "newblockedlist", f, f, false);
            ImageRenderer.E(ListValueComponent.J.d, f4 - 0.5f, f5, "newblocked", f, f, false);
        } else {
            ImageRenderer.E(color9, f4 + 0.5f, f5, "newallowedlist", f, f, false);
            ImageRenderer.E(ListValueComponent.J.B, f4 + 0.5f, f5, "newallowed", f, f, false);
        }
    }

    @Override
    public void c() {
        if (!this.J0 && this.J2 != null) {
            this.J2.h();
        }
        super.c();
    }

    public void X(boolean bl) {
        this.J0 = bl;
    }

    @Override
    public double C() {
        if (this.JR == ValueComponentMode.STANDALONE) {
            return 23.0;
        }
        return super.C();
    }

    private boolean F$src$Z$133grxy() {
        if (!(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa() instanceof ClickGuiMainFrame)) {
            return false;
        }
        if (!(this.K.getParent() instanceof BooleanValue)) {
            return false;
        }
        return ((BooleanValue)this.K.getParent()).q$src$Ljava_util_List_$fyau59().size() == 1;
    }

    public boolean n$src$Z$13pgjoe() {
        return this.Je;
    }

    public String E() {
        return this.JS;
    }

    public void W(ValueComponentMode valueComponentMode) {
        this.JR = valueComponentMode;
    }
}

