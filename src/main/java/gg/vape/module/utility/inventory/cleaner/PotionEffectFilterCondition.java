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
        return this.c(string);
    }

    @Override
    public PotionEffectFilterCondition J(ComparisonOperator operator) {
        return this.H(operator);
    }

    @Override
    public PotionEffectFilterCondition w() {
        return this.p$src$Lgg_vape_module_utility_inventory_cleaner_Potion$1axgq5l();
    }
    private Short Q;
    private ComparisonOperator z;
    private PotionEffectFilterMode c = PotionEffectFilterMode.HAS;
    private int d = 1;

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.POTION_EFFECT;
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    @Override
    public ComparisonOperator p() {
        return this.z;
    }

    public PotionEffectFilterCondition m(Short s) {
        this.Q = s;
        return this;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = NumericFilterCondition.super.L();
        jsonObject.addProperty("mode", this.c.getName());
        jsonObject.addProperty("potionId", (Number)this.Q);
        jsonObject.addProperty("level", (Number)this.d);
        return jsonObject;
    }

    @Nullable
    public PotionEntry z() {
        if (this.Q == null) {
            return null;
        }
        return PotionRegistry.A(this.Q);
    }

    public PotionEffectFilterMode K$src$Lgg_vape_module_utility_inventory_cleaner_Potion$q09io() {
        return this.c;
    }

    @Override
    public boolean g(ItemStack itemStack) {
        if (this.Q == null) {
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
            if (potionEntry == null || potionEntry.T() != this.Q.shortValue()) continue;
            switch (this.c) {
                case HAS: {
                    return true;
                }
                case LEVEL: {
                    return this.z.p(potionEffect.L(), this.d);
                }
                case DURATION: {
                    return this.z.p(potionEffect.k(), this.d);
                }
            }
        }
        return false;
    }

    public PotionEffectFilterCondition H(ComparisonOperator comparisonOperator) {
        this.z = comparisonOperator;
        return this;
    }

    public PotionEffectFilterCondition() {
        this.z = ComparisonOperator.EQUALS;
    }

    public int b() {
        return this.d;
    }

    public Short i() {
        return this.Q;
    }

    public PotionEffectFilterCondition O(PotionEffectFilterMode potionEffectFilterMode) {
        this.c = potionEffectFilterMode;
        return this;
    }

    @Override
    public String k() {
        return String.valueOf(this.d);
    }

    public PotionEffectFilterCondition(JsonObject jsonObject) {
        this.z = ComparisonOperator.EQUALS;
        this.c = PotionEffectFilterMode.r(ConfigJsonUtils.P(jsonObject, "mode"));
        this.Q = ConfigJsonUtils.f(jsonObject, "potionId");
        Integer n = ConfigJsonUtils.r(jsonObject, "level");
        this.d = n != null ? n : 1;
        this.z = ComparisonOperator.a(jsonObject.get("operator").getAsString());
    }

    public PotionEffectFilterCondition N(int n) {
        this.d = n;
        return this;
    }

    public PotionEffectFilterCondition(PotionEffectFilterMode potionEffectFilterMode, short s, int n, ComparisonOperator comparisonOperator) {
        this.z = ComparisonOperator.EQUALS;
        this.c = potionEffectFilterMode;
        this.Q = s;
        this.d = n;
        this.z = comparisonOperator;
    }

    public PotionEffectFilterCondition c(String string) throws NumberFormatException {
        this.d = Integer.parseInt(string);
        return this;
    }

    public PotionEffectFilterCondition p$src$Lgg_vape_module_utility_inventory_cleaner_Potion$1axgq5l() {
        return new PotionEffectFilterCondition(this.c, this.Q, this.d, this.z);
    }
}
