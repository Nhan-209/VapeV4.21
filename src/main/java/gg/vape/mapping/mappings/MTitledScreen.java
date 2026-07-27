package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ScaledResolution;

public class MTitledScreen
extends Mapping {
    public MappingMethod c;
    public MappingField n;
    public MappingMethod d;
    private MappingField G;

    public Object o(Object object) {
        if (ForgeVersion.MC_26_2.d()) {
            Object object2 = this.G.getObject(object);
            return object2 == null ? null : this.n.getObject(object2);
        }
        return this.n.getObject(object);
    }


    public MTitledScreen() {
        this(ScaledResolution.q());
    }

    private MTitledScreen(int n) {
        super(MappedClasses.Zj);
        int n2 = n;
        if (n2 != 0) {
            if (ForgeVersion.MC_1_8_9.d()) {
                Class[] classArray = new Class[]{MappedClasses.Y, Integer.TYPE, Integer.TYPE, MappedClasses.uQ};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "renderScoreboard";
                MTitledScreen mTitledScreen = this;
                this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{MappedClasses.Y, Integer.TYPE, Integer.TYPE, MappedClasses.uQ};
            Class<Void> clazz = Void.TYPE;
            boolean bl = Wrapper.G;
            String string = "func_96136_a";
            MTitledScreen mTitledScreen = this;
            this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            Class[] classArray2 = new Class[]{MappedClasses.Y, MappedClasses.Zz};
            Class<Void> clazz2 = Void.TYPE;
            boolean bl2 = true;
            String string2 = "renderScoreboard";
            MTitledScreen mTitledScreen2 = this;
            this.c = this.Y(string2, bl2, clazz2, classArray2);
            Class<String> clazz3 = String.class;
            boolean bl3 = true;
            String string3 = "displayedTitle";
            MTitledScreen mTitledScreen3 = this;
            this.n = this.J(string3, bl3, clazz3);
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                ScaledResolution.r(++n2);
            }
            return;
        }
        if (ForgeVersion.MC_1_8_9.d()) {
            if (ForgeVersion.MC_1_16_5.d()) {
                if (ForgeVersion.MC_1_20_6.d()) {
                    if (ForgeVersion.MC_1_21_0.d()) {
                        if (ForgeVersion.MC_26_2.d()) {
                            this.d = null;
                        } else {
                            Class[] classArray = new Class[]{MappedClasses.m, MappedClasses.uy};
                            Class<Void> clazz = Void.TYPE;
                            boolean bl = true;
                            String string = ForgeVersion.MC_26_1.d() ? "extractRenderState" : "render";
                            MTitledScreen mTitledScreen = this;
                            this.d = mTitledScreen.Y(string, bl, clazz, classArray);
                        }
                    } else {
                        Class[] classArray = new Class[]{MappedClasses.m, Float.TYPE};
                        Class<Void> clazz = Void.TYPE;
                        boolean bl = true;
                        String string = "render";
                        MTitledScreen mTitledScreen = this;
                        this.d = mTitledScreen.Y(string, bl, clazz, classArray);
                    }
                } else {
                    Class[] classArray = new Class[]{MappedClasses.DQ, Float.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = true;
                    String string = "renderIngameGui";
                    MTitledScreen mTitledScreen = this;
                    this.d = mTitledScreen.Y(string, bl, clazz, classArray);
                }
            } else {
                Class[] classArray = new Class[]{Float.TYPE};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "renderGameOverlay";
                Class clazz4 = MappedClasses.Zj;
                MTitledScreen mTitledScreen = this;
                this.d = mTitledScreen.W(clazz4, string, bl, clazz, classArray);
            }
            if (!Wrapper.G && !this.d.h() && Vape.INSTANCE.isMappingsRemapped()) {
                if (ForgeVersion.MC_1_16_5.d()) {
                    if (MappedClasses.DC != null) {
                        Class[] classArray = new Class[]{MappedClasses.DQ, Float.TYPE};
                        Class<Void> clazz = Void.TYPE;
                        boolean bl = false;
                        String string = this.d.v();
                        Class clazz5 = MappedClasses.DC;
                        MTitledScreen mTitledScreen = this;
                        this.d = mTitledScreen.W(clazz5, string, bl, clazz, classArray);
                    }
                } else {
                    Class[] classArray = new Class[]{Float.TYPE};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = false;
                    String string = this.d.v();
                    Class clazz6 = MappedClasses.DC;
                    MTitledScreen mTitledScreen = this;
                    this.d = mTitledScreen.W(clazz6, string, bl, clazz, classArray);
                }
            }
        } else {
            Class[] classArray = new Class[]{Float.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = "renderGameOverlay";
            Class clazz7 = MappedClasses.Zj;
            MTitledScreen mTitledScreen = this;
            this.d = mTitledScreen.W(clazz7, string, bl, clazz, classArray);
            if (!Wrapper.G && !this.d.h() && Vape.INSTANCE.isMappingsRemapped()) {
                Class[] classArray3 = new Class[]{Float.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE};
                Class<Void> clazz8 = Void.TYPE;
                boolean bl4 = false;
                String string4 = this.d.v();
                Class clazz9 = MappedClasses.DC;
                MTitledScreen mTitledScreen4 = this;
                this.d = this.W(clazz9, string4, bl4, clazz8, classArray3);
            }
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            if (ForgeVersion.MC_1_20_6.d()) {
                if (ForgeVersion.MC_26_2.d()) {
                    this.c = null;
                } else {
                    Class[] classArray = new Class[]{MappedClasses.m, MappedClasses.Y};
                    Class<Void> clazz = Void.TYPE;
                    boolean bl = true;
                    String string = "displayScoreboardSidebar";
                    MTitledScreen mTitledScreen = this;
                    this.c = mTitledScreen.Y(string, bl, clazz, classArray);
                }
            } else {
                Class[] classArray = new Class[]{MappedClasses.DQ, MappedClasses.Y};
                Class<Void> clazz = Void.TYPE;
                boolean bl = Wrapper.G;
                String string = "func_238447_a_";
                MTitledScreen mTitledScreen = this;
                this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_26_2.d()) {
                Class clazz = MappedClasses.zK;
                boolean bl = true;
                String string = "hud";
                MTitledScreen mTitledScreen = this;
                this.G = mTitledScreen.J(string, bl, clazz);
                Class clazz10 = MappedClasses.Yr;
                boolean bl5 = true;
                String string5 = "title";
                Class clazz11 = MappedClasses.zK;
                MTitledScreen mTitledScreen5 = this;
                this.n = this.X(clazz11, string5, bl5, clazz10);
            } else {
                Class clazz = MappedClasses.Yr;
                boolean bl = true;
                String string = "displayedTitle";
                MTitledScreen mTitledScreen = this;
                this.n = mTitledScreen.J(string, bl, clazz);
            }
        } else if (ForgeVersion.MC_1_7_10.L()) {
            if (Wrapper.c.isVanillaMinecraftPresent()) {
                Class[] classArray = new Class[]{MappedClasses.Y, Integer.TYPE, Integer.TYPE, MappedClasses.uQ};
                Class<Void> clazz = Void.TYPE;
                boolean bl = true;
                String string = "renderScoreboard";
                MTitledScreen mTitledScreen = this;
                this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.Y, Integer.TYPE, Integer.TYPE, MappedClasses.uQ};
                Class<Void> clazz = Void.TYPE;
                boolean bl = Wrapper.G;
                String string = "func_96136_a";
                MTitledScreen mTitledScreen = this;
                this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            }
        } else {
            Class[] classArray = new Class[]{MappedClasses.Y, MappedClasses.Zz};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = "renderScoreboard";
            MTitledScreen mTitledScreen = this;
            this.c = mTitledScreen.Y(string, bl, clazz, classArray);
            Class<String> clazz12 = String.class;
            boolean bl6 = true;
            String string6 = "displayedTitle";
            MTitledScreen mTitledScreen6 = this;
            this.n = this.J(string6, bl6, clazz12);
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            ScaledResolution.r(++n2);
        }
    }
}

