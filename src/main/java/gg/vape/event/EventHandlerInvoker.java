package gg.vape.event;

import gg.vape.event.EventListener;
import gg.vape.event.GeneratedEventHandlerInvoker;
import gg.vape.event.IEvent;
import gg.vape.event.ReflectiveEventHandlerInvoker;
import java.lang.reflect.Method;

public interface EventHandlerInvoker {
    public <T extends IEvent> void B(T var1);

    public static EventHandlerInvoker create(EventListener eventListener, Class<? extends IEvent> clazz, Method method) {
        try {
            return new GeneratedEventHandlerInvoker(eventListener, clazz, method);
        }
        catch (Throwable throwable) {
            return new ReflectiveEventHandlerInvoker(eventListener, clazz, method);
        }
    }
}

