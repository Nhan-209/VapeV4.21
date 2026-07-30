package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEntityEnderPearl
extends Mapping {
    private MappingMethod T;
    private MappingField u;
    private static GuiComponent[] h;

    public int k(Object object) {
        if (ForgeVersion.MC_1_17.d()) {
            return this.T.invokeInt(object, new Object[0]);
        }
        return this.u.getInt(object);
    }

    public MEntityEnderPearl() {
        this(MEntityEnderPearl.I());
    }

    private MEntityEnderPearl(GuiComponent[] guiComponentArray) {
        super(MappedClasses.qM);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_17.d()) {
                Class[] classArray = new Class[]{};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "getFuse";
                MEntityEnderPearl mEntityEnderPearl = this;
                this.T = mEntityEnderPearl.Y(string, bl, clazz, classArray);
            } else {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string = "fuse";
                MEntityEnderPearl mEntityEnderPearl = this;
                this.u = mEntityEnderPearl.J(string, bl, clazz);
            }
            if (GuiComponent.getLegacyComponentState() == null) {
                MEntityEnderPearl.o(new GuiComponent[1]);
            }
            return;
        }
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "fuse";
        MEntityEnderPearl mEntityEnderPearl = this;
        this.u = mEntityEnderPearl.J(string, bl, clazz); 
        if (GuiComponent.getLegacyComponentState() == null) {
            MEntityEnderPearl.o(new GuiComponent[1]);
        }
    }

    static {
        MEntityEnderPearl.o(new GuiComponent[3]);
    }

    public static GuiComponent[] I() {
        return h;
    }


    public static void o(GuiComponent[] guiComponentArray) {
        h = guiComponentArray;
    }
}
