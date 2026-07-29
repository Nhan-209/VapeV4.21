package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.wrapper.impl.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ItemFilterSelection
implements Cloneable {
    @Nullable
    private String itemName;
    @Nullable
    private transient InventoryItemMatcher matcher;
    private transient boolean dirty = true;
    private static final String ITEMS_KEY = "items";
    @Nullable
    private transient ItemStack resolvedStack;

    public boolean matches(ItemStack itemStack) {
        String name = this.itemName;
        if (name == null) {
            return false;
        }
        InventoryItemMatcher inventoryItemMatcher = this.getMatcher();
        if (inventoryItemMatcher != null && inventoryItemMatcher.matches(itemStack, itemStack.getItem())) {
            return true;
        }
        if (itemStack.isNull()) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().resolve(itemStack);
        return itemMappingEntry != null && name.equals(itemMappingEntry.M());
    }

    @Nullable
    public ItemStack getItemStack() {
        this.ensureResolved();
        return this.resolvedStack;
    }

    private void resolve() {
        String normalized;
        this.matcher = null;
        this.resolvedStack = null;
        String normalized2 = normalized = this.itemName != null ? this.itemName.trim().toLowerCase() : null;
        if (normalized == null || normalized.isEmpty()) {
            return;
        }
        this.matcher = InventoryItemMatcherRegistry.getByName(normalized);
        if (this.matcher != null) {
            this.resolvedStack = null;
            return;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().findByName(normalized);
        if (itemMappingEntry != null) {
            this.resolvedStack = itemMappingEntry.Q();
            if (this.resolvedStack == null || this.resolvedStack.isNull()) {
                // empty if block
            }
        }
    }

    public JsonElement toJson() {
        return this.itemName != null ? new JsonPrimitive(this.itemName) : null;
    }

    public boolean isEmpty() {
        return this.itemName == null;
    }

    @Nullable
    public String getMatcherGroupName() {
        this.ensureResolved();
        return this.matcher != null ? this.matcher.getIconName() : null;
    }

    public ItemFilterSelection copy() {
        ItemFilterSelection itemFilterSelection = new ItemFilterSelection();
        itemFilterSelection.setItemName(this.itemName);
        return itemFilterSelection;
    }

    public boolean isUnresolved() {
        if (this.getMatcherGroupName() != null) {
            return false;
        }
        ItemStack itemStack = this.getItemStack();
        return (itemStack == null || itemStack.isNull()) && this.itemName != null;
    }

    private void setItemName(@Nullable String string) {
        this.assign(string, true);
    }

    @Nullable
    public InventoryItemMatcher getMatcher() {
        this.ensureResolved();
        return this.matcher;
    }

    public void setSelection(@Nullable ItemPickerSelection<String, ItemMappingEntry> itemPickerSelection) {
        if (itemPickerSelection == null || itemPickerSelection.getLeft() == null && itemPickerSelection.getRight() == null) {
            this.setItemName(null);
        } else if (itemPickerSelection.getLeft() != null) {
            this.setItemName(itemPickerSelection.getLeft());
        } else if (itemPickerSelection.getRight() != null) {
            this.setItemName(itemPickerSelection.getRight().M());
        }
    }

    private void assign(@Nullable String string, boolean resolveNow) {
        this.itemName = string;
        this.matcher = null;
        this.resolvedStack = null;
        this.dirty = true;
        if (resolveNow) {
            this.ensureResolved();
        }
    }

    private void ensureResolved() {
        if (!this.dirty) {
            return;
        }
        this.dirty = false;
        try {
            this.resolve();
        }
        catch (Throwable throwable) {
            this.matcher = null;
            this.resolvedStack = null;
        }
    }

    public ItemFilterSelection() {
    }

    @Nullable
    public String getItemName() {
        return this.itemName;
    }

    public ItemFilterSelection(JsonElement jsonElement) {
        if (jsonElement instanceof JsonObject) {
            int index = 0;
            JsonArray jsonArray = ((JsonObject)jsonElement).getAsJsonArray(ITEMS_KEY);
            if (index < jsonArray.size()) {
                this.assign(jsonArray.get(index).getAsString(), false);
            }
        } else if (jsonElement instanceof JsonPrimitive) {
            this.assign(jsonElement.getAsString(), false);
        }
    }

}

