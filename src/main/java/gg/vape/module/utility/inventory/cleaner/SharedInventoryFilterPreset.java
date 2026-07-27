package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class SharedInventoryFilterPreset
extends InventoryFilterPreset {
    public SharedInventoryFilterPreset(@Nullable UUID uUID, String string) {
        super(uUID, string);
    }

    public SharedInventoryFilterPreset copy() {
        SharedInventoryFilterPreset copy = new SharedInventoryFilterPreset(this.j(), this.getName());
        for (InventoryFilterConditionGroup inventoryFilterConditionGroup : this.z()) {
            copy.x(inventoryFilterConditionGroup.A());
        }
        return copy;
    }

    public SharedInventoryFilterPreset(JsonObject jsonObject) {
        super(jsonObject);
    }

    public SharedInventoryFilterPreset(InventoryFilterPreset preset) {
        super(preset.K());
        this.h = UUID.randomUUID();
    }
}

