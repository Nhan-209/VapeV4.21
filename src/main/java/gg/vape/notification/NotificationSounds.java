package gg.vape.notification;

import gg.vape.notification.SoundClip;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;

public class NotificationSounds {
    public static final SoundClip P;
    public static final SoundClip F;
    public static final SoundClip N;

    private static String a(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    static {
        try {
            long l = ZkmLongKeyState.a(7613601523163251817L, -6827259627466016642L, MethodHandles.lookup().lookupClass()).a(233770857830877L) ^ 0x18A84F8A3F2FL;
            String[] stringArray = new String[]{"party_invite", "ping", "message_rec"};
            N = new SoundClip(stringArray[1]);
            P = new SoundClip(stringArray[0]);
            F = new SoundClip(stringArray[2]);
        }
        catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}

