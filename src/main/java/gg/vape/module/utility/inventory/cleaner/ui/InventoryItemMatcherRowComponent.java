package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerClickableRowComponentBase;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;

public class InventoryItemMatcherRowComponent
extends InventoryCleanerClickableRowComponentBase {
    private static final String b = "...";
    private final IconGlyphComponent R;
    private final TruncatedTextComponent K;

    public InventoryItemMatcherRowComponent(InventoryItemMatcher inventoryItemMatcher, GuiClickListener guiClickListener) {
        super(guiClickListener);
        this.R = new IconGlyphComponent(inventoryItemMatcher.Z(), 6.0f, 6.0f, InventoryItemMatcherRowComponent.J.Z);
        this.K = new TruncatedTextComponent(inventoryItemMatcher.getName(), b, 50.0, 0.8, InventoryItemMatcherRowComponent.J.Z, false);
        this.o(true);
        this.H(this.K, this.R);
    }

    @Override
    public void H() {
        super.H();
        this.R.K(this.G$src$D$1b2f02a() + 10.0);
        this.R.S(this.n() + this.L() / 2.0 - this.R.L() / 2.0);
        this.K.Y(this.L());
        this.K.K(this.R.G$src$D$1b2f02a() + this.R.A() + 5.0);
        this.K.S(this.n());
    }
}

