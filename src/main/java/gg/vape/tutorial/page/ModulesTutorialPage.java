package gg.vape.tutorial.page;

import gg.vape.module.none.ClientSettings;
import gg.vape.tutorial.MultiComponentHighlightTutorialAction;
import gg.vape.tutorial.TutorialPage;
import gg.vape.tutorial.TutorialTooltipPlacement;
import gg.vape.tutorial.selector.ModulesTutorialBindExpandButtonSelector;
import gg.vape.tutorial.selector.ModulesTutorialCategoryButtonSelector;
import gg.vape.tutorial.selector.ModulesTutorialModuleBindInputSelector;
import gg.vape.tutorial.selector.ModulesTutorialModuleValuesSelector;
import gg.vape.tutorial.selector.ModulesTutorialNamedModuleRowSelector;
import gg.vape.tutorial.selector.ModulesTutorialValuesExpandButtonSelector;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.ModuleCategoryNavigationButtonComponent;

public class ModulesTutorialPage
extends TutorialPage {
    private static String D;

    static {
        ModulesTutorialPage.m(null);
    }

    public static String s() {
        return D;
    }

    public static void m(String string) {
        D = string;
    }

    public ModulesTutorialPage() {
        super("Modules");
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.J, ModuleCategoryNavigationButtonComponent.class, "Vape Module Categories", "Vape primarily consists of modules, split into separate module categories", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.J, new ModulesTutorialCategoryButtonSelector(this, ModuleCategoryNavigationButtonComponent.class), "Open the combat category", "Click the combat category button", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, ModuleComponent.class, "Modules", "Modules in this category are listed here.", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, new ModulesTutorialNamedModuleRowSelector(this, ModuleComponent.class), "Module", "For a module to do anything, you can enable it by left clicking its button.", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, new ModulesTutorialValuesExpandButtonSelector(this, ModuleComponent.class), "Open the Module Settings", "Open the module settings by clicking this button", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, new ModulesTutorialModuleValuesSelector(this, ModuleComponent.class), "Module Settings", "These are the module settings. You can configure various options to change the way the module works.", false).h("Module Settings", "Be aware that your ability to bypass depends primarily on the modules you use, and the settings that you use. We suggest checking the forums for advice on settings to use for each server that you play on.").E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, new ModulesTutorialBindExpandButtonSelector(this, ModuleComponent.class), "Close the settings", "You can close the module settings by clicking the settings button again", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.F, new ModulesTutorialModuleBindInputSelector(this, ModuleComponent.class), "Binding modules", "You can bind a module to a key on your keyboard by clicking this button", false).h("Binding modules", "If you press the bound key while in game, it will toggle the module without having to open the GUI").j(true).E(TutorialTooltipPlacement.RIGHT));
    }
}

