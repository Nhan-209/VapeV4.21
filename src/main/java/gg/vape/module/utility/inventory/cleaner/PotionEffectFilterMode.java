package gg.vape.module.utility.inventory.cleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private static final PotionEffectFilterMode[] l;
    private final String L;
    private final String n;

    @Override
    public String getName() {
        return this.n;
    }

    @Nullable
    public static PotionEffectFilterMode j(String string) {
        for (PotionEffectFilterMode potionEffectFilterMode : VALUES) {
            if (!potionEffectFilterMode.getName().equalsIgnoreCase(string)) continue;
            return potionEffectFilterMode;
        }
        return null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public String E() {
        return this.L;
    }

    private PotionEffectFilterMode(String string2, String string3) {
        this.n = string2;
        this.L = string3;
    }

    static {
        String[] stringArray = new String[]{"HAS", "The level of the potion effect.", "Duration", "How long the potion effect lasts for.", "Has", "Level", "Whether the item has the potion effect.", "LEVEL", "DURATION"};



        l = new PotionEffectFilterMode[]{HAS, LEVEL, DURATION};
        VALUES = Arrays.asList(PotionEffectFilterMode.values());
    }

    public static PotionEffectFilterMode q(String string, PotionEffectFilterMode potionEffectFilterMode) {
        PotionEffectFilterMode potionEffectFilterMode2 = PotionEffectFilterMode.j(string);
        return potionEffectFilterMode2 == null ? potionEffectFilterMode : potionEffectFilterMode2;
    }

    public static PotionEffectFilterMode r(String string) {
        return PotionEffectFilterMode.q(string, HAS);
    }
}

