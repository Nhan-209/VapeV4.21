package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionList;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionRow;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

class MaterialFilterSelectionRemoveClickHandler
implements GuiMouseListener {
    final MaterialFilterSelectionList b;
    final MaterialFilterSelectionRow j;

    MaterialFilterSelectionRemoveClickHandler(MaterialFilterSelectionList materialFilterSelectionList, MaterialFilterSelectionRow materialFilterSelectionRow) {
        this.b = materialFilterSelectionList;
        this.j = materialFilterSelectionRow;
    }

    @Override
    public void g(Point point, MouseClickButton uA) {
        this.b.Y(this.j);
    }
}

