package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MLegacyEntityRenderPreHook
extends Mapping {
    private static final String b;
    private static String[] w;
    public final MappingMethod v;

    public static String[] N() {
        return w;
    }


    public static void Q(String[] stringArray) {
        w = stringArray;
    }

    public MLegacyEntityRenderPreHook() {
        super(MappedClasses.x);
        Class[] classArray = new Class[]{MappedClasses.zm, MappedClasses.Fq, Double.TYPE, Double.TYPE, Double.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = b;
        MLegacyEntityRenderPreHook mLegacyEntityRenderPreHook = this;
        this.v = this.Y(string, bl, clazz, classArray);
        if (MLegacyEntityRenderPreHook.N() != null) {
            GuiComponent.setLegacyComponentState(new GuiComponent[4]);
            return;
        }
    }

    static {
        MLegacyEntityRenderPreHook.Q(null);
        b = "<init>";
    }
}

