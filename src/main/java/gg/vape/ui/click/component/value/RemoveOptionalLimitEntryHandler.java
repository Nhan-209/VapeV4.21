package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ListValueOptionsPanel;
import gg.vape.value.OptionalLimitEntry;
import gg.vape.value.OptionalLimitValue;

class RemoveOptionalLimitEntryHandler
implements GuiClickListener {
    final ListValueOptionsPanel b;
    final OptionalLimitEntry A;

    @Override
    public void P() {
        ((OptionalLimitValue)ListValueOptionsPanel.I(this.b)).b(this.A);
        ListValueOptionsPanel.I(this.b).g$src$V$1akzyia();
        this.b.k$src$V$admw0a();
    }

    RemoveOptionalLimitEntryHandler(ListValueOptionsPanel listValueOptionsPanel, OptionalLimitEntry optionalLimitEntry) {
        this.b = listValueOptionsPanel;
        this.A = optionalLimitEntry;
    }
}

