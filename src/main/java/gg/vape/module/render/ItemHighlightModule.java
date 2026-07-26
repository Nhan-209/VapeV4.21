package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;

public class ItemHighlightModule
extends Mod {
    private static final long c;
    private static final String b;
    public int I = (int)c;

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        ItemIconRenderer.R(ItemStack.S(Item.T(1)), 10.0f, 1.0f, 16, 16);
        ItemIconRenderer.R(ItemStack.S(Item.T(10)), 20.0f, 1.0f, 16, 16);
        ItemIconRenderer.R(ItemStack.S(Item.T(11)), 30.0f, 1.0f, 16, 16);
    }

    static {
        b = "Render Test Module";
        c = -9060873430974332925L;
    }

    public ItemHighlightModule() {
        super(b, -1, Category.Y);
    }

    @Override
    public void onEnable() {
    }
}

