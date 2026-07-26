package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;

class HudModuleGroupTabClickHandler
implements GuiClickListener {
    final HudModuleSelectorFrame b;
    final HudModuleGroup Z;

    @Override
    public void P() {
        if (HudModuleSelectorFrame.n(this.b) == this.Z) {
            return;
        }
        HudModuleSelectorFrame.M(this.b, this.Z);
        HudModuleSelectorFrame.WN.N$src$V$wrn2a4();
        this.b.d$src$V$b5ssve();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    HudModuleGroupTabClickHandler(HudModuleSelectorFrame hudModuleSelectorFrame, HudModuleGroup hudModuleGroup) {
        this.b = hudModuleSelectorFrame;
        this.Z = hudModuleGroup;
    }
}

