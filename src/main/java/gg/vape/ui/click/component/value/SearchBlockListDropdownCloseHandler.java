package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.SearchBlockListComponent;
import gg.vape.ui.click.component.value.SearchBlockListDropdownLayer;

class SearchBlockListDropdownCloseHandler
implements GuiClickListener {
    final SearchBlockListDropdownLayer V;
    final SearchBlockListComponent C;

    SearchBlockListDropdownCloseHandler(SearchBlockListDropdownLayer searchBlockListDropdownLayer, SearchBlockListComponent searchBlockListComponent) {
        this.V = searchBlockListDropdownLayer;
        this.C = searchBlockListComponent;
    }

    @Override
    public void P() {
        this.C.a(false);
    }
}

