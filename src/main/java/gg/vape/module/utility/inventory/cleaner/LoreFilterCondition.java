package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.AbstractTextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public class LoreFilterCondition
extends AbstractTextFilterCondition<LoreFilterCondition> {
    @Override
    public boolean matches(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        List<String> list = itemStack.z();
        for (String string : list) {
            if (!this.getMatchMode().matchesAny(string, this.getTexts())) continue;
            return true;
        }
        return false;
    }


    public LoreFilterCondition() {
    }

    @Override
    public InventoryFilterConditionType getType() {
        return InventoryFilterConditionType.LORE;
    }

    public LoreFilterCondition(JsonObject jsonObject) {
        super(jsonObject);
    }

    public LoreFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        super(list, textMatchMode);
    }

    public LoreFilterCondition copy() {
        return new LoreFilterCondition(this.getTexts(), this.getMatchMode());
    }
}
