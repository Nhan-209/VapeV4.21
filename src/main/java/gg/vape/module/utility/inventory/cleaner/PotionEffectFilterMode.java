package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;

public enum PotionEffectFilterMode
implements INamed,
DescribedOption {
    HAS("Has", "Whether the item has the potion effect."),
    LEVEL("Level", "The level of the potion effect."),
    DURATION("Duration", "How long the potion effect lasts for.");

    public static final List<PotionEffectFilterMode> VALUES;
    private static final PotionEffectFilterMode[] valuesCache;
    private final String description;
    private final String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Nullable
    public static PotionEffectFilterMode findByName(String string) {
        for (PotionEffectFilterMode potionEffectFilterMode : VALUES) {
            if (!potionEffectFilterMode.getName().equalsIgnoreCase(string)) continue;
            return potionEffectFilterMode;
        }
        return null;
    }


    @Override
    public String E() {
        return this.description;
    }

    private PotionEffectFilterMode(String string2, String string3) {
        this.name = string2;
        this.description = string3;
    }

    static {
        String[] stringArray = new String[]{"HAS", "The level of the potion effect.", "Duration", "How long the potion effect lasts for.", "Has", "Level", "Whether the item has the potion effect.", "LEVEL", "DURATION"};



        valuesCache = new PotionEffectFilterMode[]{HAS, LEVEL, DURATION};
        VALUES = Arrays.asList(PotionEffectFilterMode.values());
    }

    public static PotionEffectFilterMode fromNameOrDefault(String string, PotionEffectFilterMode potionEffectFilterMode) {
        PotionEffectFilterMode potionEffectFilterMode2 = PotionEffectFilterMode.findByName(string);
        return potionEffectFilterMode2 == null ? potionEffectFilterMode : potionEffectFilterMode2;
    }

    public static PotionEffectFilterMode r(String string) {
        return PotionEffectFilterMode.fromNameOrDefault(string, HAS);
    }
}

