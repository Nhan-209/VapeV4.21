package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerPanel;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class InventoryItemPickerCategoryItemClickListener
implements GuiMouseListener {
    final ItemMappingEntry Q;
    final InventoryItemPickerPanel O;

    public InventoryItemPickerCategoryItemClickListener(InventoryItemPickerPanel inventoryItemPickerPanel, ItemMappingEntry itemMappingEntry) {
        this.O = inventoryItemPickerPanel;
        this.Q = itemMappingEntry;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        InventoryItemPickerPanel.U(this.O, ItemPickerSelection.D(this.Q));
    }
}

