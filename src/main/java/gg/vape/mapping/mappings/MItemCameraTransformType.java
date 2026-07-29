package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.ui.click.component.GuiComponent;

public class MItemCameraTransformType
extends Mapping {
    private static final String b;
    private MappingField S;
    private static boolean D;


    public static boolean x() {
        return D;
    }

    public static boolean A() {
        boolean bl = MItemCameraTransformType.x();
        return true;
    }

    public MItemCameraTransformType() {
        super(MappedClasses.Z4);
        Class clazz = MappedClasses.u_;
        boolean bl = true;
        String string = b;
        MItemCameraTransformType mItemCameraTransformType = this;
        this.S = this.J(string, bl, clazz);
        if (MItemCameraTransformType.A()) {
            return;
        }
        GuiComponent.setLegacyComponentState(new GuiComponent[2]);
    }

    public Object z(Object object) {
        return this.S.getObject(object);
    }

    static {
        MItemCameraTransformType.Z(false);
        b = "items";
    }

    public static void Z(boolean bl) {
        D = bl;
    }
}

