package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.mappings.MScoreboard;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MScore
extends Mapping {
    private MappingMethod g;
    private MappingMethod K;

    public static int t(MScore mScore, Object object) {
        return mScore.J(object);
    }

    private String j(Object object) {
        return (String)this.g.invokeObject(object, new Object[0]);
    }

    public static String n(MScore mScore, Object object) {
        return mScore.j(object);
    }


    public MScore() {
        this(MScoreboard.X());
    }

    private MScore(int[] nArray) {
        super(MappedClasses.DX);
        if (nArray != null) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "owner";
                Class clazz2 = MappedClasses.p;
                MScore mScore = this;
                this.g = mScore.registerInstanceMethodForOwner(clazz2, string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "getPlayerName";
                MScore mScore = this;
                this.g = mScore.Y(string, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{};
            Class<Integer> clazz = Integer.TYPE;
            String string = "getScorePoints";
            MScore mScore = this;
            this.K = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)mScore.methodBuilder(string, clazz, classArray).setNameForVersion(ForgeVersion.MC_1_20_6.n(), "value")).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "getScore")).setOwnerClassForVersion(ForgeVersion.MC_1_21_4.n(), MappedClasses.VN)).buildMethod();
            return;
        }
        GuiComponent.setLegacyComponentState(new GuiComponent[3]);
        Class[] classArray = new Class[]{};
        Class<String> clazz = String.class;
        boolean bl = true;
        String string = "getPlayerName";
        MScore mScore = this;
        this.g = mScore.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class<Integer> clazz3 = Integer.TYPE;
        String string2 = "getScorePoints";
        MScore mScore2 = this;
        this.K = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)this.methodBuilder(string2, clazz3, classArray2).setNameForVersion(ForgeVersion.MC_1_20_6.n(), "value")).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "getScore")).setOwnerClassForVersion(ForgeVersion.MC_1_21_4.n(), MappedClasses.VN)).buildMethod();
    }

    private int J(Object object) {
        return this.K.invokeInt(object, new Object[0]);
    }
}

