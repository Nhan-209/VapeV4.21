package gg.vape.mapping;

import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ItemMappingEntry {
    private final String p;
    @Nullable
    private final Integer o;
    private static String[] A;
    @Nullable
    private final String F;
    private final String a;
    @Nullable
    private final Integer v;

    public static void d(String[] stringArray) {
        A = stringArray;
    }


    public static ItemMappingEntry w(String string) {
        String string2;
        String[] stringArray = string.split(",", -1);
        String string3 = stringArray[0];
        String string4 = stringArray[1];
        String string5 = string2 = stringArray.length > 2 ? stringArray[2] : null;
        if (string2 == null || string2.isEmpty() || string2.equals("null")) {
            string2 = null;
        }
        return new ItemMappingEntry(string3, string4, string2);
    }

    static {
        ItemMappingEntry.d(null);
    }

    @Nullable
    public String A() {
        return this.F;
    }

    public boolean P() {
        return this.p != null;
    }

    @Nullable
    public ItemStack Q() {
        ItemStack itemStack;
        Item item = this.j();
        if (item == null || item.isNull()) {
            return null;
        }
        if (ForgeVersion.MC_26_1.d()) {
            itemStack = ItemStack.G(item);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            itemStack = ItemStack.S(item);
        } else {
            itemStack = ItemStack.S(item);
            if (this.v != null) {
                itemStack.s(this.v);
            }
        }
        return itemStack;
    }

    @Nullable
    public Item j() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return Item.L(this.p);
        }
        if (this.o == null) {
            return null;
        }
        return Item.T(this.o);
    }

    public String M() {
        return this.a;
    }

    public String q() {
        return this.p;
    }

    public ItemMappingEntry(String string, String string2, @Nullable String string3) {
        this.a = string;
        this.F = string3;
        this.p = string2;
        if (string3 != null) {
            String[] stringArray = string3.split(":");
            this.o = Integer.parseInt(stringArray[0]);
            this.v = stringArray.length > 1 ? Integer.parseInt(stringArray[1]) : 0;
        } else {
            this.o = null;
            this.v = null;
        }
    }

    @Nullable
    public Integer s() {
        return this.o;
    }

    public String toString() {
        return "UniversalItem{resourceKey='" + this.a + '\'' + ", legacyId='" + this.F + '\'' + ", modernId='" + this.p + '\'' + '}';
    }

    @Nullable
    public Integer f() {
        return this.v;
    }

    public static String[] r() {
        return A;
    }
}

