package gg.vape.module.none.textgui;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.none.TextGuiSettings;
import gg.vape.module.none.textgui.TextGuiModuleWidthComparatorHelper;
import gg.vape.ui.font.SmoothFontRenderer;
import java.util.Comparator;

public class TextGuiModuleWidthComparator
implements Comparator<Mod> {
    @Override
    public int compare(Mod mod, Mod mod2) {
        return this.z(mod, mod2);
    }

    public int z(Mod mod, Mod mod2) {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().Y();
        return Double.compare(smoothFontRenderer.N(mod2.getName() + mod2.c(TextGuiSettings.U.v())), smoothFontRenderer.N(mod.getName() + mod.c(TextGuiSettings.U.v())));
    }

    public TextGuiModuleWidthComparator(TextGuiModuleWidthComparatorHelper textGuiModuleWidthComparatorHelper) {
        this();
    }

    private TextGuiModuleWidthComparator() {
    }
}
