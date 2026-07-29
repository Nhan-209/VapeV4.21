package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class ModulesTutorialBindExpandButtonSelector
extends TutorialTargetSelector<ModuleComponent> {
    ModuleComponent e;
    private static final String b = "AutoClicker";
    final ModulesTutorialPage T;

    public boolean K(ModuleComponent moduleComponent) {
        if (moduleComponent.getModule().getName().equals(b)) {
            this.e = moduleComponent;
            return true;
        }
        return false;
    }

    @Override
    public boolean X(ModuleComponent moduleComponent) {
        return this.K(moduleComponent);
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.e != null && this.e.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.e.getSettingsButton()));
            }
            return arrayList;
        }
        return null;
    }

    public ModulesTutorialBindExpandButtonSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.T = modulesTutorialPage;
        this.e = null;
    }

}
