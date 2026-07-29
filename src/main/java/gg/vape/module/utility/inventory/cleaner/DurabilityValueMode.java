package gg.vape.module.utility.inventory.cleaner;

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

    private final String description;
    public static final @UnmodifiableView List<DurabilityValueMode> VALUES;
    private final String name;

    static {
        VALUES = Arrays.asList(DurabilityValueMode.values());
    }


    @Override
    public String getName() {
        return this.name;
    }

    private DurabilityValueMode(String string2, String string3) {
        this.name = string2;
        this.description = string3;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static DurabilityValueMode fromNameOrDefault(String string, DurabilityValueMode durabilityValueMode) {
        DurabilityValueMode durabilityValueMode2 = DurabilityValueMode.findByName(string);
        return durabilityValueMode2 == null ? durabilityValueMode : durabilityValueMode2;
    }

    @Nullable
    public static DurabilityValueMode findByName(String string) {
        for (DurabilityValueMode durabilityValueMode : VALUES) {
            if (!durabilityValueMode.getName().equalsIgnoreCase(string)) continue;
            return durabilityValueMode;
        }
        return null;
    }

    public static DurabilityValueMode fromName(String string) {
        return DurabilityValueMode.fromNameOrDefault(string, PERCENTAGE);
    }
}

