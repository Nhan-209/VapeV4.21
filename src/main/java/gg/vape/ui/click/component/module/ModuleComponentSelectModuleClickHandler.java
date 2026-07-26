package gg.vape.ui.click.component.module;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;

class ModuleComponentSelectModuleClickHandler
implements GuiClickListener {
    final Mod g;
    final ModuleComponent K;
    final ModuleCategoryFrame h;

    ModuleComponentSelectModuleClickHandler(ModuleComponent moduleComponent, Mod mod, ModuleCategoryFrame moduleCategoryFrame) {
        this.K = moduleComponent;
        this.g = mod;
        this.h = moduleCategoryFrame;
    }

    @Override
    public void P() {
        Vape.INSTANCE.getModuleProfileMetadataCodec().v(this.g);
        this.h.l$src$Z$193vdc5();
    }
}

