package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MSlot;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.List;

public class MContainer
extends Mapping {
    private MappingMethod I;
    private final MappingField w;
    private final MappingMethod o;
    private final MappingField L;

    private Object n(Object object) {
        return this.I.L(object, new Object[0]);
    }

    public static Object R(MContainer mContainer, Object object) {
        return mContainer.n(object);
    }

    public List i(Object object) {
        return (List)this.L.getObject(object);
    }

    private Object J(Object object, int n) {
        return this.o.L(object, n);
    }


    public int s(Object object) {
        return this.w.getInt(object);
    }

    public static Object c(MContainer mContainer, Object object, int n) {
        return mContainer.J(object, n);
    }

    public MContainer() {
        this(MSlot.z());
    }

    private MContainer(int[] nArray) {
        super(MappedClasses.X);
        if (nArray != null) {
            Class<List> clazz = List.class;
            boolean bl = true;
            String string = "inventorySlots";
            MContainer mContainer = this;
            this.L = mContainer.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "windowId";
            MContainer mContainer2 = this;
            this.w = this.J(string2, bl2, clazz2);
            Class[] classArray = new Class[]{Integer.TYPE};
            Class clazz3 = MappedClasses.YQ;
            boolean bl3 = true;
            String string3 = "getSlot";
            MContainer mContainer3 = this;
            this.o = this.Y(string3, bl3, clazz3, classArray);
            return;
        }
        if (ForgeVersion.MC_1_17.d()) {
            Class clazz = MappedClasses.Vd;
            boolean bl = true;
            String string = "slots";
            MContainer mContainer = this;
            this.L = mContainer.J(string, bl, clazz);
            Class[] classArray = new Class[]{};
            Class clazz4 = MappedClasses.VK;
            boolean bl4 = true;
            String string4 = "getCarried";
            MContainer mContainer4 = this;
            this.I = this.Y(string4, bl4, clazz4, classArray);
        } else {
            Class<List> clazz = List.class;
            boolean bl = true;
            String string = "inventorySlots";
            MContainer mContainer = this;
            this.L = mContainer.J(string, bl, clazz);
        }
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "windowId";
        MContainer mContainer = this;
        this.w = mContainer.J(string, bl, clazz); 
        Class[] classArray = new Class[]{Integer.TYPE};
        Class clazz5 = MappedClasses.YQ;
        boolean bl5 = true;
        String string5 = "getSlot";
        MContainer mContainer5 = this;
        this.o = this.Y(string5, bl5, clazz5, classArray);
    }
}

