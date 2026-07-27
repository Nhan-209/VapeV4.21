package gg.vape.utils;

import java.util.TreeMap;

public class RomanNumeralUtil {
    private static final TreeMap<Integer, String> p;

    private static int romanDigitValue(char c) {
        if (c == 'I') {
            return 1;
        }
        if (c == 'V') {
            return 5;
        }
        if (c == 'X') {
            return 10;
        }
        if (c == 'L') {
            return 50;
        }
        if (c == 'C') {
            return 100;
        }
        if (c == 'D') {
            return 500;
        }
        return c == 'M' ? 1000 : -1;
    }


    private static String a(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            byte by;
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                by = byArray[++i];
                c = (char)(c | (char)(by & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            by = byArray[++i];
            c = (char)(c | (char)(by & 0x3F) << 6);
            by = byArray[++i];
            c = (char)(c | (char)(by & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    public static String toRoman(int n) {
        if (n < 0) {
            return String.valueOf(n);
        }
        int n2 = p.floorKey(n);
        if (n == n2) {
            return p.get(n);
        }
        return p.get(n2) + RomanNumeralUtil.toRoman(n - n2);
    }

    public static int fromRoman(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); ++i) {
            int n2 = RomanNumeralUtil.romanDigitValue(string.charAt(i));
            if (i + 1 < string.length()) {
                int n3 = RomanNumeralUtil.romanDigitValue(string.charAt(i + 1));
                if (n2 >= n3) {
                    n += n2;
                    continue;
                }
                n = n + n3 - n2;
                ++i;
                continue;
            }
            n += n2;
            ++i;
        }
        return n;
    }

    static {
        try {
            String[] stringArray = new String[]{"IV", "CM", "XC", "XL", "IX", "CD"};
            p = new TreeMap();
            p.put(1000, "M");
            p.put(900, stringArray[1]);
            p.put(500, "D");
            p.put(400, stringArray[5]);
            p.put(100, "C");
            p.put(90, stringArray[2]);
            p.put(50, "L");
            p.put(40, stringArray[3]);
            p.put(10, "X");
            p.put(9, stringArray[4]);
            p.put(5, "V");
            p.put(4, stringArray[0]);
            p.put(1, "I");
            p.put(0, "");
        }
        catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}

