package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;

public class MITooltipFlagBridge
extends Mapping {
    private static final String b;
    private final MappingField H;
    private static GuiComponent[] r;


    public static void s(GuiComponent[] guiComponentArray) {
        r = guiComponentArray;
    }

    static {
        MITooltipFlagBridge.s(new GuiComponent[2]);
        b = "WATER";
    }

    public Object s() {
        return this.H.getObject(null);
    }

    public MITooltipFlagBridge() {
        this(MITooltipFlagBridge.D());
    }

    private MITooltipFlagBridge(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Y9);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        Class clazz = MappedClasses.Y9;
        boolean bl = true;
        String string = b;
        MITooltipFlagBridge mITooltipFlagBridge = this;
        this.H = this.registerStaticField(string, bl, clazz);
        if (GuiComponent.getLegacyComponentState() == null) {
            MITooltipFlagBridge.s(new GuiComponent[1]);
        }
    }

    public static GuiComponent[] D() {
        return r;
    }
}

