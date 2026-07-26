package gg.vape.event;

import gg.vape.event.EventHandlerInvoker;
import gg.vape.event.EventListener;
import gg.vape.event.IEvent;
import gg.vape.mapping.MappingMethod;
import java.lang.reflect.Method;

public class ReflectiveEventHandlerInvoker
implements EventHandlerInvoker {
    private final MappingMethod H;
    private final EventListener m;
    private final Class<? extends IEvent> p;
    private static String[] V;

    public static String[] c() {
        return V;
    }

    public static void u(String[] stringArray) {
        V = stringArray;
    }

    static {
        if (ReflectiveEventHandlerInvoker.c() != null) {
            ReflectiveEventHandlerInvoker.u(new String[5]);
        }
    }

    @Override
    public <T extends IEvent> void B(T t) {
        this.H.c(this.m, t);
    }

    public ReflectiveEventHandlerInvoker(EventListener eventListener, Class<? extends IEvent> clazz, Method method) {
        this.m = eventListener;
        this.p = clazz;
        MappingMethod mappingMethod = new MappingMethod(null, eventListener.getClass(), method.getName(), false, false, false, Void.TYPE, clazz);
        this.H = mappingMethod.g();
    }
}

