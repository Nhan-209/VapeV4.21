package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;

public class MChestTypeHolder
extends Mapping {
    private MappingField O;
    private MappingField a;
    private static boolean k;

    public MChestTypeHolder() {
        this(MChestTypeHolder.f());
    }

    private MChestTypeHolder(boolean bl) {
        super(MappedClasses.ZY);
        Class clazz = MappedClasses.qC;
        boolean bl2 = true;
        String string = "WOODEN_TOOL_MATERIALS";
        MChestTypeHolder mChestTypeHolder = this;
        this.a = this.registerStaticField(string, bl2, clazz);
        if (bl) {
            Class clazz2 = MappedClasses.qC;
            boolean bl3 = true;
            String string2 = "GOLD_TOOL_MATERIALS";
            MChestTypeHolder mChestTypeHolder2 = this;
            this.O = this.registerStaticField(string2, bl3, clazz2);
            if (GuiComponent.getLegacyComponentState() == null) {
                MChestTypeHolder.F(false);
            }
            return;
        }
        Class clazz3 = MappedClasses.qC;
        boolean bl4 = true;
        String string3 = "GOLD_TOOL_MATERIALS";
        MChestTypeHolder mChestTypeHolder3 = this;
        this.O = this.registerStaticField(string3, bl4, clazz3);
        if (GuiComponent.getLegacyComponentState() == null) {
            MChestTypeHolder.F(true);
        }
    }

    public static boolean D() {
        boolean bl = MChestTypeHolder.f();
        return !bl;
    }

    public Object H() {
        return this.a.getObject(null);
    }


    public static void F(boolean bl) {
        k = bl;
    }

    public static boolean f() {
        return k;
    }

    public Object g() {
        return this.O.getObject(null);
    }

    static {
        MChestTypeHolder.F(true);
    }
}

