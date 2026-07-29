package gg.vape.ui.click.component.module;

import gg.vape.module.Mod;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;

class ModuleComponentToggleModEnabledClickHandler
implements GuiClickListener {
    final Mod module;
    final ModuleComponent owner;


    @Override
    public void onPrimaryClick() {
        this.module.K(!this.module.f$src$Z$148d2ux());
    }

    ModuleComponentToggleModEnabledClickHandler(ModuleComponent moduleComponent, Mod mod) {
        this.owner = moduleComponent;
        this.module = mod;
    }
}

