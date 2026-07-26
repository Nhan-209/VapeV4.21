package gg.vape.event;

import gg.vape.event.EventHandler;
import gg.vape.event.EventHandlerEntry;
import gg.vape.event.EventHandlerInvoker;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.ICancelableEvent;
import gg.vape.event.IEvent;
import gg.vape.event.impl.EventPreTick;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.jetbrains.annotations.UnmodifiableView;

public class EventListenerRegistration {
    private final Map<Class<? extends IEvent>, ArrayList<EventHandlerEntry>> H = new LinkedHashMap<Class<? extends IEvent>, ArrayList<EventHandlerEntry>>();
    private static String q;
    private final Predicate<IEvent>[] Z;
    private final EventListener A;

    public EventListenerRegistration(EventListener eventListener, Predicate<IEvent> ... predicateArray) {
        this.A = eventListener;
        this.Z = predicateArray;
        this.discoverHandlers();
    }

    public EventListener getListener() {
        return this.A;
    }

    public @UnmodifiableView Collection<Class<? extends IEvent>> getEventTypes() {
        return this.H.keySet();
    }

    public static String M() {
        return q;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    static {
        if (EventListenerRegistration.M() != null) {
            EventListenerRegistration.C("HxeBW");
        }
    }

    private void discoverHandlers() {
        try {
            for (Method method : this.A.getClass().getMethods()) {
                Class<?> clazz;
                EventHandler eventHandler = method.getDeclaredAnnotation(EventHandler.class);
                if (eventHandler == null || method.getParameterCount() != 1 || !IEvent.class.isAssignableFrom(clazz = method.getParameterTypes()[0])) continue;
                Class<? extends IEvent> clazz2 = clazz.asSubclass(IEvent.class);
                EventHandlerInvoker eventHandlerInvoker = EventHandlerInvoker.create(this.A, clazz2, method);
                EventHandlerEntry eventHandlerEntry = new EventHandlerEntry(this, eventHandler.A(), eventHandler.b(), eventHandlerInvoker);
                List<EventHandlerEntry> list = this.H.computeIfAbsent(clazz2, EventListenerRegistration::lambda$processHandlers$0);
                list.add(eventHandlerEntry);
            }
            for (List list : this.H.values()) {
                list.sort(Comparator.comparing(EventHandlerEntry::F));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void C(String string) {
        q = string;
    }

    public boolean passesFilters(IEvent iEvent) {
        for (Predicate<IEvent> predicate : this.Z) {
            if (predicate.test(iEvent)) continue;
            return false;
        }
        return true;
    }

    public <T extends IEvent> T dispatch(T t, EventPriority eventPriority) {
        boolean bl = t instanceof EventPreTick;
        ArrayList<EventHandlerEntry> arrayList = this.H.get(t.getClass());
        if (arrayList == null || arrayList.isEmpty()) {
            return t;
        }
        ICancelableEvent iCancelableEvent = t instanceof ICancelableEvent ? (ICancelableEvent)t : null;
        int n = arrayList.size();
        for (int i = 0; i < n; ++i) {
            EventHandlerEntry eventHandlerEntry = arrayList.get(i);
            if (!eventHandlerEntry.F().equals((Object)eventPriority) || eventHandlerEntry.y() && iCancelableEvent != null && iCancelableEvent.isCanceled()) continue;
            eventHandlerEntry.X(t);
        }
        return t;
    }

    private static ArrayList<EventHandlerEntry> lambda$processHandlers$0(Class<? extends IEvent> clazz) {
        return new ArrayList<>();
    }
}
