package gg.vape.module.utility.inventory;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemSearchComponent;
import gg.vape.ui.click.component.GuiClickListener;

public class HotbarSlotRuleItemPickerSearchCloseClickHandler
implements GuiClickListener {
    final HotbarSlotRuleItemSearchComponent d;

    @Override
    public void onPrimaryClick() {
        ClientSettings.getFrame(HotbarSlotRuleItemPickerFrame.class).Z$src$V$7seznp();
        ClientSettings.getFrame(HotbarSlotRuleItemPickerFrame.class).N$src$V$7ltgjd();
    }

    public HotbarSlotRuleItemPickerSearchCloseClickHandler(HotbarSlotRuleItemSearchComponent hotbarSlotRuleItemSearchComponent) {
        this.d = hotbarSlotRuleItemSearchComponent;
    }
}
