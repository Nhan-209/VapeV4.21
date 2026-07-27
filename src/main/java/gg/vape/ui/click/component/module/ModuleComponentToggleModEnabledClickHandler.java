package gg.vape.ui.click.component.module;

import gg.vape.module.Mod;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;

class ModuleComponentToggleModEnabledClickHandler
implements GuiClickListener {
    final Mod T;
    final ModuleComponent H;


    @Override
    public void P() {
        this.T.K(!this.T.f$src$Z$148d2ux());
    }

    ModuleComponentToggleModEnabledClickHandler(ModuleComponent moduleComponent, Mod mod) {
        this.H = moduleComponent;
        this.T = mod;
    }
}

