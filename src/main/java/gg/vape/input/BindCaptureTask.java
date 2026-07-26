package gg.vape.input;

import gg.vape.input.BindCaptureThread;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.Bendable;

public abstract class BindCaptureTask
implements Runnable {
    private BindCaptureThread S;
    private Bendable d;

    @Override
    public void run() {
        if (this.S == null) {
            this.S = new BindCaptureThread(this, null);
            this.S.start();
        }
    }

    public BindCaptureTask(Bendable bendable) {
        this.d = bendable;
    }

    public void B(Bendable bendable) {
        this.d = bendable;
    }

    public boolean boolean_V() {
        boolean bl = this.S != null;
        return bl;
    }

    public final void p() {
        this.S = null;
        this.void_V();
    }

    static Bendable Z(BindCaptureTask bindCaptureTask) {
        return bindCaptureTask.d;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public abstract void void_V();

    public /* synthetic */ boolean V$src$Z$xc25df() {
        return this.boolean_V();
    }
}

