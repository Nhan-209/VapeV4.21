package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import java.util.ArrayList;
import java.util.List;

public class InventoryFilterConditionGroupBuilder {
    private final List<InventoryFilterCondition<?>> P = new ArrayList();

    public InventoryFilterConditionGroupBuilder O(InventoryFilterCondition<?> inventoryFilterCondition) {
        this.P.add(inventoryFilterCondition);
        return this;
    }

    public InventoryFilterConditionGroup w() {
        InventoryFilterConditionGroup inventoryFilterConditionGroup = new InventoryFilterConditionGroup();
        InventoryFilterConditionGroup.i(inventoryFilterConditionGroup).addAll(this.P);
        return inventoryFilterConditionGroup;
    }
}

