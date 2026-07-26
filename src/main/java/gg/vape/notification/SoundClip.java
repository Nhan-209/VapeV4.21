package gg.vape.notification;

import gg.vape.Vape;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundClip {
    private static final String[] S = new String[]{".wav", ".au", ".aif", ".aiff"};
    private final byte[] u;
    private Clip Q;

    public ByteArrayInputStream x() {
        return new ByteArrayInputStream(this.u);
    }

    private static byte[] w(String string) {
        if (string.contains(".")) {
            String string2 = "sounds/" + string;
            byte[] byArray = Vape.readResource(string2);
            if (byArray != null) {
                return byArray;
            }
            throw new IllegalArgumentException("Missing sound resource: " + string);
        }
        for (String string3 : S) {
            String string4 = "sounds/" + string + string3;
            byte[] byArray = Vape.readResource(string4);
            if (byArray == null) continue;
            return byArray;
        }
        throw new IllegalArgumentException("Missing sound resource with supported extensions: " + string);
    }

    public SoundClip(String string) {
        this.u = SoundClip.w(string);
    }

    public void E(float f) {
        Object object;
        if (this.Q == null) {
            try {
                object = AudioSystem.getAudioInputStream(this.x());
                this.Q = AudioSystem.getClip();
                this.Q.open((AudioInputStream)object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        if (this.Q == null) {
            return;
        }
        object = (FloatControl)this.Q.getControl(FloatControl.Type.MASTER_GAIN);
        ((FloatControl)object).setValue(20.0f * (float)Math.log10((double)f / 100.0));
        this.Q.setFramePosition(0);
        this.Q.start();
        try {
            Thread.sleep(this.Q.getMicrosecondLength() / 1000L);
        }
        catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

