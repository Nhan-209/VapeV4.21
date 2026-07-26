package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MPacketIdFactory
extends Mapping {
    private final MappingMethod v;
    private static GuiComponent[] j;

    public static GuiComponent[] A() {
        return j;
    }

    public static void x(GuiComponent[] guiComponentArray) {
        j = guiComponentArray;
    }

    static {
        if (MPacketIdFactory.A() == null) {
            MPacketIdFactory.x(new GuiComponent[2]);
        }
    }

    public Object F(int n) {
        return this.v.O(n);
    }

    public MPacketIdFactory() {
        super(MappedClasses.Yq);
        Class[] classArray = new Class[]{Integer.TYPE};
        MPacketIdFactory mPacketIdFactory = this;
        this.v = this.g(classArray);
    }
}

