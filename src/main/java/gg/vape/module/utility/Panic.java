package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.UtilityMod;
import gg.vape.value.BooleanValue;
import java.util.ArrayList;
import java.util.List;

public class Panic
extends UtilityMod {
    private boolean reEnableActive = false;
    private final BooleanValue reEnable = BooleanValue.create(this, "Re-enable", false, "Re-enables all previously enabled modules upon pressing bind a second time");
    private final List<Mod> disabledMods = new ArrayList<Mod>();

    public Panic() {
        super("Panic", "Disables all currently enabled modules");
        this.R(false);
        this.addValue(this.reEnable);
    }


    @Override
    public void U(Mod mod) {
        if (mod != this) {
            this.reEnableActive = false;
            this.disabledMods.clear();
        }
    }

    @Override
    public void onEnable() {
        this.Y(false);
        if (this.reEnable.getEffectiveValue().booleanValue()) {
            this.reEnableActive = !this.reEnableActive;
            if (!this.reEnableActive) {
                for (Mod mod : this.disabledMods) {
                    mod.Y(true);
                }
                this.disabledMods.clear();
                return;
            }
        } else {
            this.reEnableActive = false;
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            if (!mod.r$src$Z$14eylz9() || mod == this) continue;
            mod.Y(false);
            if (!this.reEnable.getEffectiveValue().booleanValue()) continue;
            this.disabledMods.add(mod);
        }
    }
}

