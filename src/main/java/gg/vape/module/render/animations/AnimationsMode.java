package gg.vape.module.render.animations;

import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.render.Animations;

public abstract class AnimationsMode
extends SubModule<Animations> {
    public boolean i() {
        return false;
    }

    public abstract boolean M();

    public AnimationsMode(Mod mod, String string) {
        super(mod, string);
    }
}

