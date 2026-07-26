package gg.vape.event;

import gg.vape.event.EventHandlerInvoker;
import gg.vape.event.EventListener;
import gg.vape.event.GeneratedEventHandlerInvokerMarker;
import gg.vape.event.IEvent;
import gg.vape.mapping.access.GeneratedAccessorFactory;
import java.lang.reflect.Method;

public class GeneratedEventHandlerInvoker
implements EventHandlerInvoker {
    private final Class<? extends IEvent> N;
    private final GeneratedEventHandlerInvokerMarker e;
    private final EventListener j;
    static final boolean A = !GeneratedEventHandlerInvoker.class.desiredAssertionStatus();

    public GeneratedEventHandlerInvoker(EventListener eventListener, Class<? extends IEvent> clazz, Method method) throws InstantiationException, IllegalAccessException {
        this.j = eventListener;
        this.N = clazz;
        Class<? extends GeneratedEventHandlerInvokerMarker> clazz2 = GeneratedAccessorFactory.N(method.getDeclaringClass(), method);
        if (!A && clazz2 == null) {
            throw new AssertionError();
        }
        this.e = clazz2.newInstance();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public <T extends IEvent> void B(T t) {
        this.e.C(this.j, t);
    }
}

