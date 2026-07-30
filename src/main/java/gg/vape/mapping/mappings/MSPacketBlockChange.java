package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketBlockChange
extends Mapping {
    private MappingMethod U;
    private MappingField o;
    private MappingField i;
    private MappingField G;
    private MappingMethod Y;
    private MappingField N;

    public Object Y(Object object) {
        return this.i.getObject(object);
    }

    public int e(Object object) {
        return this.o.getInt(object);
    }

    public int S(Object object) {
        return this.N.getInt(object);
    }

    public int h(Object object) {
        return this.G.getInt(object);
    }

    public Object h$src$Ljava_lang_Object_$1ir33a2(Object object) {
        return this.Y.invokeObject(object, new Object[0]);
    }

    public MSPacketBlockChange() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketBlockChange(int[] nArray) {
        super(MappedClasses.DD);
        if (nArray != null) {
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.Vv;
                String string = "getBlockState";
                MSPacketBlockChange mSPacketBlockChange = this;
                this.Y = ((MappingMethodBuilder)mSPacketBlockChange.methodBuilder(string, clazz, classArray).setTypeForVersion(ForgeVersion.MC_1_16_5.n(), MappedClasses.Zl)).buildMethod();
                Class[] classArray2 = new Class[]{};
                Class clazz2 = MappedClasses.lf;
                String string2 = "getBlockPosition";
                MSPacketBlockChange mSPacketBlockChange2 = this;
                this.U = ((MappingMethodBuilder)this.methodBuilder(string2, clazz2, classArray2).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "getPos")).buildMethod();
            } else {
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = false;
                String string = "field_148887_a";
                MSPacketBlockChange mSPacketBlockChange = this;
                this.o = mSPacketBlockChange.J(string, bl, clazz);
                Class<Integer> clazz3 = Integer.TYPE;
                boolean bl2 = false;
                String string3 = "field_148885_b";
                MSPacketBlockChange mSPacketBlockChange3 = this;
                this.N = this.J(string3, bl2, clazz3);
                Class<Integer> clazz4 = Integer.TYPE;
                boolean bl3 = false;
                String string4 = "field_148886_c";
                MSPacketBlockChange mSPacketBlockChange4 = this;
                this.G = this.J(string4, bl3, clazz4);
            }
            return;
        }
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = false;
        String string = "field_148886_c";
        MSPacketBlockChange mSPacketBlockChange = this;
        this.G = mSPacketBlockChange.J(string, bl, clazz);
    }


    public Object j(Object object) {
        return this.U.invokeObject(object, new Object[0]);
    }
}

