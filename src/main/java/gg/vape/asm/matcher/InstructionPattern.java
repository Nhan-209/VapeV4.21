package gg.vape.asm.matcher;

import gg.vape.runtime.ObfuscatedRuntimeException;

public abstract class InstructionPattern {
    public static final int K;
    private static String I;
    private String p;
    private int G;
    private String M;
    private String O;
    public static final int k;
    public static final int d;
    public static final int g;
    public static final int f;
    public static final int P;
    public static final int E;
    public static final int Y;

    public static String G() {
        return I;
    }

    public InstructionPattern(int n, String string, String string2, String string3) {
        string = string.replace(".", "/");
        string3 = string3.replace(".", "/");
        this.G = n;
        this.M = string;
        this.O = string2;
        this.p = string3;
    }

    public String getName() {
        return this.O;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String getDescriptor() {
        return this.p;
    }

    public static void K(String string) {
        I = string;
    }

    public int getOpcode() {
        return this.G;
    }

    public boolean matches(InstructionPattern instructionPattern) {
        return !(this.G != instructionPattern.G || !this.M.equals(instructionPattern.M) && !this.M.equals("*") || !this.O.equals(instructionPattern.O) && !this.O.equals("*") || !this.p.equals(instructionPattern.p) && !this.p.equals("*"));
    }

    public String getOwner() {
        return this.M;
    }

    static {
        if (InstructionPattern.G() != null) {
            InstructionPattern.K("jHaNE");
        }
        long[] lArray = new long[]{-2327468895398526791L, -1358600573514940234L, -8503526305340325708L, -3693968536541069129L, 7643330736333783224L, -1797710973226712907L, 6908187125879931058L, -9093027909794266957L};
        k = (int)lArray[6];
        K = (int)lArray[5];
        Y = (int)lArray[0];
        f = (int)lArray[1];
        g = (int)lArray[4];
        P = (int)lArray[3];
        d = (int)lArray[7];
        E = (int)lArray[2];
    }
}

