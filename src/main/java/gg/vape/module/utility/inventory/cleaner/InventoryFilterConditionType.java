package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.cleaner.AttackDamageFilterCondition;
import gg.vape.module.utility.inventory.cleaner.DisplayNameFilterCondition;
import gg.vape.module.utility.inventory.cleaner.EnchantmentFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.ItemDurabilityFilterCondition;
import gg.vape.module.utility.inventory.cleaner.ItemNameFilterCondition;
import gg.vape.module.utility.inventory.cleaner.LoreFilterCondition;
import gg.vape.module.utility.inventory.cleaner.MaterialFilterCondition;
import gg.vape.module.utility.inventory.cleaner.PotionEffectFilterCondition;
import gg.vape.module.utility.inventory.cleaner.QuantityFilterCondition;
import gg.vape.unmap.INamed;
import java.util.function.Function;
import java.util.function.Supplier;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;

public enum InventoryFilterConditionType
implements INamed,
DescribedOption {
    ITEM_DURABILITY("Durability", "The amount of durability the item has remaining", ItemDurabilityFilterCondition::new, ItemDurabilityFilterCondition::new),
    ATTACK_DAMAGE("Attack Damage", "The amount of attack damage the item has", AttackDamageFilterCondition::new, AttackDamageFilterCondition::new),
    ENCHANTMENT("Enchantment", "The enchantment the item has", EnchantmentFilterCondition::new, EnchantmentFilterCondition::new),
    POTION_EFFECT("Potion Effect", "The potion effect the item has", PotionEffectFilterCondition::new, PotionEffectFilterCondition::new),
    QUANTITY("Quantity", "The item stack size", QuantityFilterCondition::new, QuantityFilterCondition::new),
    DISPLAY_NAME("Display Name", "The display name of the item, such as \"John's Sword\"", DisplayNameFilterCondition::new, DisplayNameFilterCondition::new),
    ITEM_NAME("Item Name", "The name of the item, such as \"Diamond Sword\"", ItemNameFilterCondition::new, ItemNameFilterCondition::new),
    LORE("Lore", "The lore of the item", LoreFilterCondition::new, LoreFilterCondition::new),
    MATERIAL("Material", "The material of the item", MaterialFilterCondition::new, MaterialFilterCondition::new);

    private final String T;
    private final Supplier<? extends InventoryFilterCondition> J;
    private static final InventoryFilterConditionType[] R;
    private final String s;
    private static String F;
    private final Function<JsonObject, ? extends InventoryFilterCondition> r;

    private InventoryFilterConditionType(String string2, String string3, Supplier<? extends InventoryFilterCondition> supplier, Function<JsonObject, ? extends InventoryFilterCondition> function) {
        this.T = string2;
        this.s = string3;
        this.J = supplier;
        this.r = function;
    }

    public static void H(String string) {
        F = string;
    }

    @Override
    public String E() {
        return this.s;
    }

    @Override
    public String getName() {
        return this.T;
    }

    public Function<JsonObject, ? extends InventoryFilterCondition> L() {
        return this.r;
    }

    public static String h() {
        return F;
    }

    static {
        if (InventoryFilterConditionType.h() == null) {
            InventoryFilterConditionType.H("MKlmqc");
        }
        String[] stringArray = new String[]{"Display Name", "Lore", "MATERIAL", "The amount of durability the item has remaining", "ITEM_DURABILITY", "Quantity", "DISPLAY_NAME", "ENCHANTMENT", "POTION_EFFECT", "The enchantment the item has", "Enchantment", "The potion effect the item has", "The item stack size", "The lore of the item", "Material", "Item Name", "QUANTITY", "The display name of the item, such as \"John's Sword\"", "The amount of attack damage the item has", "ATTACK_DAMAGE", "Attack Damage", "Potion Effect", "The name of the item, such as \"Diamond Sword\"", "LORE", "The material of the item", "ITEM_NAME", "Durability"};









        R = new InventoryFilterConditionType[]{ITEM_DURABILITY, ATTACK_DAMAGE, ENCHANTMENT, POTION_EFFECT, QUANTITY, DISPLAY_NAME, ITEM_NAME, LORE, MATERIAL};
    }

    public Supplier<? extends InventoryFilterCondition> O() {
        return this.J;
    }
}

