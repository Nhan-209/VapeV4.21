package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.mappings.MScoreboard;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MScorePlayerTeam
extends Mapping {
    private MappingField q;
    private MappingMethod B;
    private MappingMethod r;
    private MappingField F;
    private MappingField e;

    private String k(Object object, String string) {
        return (String)this.B.L(null, object, string);
    }

    public static Object H(MScorePlayerTeam mScorePlayerTeam, Object object, Object object2) {
        return mScorePlayerTeam.j(object, object2);
    }

    public Object z(Object object) {
        return this.F.getObject(object);
    }

    public Object b(Object object) {
        return this.e.getObject(object);
    }

    public static String L(MScorePlayerTeam mScorePlayerTeam, Object object) {
        return mScorePlayerTeam.a(object);
    }

    private String a(Object object) {
        return (String)this.q.getObject(object);
    }


    public static String M(MScorePlayerTeam mScorePlayerTeam, Object object, String string) {
        return mScorePlayerTeam.k(object, string);
    }

    public MScorePlayerTeam() {
        this(MScoreboard.X());
    }

    private MScorePlayerTeam(int[] nArray) {
        super(MappedClasses.u6);
        if (nArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{MappedClasses.Yh, MappedClasses.Yr};
                Class clazz = MappedClasses.YO;
                boolean bl = Wrapper.G;
                String string = "func_237500_a_";
                MScorePlayerTeam mScorePlayerTeam = this;
                this.B = mScorePlayerTeam.x(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.Yh, String.class};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "formatPlayerName";
                MScorePlayerTeam mScorePlayerTeam = this;
                this.B = mScorePlayerTeam.x(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_1_20_6.d()) {
                Class clazz = MappedClasses.Yr;
                boolean bl = true;
                String string = "playerPrefix";
                MScorePlayerTeam mScorePlayerTeam = this;
                this.F = mScorePlayerTeam.J(string, bl, clazz);
                Class clazz2 = MappedClasses.l5;
                boolean bl2 = true;
                String string2 = "color";
                MScorePlayerTeam mScorePlayerTeam2 = this;
                this.e = this.J(string2, bl2, clazz2);
            } else {
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "namePrefixSPT";
                MScorePlayerTeam mScorePlayerTeam = this;
                this.q = mScorePlayerTeam.J(string, bl, clazz);
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{MappedClasses.Yh, MappedClasses.Yr};
                Class clazz = MappedClasses.YO;
                String string = "formatNameForTeam";
                MScorePlayerTeam mScorePlayerTeam = this;
                this.r = ((MappingMethodBuilder)((MappingMethodBuilder)mScorePlayerTeam.u(string, clazz, classArray).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.uM)).H(true)).s();
            }
            if (GuiComponent.getLegacyComponentState() == null) {
                MScoreboard.c(new int[4]);
            }
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.Yh, String.class};
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = "formatPlayerName";
        MScorePlayerTeam mScorePlayerTeam = this;
        this.B = mScorePlayerTeam.x(string, bl, clazz, classArray);
        if (ForgeVersion.MC_1_20_6.d()) {
            Class clazz3 = MappedClasses.Yr;
            boolean bl3 = true;
            String string3 = "playerPrefix";
            MScorePlayerTeam mScorePlayerTeam3 = this;
            this.F = this.J(string3, bl3, clazz3);
            Class clazz4 = MappedClasses.l5;
            boolean bl4 = true;
            String string4 = "color";
            MScorePlayerTeam mScorePlayerTeam4 = this;
            this.e = this.J(string4, bl4, clazz4);
        }
        Class<String> clazz5 = String.class;
        boolean bl5 = true;
        String string5 = "namePrefixSPT";
        MScorePlayerTeam mScorePlayerTeam5 = this;
        this.q = this.J(string5, bl5, clazz5);
        if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray2 = new Class[]{MappedClasses.Yh, MappedClasses.Yr};
            Class clazz6 = MappedClasses.YO;
            String string6 = "formatNameForTeam";
            MScorePlayerTeam mScorePlayerTeam6 = this;
            this.r = ((MappingMethodBuilder)((MappingMethodBuilder)this.u(string6, clazz6, classArray2).X(ForgeVersion.MC_1_20_6.n(), MappedClasses.uM)).H(true)).s();
        }
        if (GuiComponent.getLegacyComponentState() == null) {
            MScoreboard.c(new int[4]);
        }
    }

    private Object j(Object object, Object object2) {
        return this.r.L(null, object, object2);
    }

    public Object I(Object object, Object object2) {
        return this.B.L(null, object, object2);
    }
}

