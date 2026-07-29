package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.ui.click.component.GuiClickListener;

class HotbarSlotRuleItemPickerHeaderCloseClickHandler
implements GuiClickListener {
    final HotbarSlotRuleItemPickerFrame k;

    HotbarSlotRuleItemPickerHeaderCloseClickHandler(HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame) {
        this.k = hotbarSlotRuleItemPickerFrame;
    }

    @Override
    public void onPrimaryClick() {
        this.k.Z$src$V$7seznp();
        this.k.N$src$V$7ltgjd();
    }
}
