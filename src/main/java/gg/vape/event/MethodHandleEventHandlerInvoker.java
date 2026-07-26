package gg.vape.event;

import gg.vape.event.EventHandlerInvoker;
import gg.vape.event.EventListener;
import gg.vape.event.IEvent;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class MethodHandleEventHandlerInvoker
implements EventHandlerInvoker {
    private final EventListener d;
    private final MethodHandle s;
    private final Class<? extends IEvent> b;

    @Override
    public <T extends IEvent> void B(T t) {
        try {
            this.s.invoke(this.d, t);
        }
        catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public MethodHandleEventHandlerInvoker(EventListener eventListener, Class<? extends IEvent> clazz, Method method) {
        this.d = eventListener;
        this.b = clazz;
        try {
            this.s = MethodHandles.lookup().unreflect(method);
        }
        catch (IllegalAccessException illegalAccessException) {
            throw new RuntimeException(illegalAccessException);
        }
    }
}

