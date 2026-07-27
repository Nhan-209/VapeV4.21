package gg.vape.ui.click.component.module;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;

class ModuleComponentExpandToggleClickHandler
implements GuiClickListener {
    final ModuleCategoryFrame i;
    final ModuleComponent x;


    @Override
    public void G() {
        this.P();
    }

    @Override
    public void P() {
        ModuleComponent.m(this.x, !ModuleComponent.v$src$Z$1nzvssj(this.x));
        if (ModuleComponent.v$src$Z$1nzvssj(this.x)) {
            this.i.G(this.x.N$src$Lgg_vape_module_Mod_$rb0ew8());
            this.x.K$src$V$lt0qn9();
        } else {
            this.i.G(null);
            this.x.l$src$V$mb5y86();
        }
        this.i.l$src$V$1mibm4x();
    }

    ModuleComponentExpandToggleClickHandler(ModuleComponent moduleComponent, ModuleCategoryFrame moduleCategoryFrame) {
        this.x = moduleComponent;
        this.i = moduleCategoryFrame;
    }
}

