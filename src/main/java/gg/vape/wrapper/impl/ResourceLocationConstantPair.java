package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ResourceLocation;

public class ResourceLocationConstantPair {
    private static GuiComponent[] o;

    public static void P(GuiComponent[] upArray) {
        o = upArray;
    }

    public static ResourceLocation B() {
        return new ResourceLocation(Vape.INSTANCE.getMappingsMapperCompat().j.s());
    }

    public static GuiComponent[] b() {
        return o;
    }

    public static ResourceLocation v() {
        return new ResourceLocation(Vape.INSTANCE.getMappingsMapperCompat().j.o());
    }

    static {
        if (ResourceLocationConstantPair.b() != null) {
            ResourceLocationConstantPair.P(new GuiComponent[3]);
        }
    }
}

