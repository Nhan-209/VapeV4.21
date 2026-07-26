package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.EntityTargetFilterComponent;
import gg.vape.ui.click.frame.FrameValueDropdownLayer;

public class EntityTargetFilterDropdownCloseHandler
implements GuiClickListener {
    final FrameValueDropdownLayer y;
    final EntityTargetFilterComponent V;

    public EntityTargetFilterDropdownCloseHandler(FrameValueDropdownLayer se_02, EntityTargetFilterComponent uj_22) {
        this.y = se_02;
        this.V = uj_22;
    }

    @Override
    public void P() {
        this.V.a(false);
    }
}

