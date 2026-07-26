package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroupBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryFilterConditionGroup
implements Cloneable {
    private final List<InventoryFilterCondition<?>> J = new ArrayList();

    public void L(InventoryFilterCondition<?> inventoryFilterCondition, InventoryFilterCondition<?> inventoryFilterCondition2) {
        int n = this.J.indexOf(inventoryFilterCondition);
        if (n != -1) {
            this.J.add(n, inventoryFilterCondition2);
        } else {
            this.J.add(inventoryFilterCondition2);
        }
        this.J.remove(inventoryFilterCondition);
    }

    public @UnmodifiableView List<InventoryFilterCondition<?>> c() {
        return this.J;
    }

    public void j(InventoryFilterCondition<?> inventoryFilterCondition) {
        this.J.remove(inventoryFilterCondition);
    }

    public InventoryFilterConditionGroup(JsonObject jsonObject) {
        JsonArray jsonArray = jsonObject.getAsJsonArray("conditions");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
            this.J.add(InventoryFilterCondition.h(jsonObject2));
        }
    }

    InventoryFilterConditionGroup() {
    }

    static List i(InventoryFilterConditionGroup inventoryFilterConditionGroup) {
        return inventoryFilterConditionGroup.J;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public JsonObject g() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.add("conditions", (JsonElement)jsonArray);
        for (InventoryFilterCondition<?> inventoryFilterCondition : this.J) {
            JsonObject jsonObject2 = inventoryFilterCondition.L();
            if (jsonObject2 == null) continue;
            jsonArray.add((JsonElement)jsonObject2);
        }
        return jsonObject;
    }

    public static InventoryFilterConditionGroupBuilder w() {
        return new InventoryFilterConditionGroupBuilder();
    }

    public void O(InventoryFilterCondition<?> inventoryFilterCondition) {
        this.J.add(inventoryFilterCondition);
    }

    public InventoryFilterConditionGroup A() {
        InventoryFilterConditionGroupBuilder inventoryFilterConditionGroupBuilder = InventoryFilterConditionGroup.w();
        for (InventoryFilterCondition<?> inventoryFilterCondition : this.J) {
            inventoryFilterConditionGroupBuilder.O((InventoryFilterCondition<?>)inventoryFilterCondition.w());
        }
        return inventoryFilterConditionGroupBuilder.w();
    }

    public boolean u(ItemStack itemStack) {
        for (InventoryFilterCondition<?> inventoryFilterCondition : this.J) {
            if (inventoryFilterCondition.g(itemStack)) continue;
            return false;
        }
        return true;
    }
}

