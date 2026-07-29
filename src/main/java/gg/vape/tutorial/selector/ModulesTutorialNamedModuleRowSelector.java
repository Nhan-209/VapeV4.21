package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.component.module.ModuleComponent;

public class ModulesTutorialNamedModuleRowSelector
extends TutorialTargetSelector<ModuleComponent> {
    final ModulesTutorialPage c;
    private static final String b = "AutoClicker";

    public boolean J(ModuleComponent moduleComponent) {
        return moduleComponent.getModule().getName().equals(b);
    }

    @Override
    public boolean X(ModuleComponent moduleComponent) {
        return this.J(moduleComponent);
    }

    public ModulesTutorialNamedModuleRowSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.c = modulesTutorialPage;
    }
}
