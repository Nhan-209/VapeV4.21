package gg.vape.tutorial.selector;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import java.util.ArrayList;

public class ModulesTutorialModuleValuesSelector
extends TutorialTargetSelector<ModuleComponent> {
    ModuleComponent n;
    private static final String b = "AutoClicker";
    final ModulesTutorialPage x;

    public boolean J(ModuleComponent moduleComponent) {
        if (moduleComponent.N$src$Lgg_vape_module_Mod_$rb0ew8().getName().equals(b)) {
            this.n = moduleComponent;
            return true;
        }
        return false;
    }

    @Override
    public boolean X(ModuleComponent moduleComponent) {
        return this.J(moduleComponent);
    }

    public ModulesTutorialModuleValuesSelector(ModulesTutorialPage modulesTutorialPage, Class clazz) {
        super(clazz);
        this.x = modulesTutorialPage;
        this.n = null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.n != null) {
                return new ArrayList<GuiComponent>(this.n.K$src$Ljava_util_List_$1hwj5d6());
            }
            return arrayList;
        }
        return null;
    }
}
