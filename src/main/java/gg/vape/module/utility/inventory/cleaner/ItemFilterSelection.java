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
    private String y;
    @Nullable
    private transient InventoryItemMatcher n;
    private transient boolean G = true;
    private static final String b = "items";
    @Nullable
    private transient ItemStack f;

    public boolean h(ItemStack itemStack) {
        String string = this.y;
        if (string == null) {
            return false;
        }
        InventoryItemMatcher inventoryItemMatcher = this.A();
        if (inventoryItemMatcher != null && inventoryItemMatcher.g(itemStack, itemStack.getItem())) {
            return true;
        }
        if (itemStack.isNull()) {
            return false;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        return itemMappingEntry != null && string.equals(itemMappingEntry.M());
    }

    @Nullable
    public ItemStack E() {
        this.r();
        return this.f;
    }

    private void I() {
        String string;
        this.n = null;
        this.f = null;
        String string2 = string = this.y != null ? this.y.trim().toLowerCase() : null;
        if (string == null || string.isEmpty()) {
            return;
        }
        this.n = InventoryItemMatcherRegistry.z(string);
        if (this.n != null) {
            this.f = null;
            return;
        }
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().b(string);
        if (itemMappingEntry != null) {
            this.f = itemMappingEntry.Q();
            if (this.f == null || this.f.isNull()) {
                // empty if block
            }
        }
    }

    public JsonElement Q() {
        return this.y != null ? new JsonPrimitive(this.y) : null;
    }

    public boolean j() {
        return this.y == null;
    }

    @Nullable
    public String V() {
        this.r();
        return this.n != null ? this.n.Z() : null;
    }

    public ItemFilterSelection y() {
        ItemFilterSelection itemFilterSelection = new ItemFilterSelection();
        itemFilterSelection.I(this.y);
        return itemFilterSelection;
    }

    public boolean i() {
        if (this.V() != null) {
            return false;
        }
        ItemStack itemStack = this.E();
        return (itemStack == null || itemStack.isNull()) && this.y != null;
    }

    private void I(@Nullable String string) {
        this.U(string, true);
    }

    @Nullable
    public InventoryItemMatcher c() {
        this.r();
        return this.n;
    }

    public void G(@Nullable ItemPickerSelection<String, ItemMappingEntry> itemPickerSelection) {
        if (itemPickerSelection == null || itemPickerSelection.N() == null && itemPickerSelection.X() == null) {
            this.I(null);
        } else if (itemPickerSelection.N() != null) {
            this.I(itemPickerSelection.N());
        } else if (itemPickerSelection.X() != null) {
            this.I(itemPickerSelection.X().M());
        }
    }

    private void U(@Nullable String string, boolean bl) {
        this.y = string;
        this.n = null;
        this.f = null;
        this.G = true;
        if (bl) {
            this.r();
        }
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    private void r() {
        if (!this.G) {
            return;
        }
        this.G = false;
        try {
            this.I();
        }
        catch (Throwable throwable) {
            this.n = null;
            this.f = null;
        }
    }

    public ItemFilterSelection() {
    }

    @Nullable
    public String J() {
        return this.y;
    }

    public ItemFilterSelection(JsonElement jsonElement) {
        if (jsonElement instanceof JsonObject) {
            int n = 0;
            JsonArray jsonArray = ((JsonObject)jsonElement).getAsJsonArray(b);
            if (n < jsonArray.size()) {
                this.U(jsonArray.get(n).getAsString(), false);
            }
        } else if (jsonElement instanceof JsonPrimitive) {
            this.U(jsonElement.getAsString(), false);
        }
    }

    @Nullable
    private InventoryItemMatcher A() {
        this.r();
        return this.n;
    }
}

