package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSlot
extends Mapping {
    private final MappingField G;
    private final MappingField Z;
    private static int[] Y;
    private MappingField f;
    private final MappingMethod r;

    public Object I(Object object) {
        return this.r.invokeObject(object, new Object[0]);
    }

    public static int[] z() {
        return Y;
    }

    public static int Y(MSlot mSlot, Object object) {
        return mSlot.P(object);
    }

    private int P(Object object) {
        return this.f.getInt(object);
    }

    public static void w(int[] nArray) {
        Y = nArray;
    }

    private int Y(Object object) {
        return this.Z.getInt(object);
    }

    public MSlot() {
        super(MappedClasses.YQ);
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.VK;
        boolean bl = true;
        String string = "getStack";
        MSlot mSlot = this;
        this.r = this.Y(string, bl, clazz, classArray);
        Class<Integer> clazz2 = Integer.TYPE;
        String string2 = "slotNumber";
        MSlot mSlot2 = this;
        this.Z = this.fieldBuilder(string2, clazz2).buildField();
        Class clazz3 = MappedClasses.l0;
        String string3 = "inventory";
        MSlot mSlot3 = this;
        this.G = ((MappingFieldBuilder)this.fieldBuilder(string3, clazz3).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "container")).buildField();
        Class<Integer> clazz4 = Integer.TYPE;
        String string4 = "slotIndex";
        MSlot mSlot4 = this;
        this.f = ((MappingFieldBuilder)this.fieldBuilder(string4, clazz4).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "index")).buildField();
    }

    public static int d(MSlot mSlot, Object object) {
        return mSlot.Y(object);
    }

    public static Object n(MSlot mSlot, Object object) {
        return mSlot.n(object);
    }

    private Object n(Object object) {
        return this.G.getObject(object);
    }

    static {
        MSlot.w(null);
    }
}

