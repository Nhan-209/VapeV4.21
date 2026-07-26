package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerClickableRowComponentBase;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPreviewComponent;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.wrapper.impl.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InventoryItemStackSelectionRowComponent
extends InventoryCleanerClickableRowComponentBase {
    private final TruncatedTextComponent i;
    private final TextButton K;
    private final InventoryItemPreviewComponent R;

    public InventoryItemStackSelectionRowComponent(ItemStack itemStack) {
        this.i = new TruncatedTextComponent(itemStack.x(), "...", 50.0, 0.8, InventoryItemStackSelectionRowComponent.J.A, false);
        this.K = new TextButton("ADD", 0.55, InventoryItemStackSelectionRowComponent.J.B, InventoryItemStackSelectionRowComponent.J.O);
        this.K.Y(8.0);
        this.K.o(14.0);
        this.K.F(false);
        this.K.h(InventoryItemStackSelectionRowComponent.J.A);
        this.K.c(true);
        this.K.Z(false);
        this.R = new InventoryItemPreviewComponent(itemStack, false);
        this.H(this.i, this.K, this.R);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void K(@Nullable GuiClickListener guiClickListener) {
        super.K(guiClickListener);
        this.K.s(guiClickListener);
    }

    @Override
    public void H() {
        super.H();
        this.R.K(this.G$src$D$1b2f02a() + 10.0);
        this.R.S(this.n() + this.L() / 2.0 - this.R.L() / 2.0);
        this.i.Y(this.L());
        this.i.K(this.R.G$src$D$1b2f02a() + this.R.A() + 5.0);
        this.i.S(this.n());
        this.K.K(this.G$src$D$1b2f02a() + this.A() - this.K.A() - 8.0);
        this.K.S(this.n() + this.L() / 2.0 - this.K.L() / 2.0);
    }

    @Override
    public void F() {
        this.K.Z(true);
    }

    @Override
    public void onEnable() {
        this.K.Z(false);
    }
}

