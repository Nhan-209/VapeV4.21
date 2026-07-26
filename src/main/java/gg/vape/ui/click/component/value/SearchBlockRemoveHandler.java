package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.SearchBlockListDropdownLayer;
import gg.vape.ui.unmap.SearchBlock;

class SearchBlockRemoveHandler
implements GuiClickListener {
    final SearchBlock K;
    final SearchBlockListDropdownLayer u;

    @Override
    public void P() {
        Vape.INSTANCE.getSearch().y(this.K);
        this.u.e();
    }

    SearchBlockRemoveHandler(SearchBlockListDropdownLayer searchBlockListDropdownLayer, SearchBlock searchBlock) {
        this.u = searchBlockListDropdownLayer;
        this.K = searchBlock;
    }
}

