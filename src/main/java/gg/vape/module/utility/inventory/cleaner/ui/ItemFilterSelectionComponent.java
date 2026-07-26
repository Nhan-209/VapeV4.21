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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private float R = 12.0f;
    private float G = 1.0f;
    @Nullable
    private AbstractInventoryFilterRule b;
    private final ItemFilterSelection v;
    private float O = 12.0f;
    private boolean i;

    public boolean isBlatantMod() {
        return this.i;
    }

    public void W(float f) {
        this.G = f;
    }

    public void K(boolean bl) {
        this.i = bl;
    }

    public ItemFilterSelectionComponent(ItemFilterSelection itemFilterSelection) {
        this.v = itemFilterSelection;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean H$src$Z$zauvhd() {
        InventoryFilterPreset inventoryFilterPreset = this.b.W();
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
        int n = arrayList.size();
        double d = this.G$src$D$1b2f02a() + 6.0;
        double d2 = this.G$src$D$1b2f02a() + 18.0;
        double d3 = this.G$src$D$1b2f02a() + 12.0;
        double d4 = this.n() + 6.0;
        double d5 = this.n() + 12.0;
        double d6 = this.n() + 18.0;
        if (n == 1) {
            int n2 = Math.max(1, Math.round(16.0f * this.G));
            ItemIconRenderer.R((ItemStack)arrayList.get(0), (float)(this.G$src$D$1b2f02a() + 7.0), (float)(this.n() + 7.0), n2, n2);
        } else if (n == 2) {
            this.w((ItemStack)arrayList.get(0), d, d5, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(1), d2, d5, this.G * 0.5f);
        } else if (n == 3) {
            this.w((ItemStack)arrayList.get(0), d3, d4, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(1), d, d6, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(2), d2, d6, this.G * 0.5f);
        } else if (n == 4) {
            this.w((ItemStack)arrayList.get(0), d, d4, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(1), d2, d4, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(2), d, d6, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(3), d2, d6, this.G * 0.5f);
        } else {
            this.w((ItemStack)arrayList.get(0), d, d4, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(1), d2, d4, this.G * 0.5f);
            this.w((ItemStack)arrayList.get(2), d, d6, this.G * 0.5f);
            this.i(n - 3, d2, d6);
        }
        return true;
    }

    @Override
    public void H() {
        ItemStack itemStack = this.v.E();
        if (itemStack != null && !itemStack.isNull()) {
            this.w("");
            float f = this.G;
            if (f >= 1.0f && Vape.INSTANCE.getClientSettings().s() > 1.0) {
                f = 0.95f;
            }
            float f2 = 16.0f * f;
            float f3 = (float)(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(f2 / 2.0f));
            float f4 = (float)(this.n() + this.L() / 2.0 - (double)(f2 / 2.0f));
            int n = Math.max(1, Math.round(f2));
            ItemIconRenderer.R(itemStack, f3, f4, n, n);
        } else {
            this.K$src$V$zci960();
        }
    }

    private void K$src$V$zci960() {
        boolean bl = this.v.j();
        String string = this.v.V();
        if (this.v.i()) {
            this.w("Unknown item: " + this.v.J());
            this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(1.0).W("?", this.G$src$D$1b2f02a() + this.A() / 2.0, this.n() + this.L() / 2.0 - 4.0, ItemFilterSelectionComponent.J.A);
        } else {
            Object object;
            if (this.v.c() != null && (object = this.v.c()).equals(HiddenInventoryItemMatchers.R) && this.z()) {
                return;
            }
            this.w("");
            Object object2 = string == null ? (this.i ? "empty-slot@2x" : null) : (object = string);
            if (object2 != null) {
                ImageRenderer.E(bl ? Color.WHITE : ItemFilterSelectionComponent.J.B, (float)(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(this.R / 2.0f)), (float)(this.n() + this.L() / 2.0 - (double)(this.O / 2.0f)), (String)object2, this.R, this.O, false);
            }
        }
    }

    private boolean z() {
        if (this.b == null) {
            return false;
        }
        return this.H$src$Z$zauvhd();
    }

    public ItemFilterSelectionComponent(AbstractInventoryFilterRule abstractInventoryFilterRule) {
        this(abstractInventoryFilterRule.q());
        this.b = abstractInventoryFilterRule;
    }

    public void D(float f) {
        this.R = f;
    }

    public float l$src$F$zungd5() {
        return this.O;
    }

    public void s(float f) {
        this.O = f;
    }

    public ItemFilterSelectionComponent(InventoryFilterRule inventoryFilterRule) {
        if (inventoryFilterRule instanceof AbstractInventoryFilterRule) {
            this.b = (AbstractInventoryFilterRule)inventoryFilterRule;
            this.v = this.b.q();
        } else {
            this.v = inventoryFilterRule.q();
        }
    }

    public float v() {
        return this.G;
    }

    public float R() {
        return this.R;
    }

    private void i(int n, double d, double d2) {
        String string = n >= 10 ? "9+" : "+" + n;
        GuiRenderPrimitives.B(d - 1.0, d2 - 1.0, 10.0, 10.0, ItemFilterSelectionComponent.J.z, 5.0f);
        this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8).W(string, d + 4.0, d2 + 1.0, ItemFilterSelectionComponent.J.A);
    }

    private void w(ItemStack itemStack, double d, double d2, float f) {
        GuiRenderPrimitives.B(d - 1.0, d2 - 1.0, 10.0, 10.0, ItemFilterSelectionComponent.J.z, 5.0f);
        int n = Math.max(1, Math.round(16.0f * f));
        ItemIconRenderer.R(itemStack, (float)d, (float)d2, n, n);
    }

    public ItemFilterSelection H$src$Lgg_vape_module_utility_inventory_cleaner_ItemFi$nujbwb() {
        return this.v;
    }
}
