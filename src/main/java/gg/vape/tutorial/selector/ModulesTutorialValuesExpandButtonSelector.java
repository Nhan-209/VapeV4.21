package gg.vape.tutorial.selector;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class ModulesTutorialValuesExpandButtonSelector
extends TutorialTargetSelector<ModuleComponent> {
    ModuleComponent t;
    final ModulesTutorialPage W;
    private static final String b = "AutoClicker";

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.t != null && this.t.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.t.t$src$Lgg_vape_ui_click_component_IconButtonComponent_$1y7qa0x()));
            }
            return arrayList;
        }
        return null;
    }

    public ModulesTutorialValuesExpandButtonSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.W = modulesTutorialPage;
        this.t = null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean V(ModuleComponent moduleComponent) {
        if (moduleComponent.N$src$Lgg_vape_module_Mod_$rb0ew8().getName().equals(b)) {
            this.t = moduleComponent;
            return true;
        }
        return false;
    }

    @Override
    public boolean X(ModuleComponent moduleComponent) {
        return this.V(moduleComponent);
    }
}
