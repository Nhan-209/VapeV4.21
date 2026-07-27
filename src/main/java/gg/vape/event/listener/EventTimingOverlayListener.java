package gg.vape.event.listener;

import gg.vape.Vape;
import gg.vape.event.EventBus;
import gg.vape.event.EventDispatchTrace;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventListenerTiming;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.listener.EventTimingDisplayLine;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventTimingOverlayListener
implements EventListener {
    private static String[] j;
    private TimerUtil O = new TimerUtil();
    private List<String> A = new ArrayList<String>();
    public static EventTimingOverlayListener e;

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        for (int i = 0; i < this.A.size(); ++i) {
            SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().D(12, false);
            double d = smoothFontRenderer.N(this.A.get(i));
            GuiRenderPrimitives.C(10.0, 8 + i * 8, d, 8.0, Color.BLACK);
            smoothFontRenderer.g(this.A.get(i), 10.0, 8 + i * 8, -1);
        }
    }

    private static Map<Class<?>, List<Long>> lambda$getTimings$0(Class<?> clazz) {
        return new ConcurrentHashMap<Class<?>, List<Long>>();
    }

    static {
        e = new EventTimingOverlayListener();
        EventTimingOverlayListener.w(null);
    }

    public static void w(String[] stringArray) {
        j = stringArray;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.O.hasTimeElapsed(3000L)) {
            this.P();
            this.O.reset();
        }
    }

    public static String[] s() {
        return j;
    }


    private void P() {
        this.A.clear();
        ConcurrentHashMap<Class<?>, Map<Class<?>, List<Long>>> concurrentHashMap = new ConcurrentHashMap<Class<?>, Map<Class<?>, List<Long>>>();
        for (EventDispatchTrace trace : EventBus.getInstance().getTimingHistory().getTraces().keySet()) {
            for (EventListenerTiming eventListenerTiming : trace.getListenerTimings()) {
                Class<?> clazz = eventListenerTiming.getRegistration().getListener().getClass();
                concurrentHashMap.computeIfAbsent(clazz, EventTimingOverlayListener::lambda$getTimings$0).computeIfAbsent(trace.getEventType(), EventTimingOverlayListener::lambda$getTimings$1).add(eventListenerTiming.getDurationNanos());
            }
        }
        ArrayList<EventTimingDisplayLine> arrayList = new ArrayList<EventTimingDisplayLine>();
        for (Map.Entry<Class<?>, Map<Class<?>, List<Long>>> entry : concurrentHashMap.entrySet()) {
            for (Map.Entry<Class<?>, List<Long>> entry2 : entry.getValue().entrySet()) {
                long l = entry2.getValue().stream().mapToLong(Long::longValue).sum() / 1000L;
                long l2 = (long)(entry2.getValue().stream().mapToLong(Long::longValue).average().orElse(0.0) / 1000.0);
                arrayList.add(new EventTimingDisplayLine(l + " " + l2 + " " + entry.getKey().getSimpleName() + " " + entry2.getKey().getName(), l));
            }
        }
        arrayList.sort(Comparator.comparingLong(EventTimingDisplayLine::h).reversed());
        this.A = arrayList.stream().map(EventTimingDisplayLine::Y).collect(Collectors.toList());
    }

    private static List<Long> lambda$getTimings$1(Class<?> clazz) {
        return new ArrayList<Long>();
    }
}
