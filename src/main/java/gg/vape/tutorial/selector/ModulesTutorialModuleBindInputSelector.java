package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class ModulesTutorialModuleBindInputSelector
extends TutorialTargetSelector<ModuleComponent> {
    private static final String b = "AutoClicker";
    final ModulesTutorialPage N;
    ModuleComponent E;

    public boolean g(ModuleComponent moduleComponent) {
        if (moduleComponent.getModule().getName().equals(b)) {
            this.E = moduleComponent;
            return true;
        }
        return false;
    }

    @Override
    public boolean X(ModuleComponent moduleComponent) {
        return this.g(moduleComponent);
    }


    public ModulesTutorialModuleBindInputSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.N = modulesTutorialPage;
        this.E = null;
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.E != null && this.E.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.E.getBindInput()));
            }
            return arrayList;
        }
        return null;
    }
}
