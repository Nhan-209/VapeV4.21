package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemListFrame;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

class HotbarSlotRuleItemSelectClickHandler
implements GuiClickListener {
    final HotbarSlotRuleItemListFrame p;
    final List T;
    final int V;

    HotbarSlotRuleItemSelectClickHandler(HotbarSlotRuleItemListFrame hotbarSlotRuleItemListFrame, List list, int itemIndex) {
        this.p = hotbarSlotRuleItemListFrame;
        this.T = list;
        this.V = itemIndex;
    }

    @Override
    public void P() {
        HotbarSlotRule hotbarSlotRule = HotbarSlotRule.c((ItemStack)this.T.get(this.V));
        HotbarSlotRuleItemListFrame.a(this.p).N$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$xa58f().u$src$Ljava_util_List_$1u5n2i3().set(HotbarSlotRuleItemListFrame.a(this.p).X$src$I$7rbe5s(), hotbarSlotRule);
    }
}

