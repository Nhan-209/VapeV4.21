package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MaterialFilterCondition;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class ItemFilterSelectionComponent
extends GuiComponent {
    private float iconWidth = 12.0f;
    private float scale = 1.0f;
    @Nullable
    private AbstractInventoryFilterRule filterRule;
    private final ItemFilterSelection selection;
    private float iconHeight = 12.0f;
    private boolean blatantMod;

    public boolean isBlatantMod() {
        return this.blatantMod;
    }

    public void W(float f) {
        this.scale = f;
    }

    public void K(boolean bl) {
        this.blatantMod = bl;
    }

    public ItemFilterSelectionComponent(ItemFilterSelection itemFilterSelection) {
        this.selection = itemFilterSelection;
    }


    private boolean renderPresetPreview() {
        InventoryFilterPreset inventoryFilterPreset = this.filterRule.W();
        if (inventoryFilterPreset == null) {
            return false;
        }
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        block0: for (InventoryFilterConditionGroup inventoryFilterConditionGroup : inventoryFilterPreset.z()) {
            for (InventoryFilterCondition<?> inventoryFilterCondition : inventoryFilterConditionGroup.c()) {
                if (!inventoryFilterCondition.K().equals(InventoryFilterConditionType.MATERIAL)) continue;
                MaterialFilterCondition materialFilterCondition = (MaterialFilterCondition)inventoryFilterCondition;
                for (ItemFilterSelection itemFilterSelection : materialFilterCondition.U()) {
                    arrayList.add(itemFilterSelection.E());
                }
                continue block0;
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        int count = arrayList.size();
        double leftX = this.G$src$D$1b2f02a() + 6.0;
        double rightX = this.G$src$D$1b2f02a() + 18.0;
        double centerX = this.G$src$D$1b2f02a() + 12.0;
        double topY = this.n() + 6.0;
        double middleY = this.n() + 12.0;
        double bottomY = this.n() + 18.0;
        if (count == 1) {
            int size = Math.max(1, Math.round(16.0f * this.scale));
            ItemIconRenderer.R((ItemStack)arrayList.get(0), (float)(this.G$src$D$1b2f02a() + 7.0), (float)(this.n() + 7.0), size, size);
        } else if (count == 2) {
            this.drawItemIcon((ItemStack)arrayList.get(0), leftX, middleY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(1), rightX, middleY, this.scale * 0.5f);
        } else if (count == 3) {
            this.drawItemIcon((ItemStack)arrayList.get(0), centerX, topY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(1), leftX, bottomY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(2), rightX, bottomY, this.scale * 0.5f);
        } else if (count == 4) {
            this.drawItemIcon((ItemStack)arrayList.get(0), leftX, topY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(1), rightX, topY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(2), leftX, bottomY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(3), rightX, bottomY, this.scale * 0.5f);
        } else {
            this.drawItemIcon((ItemStack)arrayList.get(0), leftX, topY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(1), rightX, topY, this.scale * 0.5f);
            this.drawItemIcon((ItemStack)arrayList.get(2), leftX, bottomY, this.scale * 0.5f);
            this.drawOverflowCount(count - 3, rightX, bottomY);
        }
        return true;
    }

    @Override
    public void H() {
        ItemStack itemStack = this.selection.E();
        if (itemStack != null && !itemStack.isNull()) {
            this.w("");
            float effectiveScale = this.scale;
            if (effectiveScale >= 1.0f && Vape.INSTANCE.getClientSettings().s() > 1.0) {
                effectiveScale = 0.95f;
            }
            float iconSize = 16.0f * effectiveScale;
            float drawX = (float)(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(iconSize / 2.0f));
            float drawY = (float)(this.n() + this.L() / 2.0 - (double)(iconSize / 2.0f));
            int size = Math.max(1, Math.round(iconSize));
            ItemIconRenderer.R(itemStack, drawX, drawY, size, size);
        } else {
            this.renderPlaceholder();
        }
    }

    private void renderPlaceholder() {
        boolean white = this.selection.j();
        String imageName = this.selection.V();
        if (this.selection.i()) {
            this.w("Unknown item: " + this.selection.J());
            this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(1.0).W("?", this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + this.L() / 2.0 - 4.0, ItemFilterSelectionComponent.J.A);
        } else {
            Object object;
            if (this.selection.c() != null && (object = this.selection.c()).equals(HiddenInventoryItemMatchers.R) && this.hasPresetPreview()) {
                return;
            }
            this.w("");
            Object resolvedImage = imageName == null ? (this.blatantMod ? "empty-slot@2x" : null) : (object = imageName);
            if (resolvedImage != null) {
                ImageRenderer.E(white ? Color.WHITE : ItemFilterSelectionComponent.J.B, (float)(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(this.iconWidth / 2.0f)), (float)(this.n() + this.L() / 2.0 - (double)(this.iconHeight / 2.0f)), (String)resolvedImage, this.iconWidth, this.iconHeight, false);
            }
        }
    }

    private boolean hasPresetPreview() {
        if (this.filterRule == null) {
            return false;
        }
        return this.renderPresetPreview();
    }

    public ItemFilterSelectionComponent(AbstractInventoryFilterRule abstractInventoryFilterRule) {
        this(abstractInventoryFilterRule.q());
        this.filterRule = abstractInventoryFilterRule;
    }

    public void D(float f) {
        this.iconWidth = f;
    }

    public float l$src$F$zungd5() {
        return this.iconHeight;
    }

    public void s(float f) {
        this.iconHeight = f;
    }

    public ItemFilterSelectionComponent(InventoryFilterRule inventoryFilterRule) {
        if (inventoryFilterRule instanceof AbstractInventoryFilterRule) {
            this.filterRule = (AbstractInventoryFilterRule)inventoryFilterRule;
            this.selection = this.filterRule.q();
        } else {
            this.selection = inventoryFilterRule.q();
        }
    }

    public float v() {
        return this.scale;
    }

    public float R() {
        return this.iconWidth;
    }

    private void drawOverflowCount(int extraCount, double x, double y) {
        String label = extraCount >= 10 ? "9+" : "+" + extraCount;
        GuiRenderPrimitives.B(x - 1.0, y - 1.0, 10.0, 10.0, ItemFilterSelectionComponent.J.z, 5.0f);
        this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8).W(label, x + 4.0, y + 1.0, ItemFilterSelectionComponent.J.A);
    }

    private void drawItemIcon(ItemStack itemStack, double x, double y, float f) {
        GuiRenderPrimitives.B(x - 1.0, y - 1.0, 10.0, 10.0, ItemFilterSelectionComponent.J.z, 5.0f);
        int size = Math.max(1, Math.round(16.0f * f));
        ItemIconRenderer.R(itemStack, (float)x, (float)y, size, size);
    }

    public ItemFilterSelection H$src$Lgg_vape_module_utility_inventory_cleaner_ItemFi$nujbwb() {
        return this.selection;
    }
}
