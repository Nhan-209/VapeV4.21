package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MDamageSource;
import gg.vape.wrapper.impl.ForgeVersion;

public class MBlocks
extends Mapping {
    private final MappingField n;
    private final MappingField f;
    private MappingField j;
    private final MappingField I;

    private Object B() {
        return this.j.getObject(null);
    }

    public static Object z(MBlocks mBlocks) {
        return mBlocks.F();
    }

    private Object G() {
        return this.f.getObject(null);
    }

    private Object i() {
        return this.I.getObject(null);
    }

    public static Object J(MBlocks mBlocks) {
        return mBlocks.i();
    }

    private Object F() {
        return this.n.getObject(null);
    }


    public static Object M(MBlocks mBlocks) {
        return mBlocks.G();
    }

    public static Object U(MBlocks mBlocks) {
        return mBlocks.B();
    }

    public MBlocks() {
        this(MDamageSource.r());
    }

    private MBlocks(int[] nArray) {
        super(MappedClasses.V8);
        if (nArray != null) {
            Class clazz = MappedClasses.Zk;
            boolean bl = true;
            String string = "air";
            MBlocks mBlocks = this;
            this.f = mBlocks.registerStaticField(string, bl, clazz);
            this.n = null;
            this.I = null;
            return;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            Class clazz = MappedClasses.Zk;
            boolean bl = true;
            String string = "LADDER";
            MBlocks mBlocks = this;
            this.n = mBlocks.registerStaticField(string, bl, clazz);
            Class clazz2 = MappedClasses.Zk;
            boolean bl2 = true;
            String string2 = "STONE";
            MBlocks mBlocks2 = this;
            this.I = this.registerStaticField(string2, bl2, clazz2);
            Class clazz3 = MappedClasses.Zk;
            boolean bl3 = true;
            String string3 = "AIR";
            MBlocks mBlocks3 = this;
            this.f = this.registerStaticField(string3, bl3, clazz3);
            if (ForgeVersion.MC_1_21_4.d()) {
                Class clazz4 = MappedClasses.Zk;
                boolean bl4 = true;
                String string4 = "POWDER_SNOW";
                MBlocks mBlocks4 = this;
                this.j = this.registerStaticField(string4, bl4, clazz4);
            }
        } else {
            Class clazz = MappedClasses.Zk;
            boolean bl = true;
            String string = "ladder";
            MBlocks mBlocks = this;
            this.n = mBlocks.registerStaticField(string, bl, clazz);
            Class clazz5 = MappedClasses.Zk;
            boolean bl5 = true;
            String string5 = "stone";
            MBlocks mBlocks5 = this;
            this.I = this.registerStaticField(string5, bl5, clazz5);
            Class clazz6 = MappedClasses.Zk;
            boolean bl6 = true;
            String string6 = "air";
            MBlocks mBlocks6 = this;
            this.f = this.registerStaticField(string6, bl6, clazz6);
        }
    }
}

