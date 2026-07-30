package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MBlock;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBlockHorizontal
extends Mapping {
    private MappingField c;

    public MBlockHorizontal() {
        this(MBlock.m());
    }

    private MBlockHorizontal(GuiComponent[] guiComponentArray) {
        super(MappedClasses.FQ);
        if (guiComponentArray != null) {
            Class clazz = MappedClasses.lo;
            boolean bl = true;
            String string = "FACING";
            MBlockHorizontal mBlockHorizontal = this;
            this.c = mBlockHorizontal.registerStaticField(string, bl, clazz);
            if (GuiComponent.getLegacyComponentState() == null) {
                MBlock.Y(new GuiComponent[1]);
            }
            return;
        }
        if (ForgeVersion.MC_1_21_4.d()) {
            Class clazz = MappedClasses.Vh;
            boolean bl = true;
            String string = "FACING";
            MBlockHorizontal mBlockHorizontal = this;
            this.c = mBlockHorizontal.registerStaticField(string, bl, clazz);
        } else {
            Class clazz = MappedClasses.lo;
            boolean bl = true;
            String string = "FACING";
            MBlockHorizontal mBlockHorizontal = this;
            this.c = mBlockHorizontal.registerStaticField(string, bl, clazz);
        }
        if (GuiComponent.getLegacyComponentState() == null) {
            MBlock.Y(new GuiComponent[1]);
        }
    }

    private Object E$src$Ljava_lang_Object_$1h8z3ru() {
        return this.c.getObject(null);
    }

    public static Object p(MBlockHorizontal mBlockHorizontal) {
        return mBlockHorizontal.E$src$Ljava_lang_Object_$1h8z3ru();
    }

}
