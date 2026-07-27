package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PopupSelectorComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterQuickToggleComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.EntityTargetFilterValue;

public class EntityTargetFilterPopupComponent
extends PopupSelectorComponent {
    private EntityTargetFilterValue I;
    private final FlowLayoutComponent b = (FlowLayoutComponent)this.u$src$Lgg_vape_ui_click_frame_FrameComponent_$bcl1km();


    public EntityTargetFilterPopupComponent(EntityTargetFilterValue entityTargetFilterValue) {
        super(new FlowLayoutComponent(50.0));
        this.b.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        this.b.h(new EntityTargetFilterQuickToggleComponent(entityTargetFilterValue), new Object[0]);
        this.b.h(new BooleanToggleComponent(entityTargetFilterValue.E()), new Object[0]);
        this.b.h(new BooleanToggleComponent(entityTargetFilterValue.q$src$Lgg_vape_value_BooleanValue_$4eyax4()), new Object[0]);
        this.b.h(new BooleanToggleComponent(entityTargetFilterValue.x()), new Object[0]);
        this.I = entityTargetFilterValue;
        this.C(entityTargetFilterValue);
        this.Y(20.0);
        this.u(20.0);
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.8);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n() + 2.5, this.A() - 5.0, this.L() - 5.0, this.w$src$Z$e457mb() ? EntityTargetFilterPopupComponent.J.y : EntityTargetFilterPopupComponent.J.l, 2.0f, 0.75f, 1.0f);
        ImageRenderer.E(EntityTargetFilterPopupComponent.J.W, (float)(this.G$src$D$1b2f02a() + 8.0), (float)(this.n() + 7.0), "target_single", 6.0f, 6.0f, false);
        smoothFontRenderer.d("Targets", this.G$src$D$1b2f02a() + 18.0, this.n() + 7.0, EntityTargetFilterPopupComponent.J.A);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 44.0, this.n() + 8.0, 5.0, 1.0f, EntityTargetFilterPopupComponent.J.l);
        String string = "";
        if (this.I.D().L().booleanValue()) {
            string = string + "Players, ";
        }
        if (this.I.f().L().booleanValue()) {
            string = string + "Mobs, ";
        }
        if (this.I.r$src$Lgg_vape_value_BooleanValue_$167auuf().L().booleanValue()) {
            string = string + "Peaceful, ";
        }
        boolean bl = !string.isEmpty();
        String string2 = "";
        if (this.I.E().L().booleanValue()) {
            string2 = string2 + "invisible, ";
        }
        if (this.I.q$src$Lgg_vape_value_BooleanValue_$4eyax4().L().booleanValue()) {
            string2 = string2 + "naked, ";
        }
        if (this.I.x().L().booleanValue()) {
            string2 = string2 + "behind walls, ";
        }
        if (!string2.isEmpty()) {
            string2 = "Ignoring " + string2;
        }
        if ((string = string + string2).endsWith(", ")) {
            string = string.substring(0, string.length() - 2);
        }
        if (!bl) {
            string = "None";
        }
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(string, "...", 105.0, 0.8, EntityTargetFilterPopupComponent.J.A, false);
        truncatedTextComponent.V(this.G$src$D$1b2f02a() + 52.0, this.n() + 7.0);
        smoothFontRenderer.d("edit", this.G$src$D$1b2f02a() + this.A() - 20.0, this.n() + 7.0, EntityTargetFilterPopupComponent.J.A);
    }
}

