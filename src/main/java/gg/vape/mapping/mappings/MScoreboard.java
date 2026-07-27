package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.Collection;

public class MScoreboard
extends Mapping {
    public MappingMethod P;
    private static int[] q;
    public MappingMethod a;

    private Collection I(Object object, Object object2) {
        return (Collection)this.P.L(object, object2);
    }

    public static void c(int[] nArray) {
        q = nArray;
    }

    public static Collection u(MScoreboard mScoreboard, Object object, Object object2) {
        return mScoreboard.I(object, object2);
    }


    public MScoreboard() {
        this(MScoreboard.X());
    }

    private MScoreboard(int[] nArray) {
        super(MappedClasses.F6);
        int[] nArray2 = nArray;
        if (ForgeVersion.MC_1_7_10.L() && !Wrapper.c.isVanillaMinecraftPresent()) {
            Class[] classArray = new Class[]{MappedClasses.Y};
            Class<Collection> clazz = Collection.class;
            boolean bl = Wrapper.G;
            String string = "func_96534_i";
            MScoreboard mScoreboard = this;
            this.P = mScoreboard.Y(string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_20_6.d()) {
            Class[] classArray = new Class[]{MappedClasses.Y};
            Class<Collection> clazz = Collection.class;
            boolean bl = true;
            String string = "listPlayerScores";
            MScoreboard mScoreboard = this;
            this.P = mScoreboard.Y(string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_17.d()) {
            Class[] classArray = new Class[]{MappedClasses.Y};
            Class<Collection> clazz = Collection.class;
            boolean bl = Wrapper.G;
            String string = "m_83498_";
            MScoreboard mScoreboard = this;
            this.P = mScoreboard.Y(string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray = new Class[]{MappedClasses.Y};
            Class<Collection> clazz = Collection.class;
            boolean bl = Wrapper.G;
            String string = "getSortedScores";
            MScoreboard mScoreboard = this;
            this.P = mScoreboard.Y(string, bl, clazz, classArray);
        } else {
            Class[] classArray = new Class[]{MappedClasses.Y};
            Class<Collection> clazz = Collection.class;
            boolean bl = true;
            String string = "getSortedScores";
            MScoreboard mScoreboard = this;
            this.P = mScoreboard.Y(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{String.class};
        Class clazz = MappedClasses.u6;
        boolean bl = true;
        String string = "getPlayersTeam";
        MScoreboard mScoreboard = this;
        this.a = mScoreboard.Y(string, bl, clazz, classArray);
    }

    private Object o(Object object, String string) {
        return this.a.L(object, string);
    }

    public static int[] X() {
        return q;
    }

    static {
        MScoreboard.c(new int[2]);
    }

    public static Object R(MScoreboard mScoreboard, Object object, String string) {
        return mScoreboard.o(object, string);
    }
}
