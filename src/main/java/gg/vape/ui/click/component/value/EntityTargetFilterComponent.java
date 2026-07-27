package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.value.AbstractListValueComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterQuickToggleComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.FrameValueDropdownLayer;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.EntityTargetFilterValue;
import java.awt.Color;

public class EntityTargetFilterComponent
extends AbstractListValueComponent {
    private boolean K;
    private FrameValueDropdownLayer DB;
    private FrameStackManager Dg;
    private EntityTargetFilterValue I;

    private static void lambda$onClick$1(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
    }

    private void lambda$new$0() {
        this.e$src$V$z862xo();
    }

    @Override
    public void a(boolean bl) {
        super.a(bl);
        if (!bl && this.DB != null && this.Dg != null) {
            this.Dg.m(this.DB);
        }
    }

    public EntityTargetFilterComponent(EntityTargetFilterValue entityTargetFilterValue) {
        this.I = entityTargetFilterValue;
        this.C(entityTargetFilterValue);
        this.r(this::lambda$new$0);
    }

    private void lambda$onClick$2(PanelComponent panelComponent) {
        panelComponent.S();
        panelComponent.h(new EntityTargetFilterQuickToggleComponent(this.I), new Object[0]);
        panelComponent.h(new BooleanToggleComponent(this.I.E()), new Object[0]);
        panelComponent.h(new BooleanToggleComponent(this.I.q$src$Lgg_vape_value_BooleanValue_$4eyax4()), new Object[0]);
        panelComponent.h(new BooleanToggleComponent(this.I.x()), new Object[0]);
    }

    public EntityTargetFilterValue u$src$Lgg_vape_value_EntityTargetFilterValue_$12u8kyq() {
        return this.I;
    }


    @Override
    public void H() {
        this.onDisable();
        if (this.DB != null) {
            this.DB.h();
        }
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        SmoothFontRenderer smoothFontRenderer2 = this.O(0.75);
        Color color = EntityTargetFilterComponent.J.i;
        Color color2 = this.d$src$Z$oqzxee() ? EntityTargetFilterComponent.J.A : (this.P$src$Z$og01j6() ? EntityTargetFilterComponent.J.A : EntityTargetFilterComponent.J.Z);
        double d = smoothFontRenderer.d("Targets");
        double d2 = this.n() + this.L() / 2.0 - d / 2.0 - 2.5 - 2.0;
        double d3 = d2 + 7.5 + 1.0;
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 2.5, this.A() - 10.0, this.L() - 5.0, this.P$src$Z$og01j6() ? J.z() : this.K$src$Lgg_vape_ui_click_animation_ColorAnimation_$la4la().getInterpolatedColor());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0 + 0.5, this.n() + 2.5 + 0.5, this.A() - 10.0 - 1.0, this.L() - 5.0 - 1.0, color);
        double d4 = this.G$src$D$1b2f02a() + 10.0 - 2.0;
        String string = "Target: ";
        if (this.I.D().L().booleanValue()) {
            string = string + "Players, ";
        }
        if (this.I.f().L().booleanValue()) {
            string = string + "Mobs, ";
        }
        if (this.I.r$src$Lgg_vape_value_BooleanValue_$167auuf().L().booleanValue()) {
            string = string + "Peaceful, ";
        }
        if (string.endsWith(", ")) {
            string = string.substring(0, string.length() - 2);
        }
        if (string.equals("Target: ")) {
            string = string + "Nothing";
            smoothFontRenderer.d(string, d4, d2 + 1.0, EntityTargetFilterComponent.J.d);
        } else {
            smoothFontRenderer.d(string, d4, d2 + 1.0, color2);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.I.E().L().booleanValue()) {
            stringBuilder.append("invisible, ");
        }
        if (this.I.q$src$Lgg_vape_value_BooleanValue_$4eyax4().L().booleanValue()) {
            stringBuilder.append("naked, ");
        }
        if (this.I.x().L().booleanValue()) {
            stringBuilder.append("behind walls, ");
        }
        String string2 = stringBuilder.length() < 1 ? "none" : stringBuilder.substring(0, stringBuilder.length() - 2);
        smoothFontRenderer2.d("Ignore " + string2, this.G$src$D$1b2f02a() + 10.0 - 2.0, d3, EntityTargetFilterComponent.J.Z);
    }

    private void e$src$V$z862xo() {
        Frame frame;
        if (this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null && (frame = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa()) instanceof ClickGuiMainFrame) {
            ClickGuiMainFrame clickGuiMainFrame = (ClickGuiMainFrame)frame;
            ClickGuiModulesSidecarPanel clickGuiModulesSidecarPanel = new ClickGuiModulesSidecarPanel(null);
            clickGuiModulesSidecarPanel.k(false);
            clickGuiModulesSidecarPanel.f(false);
            ClickGuiOverlaySpec clickGuiOverlaySpec = ClickGuiOverlaySpec.q().e("Target settings").C("newaim").v(clickGuiModulesSidecarPanel).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).r(ClickGuiOverlayTransitionMode.PUSH).D(EntityTargetFilterComponent::lambda$onClick$1).N(this::lambda$onClick$2).w();
            clickGuiMainFrame.Z(clickGuiOverlaySpec);
            return;
        }
        this.a(!this.P$src$Z$og01j6());
        if (this.P$src$Z$og01j6()) {
            if (this.DB == null) {
                this.DB = new FrameValueDropdownLayer(this);
            }
            this.Dg = ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
            this.Dg.q(this.DB);
        }
    }
}

