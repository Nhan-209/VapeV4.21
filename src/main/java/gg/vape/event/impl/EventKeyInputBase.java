package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.ui.click.component.GuiComponent;

public class EventKeyInputBase
extends Event {
    private static final EventListeners q = new EventListeners();
    private static GuiComponent[] Q;
    private final boolean B;
    private final int V;

    @Override
    public EventListeners getListeners() {
        return q;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public static GuiComponent[] d() {
        return Q;
    }

    public EventKeyInputBase(int n, boolean bl) {
        this.V = n;
        this.B = bl;
    }

    public static EventListeners getEventListeners() {
        return q;
    }

    public boolean isDown() {
        return this.B;
    }

    public static void S(GuiComponent[] upArray) {
        Q = upArray;
    }

    public int getKey() {
        return this.V;
    }

    static {
        EventKeyInputBase.S(new GuiComponent[1]);
    }
}

