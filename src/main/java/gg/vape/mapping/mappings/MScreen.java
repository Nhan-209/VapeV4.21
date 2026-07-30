package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ScaledResolution;

public class MScreen
extends Mapping {
    private MappingMethod Y;

    private Object m(Object object) {
        return this.Y.invokeObject(null, object);
    }

    public MScreen() {
        this(ScaledResolution.W());
    }

    private MScreen(int n) {
        super(MappedClasses.uH);
        if (n != 0) {
            if (ForgeVersion.MC_1_21_6.d()) {
                if (ForgeVersion.MC_26_2.d()) {
                    Class[] classArray = new Class[]{MappedClasses.Vo};
                    Class clazz = MappedClasses.zC;
                    boolean bl = true;
                    String string = "getMobEffectSprite";
                    Class clazz2 = MappedClasses.zK;
                    MScreen mScreen = this;
                    this.Y = mScreen.registerStaticMethodForOwner(clazz2, string, bl, clazz, classArray);
                } else {
                    Class[] classArray = new Class[]{MappedClasses.Vo};
                    Class clazz = MappedClasses.zC;
                    boolean bl = true;
                    String string = "getMobEffectSprite";
                    MScreen mScreen = this;
                    this.Y = mScreen.registerStaticMethod(string, bl, clazz, classArray);
                }
            }
            return;
        }
        if (ForgeVersion.MC_1_21_6.d()) {
            Class[] classArray = new Class[]{MappedClasses.Vo};
            Class clazz = MappedClasses.zC;
            boolean bl = true;
            String string = "getMobEffectSprite";
            Class clazz3 = MappedClasses.zK;
            MScreen mScreen = this;
            this.Y = mScreen.registerStaticMethodForOwner(clazz3, string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{MappedClasses.Vo};
        Class clazz = MappedClasses.zC;
        boolean bl = true;
        String string = "getMobEffectSprite";
        MScreen mScreen = this;
        this.Y = mScreen.registerStaticMethod(string, bl, clazz, classArray);
    }


    public static Object i(MScreen mScreen, Object object) {
        return mScreen.m(object);
    }
}
