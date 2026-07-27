package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterAction;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ItemInventoryFilterRule
extends AbstractInventoryFilterRule {
    private InventoryFilterAction filterAction = InventoryFilterAction.REMOVE;

    public void S(InventoryFilterAction inventoryFilterAction) {
        this.filterAction = inventoryFilterAction;
    }

    public ItemInventoryFilterRule() {
    }

    public InventoryFilterAction K() {
        return this.filterAction;
    }

    @Override
    public JsonObject M(boolean bl) {
        JsonObject jsonObject = super.M(bl);
        jsonObject.addProperty("filterAction", this.filterAction.getName());
        return jsonObject;
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
        this.filterAction = InventoryFilterAction.c(jsonObject.get("filterAction").getAsString());
    }
}

