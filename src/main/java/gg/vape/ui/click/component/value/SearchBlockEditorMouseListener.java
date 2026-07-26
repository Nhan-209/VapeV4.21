package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.value.SearchBlockEditorComponent;
import gg.vape.ui.unmap.SearchBlock;
import java.awt.Point;

public class SearchBlockEditorMouseListener
implements GuiMouseListener {
    final SearchBlockEditorComponent S;
    final SearchBlock I;
    private static final String b = "Clicked tracers";

    public SearchBlockEditorMouseListener(SearchBlockEditorComponent searchBlockEditorComponent, SearchBlock searchBlock) {
        this.S = searchBlockEditorComponent;
        this.I = searchBlock;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.I.H(SearchBlockEditorComponent.q(this.S).L());
        Vape.debugLog(b);
    }
}

