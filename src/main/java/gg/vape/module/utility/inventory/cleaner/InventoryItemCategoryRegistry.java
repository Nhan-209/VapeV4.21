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
    private static final Map<String, InventoryItemCategory> categoriesById = new LinkedHashMap<String, InventoryItemCategory>();
    public static final InventoryItemCategory FIRST_AVAILABLE = ((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("any_type")).withName("First available")).withDisplayName("First accessible item found in inventory")).build();


    public static @UnmodifiableView List<InventoryItemCategory> getAll() {
        return new ArrayList<InventoryItemCategory>(categoriesById.values());
    }

    public static List<InventoryItemCategory> findCompatible(ItemFilterSelection itemFilterSelection) {
        ArrayList<InventoryItemCategory> arrayList = new ArrayList<InventoryItemCategory>();
        for (InventoryItemCategory inventoryItemCategory : categoriesById.values()) {
            if (!inventoryItemCategory.isCompatible(itemFilterSelection)) continue;
            arrayList.add(inventoryItemCategory);
        }
        return arrayList;
    }

    private static InventoryItemCategory register(InventoryItemCategory inventoryItemCategory) {
        categoriesById.put(inventoryItemCategory.getId(), inventoryItemCategory);
        return inventoryItemCategory;
    }

    @Nullable
    public static InventoryItemCategory getById(String id) {
        return categoriesById.get(id);
    }

    public static void initialize() {
        InventoryItemCategoryRegistry.register(FIRST_AVAILABLE);
        InventoryItemCategory inventoryItemCategory = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("weapon_damage")).withName("Highest attack damage")).withDisplayName("Highest attack damage weapon")).withComparator(Comparator.comparingDouble(ItemStackScoreUtil::a))).addMatcher(WeaponInventoryItemMatchers.m)).addMatcher(WeaponInventoryItemMatchers.z)).addMatcher(WeaponInventoryItemMatchers.j)).build());
        InventoryItemCategory inventoryItemCategory2 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("weapon_best")).withName("Best overall")).withDisplayName("Best overall weapon\nWeighed by damage and important enchants")).withComparator(Comparator.comparingDouble(ItemStackScoreUtil::k))).addMatcher(WeaponInventoryItemMatchers.m)).addMatcher(WeaponInventoryItemMatchers.z)).addMatcher(WeaponInventoryItemMatchers.j)).build());
        WeaponInventoryItemMatchers.m.setCategory(inventoryItemCategory2);
        WeaponInventoryItemMatchers.z.setCategory(inventoryItemCategory2);
        WeaponInventoryItemMatchers.j.setCategory(inventoryItemCategory2);
        InventoryItemCategory inventoryItemCategory3 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("tool_speed")).withName("Fastest mining speed")).withComparator(Comparator.comparingDouble(ClientSettings::X))).addMatcher(ToolInventoryItemMatchers.U)).addMatcher(ToolInventoryItemMatchers.a)).addMatcher(ToolInventoryItemMatchers.q)).addMatcher(ToolInventoryItemMatchers.N)).addMatcher(ToolInventoryItemMatchers.r)).build());
        ToolInventoryItemMatchers.U.setCategory(inventoryItemCategory3);
        ToolInventoryItemMatchers.a.setCategory(inventoryItemCategory3);
        ToolInventoryItemMatchers.q.setCategory(inventoryItemCategory3);
        ToolInventoryItemMatchers.N.setCategory(inventoryItemCategory3);
        ToolInventoryItemMatchers.r.setCategory(inventoryItemCategory3);
        InventoryItemCategory inventoryItemCategory4 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("block_hardness")).withName("Hardest block")).withComparator(Comparator.comparingDouble(BlockUtil::O))).addMatcher(BlockInventoryItemMatchers.x)).addMatcher(BlockInventoryItemMatchers.f)).build());
        InventoryItemCategory inventoryItemCategory5 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("block_softness")).withName("Softest block")).withComparator(Comparator.comparingDouble(BlockUtil::O).reversed())).addMatcher(BlockInventoryItemMatchers.x)).addMatcher(BlockInventoryItemMatchers.f)).build());
        InventoryItemCategoryRegistry.register(((StackSizeInventoryItemCategoryBuilder)((StackSizeInventoryItemCategoryBuilder)((StackSizeInventoryItemCategoryBuilder)InventoryItemCategory.builder().stackSize().withId("max_stack_size")).withName("Highest stack size")).withComparator(Comparator.comparingInt(ItemStack::t))).withStackSize(2).withOperator(ComparisonOperator.GREATER_THAN_OR_EQUAL).build());
        InventoryItemCategory inventoryItemCategory6 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("bow_damage")).withName("Highest damage")).withDisplayName("Highest damage bow")).withComparator(Comparator.comparingDouble(ItemStackScoreUtil::f))).addMatcher(HiddenInventoryItemMatchers.J)).build());
        InventoryItemCategory inventoryItemCategory7 = InventoryItemCategoryRegistry.register(((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)((MatcherBackedInventoryItemCategoryBuilder)InventoryItemCategory.builder().matcherBacked().withId("bow_best")).withName("Best overall")).withDisplayName("Best overall bow\nWeighed by damage and important enchants")).withComparator(Comparator.comparingDouble(ItemStackScoreUtil::O))).addMatcher(HiddenInventoryItemMatchers.J)).build());
        HiddenInventoryItemMatchers.J.setCategory(inventoryItemCategory7);
    }
}

