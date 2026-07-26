package gg.vape.input;

import gg.vape.module.Mod;
import gg.vape.module.control.ModuleControlClaim;

public class MouseButtonInputLock
extends ModuleControlClaim {
    public void Q(Mod mod) {
        this.Q();
    }

    public void S(Mod mod) {
        this.c();
    }

    public boolean I() {
        return this.boolean_v();
    }
}

