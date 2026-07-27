package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.wrapper.impl.ItemStack;

public class QuantityFilterCondition
implements NumericFilterCondition<QuantityFilterCondition> {
    @Override
    public QuantityFilterCondition Q(String string) throws NumberFormatException {
        return this.parseAmount(string);
    }

    @Override
    public QuantityFilterCondition J(ComparisonOperator operator) {
        return this.withOperator(operator);
    }

    @Override
    public QuantityFilterCondition w() {
        return this.copy();
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
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        return this.operator.p(itemStack.t(), this.amount);
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
    public ComparisonOperator p() {
        return this.operator;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.QUANTITY;
    }

    private static NumberFormatException passthrough(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("amount", (Number)this.amount);
        return jsonObject;
    }

    public QuantityFilterCondition(int n, ComparisonOperator comparisonOperator) {
        this.amount = n;
        this.operator = comparisonOperator;
    }

    public QuantityFilterCondition(JsonObject jsonObject) {
        this.amount = jsonObject.get("amount").getAsInt();
        this.operator = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public QuantityFilterCondition copy() {
        return new QuantityFilterCondition(this.amount, this.operator);
    }

    @Override
    public String k() {
        return String.valueOf(this.amount);
    }
}
