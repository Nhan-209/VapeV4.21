package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleSlotSelectorComponent;
import gg.vape.ui.click.component.GuiClickListener;

class HotbarSlotRuleSlotSelectClickHandler
implements GuiClickListener {
    final int d;
    final HotbarSlotRuleItemPickerFrame F;
    final HotbarSlotRuleSlotSelectorComponent i;

    HotbarSlotRuleSlotSelectClickHandler(HotbarSlotRuleSlotSelectorComponent hotbarSlotRuleSlotSelectorComponent, HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame, int slotIndex) {
        this.i = hotbarSlotRuleSlotSelectorComponent;
        this.F = hotbarSlotRuleItemPickerFrame;
        this.d = slotIndex;
    }

    @Override
    public void onSecondaryClick() {
        this.F.N$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$xa58f().u$src$Ljava_util_List_$1u5n2i3().set(this.d, new HotbarSlotRule(0));
    }

    @Override
    public void onPrimaryClick() {
        this.F.t(this.d);
    }
}

