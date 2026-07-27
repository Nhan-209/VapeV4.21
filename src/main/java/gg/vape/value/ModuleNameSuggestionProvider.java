package gg.vape.value;

import gg.vape.Vape;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.value.AbstractListValueSuggestionProvider;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.UnmodifiableView;

public class ModuleNameSuggestionProvider
extends AbstractListValueSuggestionProvider {
    private List<String> W;
    private boolean x;
    private static GuiComponent[] D;

    public ModuleNameSuggestionProvider(boolean bl) {
        this.x = bl;
    }

    public static GuiComponent[] R() {
        return D;
    }

    public static void D(GuiComponent[] guiComponentArray) {
        D = guiComponentArray;
    }

    public ModuleNameSuggestionProvider() {
        this(false);
    }

    @Override
    public @UnmodifiableView List<String> getValues() {
        if (this.W == null) {
            this.W = new ArrayList<String>();
            for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
                if (mod.getCategory() == Category.b || this.x && mod.getCategory() == Category.w) continue;
                this.W.add(mod.getName());
            }
        }
        return this.W;
    }


    static {
        if (ModuleNameSuggestionProvider.R() != null) {
            ModuleNameSuggestionProvider.D(new GuiComponent[3]);
        }
    }
}

