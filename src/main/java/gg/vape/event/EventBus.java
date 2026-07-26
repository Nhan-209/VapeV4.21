package gg.vape.event;

import gg.vape.event.EventDispatchTrace;
import gg.vape.event.EventListener;
import gg.vape.event.EventListenerRegistration;
import gg.vape.event.EventListenerTiming;
import gg.vape.event.EventListeners;
import gg.vape.event.EventPriority;
import gg.vape.event.EventTimingHistory;
import gg.vape.event.IEvent;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class EventBus {
    public static boolean y;
    private static int s;
    private static Method Y;
    private final EventTimingHistory E;
    private static Method p;
    private final Map<Class<? extends IEvent>, EventListeners> x = new LinkedHashMap<Class<? extends IEvent>, EventListeners>();
    private final Map<EventListener, EventListenerRegistration> k = new LinkedHashMap<EventListener, EventListenerRegistration>();
    private static final EventBus n;
    private final Map<Class<? extends IEvent>, ArrayList<EventListenerRegistration>> j = new LinkedHashMap<Class<? extends IEvent>, ArrayList<EventListenerRegistration>>();

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public static void x(int n) {
        s = n;
    }

    public EventTimingHistory getTimingHistory() {
        return this.E;
    }

    public static Method getHasListenersMethod() {
        if (Y == null) {
            for (Method method : EventListeners.class.getDeclaredMethods()) {
                if (method.getReturnType() != Boolean.TYPE || method.getParameterCount() != 0) continue;
                Y = method;
                break;
            }
        }
        return Y;
    }

    private EventListeners resolveEventListeners(Class<? extends IEvent> clazz) {
        EventListeners eventListeners = this.x.get(clazz);
        if (eventListeners != null) {
            return eventListeners;
        }
        try {
            Method method = EventBus.findEventListenersAccessor(clazz);
            if (method == null) {
                return null;
            }
            EventListeners eventListeners2 = (EventListeners)method.invoke(null, new Object[0]);
            this.x.put(clazz, eventListeners2);
            return eventListeners2;
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    static {
        n = new EventBus();
        y = false;
        EventBus.x(32);
    }

    public static int O() {
        return s;
    }

    public static int X() {
        int n = EventBus.O();
        return 0;
    }

    public Map<Class<? extends IEvent>, ArrayList<EventListenerRegistration>> getRegistrationsByEventType() {
        return this.j;
    }

    public static Method findEventListenersAccessor(Class<? extends IEvent> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getReturnType() != EventListeners.class || method.getParameterCount() != 0 || !Modifier.isStatic(method.getModifiers())) continue;
            return method;
        }
        if (IEvent.class.isAssignableFrom(clazz.getSuperclass())) {
            return EventBus.findEventListenersAccessor(clazz.getSuperclass().asSubclass(IEvent.class));
        }
        return null;
    }

    public <T extends IEvent> T post(T t) {
        EventDispatchTrace eventDispatchTrace = null;
        if (y) {
            eventDispatchTrace = new EventDispatchTrace(t.getClass());
        }
        try {
            ArrayList<EventListenerRegistration> arrayList = this.j.get(t.getClass());
            if (arrayList != null && !arrayList.isEmpty()) {
                ArrayList<EventListenerRegistration> arrayList2 = new ArrayList<EventListenerRegistration>();
                for (int i = 0; i < arrayList.size(); ++i) {
                    EventListenerRegistration eventListenerRegistration = arrayList.get(i);
                    if (!eventListenerRegistration.passesFilters(t)) continue;
                    arrayList2.add(eventListenerRegistration);
                }
                if (!arrayList2.isEmpty()) {
                    for (EventPriority eventPriority : EventPriority.values()) {
                        for (int i = 0; i < arrayList2.size(); ++i) {
                            try {
                                EventListenerRegistration eventListenerRegistration = (EventListenerRegistration)arrayList2.get(i);
                                EventListenerTiming eventListenerTiming = null;
                                if (y) {
                                    eventListenerTiming = new EventListenerTiming(eventListenerRegistration);
                                }
                                eventListenerRegistration.dispatch(t, eventPriority);
                                if (!y) continue;
                                eventListenerTiming.finish();
                                eventDispatchTrace.addListenerTiming(eventListenerTiming);
                                continue;
                            }
                            catch (Throwable throwable) {
                                // empty catch block
                            }
                        }
                    }
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (y) {
            eventDispatchTrace.finish();
            this.E.addTrace(eventDispatchTrace);
        }
        return t;
    }

    private static ArrayList lambda$registerListener$0(Class clazz) {
        return new ArrayList();
    }

    public boolean unregisterListener(EventListener eventListener) {
        if (eventListener == null) {
            return false;
        }
        EventListenerRegistration eventListenerRegistration = this.k.remove(eventListener);
        if (eventListenerRegistration == null) {
            return false;
        }
        Collection<Class<? extends IEvent>> collection = eventListenerRegistration.getEventTypes();
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        for (Class<? extends IEvent> clazz : collection) {
            List list = this.j.get(clazz);
            if (list == null) continue;
            list.remove(eventListenerRegistration);
            this.resolveEventListeners(clazz).decrementListenerCount();
        }
        return true;
    }

    public static EventBus getInstance() {
        return n;
    }

    public EventBus() {
        this.E = new EventTimingHistory();
    }

    @SafeVarargs
    public final void registerListener(EventListener eventListener, Predicate<IEvent> ... predicateArray) {
        if (this.k.containsKey(eventListener)) {
            return;
        }
        EventListenerRegistration eventListenerRegistration = new EventListenerRegistration(eventListener, predicateArray);
        this.k.put(eventListener, eventListenerRegistration);
        for (Class<? extends IEvent> clazz : eventListenerRegistration.getEventTypes()) {
            this.j.computeIfAbsent(clazz, EventBus::lambda$registerListener$0).add(eventListenerRegistration);
            this.resolveEventListeners(clazz).incrementListenerCount();
        }
    }

    public static Method getFireMethod(Class<?> clazz) {
        if (p == null) {
            for (Method method : IEvent.class.getDeclaredMethods()) {
                if (method.getReturnType() != Boolean.TYPE || method.getParameterCount() != 0) continue;
                p = method;
                break;
            }
        }
        return p;
    }
}
