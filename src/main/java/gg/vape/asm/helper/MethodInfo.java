package gg.vape.asm.helper;

import gg.vape.runtime.ObfuscatedRuntimeException;

public class MethodInfo {
    private String k = null;
    private String i = null;

    public MethodInfo(String string) {
        this.k = string;
    }

    public MethodInfo(String string, String string2) {
        this.k = string;
        this.i = string2;
    }

    public boolean O(String string, String string2) {
        boolean bl = this.k.equals(string) && (this.i == null || this.i.equals(string2));
        return bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

