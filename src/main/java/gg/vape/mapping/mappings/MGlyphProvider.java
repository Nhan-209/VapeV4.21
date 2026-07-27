package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MGlyphProvider
extends Mapping {
    private static int[] I;
    private static final String b;
    public MappingMethod f;

    public static int[] f() {
        return I;
    }

    static {
        MGlyphProvider.D(null);
        b = "getGlyph";
    }

    private Object A(Object object, int n) {
        return this.f.L(object, n);
    }

    public MGlyphProvider() {
        this(MGlyphProvider.f());
    }

    private MGlyphProvider(int[] nArray) {
        super(MappedClasses.Yx);
        int[] nArray2 = nArray;
        Class[] classArray = new Class[]{Integer.TYPE};
        Class clazz = MappedClasses.qd;
        boolean bl = true;
        String string = b;
        MGlyphProvider mGlyphProvider = this;
        this.f = this.Y(string, bl, clazz, classArray);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MGlyphProvider.D(new int[3]);
        }
    }


    public static Object Q(MGlyphProvider mGlyphProvider, Object object, int n) {
        return mGlyphProvider.A(object, n);
    }

    public static void D(int[] nArray) {
        I = nArray;
    }
}

