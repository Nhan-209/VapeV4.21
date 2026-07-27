package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MChannel
extends Mapping {
    private MappingMethod q;
    private static GuiComponent[] X;
    private static final String b;


    public static GuiComponent[] x() {
        return X;
    }

    public MChannel() {
        this(MChannel.x());
    }

    private MChannel(GuiComponent[] guiComponentArray) {
        super(MappedClasses.lc);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_17.d()) {
            Class[] classArray = new Class[]{};
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = true;
            String string = b;
            MChannel mChannel = this;
            this.q = this.Y(string, bl, clazz, classArray);
        }
    }

    private boolean H(Object object) {
        return this.q.e(object, new Object[0]);
    }

    static {
        MChannel.j(null);
        b = "usesBlockLight";
    }

    public static void j(GuiComponent[] guiComponentArray) {
        X = guiComponentArray;
    }

    public static boolean u(MChannel mChannel, Object object) {
        return mChannel.H(object);
    }
}

