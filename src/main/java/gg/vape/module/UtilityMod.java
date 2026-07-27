package gg.vape.module;

import gg.vape.module.Category;
import gg.vape.module.Mod;

public class UtilityMod
extends Mod {
    public UtilityMod(String name) {
        super(name, 0, 0, Category.Y, "");
    }

    public UtilityMod(String name, String description) {
        super(name, 0, 0, Category.Y, description);
    }

    @Override
    public boolean X() {
        return true;
    }

    public UtilityMod(String name, Category category, String description) {
        super(name, 0, 0, category, description);
    }
}

