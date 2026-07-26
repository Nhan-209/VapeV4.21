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

    public SharedInventoryFilterPreset e() {
        SharedInventoryFilterPreset pV = new SharedInventoryFilterPreset(this.j(), this.getName());
        for (InventoryFilterConditionGroup inventoryFilterConditionGroup : this.z()) {
            pV.x(inventoryFilterConditionGroup.A());
        }
        return pV;
    }

    public SharedInventoryFilterPreset(JsonObject jsonObject) {
        super(jsonObject);
    }

    public SharedInventoryFilterPreset(InventoryFilterPreset pJ) {
        super(pJ.K());
        this.h = UUID.randomUUID();
    }
}

