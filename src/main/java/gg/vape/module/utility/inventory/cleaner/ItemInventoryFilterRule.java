package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterAction;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ItemInventoryFilterRule
extends AbstractInventoryFilterRule {
    private InventoryFilterAction h = InventoryFilterAction.REMOVE;

    public void S(InventoryFilterAction inventoryFilterAction) {
        this.h = inventoryFilterAction;
    }

    public ItemInventoryFilterRule() {
    }

    public InventoryFilterAction K() {
        return this.h;
    }

    @Override
    public JsonObject M(boolean bl) {
        JsonObject jsonObject = super.M(bl);
        jsonObject.addProperty("filterAction", this.h.getName());
        return jsonObject;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    @Nullable
    public InventoryFilterPreset W() {
        UUID uUID = this.t();
        if (uUID != null) {
            return Vape.INSTANCE.getInventoryFilterPresetRegistry().r().l(uUID);
        }
        return this.J();
    }

    public ItemInventoryFilterRule(JsonObject jsonObject) {
        super(jsonObject);
        this.h = InventoryFilterAction.c(jsonObject.get("filterAction").getAsString());
    }
}

