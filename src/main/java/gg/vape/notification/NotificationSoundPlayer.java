package gg.vape.notification;

import gg.vape.Vape;
import gg.vape.notification.SoundClip;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.concurrent.atomic.AtomicReference;

public class NotificationSoundPlayer {
    private static int[] A;
    private final AtomicReference<SoundClip> r = new AtomicReference();

    public NotificationSoundPlayer() {
        this.e();
    }

    static {
        if (NotificationSoundPlayer.v() == null) {
            NotificationSoundPlayer.j(new int[3]);
        }
    }

    public void P() {
        if (this.r.get() != null) {
            SoundClip soundClip = this.r.get();
            this.r.set(null);
            if (!this.s()) {
                soundClip.E(this.B());
            }
        }
    }

    public boolean s() {
        return Vape.INSTANCE.getPublicProfileSettings().m.L();
    }

    public static int[] v() {
        return A;
    }

    public static void j(int[] nArray) {
        A = nArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public float B() {
        return ((Double)Vape.INSTANCE.getPublicProfileSettings().h.K()).floatValue();
    }

    public void q(SoundClip soundClip) {
        this.r.set(soundClip);
    }

    public void e() {
        new Thread(this::lambda$startSoundThread$0).start();
    }

    private void lambda$startSoundThread$0() {
        while (!Vape.INSTANCE.enabled) {
            try {
                Thread.sleep(100L);
                this.P();
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
    }
}

