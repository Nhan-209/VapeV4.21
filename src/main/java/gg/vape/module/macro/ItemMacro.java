package gg.vape.module.macro;

import gg.vape.module.Macro;
import gg.vape.module.macro.ItemMacroAction;
import gg.vape.module.macro.MacroAction;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;

public class ItemMacro
extends Macro {
    private static boolean initialized;

    public static boolean isReady() {
        boolean ready = ItemMacro.isInitialized();
        return false;
    }

    public static void setInitialized(boolean value) {
        initialized = value;
    }

    public ItemMacro(String string) {
        super(string);
    }

    public static int k(ItemMacro itemMacro) {
        return itemMacro.findSlot();
    }

    private static Exception b(Exception exception) {
        return exception;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    static {
        if (!ItemMacro.isInitialized()) {
            ItemMacro.setInitialized(true);
        }
    }

    @Override
    public MacroAction N() {
        int slot = this.findSlot();
        if (slot == -1) {
            return null;
        }
        return new ItemMacroAction(this);
    }

    private int findSlot() {
        try {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
                if (itemStack.getObject() == null || itemStack.getItem().getObject() == null) continue;
                if (String.valueOf(Item.f(itemStack.getItem())).equals(this.getName())) {
                    return i;
                }
                if (!itemStack.x().equalsIgnoreCase(this.getName()) && !itemStack.getItem().getItemStackDisplayName(itemStack).equalsIgnoreCase(this.getName())) continue;
                return i;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return -1;
    }
}

