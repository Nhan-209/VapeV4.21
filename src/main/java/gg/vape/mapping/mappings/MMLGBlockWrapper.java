package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.mappings.MChestTypeHolder;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MMLGBlockWrapper
extends Mapping {
    private final MappingField n;
    private final MappingField y;

    public static Object a(MMLGBlockWrapper mMLGBlockWrapper) {
        return mMLGBlockWrapper.R();
    }

    private Object R() {
        return this.n.getObject(null);
    }


    public MMLGBlockWrapper() {
        this(MChestTypeHolder.f());
    }

    private MMLGBlockWrapper(boolean bl) {
        super(MappedClasses.Za);
        if (bl) {
            Class clazz = MappedClasses.W;
            String string = "WATER";
            MMLGBlockWrapper mMLGBlockWrapper = this;
            this.y = ((MappingFieldBuilder)((MappingFieldBuilder)mMLGBlockWrapper.T(string, clazz).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.qC)).H(true)).z();
            Class clazz2 = MappedClasses.W;
            String string2 = "LAVA";
            MMLGBlockWrapper mMLGBlockWrapper2 = this;
            this.n = ((MappingFieldBuilder)((MappingFieldBuilder)this.T(string2, clazz2).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.qC)).H(true)).z();
            return;
        }
        Class clazz = MappedClasses.W;
        String string = "WATER";
        MMLGBlockWrapper mMLGBlockWrapper = this;
        this.y = ((MappingFieldBuilder)((MappingFieldBuilder)mMLGBlockWrapper.T(string, clazz).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.qC)).H(true)).z();
        Class clazz3 = MappedClasses.W;
        String string3 = "LAVA";
        MMLGBlockWrapper mMLGBlockWrapper3 = this;
        this.n = ((MappingFieldBuilder)((MappingFieldBuilder)this.T(string3, clazz3).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.qC)).H(true)).z();
        GuiComponent.D(new GuiComponent[5]);
    }

    private Object S() {
        return this.y.getObject(null);
    }

    public static Object m(MMLGBlockWrapper mMLGBlockWrapper) {
        return mMLGBlockWrapper.S();
    }
}

