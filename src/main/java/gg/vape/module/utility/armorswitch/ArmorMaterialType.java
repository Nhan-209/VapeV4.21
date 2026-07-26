package gg.vape.module.utility.armorswitch;

import gg.vape.runtime.ObfuscatedRuntimeException;

public enum ArmorMaterialType {
    LEATHER("leather"),
    IRON("iron"),
    DIAMOND("diamond"),
    GOLD("gold"),
    NETHERITE("netherite"),
    CHAINMAIL("chainmail");

    private static final /* synthetic */ ArmorMaterialType[] r;
    private static boolean Z;
    private final String u;

    public static boolean V() {
        return Z;
    }

    public static boolean w() {
        boolean bl = ArmorMaterialType.V();
        return false;
    }

    private ArmorMaterialType(String string2) {
        this.u = string2;
    }

    public boolean G(String string) {
        return string.toLowerCase().contains(this.u);
    }

    public String H() {
        return this.u;
    }

    static {
        if (!ArmorMaterialType.V()) {
            ArmorMaterialType.z(true);
        }
        String[] stringArray = new String[]{"NETHERITE", "CHAINMAIL", "GOLD", "iron", "LEATHER", "chainmail", "IRON", "diamond", "DIAMOND", "netherite", "gold", "leather"};






        r = new ArmorMaterialType[]{LEATHER, IRON, DIAMOND, GOLD, NETHERITE, CHAINMAIL};
    }

    public static void z(boolean bl) {
        Z = bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

