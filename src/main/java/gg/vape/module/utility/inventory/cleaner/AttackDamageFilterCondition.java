package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemAttributeModifiers;
import gg.vape.wrapper.impl.ItemStack;

public class AttackDamageFilterCondition
implements NumericFilterCondition<AttackDamageFilterCondition> {
    @Override
    public AttackDamageFilterCondition parseValue(String value) throws NumberFormatException {
        this.damage = Integer.parseInt(value);
        return this;
    }

    @Override
    public AttackDamageFilterCondition withOperator(ComparisonOperator operator) {
        this.operator = operator;
        return this;
    }

    @Override
    public AttackDamageFilterCondition copy() {
        return new AttackDamageFilterCondition(this.damage, this.operator);
    }
    private int damage;
    private ComparisonOperator operator = ComparisonOperator.EQUALS;

    public AttackDamageFilterCondition(JsonObject jsonObject) {
        this.damage = jsonObject.get("damage").getAsInt();
        this.operator = ComparisonOperator.fromName(jsonObject.get("operator").getAsString());
    }

    public int getDamage() {
        return this.damage;
    }

    public AttackDamageFilterCondition(int n, ComparisonOperator comparisonOperator) {
        this.damage = n;
        this.operator = comparisonOperator;
    }

    @Override
    public InventoryFilterConditionType getType() {
        return InventoryFilterConditionType.ATTACK_DAMAGE;
    }

    @Override
    public ComparisonOperator getOperator() {
        return this.operator;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = NumericFilterCondition.super.toJson();
        jsonObject.addProperty("damage", (Number)this.damage);
        return jsonObject;
    }

    @Override
    public boolean matches(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        float f = 0.0f;
        ItemAttributeModifiers itemAttributeModifiers = itemStack.o();
        if (itemAttributeModifiers.i() > 0) {
            int n = ForgeVersion.MC_1_12_2.L() ? 1 : 0;
            AttributeModifier attributeModifier = new AttributeModifier(itemAttributeModifiers.f().toArray()[n]);
            f += (float)attributeModifier.getAmount();
        }
        return this.operator.compare(f, this.damage);
    }

    @Override
    public String getValueText() {
        return String.valueOf(this.damage);
    }

    public AttackDamageFilterCondition withDamage(int n) {
        this.damage = n;
        return this;
    }

    public AttackDamageFilterCondition() {
    }
}
