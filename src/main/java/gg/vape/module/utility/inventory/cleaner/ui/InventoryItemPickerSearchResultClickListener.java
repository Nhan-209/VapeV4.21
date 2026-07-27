package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerPanel;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class InventoryItemPickerSearchResultClickListener
implements GuiMouseListener {
    final InventoryItemPickerPanel P;
    final ItemMappingEntry D;

    private void selectItem(ItemMappingEntry itemMappingEntry) {
        InventoryItemPickerPanel.U(this.P, ItemPickerSelection.D(itemMappingEntry));
        InventoryItemPickerPanel.a(this.P).remove(itemMappingEntry.M());
        this.P.a$src$V$bf004p();
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        ClientSettings.f6.execute(() -> this.selectItem(this.D));
    }

    public InventoryItemPickerSearchResultClickListener(InventoryItemPickerPanel inventoryItemPickerPanel, ItemMappingEntry itemMappingEntry) {
        this.P = inventoryItemPickerPanel;
        this.D = itemMappingEntry;
    }
}

