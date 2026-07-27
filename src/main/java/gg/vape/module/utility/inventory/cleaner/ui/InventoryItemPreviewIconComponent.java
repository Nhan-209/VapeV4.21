package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPreviewComponent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class InventoryItemPreviewIconComponent
extends GuiComponent {
    @Nullable
    private final ItemStack R;
    final InventoryItemPreviewComponent o;
    @Nullable
    private final Item b;
    private final ColorAnimation I;

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), InventoryItemPreviewIconComponent.J.y);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.I.getInterpolatedColor(), 1.5f, 0.75f, 1.0f);
        if (this.R != null) {
            ItemIconRenderer.R(this.R, (float)(this.G$src$D$1b2f02a() + 2.0), (float)(this.n() + 2.0), 8, 8);
        }
    }

    @Override
    public double x() {
        return 12.0;
    }

    public InventoryItemPreviewIconComponent(@Nullable InventoryItemPreviewComponent inventoryItemPreviewComponent, ItemStack itemStack) {
        this.o = inventoryItemPreviewComponent;
        this.I = new ColorAnimation(0.15, new Color(0, 0, 0, 0), InventoryItemPreviewIconComponent.J.O);
        this.b = itemStack != null && itemStack.isNotNull() ? itemStack.getItem() : null;
        this.R = itemStack != null && itemStack.isNotNull() ? itemStack : null;
    }

    public InventoryItemPreviewIconComponent(@Nullable InventoryItemPreviewComponent inventoryItemPreviewComponent, Item item) {
        this.o = inventoryItemPreviewComponent;
        this.I = new ColorAnimation(0.15, new Color(0, 0, 0, 0), InventoryItemPreviewIconComponent.J.O);
        this.b = item != null && item.isNotNull() ? item : null;
        this.R = item != null && item.isNotNull() ? ItemStack.S(item) : null;
    }

    @Override
    public void onEnable() {
        this.I.J();
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.I.J();
        }
    }

}

