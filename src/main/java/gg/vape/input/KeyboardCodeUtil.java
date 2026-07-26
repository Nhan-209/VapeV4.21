package gg.vape.input;

import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import org.lwjgl.input.Keyboard;

public class KeyboardCodeUtil {
    static int[] W;
    private static final String b;

    static {
        b = "Grave";
        W = new int[10000];
        KeyboardCodeUtil.W[1] = 27;
        KeyboardCodeUtil.W[2] = 49;
        KeyboardCodeUtil.W[3] = 50;
        KeyboardCodeUtil.W[4] = 51;
        KeyboardCodeUtil.W[5] = 52;
        KeyboardCodeUtil.W[6] = 53;
        KeyboardCodeUtil.W[7] = 54;
        KeyboardCodeUtil.W[8] = 55;
        KeyboardCodeUtil.W[9] = 56;
        KeyboardCodeUtil.W[10] = 57;
        KeyboardCodeUtil.W[11] = 48;
        KeyboardCodeUtil.W[12] = 189;
        KeyboardCodeUtil.W[13] = 187;
        KeyboardCodeUtil.W[14] = 8;
        KeyboardCodeUtil.W[15] = 9;
        KeyboardCodeUtil.W[16] = 81;
        KeyboardCodeUtil.W[17] = 87;
        KeyboardCodeUtil.W[18] = 69;
        KeyboardCodeUtil.W[19] = 82;
        KeyboardCodeUtil.W[20] = 84;
        KeyboardCodeUtil.W[21] = 89;
        KeyboardCodeUtil.W[22] = 85;
        KeyboardCodeUtil.W[23] = 73;
        KeyboardCodeUtil.W[24] = 79;
        KeyboardCodeUtil.W[25] = 80;
        KeyboardCodeUtil.W[26] = 219;
        KeyboardCodeUtil.W[27] = 221;
        KeyboardCodeUtil.W[28] = 13;
        KeyboardCodeUtil.W[29] = 162;
        KeyboardCodeUtil.W[30] = 65;
        KeyboardCodeUtil.W[31] = 83;
        KeyboardCodeUtil.W[32] = 68;
        KeyboardCodeUtil.W[33] = 70;
        KeyboardCodeUtil.W[34] = 71;
        KeyboardCodeUtil.W[35] = 72;
        KeyboardCodeUtil.W[36] = 74;
        KeyboardCodeUtil.W[37] = 75;
        KeyboardCodeUtil.W[38] = 76;
        KeyboardCodeUtil.W[39] = 186;
        KeyboardCodeUtil.W[40] = 222;
        KeyboardCodeUtil.W[41] = 192;
        KeyboardCodeUtil.W[42] = 160;
        KeyboardCodeUtil.W[43] = 220;
        KeyboardCodeUtil.W[44] = 90;
        KeyboardCodeUtil.W[45] = 88;
        KeyboardCodeUtil.W[46] = 67;
        KeyboardCodeUtil.W[47] = 86;
        KeyboardCodeUtil.W[48] = 66;
        KeyboardCodeUtil.W[49] = 78;
        KeyboardCodeUtil.W[50] = 77;
        KeyboardCodeUtil.W[51] = 188;
        KeyboardCodeUtil.W[52] = 190;
        KeyboardCodeUtil.W[53] = 191;
        KeyboardCodeUtil.W[54] = 161;
        KeyboardCodeUtil.W[55] = 106;
        KeyboardCodeUtil.W[56] = 164;
        KeyboardCodeUtil.W[57] = 32;
        KeyboardCodeUtil.W[58] = 20;
        KeyboardCodeUtil.W[59] = 112;
        KeyboardCodeUtil.W[60] = 113;
        KeyboardCodeUtil.W[61] = 114;
        KeyboardCodeUtil.W[62] = 115;
        KeyboardCodeUtil.W[63] = 116;
        KeyboardCodeUtil.W[64] = 117;
        KeyboardCodeUtil.W[65] = 118;
        KeyboardCodeUtil.W[66] = 119;
        KeyboardCodeUtil.W[67] = 120;
        KeyboardCodeUtil.W[68] = 121;
        KeyboardCodeUtil.W[69] = 144;
        KeyboardCodeUtil.W[70] = 145;
        KeyboardCodeUtil.W[71] = 103;
        KeyboardCodeUtil.W[72] = 104;
        KeyboardCodeUtil.W[73] = 105;
        KeyboardCodeUtil.W[74] = 109;
        KeyboardCodeUtil.W[75] = 100;
        KeyboardCodeUtil.W[76] = 101;
        KeyboardCodeUtil.W[77] = 102;
        KeyboardCodeUtil.W[78] = 107;
        KeyboardCodeUtil.W[79] = 97;
        KeyboardCodeUtil.W[80] = 98;
        KeyboardCodeUtil.W[81] = 99;
        KeyboardCodeUtil.W[82] = 96;
        KeyboardCodeUtil.W[83] = 110;
        KeyboardCodeUtil.W[87] = 122;
        KeyboardCodeUtil.W[88] = 123;
        KeyboardCodeUtil.W[100] = 124;
        KeyboardCodeUtil.W[101] = 125;
        KeyboardCodeUtil.W[102] = 126;
        KeyboardCodeUtil.W[103] = 127;
        KeyboardCodeUtil.W[104] = 128;
        KeyboardCodeUtil.W[105] = 129;
        KeyboardCodeUtil.W[112] = 21;
        KeyboardCodeUtil.W[113] = 130;
        KeyboardCodeUtil.W[121] = 28;
        KeyboardCodeUtil.W[123] = 29;
        KeyboardCodeUtil.W[141] = 146;
        KeyboardCodeUtil.W[146] = 186;
        KeyboardCodeUtil.W[156] = 13;
        KeyboardCodeUtil.W[157] = 163;
        KeyboardCodeUtil.W[179] = 188;
        KeyboardCodeUtil.W[184] = 165;
        KeyboardCodeUtil.W[197] = 19;
        KeyboardCodeUtil.W[199] = 36;
        KeyboardCodeUtil.W[200] = 38;
        KeyboardCodeUtil.W[201] = 33;
        KeyboardCodeUtil.W[203] = 37;
        KeyboardCodeUtil.W[205] = 39;
        KeyboardCodeUtil.W[207] = 35;
        KeyboardCodeUtil.W[208] = 40;
        KeyboardCodeUtil.W[209] = 34;
        KeyboardCodeUtil.W[210] = 45;
        KeyboardCodeUtil.W[211] = 46;
        KeyboardCodeUtil.W[218] = 12;
    }

