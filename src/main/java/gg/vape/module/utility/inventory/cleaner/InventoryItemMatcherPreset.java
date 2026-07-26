package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPresetBuilder;
import java.util.List;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryItemMatcherPreset
extends AbstractInventoryFilterPreset {
    private final List<InventoryFilterConditionGroup> q;
    private final List<InventoryItemMatcher> S;

    InventoryItemMatcherPreset(String string, List<InventoryFilterConditionGroup> list, List<InventoryItemMatcher> list2) {
        super(string);
        this.q = list;
        this.S = list2;
    }

    @Override
    public List<InventoryFilterConditionGroup> z() {
        return this.q;
    }

    public static InventoryItemMatcherPresetBuilder J() {
        return new InventoryItemMatcherPresetBuilder();
    }

    public @UnmodifiableView List<InventoryItemMatcher> U() {
        return this.S;
    }
}

