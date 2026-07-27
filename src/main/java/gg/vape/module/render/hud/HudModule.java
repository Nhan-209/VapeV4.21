package gg.vape.module.render.hud;

import gg.vape.module.Mod;
import gg.vape.module.render.hud.HudModuleGroup;

public class HudModule
extends Mod {
    private HudModuleGroup D;
    private static int a;
    private String V;
    private boolean Z;
    private Class I;

    public HudModule(String string, HudModuleGroup hudModuleGroup, String string2, Class clazz) {
        this(string, hudModuleGroup, string2, clazz, 0);
    }

    public Class j$src$Ljava_lang_Class_$wxgaiy() {
        return this.I;
    }

    static {
        if (HudModule.B$src$I$5dcmu4() != 0) {
            HudModule.g(31);
        }
    }

    @Override
    public void y() {
    }

    public String s$src$Ljava_lang_String_$pdppcm() {
        return this.V;
    }

    public static int s() {
        return a;
    }

    public static void g(int n) {
        a = n;
    }

    public HudModule(String string, HudModuleGroup hudModuleGroup, String string2, Class clazz, int n) {
        super(string, n);
        this.D = hudModuleGroup;
        this.V = string2;
        this.I = clazz;
    }

    public boolean W() {
        return this.Z;
    }

    public HudModule(String string, HudModuleGroup hudModuleGroup, String string2, int n) {
        this(string, hudModuleGroup, string2, null, n);
    }

    public void q$src$V$1apmftw(boolean bl) {
        this.Z = bl;
    }

    public HudModule(String string, HudModuleGroup hudModuleGroup, String string2) {
        this(string, hudModuleGroup, string2, null);
    }


    public static int B$src$I$5dcmu4() {
        int n = HudModule.s();
        if (n == 0) {
            return 80;
        }
        return 0;
    }

    public HudModuleGroup F$src$Lgg_vape_module_render_hud_HudModuleGroup_$1x5d82w() {
        return this.D;
    }
}

