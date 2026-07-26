package gg.vape.module;

import gg.vape.module.Category;
import gg.vape.module.Mod;

public class UtilityMod
extends Mod {
    public UtilityMod(String string) {
        super(string, 0, 0, Category.Y, "");
    }

    public UtilityMod(String string, String string2) {
        super(string, 0, 0, Category.Y, string2);
    }

    @Override
    public boolean X() {
        return true;
    }

    public UtilityMod(String string, Category category, String string2) {
        super(string, 0, 0, category, string2);
    }
}

