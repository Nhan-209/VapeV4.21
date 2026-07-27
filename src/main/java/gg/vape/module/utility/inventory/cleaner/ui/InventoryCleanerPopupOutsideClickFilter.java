package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.frame.PopupFrame;
import java.awt.Point;

class InventoryCleanerPopupOutsideClickFilter
implements GuiMouseListener {
    final InventoryCleanerPopupFrame P;
    final PopupFrame o;

    InventoryCleanerPopupOutsideClickFilter(InventoryCleanerPopupFrame inventoryCleanerPopupFrame, PopupFrame popupFrame) {
        this.P = inventoryCleanerPopupFrame;
        this.o = popupFrame;
    }

    @Override
    public boolean Q(Point point) {
        if (this.P.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().Q().R(point) && !this.o.Q().R(point)) {
            return true;
        }
        return GuiMouseListener.super.Q(point);
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

