package gg.vape.module;

import gg.vape.Vape;
import gg.vape.module.Mod;

public class DelayedModuleToggleTask
implements Runnable {
    private final boolean u;
    private final Mod R;
    private final long U;
    private boolean Z = true;

    public DelayedModuleToggleTask(Mod mod, long l, boolean bl) {
        this.R = mod;
        this.U = l;
        this.u = bl;
    }

    public boolean z() {
        return this.Z;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public void y(boolean bl) {
        this.Z = bl;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(this.U);
                if (this.R.r$src$Z$14eylz9()) {
                    this.R.onScheduledAction();
                }
            }
            catch (Exception ignored) {
            }
        } while (!Vape.INSTANCE.isEnabled() && this.u && this.Z);
    }
}
