package gg.vape.runtime.obfuscation;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import java.lang.invoke.MethodHandles;

public class DecryptedStringPairConstants {
    public static final String I;
    public static final String F;

    static {
        long l = ZkmLongKeyState.a(-5042964237183488681L, 3662167289083262016L, MethodHandles.lookup().lookupClass()).a(139237487593677L) ^ 0x3C76E29D342BL;
        String[] stringArray = new String[]{"zeus-prod.vape.gg:8091", "https://online.vape.gg"};
        F = stringArray[0];
        I = stringArray[1];
    }

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
}

