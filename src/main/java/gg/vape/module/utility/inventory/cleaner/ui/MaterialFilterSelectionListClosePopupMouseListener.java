package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionList;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import java.awt.Point;

class MaterialFilterSelectionListClosePopupMouseListener
implements GuiMouseListener {
    final MaterialFilterSelectionList H;
    final AnchoredPopupFrame D;

    MaterialFilterSelectionListClosePopupMouseListener(MaterialFilterSelectionList materialFilterSelectionList, AnchoredPopupFrame anchoredPopupFrame) {
        this.H = materialFilterSelectionList;
        this.D = anchoredPopupFrame;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (!this.D.w$src$Z$e457mb() && !this.D.D$src$Lgg_vape_ui_click_component_GuiComponent_$srx612().w$src$Z$e457mb()) {
            ClientSettings.K(this.D);
        }
    }

}

