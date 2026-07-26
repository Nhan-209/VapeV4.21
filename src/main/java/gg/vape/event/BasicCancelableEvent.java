package gg.vape.event;

import gg.vape.event.ICancelableEvent;

public abstract class BasicCancelableEvent
implements ICancelableEvent {
    private boolean y;

    @Override
    public void setCancelled(boolean bl) {
        this.y = bl;
    }

    @Override
    public boolean isCanceled() {
        return this.y;
    }
}

