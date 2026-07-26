package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ListValueOptionsPanel;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.LimitValue;

public class RemoveLimitValueEntryHandler
implements GuiClickListener {
    final ItemLimitData B;
    final ListValueOptionsPanel E;

    public RemoveLimitValueEntryHandler(ListValueOptionsPanel listValueOptionsPanel, ItemLimitData itemLimitData) {
        this.E = listValueOptionsPanel;
        this.B = itemLimitData;
    }

    @Override
    public void P() {
        ((LimitValue)ListValueOptionsPanel.I(this.E)).k(this.B);
        ListValueOptionsPanel.I(this.E).g$src$V$1akzyia();
        this.E.k$src$V$admw0a();
    }
}

