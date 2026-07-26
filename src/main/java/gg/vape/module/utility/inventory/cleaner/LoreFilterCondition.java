package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.AbstractTextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ItemStack;
import java.util.List;

public class LoreFilterCondition
extends AbstractTextFilterCondition<LoreFilterCondition> {
    @Override
    public LoreFilterCondition H() {
        return this.x();
    }
    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        List<String> list = itemStack.z();
        for (String string : list) {
            if (!this.M().M(string, this.M$src$Ljava_util_List_$bgq9xa())) continue;
            return true;
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public LoreFilterCondition() {
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.LORE;
    }

    public LoreFilterCondition(JsonObject jsonObject) {
        super(jsonObject);
    }

    public LoreFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        super(list, textMatchMode);
    }

    public LoreFilterCondition x() {
        return new LoreFilterCondition(this.M$src$Ljava_util_List_$bgq9xa(), this.M());
    }
}
