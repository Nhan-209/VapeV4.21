package gg.vape.sync;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.utils.SleepUtil;

public class SyncDebounceWorker
implements Runnable {
    private static final long DEBOUNCE_MILLIS = 3000L;
    private long lastChangeTime;

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            this.processPendingSave();
        }
    }

    public void markChanged() {
        this.lastChangeTime = System.currentTimeMillis();
    }

    private void processPendingSave() {
        try {
            SleepUtil.sleep(1000L);
            if (!Vape.INSTANCE.getPublicProfileSettings().o.L()) {
                return;
            }
            if (!ClientSettings.fW.l$src$Z$1gzcm82() && !ClientSettings.fW.P) {
                return;
            }
            long observedChangeTime = this.lastChangeTime;
            if (Vape.INSTANCE.getSyncThread().hasPendingSave()) {
                SleepUtil.sleep(DEBOUNCE_MILLIS);
                if (this.lastChangeTime == observedChangeTime) {
                    Vape.INSTANCE.getSyncThread().requestSave();
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }
}
