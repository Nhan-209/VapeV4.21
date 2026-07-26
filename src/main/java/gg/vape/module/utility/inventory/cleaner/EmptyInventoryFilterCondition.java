package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.wrapper.impl.ItemStack;

public class EmptyInventoryFilterCondition
implements InventoryFilterCondition<EmptyInventoryFilterCondition> {
    @Override
    public EmptyInventoryFilterCondition w() {
        return this.Q();
    }
    @Override
    public JsonObject L() {
        return null;
    }

    @Override
    public InventoryFilterConditionType K() {
        return null;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        return false;
    }

    public EmptyInventoryFilterCondition Q() {
        return new EmptyInventoryFilterCondition();
    }
}
