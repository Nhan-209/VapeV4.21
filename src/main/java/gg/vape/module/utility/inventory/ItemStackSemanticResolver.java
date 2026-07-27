package gg.vape.module.utility.inventory;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.armorswitch.ArmorMaterialType;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.List;
import gg.vape.module.utility.inventory.ArmorItemMappingEntry;
import org.jetbrains.annotations.Nullable;

public class ItemStackSemanticResolver {
    private final List<ItemMappingEntry> h = new ArrayList<ItemMappingEntry>();
    private final List<ItemMappingEntry> l = new ArrayList<ItemMappingEntry>();
    public static boolean A = true;

    public void p() {
        String[] stringArray;
        String string = "universal_items.csv";
        byte[] byArray = Vape.readResource(string);
        for (String string2 : stringArray = new String(byArray).split("\n")) {
            String string3 = string2.trim();
            ItemMappingEntry itemMappingEntry = ItemMappingEntry.w(string3);
            if (itemMappingEntry.j() != null && ItemStackScoreUtil.R(itemMappingEntry.j())) {
                for (ArmorMaterialType armorMaterialType : ArmorMaterialType.values()) {
                    if (!armorMaterialType.G(itemMappingEntry.M())) continue;
                    itemMappingEntry = new ArmorItemMappingEntry(itemMappingEntry, armorMaterialType);
                }
            }
            this.h.add(itemMappingEntry);
            if (itemMappingEntry.A() == null) continue;
            this.l.add(itemMappingEntry);
        }
    }

    @Nullable
    public ItemMappingEntry b(String string) {
        for (ItemMappingEntry itemMappingEntry : this.h) {
            if (!itemMappingEntry.M().equals(string)) continue;
            return itemMappingEntry;
        }
        return null;
    }


    public void Q() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (ItemStack itemStack : ItemStackScoreUtil.S()) {
            ItemMappingEntry itemMappingEntry = this.j(itemStack);
            if (itemMappingEntry != null) continue;
            arrayList.add(itemStack);
        }
        if (!A) {
            return;
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Vape.debugLog("Failed to find " + arrayList.size() + " item(s):");
    }

    @Nullable
    public ItemMappingEntry K(int n, int n2) {
        for (ItemMappingEntry itemMappingEntry : this.l) {
            assert (itemMappingEntry.s() != null);
            if (itemMappingEntry.s() != n || itemMappingEntry.f() == null || itemMappingEntry.f() != n2) continue;
            return itemMappingEntry;
        }
        return null;
    }

    @Nullable
    public ItemMappingEntry l(int n) {
        for (ItemMappingEntry itemMappingEntry : this.l) {
            assert (itemMappingEntry.s() != null);
            if (itemMappingEntry.s() != n || itemMappingEntry.f() != null && itemMappingEntry.f() != 0) continue;
            return itemMappingEntry;
        }
        return null;
    }

    @Nullable
    public ItemMappingEntry j(ItemStack itemStack) {
        ItemMappingEntry itemMappingEntry;
        if (itemStack.isNull()) {
            return null;
        }
        Item item = itemStack.getItem();
        if (item.isNull()) {
            return null;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            String string = item.getObject().toString();
            String string2 = ForgeVersion.MC_1_21_0.d() ? string : "minecraft:" + string;
            return this.b(string2);
        }
        int n = item.P();
        int n2 = itemStack.L();
        if (!item.p()) {
            n2 = 0;
        }
        if ((itemMappingEntry = this.K(n, n2)) != null) {
            return itemMappingEntry;
        }
        if (itemStack.L() != 0) {
            return null;
        }
        return this.l(n);
    }
}

