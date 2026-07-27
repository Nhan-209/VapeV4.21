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
    public DisplayNameFilterCondition H() {
        return this.E();
    }
    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.DISPLAY_NAME;
    }

    public DisplayNameFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        super(list, textMatchMode);
    }

    public DisplayNameFilterCondition(JsonObject jsonObject) {
        super(jsonObject);
    }


    public DisplayNameFilterCondition E() {
        return new DisplayNameFilterCondition(this.M$src$Ljava_util_List_$bgq9xa(), this.M());
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        String string = itemStack.x();
        return this.M().M(string, this.M$src$Ljava_util_List_$bgq9xa());
    }

    public DisplayNameFilterCondition() {
    }
}
