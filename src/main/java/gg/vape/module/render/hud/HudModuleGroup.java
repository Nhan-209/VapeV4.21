package gg.vape.module.render.hud;

import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.INamed;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class HudModuleGroup
implements INamed {
    public static HudModuleGroup T;
    public static List<HudModuleGroup> j;
    private final String name;
    private final String key;
    public static HudModuleGroup J;
    private static GuiComponent[] components;
    public static HudModuleGroup f;
    public static HudModuleGroup r;

    public String toString() {
        return this.name;
    }

    public HudModuleGroup(String string, String string2) {
        this.name = string;
        this.key = string2;
    }

    public static GuiComponent[] l() {
        return components;
    }

    public String R() {
        return this.key;
    }

    public static void C(GuiComponent[] guiComponentArray) {
        components = guiComponentArray;
    }

    static {
        long l = ZkmLongKeyState.a(-8838303008983543313L, 3094688805978973295L, MethodHandles.lookup().lookupClass()).a(199117041565494L) ^ 0x7F52C098113FL;
        if (HudModuleGroup.l() != null) {
            HudModuleGroup.C(new GuiComponent[1]);
        }
        String[] stringArray = new String[]{"HUD", "All", "Favorite", "Game"};
        r = new HudModuleGroup(stringArray[2]);
        J = new HudModuleGroup(stringArray[1]);
        f = new HudModuleGroup(stringArray[0]);
        T = new HudModuleGroup(stringArray[3]);
        j = new ArrayList<HudModuleGroup>();
        j.add(r);
        j.add(J);
        j.add(f);
        j.add(T);
    }

    public HudModuleGroup(String string) {
        this(string, "");
    }

    public static List<HudModuleGroup> C() {
        return j;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

