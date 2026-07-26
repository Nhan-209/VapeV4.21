package gg.vape.mapping;

import gg.vape.event.EventBus;
import gg.vape.event.IEvent;
import gg.vape.mapping.InsertedCallbackEventMarker;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import java.lang.reflect.Method;

public class EventInjectionSpec {
    private static GuiComponent[] g;
    private String c = null;
    private boolean w = true;
    private Class N;
    private boolean G = true;
    static int C;
    private String J;
    private final MappingMethod z;
    private String R = "";
    private final Class Q;
    private String S;

    public void d(String string) {
        this.c = string;
    }

    public MappingMethod F() {
        return this.z;
    }

    public void f(String string) {
        this.J = string;
    }

    public void v(Class clazz) {
        this.N = clazz;
    }

    public boolean s() {
        return this.w;
    }

    public String n() {
        boolean bl;
        Object object;
        Object object2 = "";
        String string = "";
        String string2 = "";
        if (this.G) {
            string2 = "event" + ++C;
            String string3 = "res" + string2;
            string = this.Q.getName() + " " + string2 + " = new " + this.Q.getName() + "(" + (this.c == null ? "" : this.c) + ");\n";
            object2 = object = "boolean " + string3 + " = " + string2 + "." + EventBus.getFireMethod(this.Q).getName() + "();";
            object2 = (String)object2 + "if(" + string3 + ") { return " + this.R.replace("$event", string2) + "; } ";
        } else {
            object2 = "new " + this.Q.getName() + "(" + (this.c == null ? "" : this.c) + ")." + EventBus.getFireMethod(this.Q).getName() + "();";
        }
        if (this.J != null) {
            object2 = "if (" + this.J + ") { " + (String)object2 + " }";
        }
        string = string + (String)object2;
        if (this.S != null) {
            string = string + this.S.replace("$event", string2);
        }
        if (this.N != null) {
            string = "if($0 instanceof " + this.N.getName() + ") { " + string + " }";
        }
        boolean bl2 = bl = IEvent.class.isAssignableFrom(this.Q) && InsertedCallbackEventMarker.class.isAssignableFrom(this.Q);
        if (bl) {
            object = EventBus.findEventListenersAccessor(this.Q);
            string = "if(" + this.Q.getName() + "#" + ((Method)object).getName() + "()." + EventBus.getHasListenersMethod().getName() + "()) { " + string + "}";
        }
        string = "{ " + string + " }";
        return string;
    }

    public EventInjectionSpec(MappingMethod mappingMethod, Class clazz) {
        this.z = mappingMethod;
        this.Q = clazz;
    }

    static {
        EventInjectionSpec.o(null);
        C = 0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void V(String string) {
        this.S = string;
    }

    public void o(boolean bl) {
        this.G = bl;
    }

    public static void o(GuiComponent[] guiComponentArray) {
        g = guiComponentArray;
    }

    public Class v() {
        return this.Q;
    }

    public void H(String string) {
        this.R = string;
    }

    public void Z(boolean bl) {
        this.w = bl;
    }

    public static GuiComponent[] F$src$ALgg_vape_ui_click_component_GuiComponent_$1y0d4sz() {
        return g;
    }
}

