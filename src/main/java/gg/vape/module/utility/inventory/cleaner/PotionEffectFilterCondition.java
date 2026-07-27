package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.module.utility.inventory.cleaner.PotionEffectFilterMode;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class PotionEffectFilterCondition
implements NumericFilterCondition<PotionEffectFilterCondition> {
    @Override
    public PotionEffectFilterCondition Q(String string) throws NumberFormatException {
        return this.parseLevel(string);
    }

    @Override
    public PotionEffectFilterCondition J(ComparisonOperator operator) {
        return this.withOperator(operator);
    }

    @Override
    public PotionEffectFilterCondition w() {
        return this.p$src$Lgg_vape_module_utility_inventory_cleaner_Potion$1axgq5l();
    }
    private Short potionId;
    private ComparisonOperator operator;
    private PotionEffectFilterMode mode = PotionEffectFilterMode.HAS;
    private int level = 1;

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.POTION_EFFECT;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    @Override
    public ComparisonOperator p() {
        return this.operator;
    }

    public PotionEffectFilterCondition m(Short s) {
        this.potionId = s;
        return this;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("mode", this.mode.getName());
        jsonObject.addProperty("potionId", (Number)this.potionId);
        jsonObject.addProperty("level", (Number)this.level);
        return jsonObject;
    }

    @Nullable
    public PotionEntry z() {
        if (this.potionId == null) {
            return null;
        }
        return PotionRegistry.A(this.potionId);
    }

    public PotionEffectFilterMode K$src$Lgg_vape_module_utility_inventory_cleaner_Potion$q09io() {
        return this.mode;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (this.potionId == null) {
            return false;
        }
        if (itemStack.isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (item.isNull() || !item.isInstance(MappedClasses.Di) && !item.isInstance(MappedClasses.o)) {
            return false;
        }
        ItemSplashPotion itemSplashPotion = new ItemSplashPotion(item);
        List<PotionEffect> list = itemSplashPotion.getPotionEffects(itemStack);
        for (PotionEffect potionEffect : list) {
            PotionEntry potionEntry = PotionRegistry.R(potionEffect);
            if (potionEntry == null || potionEntry.T() != this.potionId.shortValue()) continue;
            switch (this.mode) {
                case HAS: {
                    return true;
                }
                case LEVEL: {
                    return this.operator.p(potionEffect.L(), this.level);
                }
                case DURATION: {
                    return this.operator.p(potionEffect.k(), this.level);
                }
            }
        }
        return false;
    }

    public PotionEffectFilterCondition withOperator(ComparisonOperator comparisonOperator) {
        this.operator = comparisonOperator;
        return this;
    }

    public PotionEffectFilterCondition() {
        this.operator = ComparisonOperator.EQUALS;
    }

    public int getLevel() {
        return this.level;
    }

    public Short getPotionId() {
        return this.potionId;
    }

    public PotionEffectFilterCondition O(PotionEffectFilterMode potionEffectFilterMode) {
        this.mode = potionEffectFilterMode;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.level);
    }

    public PotionEffectFilterCondition(JsonObject jsonObject) {
        this.operator = ComparisonOperator.EQUALS;
        this.mode = PotionEffectFilterMode.r(ConfigJsonUtils.P(jsonObject, "mode"));
        this.potionId = ConfigJsonUtils.f(jsonObject, "potionId");
        Integer n = ConfigJsonUtils.r(jsonObject, "level");
        this.level = n != null ? n : 1;
        this.operator = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public PotionEffectFilterCondition N(int n) {
        this.level = n;
        return this;
    }

    public PotionEffectFilterCondition(PotionEffectFilterMode potionEffectFilterMode, short s, int n, ComparisonOperator comparisonOperator) {
        this.operator = ComparisonOperator.EQUALS;
        this.mode = potionEffectFilterMode;
        this.potionId = s;
        this.level = n;
        this.operator = comparisonOperator;
    }

    public PotionEffectFilterCondition parseLevel(String string) throws NumberFormatException {
        this.level = Integer.parseInt(string);
        return this;
    }

    public PotionEffectFilterCondition p$src$Lgg_vape_module_utility_inventory_cleaner_Potion$1axgq5l() {
        return new PotionEffectFilterCondition(this.mode, this.potionId, this.level, this.operator);
    }
}
