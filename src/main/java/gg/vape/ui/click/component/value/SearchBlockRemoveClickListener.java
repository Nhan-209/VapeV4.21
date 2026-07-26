package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.SearchBlockListComponent;
import gg.vape.ui.unmap.SearchBlock;

public class SearchBlockRemoveClickListener
implements GuiClickListener {
    final SearchBlock U;
    final Runnable u;
    final SearchBlockListComponent S;

    @Override
    public void P() {
        Vape.INSTANCE.getSearch().y(this.U);
        this.u.run();
    }

    public SearchBlockRemoveClickListener(SearchBlockListComponent ud_22, SearchBlock searchBlock, Runnable runnable) {
        this.S = ud_22;
        this.U = searchBlock;
        this.u = runnable;
    }
}

