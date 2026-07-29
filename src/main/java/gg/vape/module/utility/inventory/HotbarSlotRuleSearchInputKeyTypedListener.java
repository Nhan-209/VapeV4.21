package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemSearchComponent;
import gg.vape.ui.click.component.GuiKeyTypedListener;

public class HotbarSlotRuleSearchInputKeyTypedListener
implements GuiKeyTypedListener {
    final HotbarSlotRuleItemPickerFrame F;
    final HotbarSlotRuleItemSearchComponent l;

    public HotbarSlotRuleSearchInputKeyTypedListener(HotbarSlotRuleItemSearchComponent hotbarSlotRuleItemSearchComponent, HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame) {
        this.l = hotbarSlotRuleItemSearchComponent;
        this.F = hotbarSlotRuleItemPickerFrame;
    }

    @Override
    public void onKeyTyped(char c, int n) {
        this.F.s(HotbarSlotRuleItemSearchComponent.g(this.l).isShowEditButton());
    }
}
