package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MITooltipFlag
extends Mapping {
    private static GuiComponent[] p;
    private final MappingField x;

    public MITooltipFlag() {
        this(MITooltipFlag.B());
    }

    private MITooltipFlag(GuiComponent[] guiComponentArray) {
        super(MappedClasses.zX);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_20_6.d()) {
            Class clazz = MappedClasses.qB;
            boolean bl = true;
            String string = "SEARCH";
            Class clazz2 = MappedClasses.qj;
            MITooltipFlag mITooltipFlag = this;
            this.x = mITooltipFlag.s(clazz2, string, bl, clazz);
        } else if (ForgeVersion.MC_1_12_2.d()) {
            Class clazz = MappedClasses.zX;
            boolean bl = true;
            String string = "SEARCH";
            MITooltipFlag mITooltipFlag = this;
            this.x = mITooltipFlag.u(string, bl, clazz);
        } else {
            Class clazz = MappedClasses.zX;
            boolean bl = true;
            String string = "tabAllSearch";
            MITooltipFlag mITooltipFlag = this;
            this.x = mITooltipFlag.u(string, bl, clazz); 
        }
    }

    public static void j(GuiComponent[] guiComponentArray) {
        p = guiComponentArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Object h() {
        return this.x.getObject(null);
    }

    public static GuiComponent[] B() {
        return p;
    }

    static {
        MITooltipFlag.j(new GuiComponent[2]);
    }
}
