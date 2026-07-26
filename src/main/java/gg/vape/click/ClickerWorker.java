package gg.vape.click;

import gg.vape.Vape;
import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.module.combat.ClickerMod;
import gg.vape.utils.SleepUtil;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClickerWorker
implements Runnable {
    private final AtomicBoolean H = new AtomicBoolean(false);
    private final Object j = new Object();
    private boolean k;
    private final ClickerMod u;

    private static InterruptedException a(InterruptedException interruptedException) {
        return interruptedException;
    }

    public void K() {
        if (!this.k) {
            this.k = true;
            Thread thread = new Thread(this);
            thread.start();
        }
        this.C();
    }

    public ClickerWorker(ClickerMod clickerMod) {
        this.u = clickerMod;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void F() {
        ClickEngine clickEngine = this.u.s;
        try {
            Object object = this.j;
            synchronized (object) {
                if (!this.H.get()) {
                    try {
                        this.j.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            if (clickEngine.s() == ClickButton.LEFT && this.u.C()) {
                return;
            }
            ClickerMod clickerMod = this.u;
            clickerMod.U$src$V$ml8mr6();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void run() {
        while (true) {
            SleepUtil.sleep(5L);
            this.F();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void C() {
        Object object = this.j;
        synchronized (object) {
            this.H.set(true);
            this.j.notify();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void W() {
        Object object = this.j;
        synchronized (object) {
            this.H.set(false);
        }
    }
}

