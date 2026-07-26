package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MGuiContainer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MGuiInventory
extends Mapping {
    private MappingMethod n;

    public void H(int n, int n2, int n3, float f, float f2, Object object) {
        this.n.c(null, n, n2, n3, Float.valueOf(f), Float.valueOf(f2), object);
    }

    public MGuiInventory() {
        this(MGuiContainer.l());
    }

    private MGuiInventory(String[] stringArray) {
        super(MappedClasses.YS);
        String[] stringArray2 = stringArray;
        if (!ForgeVersion.MC_1_20_6.d()) {
            if (ForgeVersion.MC_1_7_10.L()) {
                if (Wrapper.c.isVanillaMinecraftPresent()) {
                    Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE, MappedClasses.zm};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = true;
                    String string = "drawEntityOnScreen";
                    MGuiInventory mGuiInventory = this;
                    this.n = mGuiInventory.x(string, bl, clazz, classArray);
                } else {
                    Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE, MappedClasses.zm};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = Wrapper.G;
                    String string = "func_147046_a";
                    MGuiInventory mGuiInventory = this;
                    this.n = mGuiInventory.x(string, bl, clazz, classArray);
                }
            } else {
                Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE, MappedClasses.zm};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "drawEntityOnScreen";
                MGuiInventory mGuiInventory = this;
                this.n = mGuiInventory.x(string, bl, clazz, classArray); 
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
