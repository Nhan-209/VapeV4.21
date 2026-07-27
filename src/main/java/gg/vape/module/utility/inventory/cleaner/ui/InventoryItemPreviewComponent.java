package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPreviewIconComponent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class InventoryItemPreviewComponent
extends GuiComponent {
    private final ColorAnimation a;
    private final boolean O;
    private static final String i = "newclose";
    private final InventoryItemPreviewIconComponent K;
    private final ColorAnimation G;

    @Override
    public double C() {
        return 14.0;
    }

    public InventoryItemPreviewComponent(@Nullable ItemStack itemStack, boolean bl) {
        this.G = new ColorAnimation(0.15, new Color(0, 0, 0, 0), InventoryItemPreviewComponent.J.d);
        this.a = new ColorAnimation(0.15, new Color(0, 0, 0, 0), Color.WHITE);
        this.K = new InventoryItemPreviewIconComponent(this, itemStack);
        this.O = bl;
        this.o(true);
        this.H(this.K);
    }

    @Override
    public double x() {
        return 14.0;
    }

    public InventoryItemPreviewComponent(@Nullable Item item, boolean bl) {
        this.G = new ColorAnimation(0.15, new Color(0, 0, 0, 0), InventoryItemPreviewComponent.J.d);
        this.a = new ColorAnimation(0.15, new Color(0, 0, 0, 0), Color.WHITE);
        this.K = new InventoryItemPreviewIconComponent(this, item);
        this.O = bl;
        this.o(true);
        this.H(this.K);
    }


    @Override
    public void c() {
        this.K.K(this.G$src$D$1b2f02a() + 1.0);
        this.K.S(this.n() + 1.0);
        super.c();
        if (this.O) {
            GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() - 5.0, this.n(), 6.0, 1.0, this.G.getInterpolatedColor());
            ImageRenderer.E(this.a.getInterpolatedColor(), (float)(this.G$src$D$1b2f02a() + this.A() - 5.5), (float)(this.n() - 1.0), i, 7.0f, 7.0f, false);
        }
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.G.J();
            this.a.J();
        }
    }

    @Override
    public void onEnable() {
        this.G.J();
        this.a.J();
    }
}