    public static int M(int n, int n2) {
        boolean bl;
        int n3 = n;
        int n4 = (n2 & 0xFF0000) >> 16;
        boolean bl2 = bl = (n2 & 0x1000000) != 0;
        if (bl) {
            switch (n) {
                case 16: {
                    n3 = NativeBridge.mvk(n4, 3);
                    break;
                }
                case 17: {
                    n3 = 163;
                    break;
                }
                case 18: {
                    n3 = 165;
                    break;
                }
                default: {
                    n3 = n;
                }
            }
            return n3;
        }
        switch (n) {
            case 16: {
                n3 = NativeBridge.mvk(n4, 3);
                break;
            }
            case 17: {
                n3 = 162;
                break;
            }
            case 18: {
                n3 = 164;
                break;
            }
            default: {
                n3 = n;
            }
        }
        return n3;
    }

    public static int m(int n) {
        if (n > W.length - 1) {
            return 0;
        }
        return W[n];
    }

    public static String c(int n) {
        if (n == 192) {
            return b;
        }
        int n2 = NativeBridge.mvk(n, 0);
        switch (n) {
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 45: 
            case 46: 
            case 111: 
            case 144: {
                n2 |= 0x100;
            }
        }
        String string = NativeBridge.gkn(n2 << 16);
        return string;
    }

    public static int s(long l) {
        long l2 = l & 0xFFFFFFFFFFFF0000L;
        if (l2 == 131072L) {
            return 3;
        }
        if (l2 == 65536L) {
            return 4;
        }
        return 5;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void v() {
        if (ForgeVersion.MC_1_16_5.v() && Keyboard.areRepeatEventsEnabled()) {
            Keyboard.enableRepeatEvents((boolean)false);
        }
    }
}

