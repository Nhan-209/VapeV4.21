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

    private final String Y;
    private static final EnchantmentFilterMode[] C;
    public static final List<EnchantmentFilterMode> VALUES;
    private final String M;

    public static EnchantmentFilterMode V(String string) {
        return EnchantmentFilterMode.G(string, HAS);
    }

    @Override
    public String getName() {
        return this.M;
    }

    @Override
    public String E() {
        return this.Y;
    }

    static {
        String[] stringArray = new String[]{"HAS", "Whether the item has the enchantment.", "Has", "The level of the enchantment.", "Level", "LEVEL"};


        C = new EnchantmentFilterMode[]{HAS, LEVEL};
        VALUES = Arrays.asList(EnchantmentFilterMode.values());
    }

    private EnchantmentFilterMode(String string2, String string3) {
        this.M = string2;
        this.Y = string3;
    }

    public static EnchantmentFilterMode G(String string, EnchantmentFilterMode enchantmentFilterMode) {
        EnchantmentFilterMode enchantmentFilterMode2 = EnchantmentFilterMode.Y(string);
        return enchantmentFilterMode2 == null ? enchantmentFilterMode : enchantmentFilterMode2;
    }

    @Nullable
    public static EnchantmentFilterMode Y(String string) {
        for (EnchantmentFilterMode enchantmentFilterMode : VALUES) {
            if (!enchantmentFilterMode.getName().equalsIgnoreCase(string)) continue;
            return enchantmentFilterMode;
        }
        return null;
    }

}

