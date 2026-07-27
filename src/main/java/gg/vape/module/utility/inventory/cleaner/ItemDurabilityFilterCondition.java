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
        return this.parseDamage(string);
    }

    @Override
    public ItemDurabilityFilterCondition J(ComparisonOperator operator) {
        return this.withOperator(operator);
    }

    @Override
    public ItemDurabilityFilterCondition w() {
        return this.copy();
    }
    private int damage;
    private DurabilityValueMode valueMode;
    private ComparisonOperator operator = ComparisonOperator.EQUALS;

    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        int maxDurability = itemStack.getItem().a();
        int durabilityValue = 0;
        switch (DurabilityValueModeSwitchMap.o[this.valueMode.ordinal()]) {
            case 1: {
                durabilityValue = (int)((double)(maxDurability - itemStack.L()) / (double)maxDurability * 100.0);
                break;
            }
            case 2: {
                durabilityValue = maxDurability - itemStack.L();
            }
        }
        return this.operator.p(durabilityValue, this.damage);
    }

    @Override
    public ComparisonOperator p() {
        return this.operator;
    }

    public ItemDurabilityFilterCondition copy() {
        return new ItemDurabilityFilterCondition(this.damage, this.operator, this.valueMode);
    }

    public DurabilityValueMode W() {
        return this.valueMode;
    }

    public ItemDurabilityFilterCondition withOperator(ComparisonOperator comparisonOperator) {
        this.operator = comparisonOperator;
        return this;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("durabilityMode", this.valueMode.getName());
        jsonObject.addProperty("damage", (Number)this.damage);
        return jsonObject;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    public ItemDurabilityFilterCondition parseDamage(String string) throws NumberFormatException {
        this.damage = Integer.parseInt(string);
        return this;
    }

    public ItemDurabilityFilterCondition(JsonObject jsonObject) {
        this.valueMode = DurabilityValueMode.PERCENTAGE;
        this.damage = jsonObject.get("damage").getAsInt();
        this.operator = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public ItemDurabilityFilterCondition(int damage, ComparisonOperator comparisonOperator, DurabilityValueMode durabilityValueMode) {
        this.valueMode = DurabilityValueMode.PERCENTAGE;
        this.damage = damage;
        this.operator = comparisonOperator;
        this.valueMode = durabilityValueMode;
    }

    public ItemDurabilityFilterCondition() {
        this.valueMode = DurabilityValueMode.PERCENTAGE;
    }

    public ItemDurabilityFilterCondition withDamage(int damage) {
        this.damage = damage;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.damage);
    }

    public ItemDurabilityFilterCondition m(DurabilityValueMode durabilityValueMode) {
        this.valueMode = durabilityValueMode;
        return this;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.ITEM_DURABILITY;
    }
}
