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
    public AttackDamageFilterCondition Q(String string) throws NumberFormatException {
        return this.R(string);
    }

    @Override
    public AttackDamageFilterCondition J(ComparisonOperator operator) {
        return this.F(operator);
    }

    @Override
    public AttackDamageFilterCondition w() {
        return this.j();
    }
    private int g;
    private ComparisonOperator F = ComparisonOperator.EQUALS;

    public AttackDamageFilterCondition(JsonObject jsonObject) {
        this.g = jsonObject.get("damage").getAsInt();
        this.F = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public int Z() {
        return this.g;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    public AttackDamageFilterCondition(int n, ComparisonOperator comparisonOperator) {
        this.g = n;
        this.F = comparisonOperator;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.ATTACK_DAMAGE;
    }

    @Override
    public ComparisonOperator p() {
        return this.F;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("damage", (Number)this.g);
        return jsonObject;
    }

    public AttackDamageFilterCondition R(String string) {
        this.g = Integer.parseInt(string);
        return this;
    }

    public AttackDamageFilterCondition F(ComparisonOperator comparisonOperator) {
        this.F = comparisonOperator;
        return this;
    }

    @Override
    public boolean g(ItemStack itemStack) {
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
        return this.F.A(f, this.g);
    }

    @Override
    public String k() {
        return String.valueOf(this.g);
    }

    public AttackDamageFilterCondition O(int n) {
        this.g = n;
        return this;
    }

    public AttackDamageFilterCondition j() {
        return new AttackDamageFilterCondition(this.g, this.F);
    }

    public AttackDamageFilterCondition() {
    }
}
