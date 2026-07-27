package gg.vape.module.none;

import gg.vape.module.Category;
import gg.vape.module.Mod;

public class NoOpModuleShellB
extends Mod {
    private static final String NAME = "CPSMod";

    public NoOpModuleShellB() {
        super(NAME, 0, 0, Category.b, null);
    }

    @Override
    public void t() {
    }
}

