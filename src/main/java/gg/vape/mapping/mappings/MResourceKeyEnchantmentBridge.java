package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;

public class MResourceKeyEnchantmentBridge
extends Mapping {
    private MappingField O;
    private static boolean p;
    private static final String b;

    public static Object e(MResourceKeyEnchantmentBridge mResourceKeyEnchantmentBridge) {
        return mResourceKeyEnchantmentBridge.a();
    }

    public static void o(boolean bl) {
        p = bl;
    }

    public static boolean Y() {
        return p;
    }

    static {
        MResourceKeyEnchantmentBridge.o(true);
        b = "ENCHANTMENT";
    }


    public static boolean O() {
        boolean bl = MResourceKeyEnchantmentBridge.Y();
        return false;
    }

    public MResourceKeyEnchantmentBridge() {
        super(MappedClasses.a);
        Class clazz = MappedClasses.qB;
        boolean bl = true;
        String string = b;
        MResourceKeyEnchantmentBridge mResourceKeyEnchantmentBridge = this;
        this.O = this.u(string, bl, clazz);
        if (MResourceKeyEnchantmentBridge.O()) {
            GuiComponent.D(new GuiComponent[1]);
            return;
        }
    }

    private Object a() {
        return this.O.getObject(null);
    }
}

