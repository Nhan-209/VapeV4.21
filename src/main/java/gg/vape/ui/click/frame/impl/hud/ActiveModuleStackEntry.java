package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;

public class ActiveModuleStackEntry {
    public final Mod G;
    public final ModDisplayInfo E;

    public ActiveModuleStackEntry(Mod mod, ModDisplayInfo modDisplayInfo) {
        this.G = mod;
        this.E = modDisplayInfo;
    }
}

