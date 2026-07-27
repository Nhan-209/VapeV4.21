package gg.vape.utils;

import gg.vape.runtime.ObfuscatedRuntimeException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {
    private static boolean N;
    private static int[] s;
    private static final char[] p;

    public static String encodeBase64(byte[] byArray) {
        int n = byArray.length;
        byte[] byArray2 = new byte[byArray.length * 4];
        int n2 = 0;
        int n3 = -6;
        int n4 = 0;
        for (int i = 0; i < n; ++i) {
            n2 = (n2 << 8) + byArray[i];
            n3 += 8;
            while (n3 >= 0) {
                byArray2[n4++] = (byte)p[n2 >> n3 & 0x3F];
                n3 -= 6;
            }
        }
        if (n3 > -6) {
            byArray2[n4++] = (byte)p[n2 << 8 >> n3 + 8 & 0x3F];
        }
        while (n4 % 4 != 0) {
            byArray2[n4++] = 61;
        }
        byArray2[n4] = 0;
        byte[] byArray3 = new byte[n4];
        System.arraycopy(byArray2, 0, byArray3, 0, n4);
        return new String(byArray3);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static byte[] decodeBase64(String string) {
        char c;
        int n;
        byte[] byArray = new byte[string.length() + 1];
        int n2 = 0;
        if (!N) {
            s = new int[256];
            for (n = 0; n < 256; ++n) {
                Base64Util.s[n] = -1;
            }
            for (n = 0; n < 64; ++n) {
                Base64Util.s[Base64Util.p[n]] = n;
            }
            N = true;
        }
        n = 0;
        int n3 = -8;
        for (int i = 0; i < string.length() && s[c = string.charAt(i)] != -1; ++i) {
            n = (n << 6) + s[c];
            if ((n3 += 6) < 0) continue;
            byArray[n2++] = (byte)(n >> n3 & 0xFF);
            n3 -= 8;
        }
        byArray[n2] = 0;
        return byArray;
    }

    public static String encodeUtf8Base64(String string) {
        return new String(Base64.getEncoder().encode(string.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public static String decodeUtf8Base64(String string) {
        return new String(Base64.getDecoder().decode(string.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
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

    static {
        try {
            String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
            p = string.toCharArray();
        }
        catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}

