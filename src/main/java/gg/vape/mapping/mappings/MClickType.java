package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSlot;
import gg.vape.ui.click.component.GuiComponent;

public class MClickType
extends Mapping {
    private final MappingField P;
    private final MappingField B;
    private final MappingField G;
    private final MappingField n;
    private final MappingField k;
    private final MappingField H;
    private final MappingField w;

    public static Object T(MClickType mClickType) {
        return mClickType.QUICK_CRAFT();
    }

    public static Object f(MClickType mClickType) {
        return mClickType.QUICK_MOVE();
    }

    public static Object J(MClickType mClickType) {
        return mClickType.THROW();
    }

    public static Object a(MClickType mClickType) {
        return mClickType.SWAP();
    }

    public MClickType() {
        this(MSlot.z());
    }

    private MClickType(int[] nArray) {
        super(MappedClasses.V_);
        Class clazz = MappedClasses.V_;
        boolean bl = true;
        String string = "PICKUP";
        MClickType mClickType = this;
        this.G = this.registerStaticField(string, bl, clazz);
        if (nArray != null) {
            Class clazz2 = MappedClasses.V_;
            boolean bl2 = true;
            String string2 = "QUICK_MOVE";
            MClickType mClickType2 = this;
            this.P = this.registerStaticField(string2, bl2, clazz2);
            Class clazz3 = MappedClasses.V_;
            boolean bl3 = true;
            String string3 = "SWAP";
            MClickType mClickType3 = this;
            this.B = this.registerStaticField(string3, bl3, clazz3);
            Class clazz4 = MappedClasses.V_;
            boolean bl4 = true;
            String string4 = "CLONE";
            MClickType mClickType4 = this;
            this.k = this.registerStaticField(string4, bl4, clazz4);
            Class clazz5 = MappedClasses.V_;
            boolean bl5 = true;
            String string5 = "THROW";
            MClickType mClickType5 = this;
            this.H = this.registerStaticField(string5, bl5, clazz5);
            Class clazz6 = MappedClasses.V_;
            boolean bl6 = true;
            String string6 = "QUICK_CRAFT";
            MClickType mClickType6 = this;
            this.w = this.registerStaticField(string6, bl6, clazz6);
            Class clazz7 = MappedClasses.V_;
            boolean bl7 = true;
            String string7 = "PICKUP_ALL";
            MClickType mClickType7 = this;
            this.n = this.registerStaticField(string7, bl7, clazz7);
            GuiComponent.setLegacyComponentState(new GuiComponent[4]);
            return;
        }
        Class clazz8 = MappedClasses.V_;
        boolean bl8 = true;
        String string8 = "QUICK_MOVE";
        MClickType mClickType8 = this;
        this.P = this.registerStaticField(string8, bl8, clazz8);
        Class clazz9 = MappedClasses.V_;
        boolean bl9 = true;
        String string9 = "SWAP";
        MClickType mClickType9 = this;
        this.B = this.registerStaticField(string9, bl9, clazz9);
        Class clazz10 = MappedClasses.V_;
        boolean bl10 = true;
        String string10 = "CLONE";
        MClickType mClickType10 = this;
        this.k = this.registerStaticField(string10, bl10, clazz10);
        Class clazz11 = MappedClasses.V_;
        boolean bl11 = true;
        String string11 = "THROW";
        MClickType mClickType11 = this;
        this.H = this.registerStaticField(string11, bl11, clazz11);
        Class clazz12 = MappedClasses.V_;
        boolean bl12 = true;
        String string12 = "QUICK_CRAFT";
        MClickType mClickType12 = this;
        this.w = this.registerStaticField(string12, bl12, clazz12);
        Class clazz13 = MappedClasses.V_;
        boolean bl13 = true;
        String string13 = "PICKUP_ALL";
        MClickType mClickType13 = this;
        this.n = this.registerStaticField(string13, bl13, clazz13);
    }

    private Object QUICK_CRAFT() {
        return this.w.getObject(null);
    }

    public static Object y(MClickType mClickType) {
        return mClickType.CLONE();
    }

    private Object PICKUP_ALL() {
        return this.n.getObject(null);
    }

    private Object PICKUP() {
        return this.G.getObject(null);
    }

    private Object SWAP() {
        return this.B.getObject(null);
    }

    private Object THROW() {
        return this.H.getObject(null);
    }

    private Object CLONE() {
        return this.k.getObject(null);
    }

    public static Object K(MClickType mClickType) {
        return mClickType.PICKUP_ALL();
    }

    private Object QUICK_MOVE() {
        return this.P.getObject(null);
    }

    public static Object x(MClickType mClickType) {
        return mClickType.PICKUP();
    }

}

