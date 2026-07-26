package gg.vape.sync;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import java.util.concurrent.atomic.AtomicBoolean;

public class SyncStoreRequestWorker
implements Runnable {
    private final AtomicBoolean R;
    private final TimerUtil C = new TimerUtil();

    public void Y() {
        this.R.set(true);
    }

    public SyncStoreRequestWorker() {
        this.R = new AtomicBoolean();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (!this.R.get()) continue;
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            Vape.INSTANCE.getSyncThread().z();
            this.R.set(false);
        }
    }
}

