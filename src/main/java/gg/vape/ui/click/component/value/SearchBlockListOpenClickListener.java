package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.SearchBlockListComponent;

public class SearchBlockListOpenClickListener
implements GuiClickListener {
    final SearchBlockListComponent k;

    @Override
    public void P() {
        SearchBlockListComponent.e(this.k);
    }

    public SearchBlockListOpenClickListener(SearchBlockListComponent ud_22) {
        this.k = ud_22;
    }
}

