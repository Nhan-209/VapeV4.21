package gg.vape.module.utility.inventory.cleaner;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatchContext;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.InventoryMatcherListMode;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.StringInventoryItemMatcherBuilder;
import gg.vape.module.utility.inventory.cleaner.StringMatchOperator;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Map;

public class BlockInventoryItemMatchers {
    public static final ClassInventoryItemMatcher x;
    public static final StringInventoryItemMatcher f;

    static {
        String[] stringArray = new String[]{"building-block-hover@2x", "_planks", "building-blocks", "any-block", "obsidian", "blocks-hover@2x", "stone", "_wool", "Any block", "Any type of building block", "Building blocks", "red_sandstone", "terracotta", "end_stone"};
        x = ((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)((ClassInventoryItemMatcherBuilder)InventoryItemMatcher.c().Y().n(stringArray[3])).m(stringArray[8])).H(stringArray[5])).A(InventoryItemMatcherGroup.BLOCKS)).Q(MappedClasses.Vw).p(InventoryMatcherListMode.WHITELIST).o();
        StringInventoryItemMatcherBuilder stringInventoryItemMatcherBuilder = ((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)((StringInventoryItemMatcherBuilder)InventoryItemMatcher.c().y().n(stringArray[2])).m(stringArray[10])).M(stringArray[9])).H(stringArray[0])).A(InventoryItemMatcherGroup.BLOCKS)).R(stringArray[7], StringMatchOperator.ENDS).R(stringArray[6], StringMatchOperator.EQUALS).R(stringArray[1], StringMatchOperator.ENDS).R(stringArray[11], StringMatchOperator.EQUALS).R(stringArray[12], StringMatchOperator.ENDS).R(stringArray[13], StringMatchOperator.EQUALS).R(stringArray[4], StringMatchOperator.EQUALS);
        stringInventoryItemMatcherBuilder.N((first, second) -> {
            String firstName = first.f().M();
            String secondName = second.f().M();
            int firstRank = -1;
            int secondRank = -1;
            int rank = stringInventoryItemMatcherBuilder.X().size();
            for (Map.Entry<String, StringMatchOperator> entry : stringInventoryItemMatcherBuilder.X().entrySet()) {
                String pattern = entry.getKey();
                StringMatchOperator operator = entry.getValue();
                if (operator.z().test(firstName, pattern)) {
                    firstRank = rank;
                }
                if (operator.z().test(secondName, pattern)) {
                    secondRank = rank;
                }
                --rank;
                if (firstRank != -1 && secondRank != -1) {
                    break;
                }
            }
            return Integer.compare(firstRank, secondRank);
        });
        f = stringInventoryItemMatcherBuilder.g();
    }

    public static void C() {
        InventoryItemMatcherRegistry.R(x);
        InventoryItemMatcherRegistry.R(f);
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
