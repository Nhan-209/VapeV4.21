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
        return this.s(string);
    }

    @Override
    public QuantityFilterCondition J(ComparisonOperator operator) {
        return this.Y(operator);
    }

    @Override
    public QuantityFilterCondition w() {
        return this.G();
    }
    private int W = 1;
    private ComparisonOperator M = ComparisonOperator.EQUALS;

    public QuantityFilterCondition s(String string) {
        this.W = Integer.parseInt(string);
        return this;
    }

    public int A() {
        return this.W;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        return this.M.p(itemStack.t(), this.W);
    }

    public QuantityFilterCondition() {
    }

    public QuantityFilterCondition Y(ComparisonOperator comparisonOperator) {
        this.M = comparisonOperator;
        return this;
    }

    public QuantityFilterCondition C(int n) {
        this.W = n;
        return this;
    }

    @Override
    public ComparisonOperator p() {
        return this.M;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.QUANTITY;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("amount", (Number)this.W);
        return jsonObject;
    }

    public QuantityFilterCondition(int n, ComparisonOperator comparisonOperator) {
        this.W = n;
        this.M = comparisonOperator;
    }

    public QuantityFilterCondition(JsonObject jsonObject) {
        this.W = jsonObject.get("amount").getAsInt();
        this.M = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public QuantityFilterCondition G() {
        return new QuantityFilterCondition(this.W, this.M);
    }

    @Override
    public String k() {
        return String.valueOf(this.W);
    }
}
