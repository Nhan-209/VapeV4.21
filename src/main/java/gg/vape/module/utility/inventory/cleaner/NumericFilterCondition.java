package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;

public interface NumericFilterCondition<T extends InventoryFilterCondition<T>>
extends InventoryFilterCondition<T> {
    public static final String i = "operator";

    public T J(ComparisonOperator var1);

    public ComparisonOperator p();

    public T Q(String var1) throws NumberFormatException;

    public String k();

    @Override
    default public JsonObject L() {
        JsonObject jsonObject = InventoryFilterCondition.super.L();
        jsonObject.addProperty(i, this.p().getName());
        return jsonObject;
    }
}

