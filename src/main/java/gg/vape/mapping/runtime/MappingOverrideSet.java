package gg.vape.mapping.runtime;

import gg.vape.mapping.Mapping;
import java.util.HashMap;
import java.util.Map;

public abstract class MappingOverrideSet {
    private int R;
    private static String i;
    private Map<Class, Class> D = new HashMap<Class, Class>();

    public static String K() {
        return i;
    }

    protected abstract void J();

    public static void X(String string) {
        i = string;
    }

    public <T extends Mapping, O extends T> void g(Class<T> clazz, Class<O> clazz2) {
        this.D.put(clazz, clazz2);
    }

    public MappingOverrideSet(int n) {
        this.R = n;
    }

    public Class z(Class clazz) {
        return this.D.get(clazz);
    }

    static {
        if (MappingOverrideSet.K() == null) {
            MappingOverrideSet.X("idae5");
        }
    }
}

