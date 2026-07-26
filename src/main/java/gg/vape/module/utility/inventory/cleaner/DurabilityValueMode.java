package gg.vape.module.utility.inventory.cleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public enum DurabilityValueMode
implements INamed,
DescribedOption {
    PERCENTAGE("Percentage", "The durability of the item as a percentage"),
    VALUE("Value", "The durability of the item as the direct value");

    private static final DurabilityValueMode[] L;
    private final String Q;
    public static final @UnmodifiableView List<DurabilityValueMode> VALUES;
    private final String K;

    static {
        String[] stringArray = new String[]{"Value", "The durability of the item as a percentage", "VALUE", "The durability of the item as the direct value", "Percentage", "PERCENTAGE"};


        L = new DurabilityValueMode[]{PERCENTAGE, VALUE};
        VALUES = Arrays.asList(DurabilityValueMode.values());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public String getName() {
        return this.K;
    }

    private DurabilityValueMode(String string2, String string3) {
        this.K = string2;
        this.Q = string3;
    }

    @Override
    public String E() {
        return this.Q;
    }

    public static DurabilityValueMode B(String string, DurabilityValueMode durabilityValueMode) {
        DurabilityValueMode durabilityValueMode2 = DurabilityValueMode.C(string);
        return durabilityValueMode2 == null ? durabilityValueMode : durabilityValueMode2;
    }

    @Nullable
    public static DurabilityValueMode C(String string) {
        for (DurabilityValueMode durabilityValueMode : VALUES) {
            if (!durabilityValueMode.getName().equalsIgnoreCase(string)) continue;
            return durabilityValueMode;
        }
        return null;
    }

    public static DurabilityValueMode i(String string) {
        return DurabilityValueMode.B(string, PERCENTAGE);
    }
}

