package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEnumHandBridge;
import gg.vape.ui.click.component.GuiComponent;

public class MRenderStateBuilder
extends Mapping {
    private MappingMethod H;
    private static final String b = "drawWithShader";

    public MRenderStateBuilder() {
        this(MEnumHandBridge.s());
    }

    private MRenderStateBuilder(int n) {
        super(MappedClasses.ug);
        if (n != 0) {
            Class[] classArray = new Class[]{MappedClasses.qE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = b;
            MRenderStateBuilder mRenderStateBuilder = this;
            this.H = mRenderStateBuilder.x(string, bl, clazz, classArray);
            GuiComponent.D(new GuiComponent[1]);
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.qE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = true;
        String string = b;
        MRenderStateBuilder mRenderStateBuilder = this;
        this.H = mRenderStateBuilder.x(string, bl, clazz, classArray);
    }


    public static void E(MRenderStateBuilder mRenderStateBuilder, Object object) {
        mRenderStateBuilder.Z(object);
    }

    private void Z(Object object) {
        this.H.c(null, object);
    }
}

