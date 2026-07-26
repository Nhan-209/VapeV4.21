package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ScaledResolution;
import java.util.Comparator;

public class MGuiPlayerTabOverlay
extends Mapping {
    private MappingMethod L;
    public MappingMethod O;
    final MappingField h;

    public static Object Z(MGuiPlayerTabOverlay mGuiPlayerTabOverlay, Object object, Object object2, Object object3) {
        return mGuiPlayerTabOverlay.C(object, object2, object3);
    }

    public static Object b(MGuiPlayerTabOverlay mGuiPlayerTabOverlay) {
        return mGuiPlayerTabOverlay.U();
    }

    private Object C(Object object, Object object2, Object object3) {
        return this.L.L(object, object2, object3);
    }

    public static String d(MGuiPlayerTabOverlay mGuiPlayerTabOverlay, Object object, Object object2) {
        return mGuiPlayerTabOverlay.K(object, object2);
    }

    private Object U() {
        return this.h.getObject(null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MGuiPlayerTabOverlay() {
        this(ScaledResolution.W());
    }

    private MGuiPlayerTabOverlay(int n) {
        super(MappedClasses.lF);
        if (n != 0) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class<Comparator> clazz = Comparator.class;
                boolean bl = Wrapper.G;
                String string = "PLAYER_COMPARATOR";
                MGuiPlayerTabOverlay mGuiPlayerTabOverlay = this;
                this.h = mGuiPlayerTabOverlay.u(string, bl, clazz);
            } else {
                Class clazz = MappedClasses.F4;
                boolean bl = Wrapper.G;
                String string = "field_175252_a";
                MGuiPlayerTabOverlay mGuiPlayerTabOverlay = this;
                this.h = mGuiPlayerTabOverlay.u(string, bl, clazz);
            }
            Class[] classArray = new Class[]{MappedClasses.Zc};
            Class<String> clazz = String.class;
            String string = "getPlayerName";
            MGuiPlayerTabOverlay mGuiPlayerTabOverlay = this;
            this.O = ((MappingMethodBuilder)((MappingMethodBuilder)mGuiPlayerTabOverlay.u(string, clazz, classArray).X(ForgeVersion.MC_1_16_5.n(), MappedClasses.Yr)).A(ForgeVersion.MC_1_16_5.n(), "getNameForDisplay")).s();
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray2 = new Class[]{MappedClasses.Zc, MappedClasses.YO};
                Class clazz2 = MappedClasses.Yr;
                String string2 = "decorateName";
                MGuiPlayerTabOverlay mGuiPlayerTabOverlay2 = this;
                this.L = ((MappingMethodBuilder)this.u(string2, clazz2, classArray2).v(ForgeVersion.MC_1_20_6.n(), MappedClasses.Zc, MappedClasses.uM).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.Yr)).s();
            }
            return;
        }
        Class clazz = MappedClasses.F4;
        boolean bl = Wrapper.G;
        String string = "field_175252_a";
        MGuiPlayerTabOverlay mGuiPlayerTabOverlay = this;
        this.h = mGuiPlayerTabOverlay.u(string, bl, clazz); 
        Class[] classArray = new Class[]{MappedClasses.Zc};
        Class<String> clazz3 = String.class;
        String string3 = "getPlayerName";
        MGuiPlayerTabOverlay mGuiPlayerTabOverlay3 = this;
        this.L = ((MappingMethodBuilder)((MappingMethodBuilder)this.u(string3, clazz3, classArray).X(ForgeVersion.MC_1_16_5.n(), MappedClasses.Yr)).A(ForgeVersion.MC_1_16_5.n(), "getNameForDisplay")).s();
    }

    private String K(Object object, Object object2) {
        return (String)this.O.L(object, object2);
    }
}

