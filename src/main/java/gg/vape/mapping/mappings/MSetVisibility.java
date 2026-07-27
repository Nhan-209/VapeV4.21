package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MVisGraph;
import gg.vape.ui.click.component.GuiComponent;

public class MSetVisibility
extends Mapping {
    public final MappingMethod X;
    public final MappingMethod z;


    public void t(Object object, boolean bl) {
        this.X.c(object, bl);
    }

    public MSetVisibility() {
        this(MVisGraph.r());
    }

    private MSetVisibility(String string) {
        super(MappedClasses.qG);
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string2 = "<init>";
        MSetVisibility mSetVisibility = this;
        this.z = this.Y(string2, bl, clazz, classArray);
        if (string != null) {
            Class[] classArray2 = new Class[]{Boolean.TYPE};
            Class<Void> clazz2 = Void.TYPE;
            boolean bl2 = true;
            String string3 = "setAllVisible";
            MSetVisibility mSetVisibility2 = this;
            this.X = this.Y(string3, bl2, clazz2, classArray2);
            return;
        }
        Class[] classArray3 = new Class[]{Boolean.TYPE};
        Class<Void> clazz3 = Void.TYPE;
        boolean bl3 = true;
        String string4 = "setAllVisible";
        MSetVisibility mSetVisibility3 = this;
        this.X = this.Y(string4, bl3, clazz3, classArray3);
        GuiComponent.D(new GuiComponent[2]);
    }
}

