package gg.vape.ui.click.component.module;

import gg.vape.ui.click.component.module.ModuleComponent;
import java.util.Comparator;

class ModuleComponentVerticalComparator
implements Comparator<ModuleComponent> {
    final ModuleComponent L;

    ModuleComponentVerticalComparator(ModuleComponent moduleComponent) {
        this.L = moduleComponent;
    }

    public int e(ModuleComponent moduleComponent, ModuleComponent moduleComponent2) {
        return (int)moduleComponent.n() - (int)moduleComponent2.n();
    }

    @Override
    public int compare(ModuleComponent moduleComponent, ModuleComponent moduleComponent2) {
        return this.e(moduleComponent, moduleComponent2);
    }
}
