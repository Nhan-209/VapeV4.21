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
    private EnchantmentFilterMode W = EnchantmentFilterMode.HAS;
    private int b = 1;
    private String G;
    private ComparisonOperator C = ComparisonOperator.EQUALS;

    public EnchantmentFilterMode v() {
        return this.W;
    }

    public EnchantmentFilterCondition l(String string) {
        this.G = string;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.b);
    }

    public EnchantmentFilterCondition y(int n) {
        this.b = n;
        return this;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (this.G == null) {
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
            if (!StringUtils.Q(enchantment.getTranslatedName(1)).equalsIgnoreCase(this.G)) continue;
            if (this.W == EnchantmentFilterMode.HAS) {
                return true;
            }
            if (this.W != EnchantmentFilterMode.LEVEL) continue;
            return this.C.p(entry.getValue().shortValue(), this.b);
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
        jsonObject.addProperty("mode", this.W.getName());
        jsonObject.addProperty("enchantment", this.G);
        jsonObject.addProperty("level", (Number)this.b);
        return jsonObject;
    }

    public String A() {
        return this.G;
    }

    @Override
    public ComparisonOperator p() {
        return this.C;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    public EnchantmentFilterCondition j(String string) throws NumberFormatException {
        this.b = Integer.parseInt(string);
        return this;
    }

    public EnchantmentFilterCondition(JsonObject jsonObject) {
        this.W = EnchantmentFilterMode.V(ConfigJsonUtils.P(jsonObject, "mode"));
        this.G = ConfigJsonUtils.P(jsonObject, "enchantment");
        Integer n = ConfigJsonUtils.r(jsonObject, "level");
        this.b = n != null ? n : 1;
        this.C = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public EnchantmentFilterCondition M(EnchantmentFilterMode enchantmentFilterMode) {
        this.W = enchantmentFilterMode;
        return this;
    }

    public int z() {
        return this.b;
    }

    public EnchantmentFilterCondition h() {
        return new EnchantmentFilterCondition(this.W, this.G, this.b, this.C);
    }

    public EnchantmentFilterCondition(EnchantmentFilterMode enchantmentFilterMode, String string, int n, ComparisonOperator comparisonOperator) {
        this.W = enchantmentFilterMode;
        this.G = string;
        this.b = n;
        this.C = comparisonOperator;
    }

    public EnchantmentFilterCondition D(ComparisonOperator comparisonOperator) {
        this.C = comparisonOperator;
        return this;
    }
}
