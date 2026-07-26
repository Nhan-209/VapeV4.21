package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MVisGraph
extends Mapping {
    private static String S;
    public MappingMethod V;
    public MappingMethod o;

    public static String r() {
        return S;
    }

    public static void b(String string) {
        S = string;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MVisGraph() {
        this(MVisGraph.r());
    }

    private MVisGraph(String string) {
        super(MappedClasses.Y7);
        if (string != null) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.qG;
            boolean bl = true;
            String string2 = "computeVisibility";
            MVisGraph mVisGraph = this;
            this.V = mVisGraph.Y(string2, bl, clazz, classArray);
            if (ForgeVersion.MC_1_12_2.d()) {
                Class[] classArray2 = new Class[]{MappedClasses.lf};
                Class<Void> clazz2 = Void.TYPE;
                boolean bl2 = true;
                String string3 = "setOpaqueCube";
                MVisGraph mVisGraph2 = this;
                this.o = this.Y(string3, bl2, clazz2, classArray2);
            } else if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray3 = new Class[]{MappedClasses.lf};
                Class<Void> clazz3 = Void.TYPE;
                boolean bl3 = Wrapper.G;
                String string4 = "func_178606_a";
                MVisGraph mVisGraph3 = this;
                this.o = this.Y(string4, bl3, clazz3, classArray3);
            }
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MVisGraph.b("K5b3Uc");
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class clazz = MappedClasses.qG;
        boolean bl = true;
        String string5 = "computeVisibility";
        MVisGraph mVisGraph = this;
        this.V = mVisGraph.Y(string5, bl, clazz, classArray);
        if (ForgeVersion.MC_1_12_2.d()) {
            Class[] classArray4 = new Class[]{MappedClasses.lf};
            Class<Void> clazz4 = Void.TYPE;
            boolean bl4 = Wrapper.G;
            String string6 = "func_178606_a";
            MVisGraph mVisGraph4 = this;
            this.o = this.Y(string6, bl4, clazz4, classArray4);
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MVisGraph.b("K5b3Uc");
        }
    }

    static {
        MVisGraph.b("qWXvyc");
    }
}

