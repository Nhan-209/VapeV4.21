package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;

public enum EnchantmentFilterMode
implements INamed,
DescribedOption {
    HAS("Has", "Whether the item has the enchantment."),
    LEVEL("Level", "The level of the enchantment.");

    private final String description;
    public static final List<EnchantmentFilterMode> VALUES;
    private final String name;

    public static EnchantmentFilterMode fromName(String string) {
        return EnchantmentFilterMode.fromNameOrDefault(string, HAS);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    static {
        VALUES = Arrays.asList(EnchantmentFilterMode.values());
    }

    private EnchantmentFilterMode(String string2, String string3) {
        this.name = string2;
        this.description = string3;
    }

    public static EnchantmentFilterMode fromNameOrDefault(String string, EnchantmentFilterMode enchantmentFilterMode) {
        EnchantmentFilterMode enchantmentFilterMode2 = EnchantmentFilterMode.findByName(string);
        return enchantmentFilterMode2 == null ? enchantmentFilterMode : enchantmentFilterMode2;
    }

    @Nullable
    public static EnchantmentFilterMode findByName(String string) {
        for (EnchantmentFilterMode enchantmentFilterMode : VALUES) {
            if (!enchantmentFilterMode.getName().equalsIgnoreCase(string)) continue;
            return enchantmentFilterMode;
        }
        return null;
    }

}

