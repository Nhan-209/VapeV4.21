package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.frame.ModuleCategoryNavigationButtonComponent;

public class ModulesTutorialCategoryButtonSelector
extends TutorialTargetSelector<ModuleCategoryNavigationButtonComponent> {
    private static final String b = "Combat";
    final ModulesTutorialPage d;

    public boolean W(ModuleCategoryNavigationButtonComponent moduleCategoryNavigationButtonComponent) {
        return moduleCategoryNavigationButtonComponent.N$src$Ljava_lang_String_$wy122q().equals(b);
    }

    @Override
    public boolean X(ModuleCategoryNavigationButtonComponent moduleCategoryNavigationButtonComponent) {
        return this.W(moduleCategoryNavigationButtonComponent);
    }

    public ModulesTutorialCategoryButtonSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.d = modulesTutorialPage;
    }
}
