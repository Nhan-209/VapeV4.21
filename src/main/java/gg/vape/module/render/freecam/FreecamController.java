package gg.vape.module.render.freecam;

import gg.vape.Vape;
import gg.vape.event.EventListener;
import gg.vape.module.Mod;

public abstract class FreecamController<T extends Mod>
implements EventListener {
    protected static final Vape i = Vape.INSTANCE;
    protected final T n;

    public FreecamController(T mod) {
        this.n = mod;
    }

    public T l() {
        return this.n;
    }

    public void B() {
    }

    public void I() {
    }
}

