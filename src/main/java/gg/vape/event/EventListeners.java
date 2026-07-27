package gg.vape.event;

import gg.vape.ui.click.component.GuiComponent;
import java.util.concurrent.atomic.AtomicInteger;

public class EventListeners {
    private final AtomicInteger r = new AtomicInteger(0);
    private static GuiComponent[] D;

    public static GuiComponent[] V() {
        return D;
    }

    public void incrementListenerCount() {
        this.r.incrementAndGet();
    }

    public boolean hasListeners() {
        boolean bl = this.r.get() > 0;
        return bl;
    }

    public static void R(GuiComponent[] upArray) {
        D = upArray;
    }


    public void decrementListenerCount() {
        this.r.decrementAndGet();
    }

    static {
        if (EventListeners.V() != null) {
            EventListeners.R(new GuiComponent[2]);
        }
    }
}

