package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.AbstractTextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public class ItemNameFilterCondition
extends AbstractTextFilterCondition<ItemNameFilterCondition> {
    @Override
    public ItemNameFilterCondition H() {
        return this.copy();
    }
    public ItemNameFilterCondition(JsonObject jsonObject) {
        super(jsonObject);
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ItemNameFilterCondition() {
    }

    public ItemNameFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        super(list, textMatchMode);
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        return this.M().M(itemStack.getItem().getItemStackDisplayName(itemStack), this.M$src$Ljava_util_List_$bgq9xa());
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.ITEM_NAME;
    }

    public ItemNameFilterCondition copy() {
        return new ItemNameFilterCondition(this.M$src$Ljava_util_List_$bgq9xa(), this.M());
    }
}
