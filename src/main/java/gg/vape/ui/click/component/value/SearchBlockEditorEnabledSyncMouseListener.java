package gg.vape.ui.click.component.value;

import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.value.SearchBlockEditorComponent;
import gg.vape.ui.unmap.SearchBlock;
import java.awt.Point;

public class SearchBlockEditorEnabledSyncMouseListener
implements GuiMouseListener {
    final SearchBlock R;
    final SearchBlockEditorComponent L;

    public SearchBlockEditorEnabledSyncMouseListener(SearchBlockEditorComponent searchBlockEditorComponent, SearchBlock searchBlock) {
        this.L = searchBlockEditorComponent;
        this.R = searchBlock;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.R.M(SearchBlockEditorComponent.S(this.L).java_lang_Boolean_L());
    }
}

