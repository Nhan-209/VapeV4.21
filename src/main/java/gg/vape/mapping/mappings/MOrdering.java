package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import java.util.List;

public class MOrdering
extends Mapping {
    public MappingMethod C;
    private static final String c;
    private static String d;

    public static String y() {
        return d;
    }

    public static void u(String string) {
        d = string;
    }

    public List n(Object object, Iterable iterable) {
        return (List)this.C.L(object, iterable);
    }

    public MOrdering() {
        super(MappedClasses.F4);
        Class[] classArray = new Class[]{Iterable.class};
        Class<List> clazz = List.class;
        boolean bl = false;
        String string = c;
        MOrdering mOrdering = this;
        this.C = this.Y(string, bl, clazz, classArray);
    }

    static {
        MOrdering.u("XPzfA");
        c = "sortedCopy";
    }
}

