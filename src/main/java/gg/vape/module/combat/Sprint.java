package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Scaffold;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;

public class Sprint
extends Mod {
    private final BooleanValue k = BooleanValue.create(this, "Cancel Invis", false, "Does not sprint when you are invisible.\nUseful to prevent sprint particles.");
    private Scaffold t;
    private static final long o = -2755412642150416500L;

    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState(Minecraft.gameSettings().r(), false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void e(EventPreEntityUpdate eventPreEntityUpdate) {
        boolean bl;
        if (this.t == null) {
            this.t = Vape.INSTANCE.getModManager().getMod(Scaffold.class);
        }
        if (!Minecraft.currentScreen().isNull()) {
            return;
        }
        if (SharedModuleControlClaims.l.s()) {
            return;
        }
        if (this.k.L().booleanValue() && Minecraft.thePlayer().i(PotionRegistry.R) && !Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().H()) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().r();
        boolean bl2 = bl = !this.t.o$src$Z$dv6vsx() && !Minecraft.thePlayer().B$src$Z$f90iek() && !Minecraft.thePlayer().r();
        if (!keyBinding.isKeyDown() && bl) {
            KeyBinding.setKeyBindState(keyBinding, true);
        }
    }

    public Sprint() {
        super("Sprint", (int)o, Category.g, "Sets your sprinting to true.");
        this.addValue(this.k);
    }
}

