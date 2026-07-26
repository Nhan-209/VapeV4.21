package gg.vape.ui.click.frame;

import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterDropdownCloseHandler;
import gg.vape.ui.click.component.value.EntityTargetFilterQuickToggleComponent;
import gg.vape.ui.click.component.value.FloatingValueDropdownLayer;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;

public class FrameValueDropdownLayer
extends FloatingValueDropdownLayer<EntityTargetFilterComponent> {
    @Override
    public void e() {
    }

    public FrameValueDropdownLayer(EntityTargetFilterComponent entityTargetFilterComponent) {
        super(entityTargetFilterComponent);
        this.Y(new PublicProfilesFrameHeaderActionComponent(this, "newaim", "Target settings", 0.8).Q(new EntityTargetFilterDropdownCloseHandler(this, entityTargetFilterComponent)));
        this.h(new EntityTargetFilterQuickToggleComponent(entityTargetFilterComponent.u$src$Lgg_vape_value_EntityTargetFilterValue_$12u8kyq()), new Object[0]);
        this.h(new BooleanToggleComponent(entityTargetFilterComponent.u$src$Lgg_vape_value_EntityTargetFilterValue_$12u8kyq().E()), new Object[0]);
        this.h(new BooleanToggleComponent(entityTargetFilterComponent.u$src$Lgg_vape_value_EntityTargetFilterValue_$12u8kyq().q$src$Lgg_vape_value_BooleanValue_$4eyax4()), new Object[0]);
        this.h(new BooleanToggleComponent(entityTargetFilterComponent.u$src$Lgg_vape_value_EntityTargetFilterValue_$12u8kyq().x()), new Object[0]);
    }
}

