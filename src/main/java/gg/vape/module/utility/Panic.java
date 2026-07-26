package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.UtilityMod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import java.util.ArrayList;
import java.util.List;

public class Panic
extends UtilityMod {
    private boolean F = false;
    private final BooleanValue o = BooleanValue.create(this, "Re-enable", false, "Re-enables all previously enabled modules upon pressing bind a second time");
    private final List<Mod> a = new ArrayList<Mod>();

    public Panic() {
        super("Panic", "Disables all currently enabled modules");
        this.R(false);
        this.addValue(this.o);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void U(Mod mod) {
        if (mod != this) {
            this.F = false;
            this.a.clear();
        }
    }

    @Override
    public void onEnable() {
        this.Y(false);
        if (this.o.L().booleanValue()) {
            boolean bl = this.F = !this.F;
            if (!this.F) {
                for (Mod mod : this.a) {
                    mod.Y(true);
                }
                this.a.clear();
                return;
            }
        } else {
            this.F = false;
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            if (!mod.r$src$Z$14eylz9() || mod == this) continue;
            mod.Y(false);
            if (!this.o.L().booleanValue()) continue;
            this.a.add(mod);
        }
    }
}

