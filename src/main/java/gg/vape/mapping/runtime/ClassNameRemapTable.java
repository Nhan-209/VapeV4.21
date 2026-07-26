package gg.vape.mapping.runtime;

import gg.vape.mapping.runtime.RuntimeNameMappingRegistry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.HashMap;

public class ClassNameRemapTable {
    public static boolean m = false;
    private HashMap<String, String> L = new HashMap();
    private static int[] y;

    public static int[] m() {
        return y;
    }

    /*
     * WARNING - void declaration
     */
    public void Q(String string, String string2) {
        String string3 = this.L.get(string);
        this.L.put(string, string2);
        if (m) {
            String string5 = RuntimeNameMappingRegistry.remapClassName(string);
            String string4 = string5 != null ? string5 : string;
            RuntimeNameMappingRegistry.registerClassName(string4, string2);
        }
    }

    public static void O(int[] nArray) {
        y = nArray;
    }

    public String n(String string) {
        return this.L.getOrDefault(string, null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    static {
        ClassNameRemapTable.O(new int[3]);
    }
}
