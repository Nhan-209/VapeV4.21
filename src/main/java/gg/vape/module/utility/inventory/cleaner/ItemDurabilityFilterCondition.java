package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.DurabilityValueMode;
import gg.vape.module.utility.inventory.cleaner.DurabilityValueModeSwitchMap;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.wrapper.impl.ItemStack;

public class ItemDurabilityFilterCondition
implements NumericFilterCondition<ItemDurabilityFilterCondition> {
    @Override
    public ItemDurabilityFilterCondition Q(String string) throws NumberFormatException {
        return this.g(string);
    }

    @Override
    public ItemDurabilityFilterCondition J(ComparisonOperator operator) {
        return this.N(operator);
    }

    @Override
    public ItemDurabilityFilterCondition w() {
        return this.B();
    }
    private int T;
    private DurabilityValueMode C;
    private ComparisonOperator n = ComparisonOperator.EQUALS;

    public int Y() {
        return this.T;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        int n = itemStack.getItem().a();
        int n2 = 0;
        switch (DurabilityValueModeSwitchMap.o[this.C.ordinal()]) {
            case 1: {
                n2 = (int)((double)(n - itemStack.L()) / (double)n * 100.0);
                break;
            }
            case 2: {
                n2 = n - itemStack.L();
            }
        }
        return this.n.p(n2, this.T);
    }

    @Override
    public ComparisonOperator p() {
        return this.n;
    }

    public ItemDurabilityFilterCondition B() {
        return new ItemDurabilityFilterCondition(this.T, this.n, this.C);
    }

    public DurabilityValueMode W() {
        return this.C;
    }

    public ItemDurabilityFilterCondition N(ComparisonOperator comparisonOperator) {
        this.n = comparisonOperator;
        return this;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("durabilityMode", this.C.getName());
        jsonObject.addProperty("damage", (Number)this.T);
        return jsonObject;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    public ItemDurabilityFilterCondition g(String string) throws NumberFormatException {
        this.T = Integer.parseInt(string);
        return this;
    }

    public ItemDurabilityFilterCondition(JsonObject jsonObject) {
        this.C = DurabilityValueMode.PERCENTAGE;
        this.T = jsonObject.get("damage").getAsInt();
        this.n = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public ItemDurabilityFilterCondition(int n, ComparisonOperator comparisonOperator, DurabilityValueMode durabilityValueMode) {
        this.C = DurabilityValueMode.PERCENTAGE;
        this.T = n;
        this.n = comparisonOperator;
        this.C = durabilityValueMode;
    }

    public ItemDurabilityFilterCondition() {
        this.C = DurabilityValueMode.PERCENTAGE;
    }

    public ItemDurabilityFilterCondition y(int n) {
        this.T = n;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.T);
    }

    public ItemDurabilityFilterCondition m(DurabilityValueMode durabilityValueMode) {
        this.C = durabilityValueMode;
        return this;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.ITEM_DURABILITY;
    }
}
