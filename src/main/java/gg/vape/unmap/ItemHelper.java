package gg.vape.unmap;

import gg.vape.mapping.MappedClasses;
import gg.vape.unmap.ItemMatchRule;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.DataComponentMap;
import gg.vape.wrapper.impl.DataComponents;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.HashSet;
import java.util.function.Predicate;

public class ItemHelper {
    private final HashSet<ItemMatchRule> X = new HashSet();

    private static boolean lambda$new$0(Item item) {
        if (!item.isInstance(MappedClasses.Vw)) {
            return false;
        }
        if (ForgeVersion.MC_1_21_4.d()) {
            DataComponentMap dataComponentMap = item.g();
            boolean bl = dataComponentMap.V(DataComponents.d());
            return !bl;
        }
        return true;
    }

    private void registerItemPredicate(ItemMatchRule itemMatchRule, Predicate<Item> predicate) {
        itemMatchRule.setPredicate(predicate);
        this.X.add(itemMatchRule);
    }

    private void registerCharacterPredicate(ItemMatchRule itemMatchRule, Predicate<Character> predicate) {
        itemMatchRule.setPredicate(predicate);
        this.X.add(itemMatchRule);
    }

    public Predicate<Character> findCharacterRule(String string) {
        for (ItemMatchRule itemMatchRule : this.X) {
            for (String string2 : itemMatchRule.getAliases()) {
                if (!string2.equalsIgnoreCase(string)) continue;
                return itemMatchRule.getPredicate();
            }
        }
        return null;
    }

    public boolean matchesItem(String string, ItemStack itemStack) {
        Item item = null;
        if (itemStack.isNotNull()) {
            item = itemStack.getItem();
        }
        for (ItemMatchRule itemMatchRule : this.X) {
            for (String string2 : itemMatchRule.getAliases()) {
                if (!string2.equalsIgnoreCase(string)) continue;
                if (itemMatchRule.getAcceptedClasses() != null) {
                    if (itemMatchRule.getAcceptedClasses().length == 0) {
                        if (!itemStack.isNull()) continue;
                        return true;
                    }
                    for (Class clazz : itemMatchRule.getAcceptedClasses()) {
                        if (item == null || !item.isInstance(clazz)) continue;
                        return true;
                    }
                    continue;
                }
                if (itemMatchRule.getPredicate() == null || item == null || !itemMatchRule.getPredicate().test(item)) continue;
                return true;
            }
        }
        return false;
    }

    private void registerClassRule(ItemMatchRule itemMatchRule, Class ... classArray) {
        itemMatchRule.setAcceptedClasses(classArray);
        this.X.add(itemMatchRule);
    }

    public ItemHelper() {
        Predicate<Item> predicate = ItemHelper::lambda$new$0;
        Predicate<Item> predicate2 = ItemHelper::lambda$new$1;
        this.registerItemPredicate(new ItemMatchRule(new String[]{"sword", "swords"}, null), ItemStackScoreUtil::h);
        this.registerClassRule(new ItemMatchRule(new String[]{"shovel", "shovels", "spade", "spades"}, null), MappedClasses.FM);
        this.registerClassRule(new ItemMatchRule(new String[]{"axe", "axes"}, null), MappedClasses.YP);
        this.registerItemPredicate(new ItemMatchRule(new String[]{"pickaxe", "pickaxes"}, null), ItemStackScoreUtil::m);
        this.registerItemPredicate(new ItemMatchRule(new String[]{"block", "blocks"}, null), predicate);
        this.registerClassRule(new ItemMatchRule(new String[]{"fists", "none", "fist", "hand"}, null), new Class[0]);
        this.registerItemPredicate(new ItemMatchRule(new String[]{"food", "foods"}, null), predicate2);
        this.registerClassRule(new ItemMatchRule(new String[]{"potion", "potions"}, null), MappedClasses.Di);
        this.registerCharacterPredicate(new ItemMatchRule(new String[]{"bed", "beds"}, null), BlockUtil::v);
    }


    private static boolean lambda$new$1(Item item) {
        if (ForgeVersion.MC_1_20_6.d()) {
            DataComponentMap dataComponentMap = item.g();
            return dataComponentMap.V(DataComponents.d());
        }
        return item.isInstance(MappedClasses.DL);
    }
}

