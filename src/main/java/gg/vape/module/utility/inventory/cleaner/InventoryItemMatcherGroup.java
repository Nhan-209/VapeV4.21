package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;

public enum InventoryItemMatcherGroup
implements INamed,
DescribedOption {
    HIDDEN("Hidden"),
    WEAPONS("Weapons", "Picking any type of weapon", "weapons"),
    TOOLS("Tools", "Picking any type of tool or weapon", "tools"),
    FOOD("Food", "Picking any type of food", "food-hover@2x"),
    BLOCKS("Blocks", "Picking any type of block", "blocks-hover@2x"),
    ARMOR("Armor", "Picking any type of Armor", "armor_item");

    public static final List<InventoryItemMatcherGroup> VALUES;
    private final String z;
    private static final /* synthetic */ InventoryItemMatcherGroup[] Z;
    @Nullable
    private final String e;
    @Nullable
    private final String X;

    private InventoryItemMatcherGroup(String string2) {
        this(string2, null, null);
    }

    private InventoryItemMatcherGroup(@Nullable String string2, String string3, String string4) {
        this.z = string2;
        this.e = string3;
        this.X = string4;
    }

    static {
        String[] stringArray = new String[]{"WEAPONS", "blocks-hover@2x", "Weapons", "Food", "weapons", "FOOD", "Armor", "armor_item", "HIDDEN", "Picking any type of tool or weapon", "Picking any type of Armor", "Hidden", "Picking any type of weapon", "food-hover@2x", "Tools", "ARMOR", "Picking any type of block", "tools", "Blocks", "Picking any type of food", "TOOLS", "BLOCKS"};






        Z = new InventoryItemMatcherGroup[]{HIDDEN, WEAPONS, TOOLS, FOOD, BLOCKS, ARMOR};
        VALUES = Arrays.asList(InventoryItemMatcherGroup.values());
    }

    @Override
    public String E() {
        return this.e;
    }

    @Override
    public String getName() {
        return this.z;
    }

    @Nullable
    public String u() {
        return this.X;
    }
}

