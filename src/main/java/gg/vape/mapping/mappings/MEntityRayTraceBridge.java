package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MItemAttributeModifiersComponent;
import gg.vape.ui.click.component.GuiComponent;
import java.util.function.Predicate;

public class MEntityRayTraceBridge
extends Mapping {
    private static final String b = "getClosesetHit";
    private final MappingMethod n;

    private Object B(Object object, Object object2, float f, Predicate<Object> predicate) {
        return this.n.L(object, object2, Float.valueOf(f), predicate);
    }


    public static Object A(MEntityRayTraceBridge mEntityRayTraceBridge, Object object, Object object2, float f, Predicate predicate) {
        return mEntityRayTraceBridge.B(object, object2, f, predicate);
    }

    public MEntityRayTraceBridge() {
        this(MItemAttributeModifiersComponent.F());
    }

    private MEntityRayTraceBridge(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Dr);
        if (guiComponentArray != null) {
            Class[] classArray = new Class[]{MappedClasses.zc, Float.TYPE, Predicate.class};
            Class clazz = MappedClasses.DT;
            boolean bl = true;
            String string = b;
            MEntityRayTraceBridge mEntityRayTraceBridge = this;
            this.n = mEntityRayTraceBridge.Y(string, bl, clazz, classArray);
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.zc, Float.TYPE, Predicate.class};
        Class clazz = MappedClasses.DT;
        boolean bl = true;
        String string = b;
        MEntityRayTraceBridge mEntityRayTraceBridge = this;
        this.n = mEntityRayTraceBridge.Y(string, bl, clazz, classArray); 
        GuiComponent.D(new GuiComponent[5]);
    }
}
