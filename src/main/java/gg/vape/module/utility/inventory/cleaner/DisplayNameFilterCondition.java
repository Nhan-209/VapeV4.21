package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.AbstractTextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public class DisplayNameFilterCondition
extends AbstractTextFilterCondition<DisplayNameFilterCondition> {
    @Override
    public DisplayNameFilterCondition copy() {
        return new DisplayNameFilterCondition(this.getTexts(), this.getMatchMode());
    }

    @Override
    public InventoryFilterConditionType getType() {
        return InventoryFilterConditionType.DISPLAY_NAME;
    }

    public DisplayNameFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        super(list, textMatchMode);
    }

    public DisplayNameFilterCondition(JsonObject jsonObject) {
        super(jsonObject);
    }


    public boolean matches(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        String string = itemStack.x();
        return this.getMatchMode().matchesAny(string, this.getTexts());
    }

    public DisplayNameFilterCondition() {
    }
}
