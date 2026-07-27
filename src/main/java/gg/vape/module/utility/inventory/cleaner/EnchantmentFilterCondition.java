package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.EnchantmentFilterMode;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.utils.StringUtils;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Map;

public class EnchantmentFilterCondition
implements NumericFilterCondition<EnchantmentFilterCondition> {
    @Override
    public EnchantmentFilterCondition Q(String string) throws NumberFormatException {
        return this.j(string);
    }

    @Override
    public EnchantmentFilterCondition J(ComparisonOperator operator) {
        return this.D(operator);
    }

    @Override
    public EnchantmentFilterCondition w() {
        return this.h();
    }
    private EnchantmentFilterMode mode = EnchantmentFilterMode.HAS;
    private int level = 1;
    private String enchantment;
    private ComparisonOperator operator = ComparisonOperator.EQUALS;

    public EnchantmentFilterMode v() {
        return this.mode;
    }

    public EnchantmentFilterCondition l(String string) {
        this.enchantment = string;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.level);
    }

    public EnchantmentFilterCondition y(int n) {
        this.level = n;
        return this;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (this.enchantment == null) {
            return false;
        }
        if (itemStack.isNull()) {
            return false;
        }
        Map<Enchantment, Short> map = EnchantmentUtil.A(itemStack);
        if (map.isEmpty()) {
            return false;
        }
        for (Map.Entry<Enchantment, Short> entry : map.entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (!StringUtils.Q(enchantment.getTranslatedName(1)).equalsIgnoreCase(this.enchantment)) continue;
            if (this.mode == EnchantmentFilterMode.HAS) {
                return true;
            }
            if (this.mode != EnchantmentFilterMode.LEVEL) continue;
            return this.operator.p(entry.getValue().shortValue(), this.level);
        }
        return false;
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.ENCHANTMENT;
    }

    public EnchantmentFilterCondition() {
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("mode", this.mode.getName());
        jsonObject.addProperty("enchantment", this.enchantment);
        jsonObject.addProperty("level", (Number)this.level);
        return jsonObject;
    }

    public String A() {
        return this.enchantment;
    }

    @Override
    public ComparisonOperator p() {
        return this.operator;
    }

    private static NumberFormatException rethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    public EnchantmentFilterCondition j(String string) throws NumberFormatException {
        this.level = Integer.parseInt(string);
        return this;
    }

    public EnchantmentFilterCondition(JsonObject jsonObject) {
        this.mode = EnchantmentFilterMode.V(ConfigJsonUtils.P(jsonObject, "mode"));
        this.enchantment = ConfigJsonUtils.P(jsonObject, "enchantment");
        Integer n = ConfigJsonUtils.r(jsonObject, "level");
        this.level = n != null ? n : 1;
        this.operator = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public EnchantmentFilterCondition M(EnchantmentFilterMode enchantmentFilterMode) {
        this.mode = enchantmentFilterMode;
        return this;
    }

    public int z() {
        return this.level;
    }

    public EnchantmentFilterCondition h() {
        return new EnchantmentFilterCondition(this.mode, this.enchantment, this.level, this.operator);
    }

    public EnchantmentFilterCondition(EnchantmentFilterMode enchantmentFilterMode, String string, int n, ComparisonOperator comparisonOperator) {
        this.mode = enchantmentFilterMode;
        this.enchantment = string;
        this.level = n;
        this.operator = comparisonOperator;
    }

    public EnchantmentFilterCondition D(ComparisonOperator comparisonOperator) {
        this.operator = comparisonOperator;
        return this;
    }
}
