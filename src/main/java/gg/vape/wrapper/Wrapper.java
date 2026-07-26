package gg.vape.wrapper;

import gg.vape.Vape;
import gg.vape.ui.click.component.GuiComponent;
import java.util.HashMap;
import java.util.Map;

public class Wrapper {
    private static final Map<String, Integer> O;
    public static boolean D;
    private static final String l;
    private static GuiComponent[] V;
    public static boolean G;
    protected Object I;
    public static Vape c;

    public String toString() {
        if (this.I == null) {
            return l;
        }
        try {
            return this.I.toString();
        }
        catch (Exception exception) {
            return "";
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public boolean isInstance(Class clazz) {
        return this.isNotNull() && clazz != null && clazz.isInstance(this.I);
    }

    public Object getObject() {
        return this.I;
    }

    public boolean isNull() {
        return this.I == null;
    }

    public Wrapper(Object object) {
        if (object instanceof Wrapper) {
            Wrapper wrapper = (Wrapper)object;
            this.I = wrapper.getObject();
            return;
        }
        this.I = object;
    }

    static {
        Wrapper.p(null);
        l = "Null instance";
        c = Vape.INSTANCE;
        G = c.isNativeAvailable();
        D = false;
        O = new HashMap<String, Integer>();
    }

    public static GuiComponent[] m() {
        return V;
    }

    public boolean isNotNull() {
        return !this.isNull();
    }

    public static void p(GuiComponent[] guiComponentArray) {
        V = guiComponentArray;
    }

    public boolean equals(Object object) {
        if (object == null || this.I == null) {
            return false;
        }
        if (object instanceof Wrapper) {
            Wrapper wrapper = (Wrapper)object;
            return this.I.equals(wrapper.I);
        }
        return super.equals(object);
    }
}

