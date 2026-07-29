package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.wrapper.impl.ItemStack;

public class QuantityFilterCondition
implements NumericFilterCondition<QuantityFilterCondition> {
    @Override
    public QuantityFilterCondition parseValue(String string) throws NumberFormatException {
        return this.parseAmount(string);
    }

    private int amount = 1;
    private ComparisonOperator operator = ComparisonOperator.EQUALS;

    public QuantityFilterCondition parseAmount(String string) {
        this.amount = Integer.parseInt(string);
        return this;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public boolean matches(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        return this.operator.compare(itemStack.t(), this.amount);
    }

    public QuantityFilterCondition() {
    }

    public QuantityFilterCondition withOperator(ComparisonOperator comparisonOperator) {
        this.operator = comparisonOperator;
        return this;
    }

    public QuantityFilterCondition withAmount(int n) {
        this.amount = n;
        return this;
    }

    @Override
    public ComparisonOperator getOperator() {
        return this.operator;
    }

    @Override
    public InventoryFilterConditionType getType() {
        return InventoryFilterConditionType.QUANTITY;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = NumericFilterCondition.super.toJson();
        jsonObject.addProperty("amount", (Number)this.amount);
        return jsonObject;
    }

    public QuantityFilterCondition(int n, ComparisonOperator comparisonOperator) {
        this.amount = n;
        this.operator = comparisonOperator;
    }

    public QuantityFilterCondition(JsonObject jsonObject) {
        this.amount = jsonObject.get("amount").getAsInt();
        this.operator = ComparisonOperator.fromName(jsonObject.get("operator").getAsString());
    }

    public QuantityFilterCondition copy() {
        return new QuantityFilterCondition(this.amount, this.operator);
    }

    @Override
    public String getValueText() {
        return String.valueOf(this.amount);
    }
}
