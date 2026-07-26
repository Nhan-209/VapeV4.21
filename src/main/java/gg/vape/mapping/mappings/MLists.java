package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MOrdering;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;
import java.util.List;

public class MLists
extends Mapping {
    public MappingMethod P;
    public MappingMethod v;

    public ArrayList V(Iterable iterable) {
        return (ArrayList)this.v.L(null, iterable);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public List c(List list) {
        return (List)this.P.L(null, list);
    }

    public MLists() {
        this(MOrdering.y());
    }

    private MLists(String string) {
        super(MappedClasses.VZ);
        Class[] classArray = new Class[]{List.class};
        Class<List> clazz = List.class;
        boolean bl = false;
        String string2 = "reverse";
        MLists mLists = this;
        this.P = this.x(string2, bl, clazz, classArray);
        String string3 = string;
        Class[] classArray2 = new Class[]{Iterable.class};
        Class<ArrayList> clazz2 = ArrayList.class;
        boolean bl2 = false;
        String string4 = "newArrayList";
        MLists mLists2 = this;
        this.v = this.x(string4, bl2, clazz2, classArray2);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MOrdering.u("Ze85D");
        }
    }
}

