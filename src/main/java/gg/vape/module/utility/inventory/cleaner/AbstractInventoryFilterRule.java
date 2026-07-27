package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryRegistry;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractInventoryFilterRule
implements InventoryFilterRule {
    @Nullable
    private InventoryFilterPreset m;
    @Nullable
    private InventoryItemCategory n;
    private final ItemFilterSelection H;
    @Nullable
    private UUID v;
    private static int[] f;

    @Override
    public ItemFilterSelection q() {
        return this.H;
    }

    @Override
    @Nullable
    public UUID t() {
        return this.v;
    }

    public static int[] o$src$AI$fei8tr() {
        return f;
    }

    @Override
    public InventoryItemCategory L() {
        InventoryItemCategory inventoryItemCategory;
        InventoryItemMatcher inventoryItemMatcher = this.q().c();
        if (inventoryItemMatcher == null && this.H.E() != null) {
            inventoryItemMatcher = InventoryItemMatcherRegistry.S(this.H.E());
        }
        if (inventoryItemMatcher != null && (inventoryItemCategory = inventoryItemMatcher.G()) != null) {
            return inventoryItemCategory;
        }
        return InventoryItemCategoryRegistry.m;
    }

    @Override
    @NotNull
    public InventoryItemCategory o() {
        return this.n != null ? this.n : this.L();
    }

    @Override
    public void U() {
        this.m = null;
        this.v = null;
    }

    @Override
    public void p(@Nullable InventoryFilterPreset inventoryFilterPreset) {
        this.N();
        if (inventoryFilterPreset == null) {
            return;
        }
        if (inventoryFilterPreset instanceof SharedInventoryFilterPreset) {
            this.v = inventoryFilterPreset.j();
        } else {
            this.m = inventoryFilterPreset;
        }
    }

    @Override
    public void i(@Nullable InventoryItemCategory inventoryItemCategory) {
        this.n = inventoryItemCategory;
    }

    static {
        AbstractInventoryFilterRule.u(new int[1]);
    }

    public AbstractInventoryFilterRule(JsonObject jsonObject) {
        this.H = new ItemFilterSelection(jsonObject.get("itemFilter"));
        if (jsonObject.has("customRule")) {
            JsonElement jsonElement = jsonObject.get("customRule");
            if (jsonElement.isJsonPrimitive()) {
                this.v = ConfigJsonUtils.u(jsonObject, "customRule");
            } else {
                this.m = new InventoryFilterPreset(jsonObject.getAsJsonObject("customRule"));
            }
        }
        if (jsonObject.has("priority")) {
            this.n = InventoryItemCategoryRegistry.n(jsonObject.get("priority").getAsString());
        }
    }

    private void N() {
        this.m = null;
        this.v = null;
    }

    public AbstractInventoryFilterRule() {
        this.H = new ItemFilterSelection();
    }

    public static void u(int[] nArray) {
        f = nArray;
    }

    public JsonObject M(boolean bl) {
        InventoryItemCategory inventoryItemCategory;
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("itemFilter", this.H.Q());
        InventoryFilterPreset inventoryFilterPreset = this.m;
        if (bl || inventoryFilterPreset != null) {
            if (inventoryFilterPreset == null) {
                inventoryFilterPreset = this.W();
            }
            if (inventoryFilterPreset != null) {
                jsonObject.add("customRule", (JsonElement)inventoryFilterPreset.K());
            }
        } else if (this.v != null) {
            jsonObject.addProperty("customRule", this.v.toString());
        }
        if ((inventoryItemCategory = this.n) != null) {
            jsonObject.addProperty("priority", inventoryItemCategory.F());
        }
        return jsonObject;
    }


    @Nullable
    protected InventoryFilterPreset J() {
        return this.m;
    }

    @Override
    public void y() {
        this.H.G(ItemPickerSelection.B());
        this.U();
    }
}

