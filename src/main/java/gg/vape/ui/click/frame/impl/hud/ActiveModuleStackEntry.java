package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;

public class ActiveModuleStackEntry {
    public final Mod module;
    public final ModDisplayInfo displayInfo;

    public ActiveModuleStackEntry(Mod mod, ModDisplayInfo modDisplayInfo) {
        this.module = mod;
        this.displayInfo = modDisplayInfo;
    }
}
