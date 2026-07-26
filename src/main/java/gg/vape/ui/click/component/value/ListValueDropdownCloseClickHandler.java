package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ListValueComponent;
import gg.vape.ui.click.component.value.ListValueDropdownLayer;

public class ListValueDropdownCloseClickHandler
implements GuiClickListener {
    final ListValueComponent z;
    final ListValueDropdownLayer b;

    public ListValueDropdownCloseClickHandler(ListValueDropdownLayer listValueDropdownLayer, ListValueComponent listValueComponent) {
        this.b = listValueDropdownLayer;
        this.z = listValueComponent;
    }

    @Override
    public void P() {
        this.z.a(false);
    }
}

