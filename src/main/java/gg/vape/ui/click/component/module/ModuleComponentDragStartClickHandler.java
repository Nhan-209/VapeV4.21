package gg.vape.ui.click.component.module;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.utils.render.RenderUtils;

class ModuleComponentDragStartClickHandler
implements GuiClickListener {
    final ModuleComponent D;
    final ModuleComponent v;

    ModuleComponentDragStartClickHandler(ModuleComponent moduleComponent, ModuleComponent moduleComponent2) {
        this.v = moduleComponent;
        this.D = moduleComponent2;
    }

    @Override
    public void P() {
        MousePosition mousePosition = RenderUtils.h();
        ModuleComponent.g(this.v, mousePosition.H);
        ModuleComponent.f(this.v, this.v.double_n());
        ModuleComponent.t(this.v, true);
        ClientSettings.fT = this.D;
    }
}

