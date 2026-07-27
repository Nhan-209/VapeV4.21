package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MCaughtEntityActionBridge
extends Mapping {
    private final MappingMethod I;
    private static boolean P;
    private final MappingMethod s;

    public MCaughtEntityActionBridge() {
        this(MCaughtEntityActionBridge.Y());
    }

    private MCaughtEntityActionBridge(boolean bl) {
        super(MappedClasses.VA);
        if (bl) {
            Class[] classArray = new Class[]{};
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl2 = false;
            String string = "inEventLoop";
            MCaughtEntityActionBridge mCaughtEntityActionBridge = this;
            this.s = mCaughtEntityActionBridge.Y(string, bl2, clazz, classArray);
            Class[] classArray2 = new Class[]{Runnable.class};
            Class<Void> clazz2 = Void.TYPE;
            boolean bl3 = false;
            String string2 = "execute";
            MCaughtEntityActionBridge mCaughtEntityActionBridge2 = this;
            this.I = this.Y(string2, bl3, clazz2, classArray2);
            return;
        }
        Class[] classArray = new Class[]{};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl4 = false;
        String string = "inEventLoop";
        MCaughtEntityActionBridge mCaughtEntityActionBridge = this;
        this.s = mCaughtEntityActionBridge.Y(string, bl4, clazz, classArray); 
        Class[] classArray3 = new Class[]{Runnable.class};
        Class<Void> clazz3 = Void.TYPE;
        boolean bl5 = false;
        String string3 = "execute";
        MCaughtEntityActionBridge mCaughtEntityActionBridge3 = this;
        this.I = this.Y(string3, bl5, clazz3, classArray3);
        GuiComponent.D(new GuiComponent[3]);
    }

    private boolean d(Object object) {
        return this.s.e(object, new Object[0]);
    }

    public static boolean g(MCaughtEntityActionBridge mCaughtEntityActionBridge, Object object) {
        return mCaughtEntityActionBridge.d(object);
    }

    static {
        MCaughtEntityActionBridge.h(false);
    }

    public static boolean R() {
        return P;
    }

    public static boolean Y() {
        boolean bl = MCaughtEntityActionBridge.R();
        return !bl;
    }

    public static void H(MCaughtEntityActionBridge mCaughtEntityActionBridge, Object object, Runnable runnable) {
        mCaughtEntityActionBridge.f(object, runnable);
    }


    public static void h(boolean bl) {
        P = bl;
    }

    private void f(Object object, Runnable runnable) {
        this.I.c(object, runnable);
    }
}

