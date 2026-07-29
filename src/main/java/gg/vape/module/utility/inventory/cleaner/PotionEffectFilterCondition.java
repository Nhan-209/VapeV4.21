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
    public PotionEffectFilterCondition parseValue(String string) throws NumberFormatException {
        return this.parseLevel(string);
    }

    @Override
    public PotionEffectFilterCondition copy() {
        return new PotionEffectFilterCondition(this.mode, this.potionId, this.level, this.operator);
    }
    private Short potionId;
    private ComparisonOperator operator;
    private PotionEffectFilterMode mode = PotionEffectFilterMode.HAS;
    private int level = 1;

    @Override
    public InventoryFilterConditionType getType() {
        return InventoryFilterConditionType.POTION_EFFECT;
    }

    @Override
    public ComparisonOperator getOperator() {
        return this.operator;
    }

    public PotionEffectFilterCondition withPotionId(Short potionId) {
        this.potionId = potionId;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = NumericFilterCondition.super.toJson();
        jsonObject.addProperty("mode", this.mode.getName());
        jsonObject.addProperty("potionId", (Number)this.potionId);
        jsonObject.addProperty("level", (Number)this.level);
        return jsonObject;
    }

    @Nullable
    public PotionEntry getPotionEntry() {
        if (this.potionId == null) {
            return null;
        }
        return PotionRegistry.A(this.potionId);
    }

    public PotionEffectFilterMode getMode() {
        return this.mode;
    }

    @Override
    public boolean matches(ItemStack itemStack) {
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
                    return this.operator.compare(potionEffect.L(), this.level);
                }
                case DURATION: {
                    return this.operator.compare(potionEffect.k(), this.level);
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

    public PotionEffectFilterCondition withMode(PotionEffectFilterMode mode) {
        this.mode = mode;
        return this;
    }

    @Override
    public String getValueText() {
        return String.valueOf(this.level);
    }

    public PotionEffectFilterCondition(JsonObject jsonObject) {
        this.operator = ComparisonOperator.EQUALS;
        this.mode = PotionEffectFilterMode.fromName(ConfigJsonUtils.P(jsonObject, "mode"));
        this.potionId = ConfigJsonUtils.f(jsonObject, "potionId");
        Integer n = ConfigJsonUtils.r(jsonObject, "level");
        this.level = n != null ? n : 1;
        this.operator = ComparisonOperator.fromName(jsonObject.get("operator").getAsString());
    }

    public PotionEffectFilterCondition withLevel(int level) {
        this.level = level;
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

}
