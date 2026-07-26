package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.wrapper.impl.ItemStack;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public interface InventoryFilterCondition<T extends InventoryFilterCondition<?>>
extends Cloneable {
    public static final String[] h = new String[2];
    public static final Map j = new HashMap(13);
    public static final String[] f = null;

    @Nullable
    default public JsonObject L() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", this.K().name());
        return jsonObject;
    }

    public boolean g(ItemStack var1);

    public InventoryFilterConditionType K();

    public T w();

    public static InventoryFilterCondition<?> h(JsonObject jsonObject) {
        InventoryFilterConditionType inventoryFilterConditionType = InventoryFilterConditionType.valueOf(jsonObject.get("type").getAsString());
        return inventoryFilterConditionType.L().apply(jsonObject);
    }
}
