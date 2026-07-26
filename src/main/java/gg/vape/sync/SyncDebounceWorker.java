package gg.vape.sync;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.utils.MathUtil;
import gg.vape.utils.SleepUtil;

public class SyncDebounceWorker
implements Runnable {
    private long j = 3000L;
    private long z;

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            this.p();
        }
    }

    public void I() {
        this.z = System.currentTimeMillis();
    }

    public long k() {
        return this.z;
    }

    public double m() {
        double d = this.z + this.j;
        double d2 = System.currentTimeMillis();
        return MathUtil.clamp((d2 - (double)this.z) / (d - (double)this.z), 0.0, 1.0);
    }

    void p() {
        try {
            SleepUtil.sleep(1000L);
            if (!Vape.INSTANCE.getPublicProfileSettings().o.L().booleanValue()) {
                return;
            }
            if (!ClientSettings.fW.l$src$Z$1gzcm82() && !ClientSettings.fW.P) {
                return;
            }
            long l = this.z;
            if (Vape.INSTANCE.getSyncThread().a$src$Z$5edl1q()) {
                SleepUtil.sleep(this.j);
                if (this.z == l) {
                    Vape.INSTANCE.getSyncThread().o();
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

