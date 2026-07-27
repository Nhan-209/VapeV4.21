package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MItemStack;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MArmorMaterial
extends Mapping {
    private final MappingField L;
    private final MappingField A;
    private final MappingField F;
    private final MappingField z;
    private final MappingField g;

    public static Object L(MArmorMaterial mArmorMaterial) {
        return mArmorMaterial.getIron();
    }


    private Object getIron() {
        return this.z.getObject(null);
    }

    public static Object u(MArmorMaterial mArmorMaterial) {
        return mArmorMaterial.getChain();
    }

    public static Object O(MArmorMaterial mArmorMaterial) {
        return mArmorMaterial.getDiamond();
    }

    private Object getCloth() {
        return this.L.getObject(null);
    }

    private Object getChain() {
        return this.F.getObject(null);
    }

    public static Object e(MArmorMaterial mArmorMaterial) {
        return mArmorMaterial.getGold();
    }

    private Object getDiamond() {
        return this.g.getObject(null);
    }

    public MArmorMaterial() {
        this(MItemStack.M());
    }

    private MArmorMaterial(int n) {
        super(MappedClasses.ZM);
        if (n != 0) {
            Class clazz = MappedClasses.ZM;
            boolean bl = Wrapper.G;
            String string = "LEATHER";
            MArmorMaterial mArmorMaterial = this;
            this.L = mArmorMaterial.u(string, bl, clazz);
            Class clazz2 = MappedClasses.ZM;
            boolean bl2 = Wrapper.G;
            String string2 = "CHAIN";
            MArmorMaterial mArmorMaterial2 = this;
            this.F = this.u(string2, bl2, clazz2);
            Class clazz3 = MappedClasses.ZM;
            boolean bl3 = Wrapper.G;
            String string3 = "IRON";
            MArmorMaterial mArmorMaterial3 = this;
            this.z = this.u(string3, bl3, clazz3);
            Class clazz4 = MappedClasses.ZM;
            boolean bl4 = Wrapper.G;
            String string4 = "GOLD";
            MArmorMaterial mArmorMaterial4 = this;
            this.A = this.u(string4, bl4, clazz4);
            Class clazz5 = MappedClasses.ZM;
            boolean bl5 = Wrapper.G;
            String string5 = "DIAMOND";
            MArmorMaterial mArmorMaterial5 = this;
            this.g = this.u(string5, bl5, clazz5);
            return;
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            Class clazz = MappedClasses.ZM;
            boolean bl = Wrapper.G;
            String string = "CLOTH";
            MArmorMaterial mArmorMaterial = this;
            this.L = mArmorMaterial.u(string, bl, clazz);
        } else {
            Class clazz = MappedClasses.ZM;
            boolean bl = Wrapper.G;
            String string = "LEATHER";
            MArmorMaterial mArmorMaterial = this;
            this.L = mArmorMaterial.u(string, bl, clazz);
        }
        Class clazz = MappedClasses.ZM;
        boolean bl = Wrapper.G;
        String string = "CHAIN";
        MArmorMaterial mArmorMaterial = this;
        this.F = mArmorMaterial.u(string, bl, clazz); 
        Class clazz6 = MappedClasses.ZM;
        boolean bl6 = Wrapper.G;
        String string6 = "IRON";
        MArmorMaterial mArmorMaterial6 = this;
        this.z = this.u(string6, bl6, clazz6);
        Class clazz7 = MappedClasses.ZM;
        boolean bl7 = Wrapper.G;
        String string7 = "GOLD";
        MArmorMaterial mArmorMaterial7 = this;
        this.A = this.u(string7, bl7, clazz7);
        Class clazz8 = MappedClasses.ZM;
        boolean bl8 = Wrapper.G;
        String string8 = "DIAMOND";
        MArmorMaterial mArmorMaterial8 = this;
        this.g = this.u(string8, bl8, clazz8);
    }

    public static Object v(MArmorMaterial mArmorMaterial) {
        return mArmorMaterial.getCloth();
    }

    private Object getGold() {
        return this.A.getObject(null);
    }
}

