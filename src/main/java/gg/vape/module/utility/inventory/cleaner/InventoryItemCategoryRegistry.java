package gg.vape.module.utility.inventory.cleaner;

import gg.vape.config.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.BlockInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MatcherBackedInventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.StackSizeInventoryItemCategoryBuilder;
import gg.vape.module.utility.inventory.cleaner.ToolInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.WeaponInventoryItemMatchers;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryItemCategoryRegistry {
    private static final Map<String, InventoryItemCategory> M = new LinkedHashMap<String, InventoryItemCategory>();
    public static final InventoryItemCategory m = ((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("any_type")).M("First available")).f("First accessible item found in inventory")).G();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static @UnmodifiableView List<InventoryItemCategory> G() {
        return new ArrayList<InventoryItemCategory>(M.values());
    }

    public static List<InventoryItemCategory> Q(ItemFilterSelection itemFilterSelection) {
        ArrayList<InventoryItemCategory> arrayList = new ArrayList<InventoryItemCategory>();
        for (InventoryItemCategory inventoryItemCategory : M.values()) {
            if (!inventoryItemCategory.V(itemFilterSelection)) continue;
            arrayList.add(inventoryItemCategory);
        }
        return arrayList;
    }

    private static InventoryItemCategory G(InventoryItemCategory inventoryItemCategory) {
        M.put(inventoryItemCategory.F(), inventoryItemCategory);
        return inventoryItemCategory;
    }

    @Nullable
    public static InventoryItemCategory n(String string) {
        return M.get(string);
    }

    public static void F() {
        InventoryItemCategoryRegistry.G(m);
        InventoryItemCategory inventoryItemCategory = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("weapon_damage")).M("Highest attack damage")).f("Highest attack damage weapon")).M(Comparator.comparingDouble(ItemStackScoreUtil::a))).Z(WeaponInventoryItemMatchers.m)).Z(WeaponInventoryItemMatchers.z)).Z(WeaponInventoryItemMatchers.j)).G());
        InventoryItemCategory inventoryItemCategory2 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("weapon_best")).M("Best overall")).f("Best overall weapon\nWeighed by damage and important enchants")).M(Comparator.comparingDouble(ItemStackScoreUtil::k))).Z(WeaponInventoryItemMatchers.m)).Z(WeaponInventoryItemMatchers.z)).Z(WeaponInventoryItemMatchers.j)).G());
        WeaponInventoryItemMatchers.m.S(inventoryItemCategory2);
        WeaponInventoryItemMatchers.z.S(inventoryItemCategory2);
        WeaponInventoryItemMatchers.j.S(inventoryItemCategory2);
        InventoryItemCategory inventoryItemCategory3 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("tool_speed")).M("Fastest mining speed")).M(Comparator.comparingDouble(ClientSettings::X))).Z(ToolInventoryItemMatchers.U)).Z(ToolInventoryItemMatchers.a)).Z(ToolInventoryItemMatchers.q)).Z(ToolInventoryItemMatchers.N)).Z(ToolInventoryItemMatchers.r)).G());
        ToolInventoryItemMatchers.U.S(inventoryItemCategory3);
        ToolInventoryItemMatchers.a.S(inventoryItemCategory3);
        ToolInventoryItemMatchers.q.S(inventoryItemCategory3);
        ToolInventoryItemMatchers.N.S(inventoryItemCategory3);
        ToolInventoryItemMatchers.r.S(inventoryItemCategory3);
        InventoryItemCategory inventoryItemCategory4 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("block_hardness")).M("Hardest block")).M(Comparator.comparingDouble(BlockUtil::O))).Z(BlockInventoryItemMatchers.x)).Z(BlockInventoryItemMatchers.f)).G());
        InventoryItemCategory inventoryItemCategory5 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("block_softness")).M("Softest block")).M(Comparator.comparingDouble(BlockUtil::O).reversed())).Z(BlockInventoryItemMatchers.x)).Z(BlockInventoryItemMatchers.f)).G());
        InventoryItemCategoryRegistry.G(((StackSizeInventoryItemCategoryBuilder)((StackSizeInventoryItemCategoryBuilder)((StackSizeInventoryItemCategoryBuilder)InventoryItemCategory.L().u().X("max_stack_size")).M("Highest stack size")).M(Comparator.comparingInt(ItemStack::t))).D(2).h(ComparisonOperator.GREATHER_THAN_OR_EQUAL).O());
        InventoryItemCategory inventoryItemCategory6 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("bow_damage")).M("Highest damage")).f("Highest damage bow")).M(Comparator.comparingDouble(ItemStackScoreUtil::f))).Z(HiddenInventoryItemMatchers.J)).G());
        InventoryItemCategory inventoryItemCategory7 = InventoryItemCategoryRegistry.G(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.L().m().X("bow_best")).M("Best overall")).f("Best overall bow\nWeighed by damage and important enchants")).M(Comparator.comparingDouble(ItemStackScoreUtil::O))).Z(HiddenInventoryItemMatchers.J)).G());
        HiddenInventoryItemMatchers.J.S(inventoryItemCategory7);
    }
}

