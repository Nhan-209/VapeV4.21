package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRuleEditorComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleGroupComponent;
import gg.vape.ui.click.component.GuiClickListener;

class HotbarSlotRuleGroupSelectClickHandler
implements GuiClickListener {
    final HotbarSlotRuleGroupComponent n;
    final HotbarSlotRuleEditorComponent l;


    @Override
    public void P() {
        if (this.n.equals(HotbarSlotRuleEditorComponent.H(this.l)) && HotbarSlotRuleEditorComponent.N(this.l).size() > 0) {
            this.l.f((HotbarSlotRuleGroupComponent)HotbarSlotRuleEditorComponent.N(this.l).get(0));
        }
        this.l.o(this.n);
        this.l.w$src$V$j701ty();
    }

    HotbarSlotRuleGroupSelectClickHandler(HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent, HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent) {
        this.l = hotbarSlotRuleEditorComponent;
        this.n = hotbarSlotRuleGroupComponent;
    }
}

