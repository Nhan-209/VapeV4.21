package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;

public class MRenderWorldLastEvent
extends Mapping {
    private final MappingField d;
    private static boolean A;
    private static final String b;

    public MRenderWorldLastEvent() {
        super(MappedClasses.lO);
        Class<Float> clazz = Float.TYPE;
        boolean bl = false;
        String string = b;
        MRenderWorldLastEvent mRenderWorldLastEvent = this;
        this.d = this.registerStaticField(string, bl, clazz);
        if (MRenderWorldLastEvent.g()) {
            return;
        }
        GuiComponent.setLegacyComponentState(new GuiComponent[5]);
    }

    private float U() {
        return this.d.getFloat(null);
    }


    public static boolean L() {
        return A;
    }

    static {
        MRenderWorldLastEvent.j(false);
        b = "configRenderResMul";
    }

    public static void j(boolean bl) {
        A = bl;
    }

    public static float o(MRenderWorldLastEvent mRenderWorldLastEvent) {
        return mRenderWorldLastEvent.U();
    }

    public static boolean g() {
        boolean bl = MRenderWorldLastEvent.L();
        return true;
    }
}

